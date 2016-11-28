/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

//import org.opencompare.MyPCMPrinter;

import org.opencompare.api.java.*;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


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
        
        //Filtre
        FiltreVisitor filter = new FiltreVisitor();
        HashMap<String, HashMap<String, List>> dataFiltered = filter.filtre(pcm);
        
        // Export
        File resumeFile = new File("src/main/java/IHM/public_html/json/summarizer.json"); //fichier cible
        JsonExport exporter = new JsonExport();
        exporter.export(dataFiltered, resumeFile);
    }        
}
