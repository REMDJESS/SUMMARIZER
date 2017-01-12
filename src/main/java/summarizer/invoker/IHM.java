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

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;

import summarizer.Operateur;
import summarizer.command.Command;
import summarizer.receiver.TypeFeatureVisitor;

public class IHM {
	
	private Scanner sc;
	private File pcmFile;
	
	private Map<String, Command> commandsList;
	private List<Integer> userListFeatures;
	private Map<Feature, String> userProductsChoice;
	private Operateur operateur;
	
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
        do{
    		System.out.println("Please enter the path to your file (ex: pcms/example.pcm) : ");
            String fichier = sc.nextLine();
            pcmFile = new File(fichier);
            if(!pcmFile.exists()){
            	System.out.println("file not found.");
            }
        }
        while(!pcmFile.exists());
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
	 * 
	 * @return
	 */
	public Operateur getOperateur(){
		return operateur;
	}
	
	/**
	 * Demande à l'utilisateur s'il veut choisir des features particuliers
	 * si oui, il lui recupère la liste des features de l'utilisateur
	 * 
	 * @param pcm PCM dans lequel se ferra le choix
	 */
	public void chooseFeatures(PCM pcm){
        String userChoice;
        do{
            System.out.println("Do you have any particular feature choices ? (Y/N) ");
            userChoice = sc.nextLine();
        }
        while(!userChoice.equals("Y") && !userChoice.equals("y") && !userChoice.equals("N") && !userChoice.equals("n"));
        
        if(userChoice.equals("Y") || userChoice.equals("y")){
            //Afficher la liste des features
            System.out.println("List of features");
            HashMap<Integer, Feature> listeFeatures = new HashMap<>();
            int featCpt = 0;
            for (Feature feature : pcm.getConcreteFeatures()) {
            	featCpt++;
            	listeFeatures.put(featCpt, feature);
            	System.out.println(featCpt + ") "+ feature.getName());
            }
            
            //Recupérer le choix de l'utilisateur
            System.out.println("Please enter the number of the selected feature and validate to choose another one.");
            System.out.println("Finish your choices by entering a letter.");
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
            			System.out.println("Please choose a number from the list.");
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
            System.out.println("Do you want a particular feature value ? (Y/N) ");
            userChoice = sc.nextLine();
        }
        while(!userChoice.equals("Y") && !userChoice.equals("y") && !userChoice.equals("N") && !userChoice.equals("n"));

        if(userChoice.equals("Y") || userChoice.equals("y")){
            //Afficher la liste des features
            System.out.println("List of features");
            HashMap<Integer, Feature> listeFeatures = new HashMap<>();
            int featCpt = 0;
            for (Feature feature : pcm.getConcreteFeatures()) {
            	featCpt++;
            	listeFeatures.put(featCpt, feature);
            	System.out.println(featCpt + ") "+ feature.getName());
            }
            
            //Recupérer le choix du feature
            System.out.println("Please enter the number of the selected feature and validate.");
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
            			System.out.println("Please choose a number from the list.");
            		}            		
            	}
            	else{
            		System.out.println("Please choose a number.");
            	}
            }
            while(!test);
            
            //Affiche les valeurs du feature
            System.out.println("Values of the feature:");
            HashMap<Integer, String> listCellValue = new HashMap<>();
            HashMap<Integer, Cell> listCell = new HashMap<>();
            int cptCellValue = 0;
            for(Cell cell: feature.getCells()){
            	if(!listCellValue.values().contains(cell.getContent())){
                	cptCellValue++;
            		listCellValue.put(cptCellValue, cell.getContent()); //Pour la vérification
            		listCell.put(cptCellValue, cell);
                	System.out.println(cptCellValue + ")" + cell.getContent());
            	}            	
            }
            
            //Recupérer la valeur choisie du feauture
            Boolean testValuePresence = false;
            String featValue;
            do{
                System.out.println("Please choose a number of value from the list.");
                featValue = sc.nextLine();
                
            	Matcher matcher = pattern.matcher(userChoice);
            	test = matcher.find();
            	if(test){
                	testValuePresence = listCellValue.containsKey(Integer.parseInt(featValue));
                	if(testValuePresence){
                        this.userProductsChoice.put(listCell.get(Integer.parseInt(featValue)).getFeature(), listCellValue.get(Integer.parseInt(featValue)));
                    }
                    else{
                    	System.out.println("Choose a number from the list.");
                    }       
            	}
                else{
                	System.out.println("Please choose a number.");
                }       
            }
            while(!testValuePresence);
            
            //Gestion de l'opérateur de comparaison
            System.out.println("List of operator:");
            int cptOperateur = 0;
            HashMap<Integer, Operateur> listOp = new HashMap<>();
            for(Operateur op: Operateur.values()){
            	cptOperateur++;
            	listOp.put(cptOperateur, op);
            	System.out.println(cptOperateur + ")" +op.toString());
            }
            
            Boolean testChoixOperateur = false;
            do{
                System.out.println("Please choose a number of operator from the list.");
                userChoice = sc.nextLine();
                
            	Matcher matcher = pattern.matcher(userChoice);
            	test = matcher.find();
            	if(test){
            		testChoixOperateur = listOp.keySet().contains(Integer.parseInt(userChoice));
            		if(testChoixOperateur){
                		operateur = listOp.get(Integer.parseInt(userChoice));
            		}
                    else{
                    	System.out.println("Choose a number from the list.");
                    }
            	}
                else{
                	System.out.println("Please choose a number.");
                }       
            }
            while(!testChoixOperateur);            
            
            invoke("trierProduit");
        }
		
	}

	/**
	 * Exécute la commande de choix aleatoire des feature
	 */
	public void randomFeatureChoose(){
		if(this.userListFeatures.isEmpty() && this.userProductsChoice.isEmpty()){
			invoke("randomFeatureChoose");
		}
	}
	
	/**
	 * Exécute la commande d'édition du resumé au format json 
	 * puis notifie à l'utilisateur la fin de l'opération
	 */
	public void jsonResume(){
		invoke("jsonResume");
        System.out.println("\nSummary generated !");
        System.out.println("You can view it by opening the file in the following location:");
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
			throw new IllegalArgumentException("No command specified.");
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
			throw new IllegalArgumentException(cmdName + " is not a valid command.");
		}
	}
		
}
