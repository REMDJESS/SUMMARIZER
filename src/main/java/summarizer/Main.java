/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

//import org.opencompare.MyPCMPrinter;

import org.opencompare.api.java.*;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.HTMLExporter;
//import org.opencompare.api.java.io.CSVExporter;
import org.opencompare.api.java.io.PCMLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author NOYAF-PC
 */
public class Main {
    

    public static void main(String[] args) {
        // Define a file representing a PCM to load
        File pcmFile = new File("pcms/example.pcm");

        try{
            //executePCM(pcmFile);
            
            JsonExport monTest = new JsonExport();
            monTest.export(pcmFile);
            
        }
        catch(Exception ex){
            
        }

        
        
    }
    
    public static void executePCM(File pcmFile) throws IOException {

        // Create a loader that can handle the file format
        PCMLoader loader = new KMFJSONLoader();

        // Load the file
        // A loader may return multiple PCM containers depending on the input format
        // A PCM container encapsulates a PCM and its associated metadata
        List<PCMContainer> pcmContainers = loader.load(pcmFile);
        
        ArrayList<String> filtreString;
        ArrayList<Float> filtreInteger;
        ArrayList<String> filtreBool;

        for (PCMContainer pcmContainer : pcmContainers) {
            // Get the PCM
            PCM pcm = pcmContainer.getPcm();

            // Browse the cells of the PCM
            for (Product product : pcm.getProducts()) {
                for (Feature feature : pcm.getConcreteFeatures()) {
                    
                    filtreString = new ArrayList<>();
                    filtreInteger = new ArrayList<>();
                    filtreBool = new ArrayList<>();

                    // Find the cell corresponding to the current feature and product
                    Cell cell = product.findCell(feature);

                    // Get information contained in the cell
                    String content = cell.getContent();
                    String rawContent = cell.getRawContent();
                    Value interpretation = cell.getInterpretation();
                    
                    //Convertion des données dans leurs types respectif
                    if(interpretation.toString().contains("IntegerValue")||interpretation.toString().contains("RealValue")){
                        if(!filtreInteger.contains(Float.parseFloat(content))){
                            filtreInteger.add(Float.parseFloat(content));
                        }
                    }
                    if(interpretation.toString().contains("StringValue")){ 
                        if(!filtreString.contains(content)){
                            filtreString.add(content);
                        }
                    }
                    if(interpretation.toString().contains("BooleanValue")||interpretation.toString().contains("Conditional")){ 
                        if(!filtreBool.contains(content)){
                            filtreBool.add(content);
                        }
                    }
                    
                    // Print the content of the cell
                    System.out.println("(" + product.getKeyContent() + ", " + feature.getName() + ") = " + content);
                }
            }
            
            // Export the PCM container to HTML
            HTMLExporter htmlExporter = new HTMLExporter();
            String html = htmlExporter.export(pcmContainer);

            // Write HTML content to file
            Path outputFile = Files.createTempFile("oc-", ".html");
            Files.write(outputFile, html.getBytes());
            System.out.println("PCM exported to " + outputFile);
            
        }
    } 

}
