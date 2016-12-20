package summarizer.invoker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;

import summarizer.command.Command;

public class IHM {
	
	private Scanner sc;
	private File pcmFile;
	
	private Map<String, Command> commandsList;
	private List<Integer> userListFeatures;
	private Map<Feature, String> userProductsChoice;
	
	/**
	 * Constructeur
	 */
	public IHM(){
		this.sc = new Scanner(System.in);
		this.commandsList = new HashMap<>();
		this.userListFeatures = new ArrayList<>();
		this.userProductsChoice = new HashMap<>();
	}
	
	/**
	 * 
	 * @return Fichier du PCM
	 */
	public File getPCMFile(){
		return this.pcmFile;
	}
	
	/**
	 * Demande à l'utilisateur de renseigner le chemin du fichier PCM
	 * puis exécute la commande définissant le PCM
	 * 
	 * @throws IOException
	 */
	public void setPCMFile() throws IOException{
        System.out.println("Veuillez saisir le chemin d'acces a votre fichier (ex: pcms/example.pcm) : ");
        String fichier = sc.nextLine();
        pcmFile = new File(fichier);

        invoke("definePCM");
	}
	
	/**
	 * 
	 * @return Liste des feature choisis par l'utilisateur
	 */
	public List<Integer> getUserListFeature(){
		return this.userListFeatures;
	}

	/**
	 * 
	 * @return 
	 */
	public Map<Feature, String> getUserProductsChoice(){
		return this.userProductsChoice;
	}
	
	/**
	 * Demande à l'utilisateur s'il veut choisir des features particulier
	 * si oui, il lui recupère la liste des feature de l'utilisateur
	 * 
	 * @param pcm PCM dans lequel se ferra le choix
	 */
	public void chooseFeatures(PCM pcm){
        String userChoice;
        do{
            System.out.println("Avez-vous des choix de features particuliers ? (O/N) ");
            userChoice = sc.nextLine();
        }
        while(!userChoice.equals("O") && !userChoice.equals("o") && !userChoice.equals("N") && !userChoice.equals("n"));
        
        if(userChoice.equals("O") || userChoice.equals("o")){
            //Afficher la liste des features
            System.out.println("Liste des features");
            HashMap<Integer, Feature> listeFeatures = new HashMap<>();
            int featCpt = 0;
            for (Feature feature : pcm.getConcreteFeatures()) {
            	featCpt++;
            	listeFeatures.put(featCpt, feature);
            	System.out.println(featCpt + ") "+ feature.getName());
            }
            
            //Recupérer le choix de l'utilisateur
            System.out.println("Veuillez saisir le numero du feature choisie puis validez pour en choisir un autre.");
            System.out.println("Terminer en saisissant une lettre.");
            List<Integer> listeDesChoix = new ArrayList<>();
            Pattern pattern = Pattern.compile("[0-9]");
            Boolean test = null;
            do{
            	userChoice = sc.nextLine();
            	Matcher matcher = pattern.matcher(userChoice);
            	test = matcher.find();
            	if(test){
            		int userChoiceInt = Integer.parseInt(userChoice);
            		if(userChoiceInt <= featCpt){
	            		listeDesChoix.add(userChoiceInt);
            		}
            		else{
            			System.out.println("Veuillez choisir un nombre de la liste !");
            		}
            	}
            }
            while(test);
            
            this.userListFeatures = listeDesChoix;
            invoke("trierFeature");
        }
	}
	
	/**
	 * 
	 * @param pcm
	 */
	public void chooseProducts(PCM pcm){
        String userChoice;
        do{
            System.out.println("Voulez vous une valeur de features particulières ? (O/N) ");
            userChoice = sc.nextLine();
        }
        while(!userChoice.equals("O") && !userChoice.equals("o") && !userChoice.equals("N") && !userChoice.equals("n"));

        if(userChoice.equals("O") || userChoice.equals("o")){
            //Afficher la liste des features
            System.out.println("Liste des features");
            HashMap<Integer, Feature> listeFeatures = new HashMap<>();
            int featCpt = 0;
            for (Feature feature : pcm.getConcreteFeatures()) {
            	featCpt++;
            	listeFeatures.put(featCpt, feature);
            	System.out.println(featCpt + ") "+ feature.getName());
            }
            
            //Recupérer le choix du feature
            System.out.println("Veuillez saisir le numero du feature choisie puis validez.");
            Feature feature = null;
            Pattern pattern = Pattern.compile("[0-9]");
            Boolean test = null;
            do{
            	userChoice = sc.nextLine();
            	Matcher matcher = pattern.matcher(userChoice);
            	test = matcher.find();
            	if(test){
            		int userChoiceInt = Integer.parseInt(userChoice);
            		test = (userChoiceInt <= featCpt);
                	if(test){
                		feature = listeFeatures.get(userChoiceInt);
                	}
            		else{
            			System.out.println("Veuillez choisir un nombre de la liste !");
            		}            		
            	}
            	else{
            		System.out.println("Veuillez saisir un nombre !");
            	}
            }
            while(!test);   
            
            //Recupérer la valeur choisie du feauture
            System.out.println("Veuillez saisir votre valeur puis validez.");
            String featValue = sc.nextLine();
            
            this.userProductsChoice.put(feature, featValue);
            invoke("trierProduit");
        }        
		
	}
	
	/**
	 * Exécute la commande d'édition du resumé au format json 
	 * puis notifie à l'utilisateur la fin de l'opération
	 */
	public void jsonResume(){
		invoke("jsonResume");
        System.out.println("Resumé généré !");
        System.out.println("Vous pourrez le visualiser en ouvrant le fichier à l'emplacement suivant:");
        System.out.println("src/main/java/IHM/public_html/index.html");		
	}
	
	/**
	 * Ajoute une commande à la liste des commande de l'IHM
	 * 
	 * @param keyword Nom de la commande à rajoutter
	 * @param cmd Commande à rajouter
	 */
	public void addCommand(String keyword, Command cmd) {
		if ((keyword == null) || (cmd == null)) {
			throw new IllegalArgumentException("Aucune commande spécifié.");
		}
		commandsList.put(keyword, cmd);
	}

	/**
	 * Execute une commande de la liste
	 * 
	 * @param cmdName Nom de la commande à exécuter
	 */
	public void invoke(String cmdName) {
		if (commandsList.containsKey(cmdName)){
			commandsList.get(cmdName).execute();
		}
		else{
			throw new IllegalArgumentException(cmdName + " n'est pas une commande valide");
		}
	}
	
}
