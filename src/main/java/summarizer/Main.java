/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

//import org.opencompare.MyPCMPrinter;

import java.io.BufferedOutputStream;
import org.opencompare.api.java.*;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


/**
 *
 * @author NOYAF-PC
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Définition du chemin d'accès au fichier pcm à manipuler
        File pcmFile = new File("pcms/example.pcm");
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
        
        // Write JSON content to file
        /*Path outputFile = Files.createTempFile("oc-", ".json");
        Files.write(outputFile, jsonString.getBytes());
        System.out.println("PCM exported to " + outputFile); */    
    }    
    
    public static void sauvegarder(String data, File cible) throws IOException{
        ObjectOutputStream oos;
        try{
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                      new FileOutputStream(cible)));

            //Ecriture des données dans le fichier cible
            oos.writeObject(data);
            //Fermeture du flux !
            oos.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
