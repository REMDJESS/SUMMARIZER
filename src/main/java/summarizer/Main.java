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
        
        //Begin format
        StringBuilder builder = new StringBuilder();
        builder.append("{ \"features\": [ ");
        
        for(Feature feature: pcm.getConcreteFeatures()){
            
            List<Cell> cells = feature.getCells(); //Récupère l'ensemble des cellules de ce feature (domaine)
            Filtre filtre = new FiltreImpl();
            Regle regle = new Regle(cells);
                        
            //Construction du feature
            builder.append("{\"nom\": \"").append(feature.getName()).append("\",");
            builder.append("\"types\": [ ");
            
            //Construnction des types du feature
            builder.append(regle.StringRule(filtre.getStringValues(cells), "String"));
            builder.append(regle.numberRule(filtre.getNumberValues(cells), "Number"));
            builder.append(regle.booleanRule(filtre.getBooleanValues(cells), "Boolean"));
            builder.append(regle.StringRule(filtre.getConditionalValues(cells), "Conditional"));
            builder.append(regle.StringRule(filtre.getDateValues(cells), "Date"));
            builder.append(regle.StringRule(filtre.getDimensionValues(cells), "Dimension"));
            builder.append(regle.StringRule(filtre.getMultipleValues(cells), "Multiple"));
            builder.append(regle.StringRule(filtre.getNotApplicableValues(cells), "NotApplicable"));
            builder.append(regle.StringRule(filtre.getPartialValues(cells), "Partial"));
            builder.append(regle.StringRule(filtre.getUnitValues(cells), "Unit"));
            builder.append(regle.StringRule(filtre.getVersionValues(cells), "Version"));
            builder.append(regle.notApplicableRule(filtre.getNotAvailableValues(cells), "NotAvailable"));            
            
            builder.deleteCharAt(builder.lastIndexOf(","));
            builder.append("]"); //EndTypes            
            builder.append("},"); //EndFeature
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("]}"); //EndFeatures 
        String jsonString =builder.toString();
        
        /**** Export *****/
        File resumeFile = new File("src/main/java/IHM/public_html/json/summarizer.json"); //fichier cible
        sauvegarder(jsonString, resumeFile);
        System.out.println("Resumé généré ! \nVeuillez ouvrir le fichier à l'emplacement suivant:");
        System.out.println("src/main/java/IHM/public_html/index.htmlpcms/example.pcm");
                
    }    
    
    public static void sauvegarder(String data, File outputFile) throws IOException{
        FileWriter fw;
        fw = new FileWriter(outputFile);
        fw.write(data);
        fw.close();
    }
    
}
