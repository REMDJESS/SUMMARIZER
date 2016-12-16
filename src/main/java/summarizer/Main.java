/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.opencompare.api.java.AbstractFeature;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;
import org.opencompare.model.impl.PCMImpl;

/**
 *
 * @author NOYAF-PC
 */
public class Main {

    public static void main(String[] args) throws IOException {        
        
        //Recupération du PCM fourni par l'utilisateur
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le chemin d'acces a votre .pcm (ex: pcms/example.pcm) : ");
        String fichier = sc.nextLine();
        File pcmFile = new File(fichier);
        
        // Définition du chemin d'accès au fichier pcm à manipuler
        //File pcmFile = new File("pcms/example.pcm");
        //File pcmFile = new File("pcms/model/Comparison_of_Macintosh_models_1.pcm");
        
        PCMLoader loader = new KMFJSONLoader();
        List<PCMContainer> pcmContainers = loader.load(pcmFile);        
        PCM pcm = pcmContainers.get(0).getPcm(); //Récupère le PCM
        
        //Recupération de la saisie de l'utilisateur concernant le choix de features particuliers
        String userChoice;
        do{
            System.out.println("Avez-vous des choix de features particuliers ? (O/N) ");
            //Recupérer le choix de l'utilisateur
            userChoice = sc.nextLine();
        }
        while(!userChoice.equals("O") && !userChoice.equals("o") && !userChoice.equals("N") && !userChoice.equals("n"));
        
        /* 
         * Si le choix est "O",
         * recupérer le choix de l'utilisateur et
         * supprimer les features non choisis du PCM        
         */
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
            System.out.println("Terminer en saisissant 'F'. ");
            List<Integer> listeDesChoix = new ArrayList<>();
            do{
            	userChoice = sc.nextLine();
            	if(!userChoice.equals("F") && !userChoice.equals("f")){
            		listeDesChoix.add(Integer.parseInt(userChoice));
            	}
            }
            while(!userChoice.equals("F") && !userChoice.equals("f"));
            
            if(listeDesChoix.size() > 0){
                //Supprimer les features choisis par l'utilisateur de la liste
                for(int i: listeDesChoix){
                	listeFeatures.remove(i);
                }
                
                //Supprimer les features restant du pcm
                for(int key: listeFeatures.keySet()){
                	pcm.removeFeature((AbstractFeature) listeFeatures.get(key));
                }
                
                //Afficher les features selectionnés par l'utilisateur
                System.out.println("Les features retenus sont: ");
                for (Feature feature : pcm.getConcreteFeatures()) {
                	System.out.println(feature.getName());
                }
            }
        }
        
        /*
         * Filtre puis export du PCM
         */
        
        //Filtre
        FiltreVisitor filter = new FiltreVisitor();
        HashMap<String, HashMap<String, List<Object>>> dataFiltered = filter.filtre(pcm);
        
        // Export
        File resumeFile = new File("src/main/java/IHM/public_html/json/summarizer.js"); //fichier cible
        JsExport exporter = new JsExport();
        exporter.export(dataFiltered, resumeFile);
        
    }        
}
