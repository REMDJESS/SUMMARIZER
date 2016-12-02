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
        
        //Afficher la liste des features
        System.out.println("Veuillez entrer le numero des features sur lesquels vous voulez votre resumé (ex: 1,2,5) puis validez : ");
        HashMap<Integer, Feature> listeFeatures = new HashMap<>();
        int cpt = 0;
        for (Feature feature : pcm.getConcreteFeatures()) {
        	cpt++;
        	listeFeatures.put(cpt, feature);
        	System.out.println(cpt + ") "+ feature.getName());
        }
        System.out.println("Si vous n'avez pas de choix particulier, validez sans rien saisir ! ");
        
        //Recupérer le choix de l'utilisateur
        String choix = sc.nextLine();
        List<Integer> listeDesChoix = new ArrayList<>();
        for(String s: choix.split(",")){
        	listeDesChoix.add(Integer.parseInt(s));
        }
        
        //Selectionner les features à supprimer en supprimant ceux choisis de la liste
        for(int i: listeDesChoix){
        	listeFeatures.remove(i);
        }
        
        //Supprimer les features du pcm
        for(int key: listeFeatures.keySet()){
        	pcm.removeFeature((AbstractFeature) listeFeatures.get(key));
        }
        
        //Afficher les features selectionnés
        System.out.println("Les features retenus sont: ");
        int cp = 0;
        for (Feature feature : pcm.getConcreteFeatures()) {
        	cp++;
        	System.out.println(cp + ") "+ feature.getName());
        }
        
        //Filtre
        FiltreVisitor filter = new FiltreVisitor();
        HashMap<String, HashMap<String, List<Object>>> dataFiltered = filter.filtre(pcm);
        
        // Export
        File resumeFile = new File("src/main/java/IHM/public_html/json/summarizer.json"); //fichier cible
        JsonExport exporter = new JsonExport();
        exporter.export(dataFiltered, resumeFile);
        
    }        
}
