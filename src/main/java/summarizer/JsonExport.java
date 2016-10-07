/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.opencompare.api.java.*;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

/**
 *
 * @author NOYAF-PC
 */
public class JsonExport {
    
    /**
     * Formater un fichier PCM en une chaine de caractère au format JSON
     * @param pcmFile Fichier PCM à convertir en JSON
     * @return
     * @throws IOException 
     */
    public String toJson(File pcmFile) throws IOException {
        // Create a loader that can handle the file format
        PCMLoader loader = new KMFJSONLoader();
        // Load the file
        // A loader may return multiple PCM containers depending on the input format
        // A PCM container encapsulates a PCM and its associated metadata
        List<PCMContainer> pcmContainers = loader.load(pcmFile);
        //Build a String
        StringBuilder builder = new StringBuilder();

        for (PCMContainer pcmContainer : pcmContainers) {            
            // Get the PCM
            PCM pcm = pcmContainer.getPcm();            
            //Begin format
            //.append("{ \n");
            // Browse the cells of the PCM
            for (Product product : pcm.getProducts()) {
                builder.append("{\"Produit\": \"").append(product.getKeyContent()).append("\", \n \"caracteristiques\" : [ \n");
                for (Feature feature : pcm.getConcreteFeatures()) {
                    // Find the cell corresponding to the current feature and product
                    Cell cell = product.findCell(feature);
                    String featureName = feature.getName().replaceAll("\"", "\'");
                    String featureValue = cell.getContent();
                    builder.append("\t {\"caracteristique\": \"").append(featureName).append("\", \"valeur\": \"").append(featureValue).append("\"}, \n");
                }
                builder.deleteCharAt(builder.lastIndexOf(","));
                builder.append("]}, \n");
            }
            builder.deleteCharAt(builder.lastIndexOf(","));
            //builder.append("}");
        }
        return builder.toString();
    }
    
    /**
     * Exporter un fichier PCM au format JSON
     * @param pcmFile Fichier PCM à convertir en JSON puis exporter
     * @throws IOException 
     */
    public void export(File pcmFile) throws IOException{
        String json = this.toJson(pcmFile);
        //File newFile = new File("/");
        
        // Write JSON content to file
        //Path outputFile = File.createTempFile("oc-", ".json", newFile);
        Path outputFile = Files.createTempFile("oc-", ".json");
        Files.write(outputFile, json.getBytes());
        System.out.println("PCM exported to " + outputFile);     
    }
    
}
