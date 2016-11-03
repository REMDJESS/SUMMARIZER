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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 *
 * @author NOYAF-PC
 */
public class Main {

    public static void main(String[] args) {
        // Define a file representing a PCM to load
        //File pcmFile = new File("pcms/example.pcm");
        File pcmFile = new File("pcms/model/Comparison_of_Macintosh_models_1.pcm");
        
        try{
            testMotif(pcmFile);
            
        }
        catch(Exception ex){
            
        }
    }
    
    public static void testMotif(File pcmFile) throws IOException {
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
                        
            /*Définition des sous-domaines */
            Collection filtreString = filtre.getStringValues(cells);
            Collection filtreNumber = filtre.getNumberValues(cells);
            Collection filtreBoolean = filtre.getBooleanValues(cells);
            Collection filtreConditional = filtre.getConditionalValues(cells);
            Collection filtreDate = filtre.getDateValues(cells);
            Collection filtreDimension = filtre.getDimensionValues(cells);
            Collection filtreMultiple = filtre.getMultipleValues(cells);
            Collection filtreNotApplicable = filtre.getNotApplicableValues(cells);
            Collection filtrePartial = filtre.getPartialValues(cells);
            Collection filtreUnit = filtre.getUnitValues(cells);
            Collection filtreVersion = filtre.getVersionValues(cells);
            Collection filtreNotAvailable = filtre.getNotAvailableValues(cells);
            
            //Construction du feature
            builder.append("{\"nom\": \"").append(feature.getName()).append("\",");
            builder.append("\"types\": [ ");
            
            //Construnction des types du feature
            builder.append(regle.StringRule(filtreString));
            builder.append(regle.numberRule(filtreNumber));
            builder.append(regle.booleanRule(filtreBoolean));
            builder.append(regle.StringRule(filtreConditional));
            builder.append(regle.StringRule(filtreDate));
            builder.append(regle.StringRule(filtreDimension));
            builder.append(regle.StringRule(filtreMultiple));
            builder.append(regle.StringRule(filtreNotApplicable));
            builder.append(regle.StringRule(filtrePartial));
            builder.append(regle.StringRule(filtreUnit));
            builder.append(regle.StringRule(filtreVersion));
            builder.append(regle.notApplicableRule(filtreNotAvailable));
            
            builder.deleteCharAt(builder.lastIndexOf(","));
            builder.append("]"); //EndTypes
            
            builder.deleteCharAt(builder.lastIndexOf(","));
            builder.append("},"); //EndFeature
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("]}"); //EndFeatures        
        
        //Configuration de l'exportation
        String jsonString =builder.toString();
        // Write JSON content to file
        //Path outputFile = File.createTempFile("oc-", ".json", newFile);
        Path outputFile = Files.createTempFile("oc-", ".json");
        Files.write(outputFile, jsonString.getBytes());
        System.out.println("PCM exported to " + outputFile);     
        
    }
    
}
