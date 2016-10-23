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
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.opencompare.MyPCMPrinter;
import org.opencompare.api.java.value.StringValue;


/**
 *
 * @author NOYAF-PC
 */
public class Main {

    public static void main(String[] args) {
        // Define a file representing a PCM to load
        File pcmFile = new File("pcms/example.pcm");
        //File pcmFile = new File("pcms/model/Comparison_of_Macintosh_models_1.pcm");
        
        try{
            testMotif(pcmFile);
            
            //JsonExport monTest = new JsonExport();
            //monTest.export(pcmFile);
            
            //executePCM(pcmFile);
            
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
    
    public static void testMotif(File pcmFile) throws IOException {
        PCMLoader loader = new KMFJSONLoader();
        List<PCMContainer> pcmContainers = loader.load(pcmFile);        
        PCM pcm = pcmContainers.get(0).getPcm(); //Récupère le PCM
        
        Motif motif = new Motif(); //Instanciation d'un objet contenant l'ensemble des motifs
        
        for(Feature feature: pcm.getConcreteFeatures()){
            List<Cell> cells = feature.getCells(); //Récupère l'ensemble des cellules de ce feature
            Filtre filtre = new Filtre(cells);
                        
            /*Définition des sous ensembles par type */
            Collection filtreString = filtre.getStringValues();
            Collection filtreNumber = filtre.getNumberValues();
            Collection filtreBoolean = filtre.getBooleanValues();
            Collection filtreConditional = filtre.getConditionalValues();
            Collection filtreDate = filtre.getDateValues();
            Collection filtreDimension = filtre.getDimensionValues();
            Collection filtreMultiple = filtre.getMultipleValues();
            Collection filtreNotApplicable = filtre.getNotApplicableValues();
            Collection filtrePartial = filtre.getPartialValues();
            Collection filtreUnit = filtre.getUnitValues();
            Collection filtreVersion = filtre.getVersionValues();
            Collection filtreNotAvailable = filtre.getNotAvailableValues();
            
            //Recupération de la taille des ensembles
            float tailleTotal = cells.size();
            float tailleFiltreString = filtreString.size();
            float tailleFiltreNumber = filtreNumber.size();
            float tailleFiltreBoolean = filtreBoolean.size();
            float tailleFiltreConditional = filtreConditional.size();
            float tailleFiltreDate = filtreDate.size();
            float tailleFiltreDimension = filtreDimension.size();
            float tailleFiltreMultiple = filtreMultiple.size();
            float tailleFiltreNotApplicable = filtreNotApplicable.size();
            float tailleFiltrePartial = filtrePartial.size();
            float tailleFiltreUnit = filtreUnit.size();
            float tailleFiltreVersion = filtreVersion.size();
            float tailleFiltreNotAvailable = filtreNotAvailable.size();
            
            /*Pourcentage générale*/
            float pourcString = (tailleFiltreString / tailleTotal) * 100;
            float pourcNumber = (tailleFiltreNumber / tailleTotal) * 100;
            float pourcBoolean = (tailleFiltreBoolean / tailleTotal) * 100;
            float pourcConditional = (tailleFiltreConditional / tailleTotal) * 100;
            float pourcDate = (tailleFiltreDate / tailleTotal) * 100;
            float pourcDimension = (tailleFiltreDimension / tailleTotal) * 100;
            float pourcMultiple = (tailleFiltreMultiple / tailleTotal) * 100;
            float pourcNotApplicable = (tailleFiltreNotApplicable / tailleTotal) * 100;
            float pourcPartial = (tailleFiltrePartial / tailleTotal) * 100;
            float pourcUnit = (tailleFiltreUnit / tailleTotal) * 100;
            float pourcVersion = (tailleFiltreVersion / tailleTotal) * 100;
            float pourcNotAvailable = (tailleFiltreNotAvailable / tailleTotal) * 100;
            
            float somme = tailleFiltreString + tailleFiltreNumber + tailleFiltreBoolean + tailleFiltreConditional + tailleFiltreDate + tailleFiltreDimension + tailleFiltreMultiple + tailleFiltreNotApplicable + tailleFiltrePartial + tailleFiltreUnit + tailleFiltreVersion + tailleFiltreNotAvailable;
            
            System.out.println("============="+ feature.getName().toUpperCase() +"================");
            /*
            System.out.println("pourcString: "+ pourcString);
            System.out.println("pourcNumber: "+ pourcNumber);
            System.out.println("pourcBoolean: "+ pourcBoolean);
            System.out.println("pourcConditional: "+ pourcConditional);
            System.out.println("pourcDate: "+ pourcDate);
            System.out.println("pourcDimension: "+ pourcDimension);
            System.out.println("pourcMultiple: "+ pourcMultiple);
            System.out.println("pourcNotApplicable: "+ pourcNotApplicable);
            System.out.println("pourcUnit: "+ pourcPartial);
            System.out.println("pourcUnit: "+ pourcUnit);
            System.out.println("pourcVersion: "+ pourcVersion);
            System.out.println("pourcNotAvailable: "+ pourcNotAvailable);*/
            
            //Opérations sur les chaînes de caractères
            if( filtreString.size() > 0 ){
                Collection values = filtreString;
                System.out.println("----------------------\n Contient " + pourcString +"% de String dont: \n -----------------");
                //Calcul du pourcentage
                Map<String, Float> pourcentages = motif.pourcentage(values);
                for(String key: pourcentages.keySet()){
                    System.out.println(pourcentages.get(key) + "% de " + key.toUpperCase());
                }
                
                float max = motif.max(pourcentages.values());
                float min = motif.min(pourcentages.values());
                if(max != min){
                    System.out.println("Les valeurs les plus représentées sont (à "+ max +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == max)
                            System.out.println("-"+ key.toUpperCase());
                    }

                    System.out.println("Les valeurs les moins représentées sont (à "+ min +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == min)
                            System.out.println("-"+ key.toUpperCase());
                    }
                }
            }
            
            //Opérations sur les nombres
            if( filtreNumber.size() > 0 ){
                Collection values = filtreNumber;
                System.out.println("----------------------\n Contient " + pourcNumber +"% de Nombre dont: \n -----------------");
                //Calcul de la moyenne
                float moyenne = motif.moyenne(values);
                System.out.println("La moyenne des valeur est " + moyenne);

                //Calcul des extremum
                float max = motif.max(filtreNumber);
                float min = motif.min(filtreNumber);
                float ecartType = motif.ecartType(values);
                System.out.println("La plus grande valeur est " + max);
                System.out.println("La plus petite valeur est " + min);
                System.out.println("L'écart type des valeurs est " + ecartType);
            }
            
            //Opérations sur les boolean
            if( filtreBoolean.size() > 0 ){
                Collection values = filtreBoolean;
                System.out.println("----------------------\n Contient " + pourcBoolean +"% de Boolean dont: \n -----------------");
                //Calcul du pourcentage
                Map<String, Float> pourcentages = motif.pourcentage(values);
                for(String key: pourcentages.keySet()){
                    System.out.println(pourcentages.get(key) + "% de " + key.toUpperCase());
                }
            }
            
            //Opérations sur les conditionals
            if( filtreConditional.size() > 0 ){
                Collection values = filtreConditional;
                System.out.println("----------------------\n Contient " + pourcConditional +"% de Conditional dont: \n -----------------");
                //Calcul du pourcentage
                Map<String, Float> pourcentages = motif.pourcentage(values);
                for(String key: pourcentages.keySet()){
                    System.out.println(pourcentages.get(key) + "% de " + key.toUpperCase());
                }
                
                float max = motif.max(pourcentages.values());
                float min = motif.min(pourcentages.values());
                if(max != min){
                    System.out.println("Les valeurs les plus représentées sont (à "+ max +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == max)
                            System.out.println("-"+ key.toUpperCase());
                    }

                    System.out.println("Les valeurs les moins représentées sont (à "+ min +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == min)
                            System.out.println("-"+ key.toUpperCase());
                    }
                }
            }
            
            //Opérations sur les dates
            if( filtreDate.size() > 0 ){
                Collection values = filtreDate;
                System.out.println("----------------------\n Contient " + pourcDate +"% de Date dont: \n -----------------");
                //Calcul du pourcentage
                Map<String, Float> pourcentages = motif.pourcentage(values);
                for(String key: pourcentages.keySet()){
                    System.out.println(pourcentages.get(key) + "% de " + key.toUpperCase());
                }
                
                float max = motif.max(pourcentages.values());
                float min = motif.min(pourcentages.values());
                if(max != min){
                    System.out.println("Les valeurs les plus représentées sont (à "+ max +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == max)
                            System.out.println("-"+ key.toUpperCase());
                    }

                    System.out.println("Les valeurs les moins représentées sont (à "+ min +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == min)
                            System.out.println("-"+ key.toUpperCase());
                    }
                }
            }
            
            //Opérations sur les dimensions
            if( filtreDimension.size() > 0 ){
                Collection values = filtreDimension;
                System.out.println("----------------------\n Contient " + pourcDimension +"% de Dimension dont: \n -----------------");
                //Calcul du pourcentage
                Map<String, Float> pourcentages = motif.pourcentage(values);
                for(String key: pourcentages.keySet()){
                    System.out.println(pourcentages.get(key) + "% de " + key.toUpperCase());
                }
                
                float max = motif.max(pourcentages.values());
                float min = motif.min(pourcentages.values());
                if(max != min){
                    System.out.println("Les valeurs les plus représentées sont (à "+ max +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == max)
                            System.out.println("-"+ key.toUpperCase());
                    }

                    System.out.println("Les valeurs les moins représentées sont (à "+ min +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == min)
                            System.out.println("-"+ key.toUpperCase());
                    }
                }
            }
            
            //Opérations sur les multiples
            if( filtreMultiple.size() > 0 ){
                Collection values = filtreMultiple;
                System.out.println("----------------------\n Contient " + pourcMultiple +"% de Multiple dont: \n -----------------");
                //Calcul du pourcentage
                Map<String, Float> pourcentages = motif.pourcentage(values);
                for(String key: pourcentages.keySet()){
                    System.out.println(pourcentages.get(key) + "% de " + key.toUpperCase());
                }
                
                float max = motif.max(pourcentages.values());
                float min = motif.min(pourcentages.values());
                if(max != min){
                    System.out.println("Les valeurs les plus représentées sont (à "+ max +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == max)
                            System.out.println("-"+ key.toUpperCase());
                    }

                    System.out.println("Les valeurs les moins représentées sont (à "+ min +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == min)
                            System.out.println("-"+ key.toUpperCase());
                    }
                }
            }
            
            //Opérations sur les NotApplicable
            if( filtreNotApplicable.size() > 0 ){
                Collection values = filtreNotApplicable;
                System.out.println("----------------------\n Contient " + pourcNotApplicable +"% de NotApplicable dont: \n -----------------");
                //Calcul du pourcentage
                Map<String, Float> pourcentages = motif.pourcentage(values);
                for(String key: pourcentages.keySet()){
                    System.out.println(pourcentages.get(key) + "% de " + key.toUpperCase());
                }
                
                float max = motif.max(pourcentages.values());
                float min = motif.min(pourcentages.values());
                if(max != min){
                    System.out.println("Les valeurs les plus représentées sont (à "+ max +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == max)
                            System.out.println("-"+ key.toUpperCase());
                    }

                    System.out.println("Les valeurs les moins représentées sont (à "+ min +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == min)
                            System.out.println("-"+ key.toUpperCase());
                    }
                }
            }

            //Opérations sur les Partial
            if( filtrePartial.size() > 0 ){
                Collection values = filtrePartial;
                System.out.println("----------------------\n Contient " + pourcPartial +"% de Partial dont: \n -----------------");
                //Calcul du pourcentage
                Map<String, Float> pourcentages = motif.pourcentage(values);
                for(String key: pourcentages.keySet()){
                    System.out.println(pourcentages.get(key) + "% de " + key.toUpperCase());
                }
                
                float max = motif.max(pourcentages.values());
                float min = motif.min(pourcentages.values());
                if(max != min){
                    System.out.println("Les valeurs les plus représentées sont (à "+ max +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == max)
                            System.out.println("-"+ key.toUpperCase());
                    }

                    System.out.println("Les valeurs les moins représentées sont (à "+ min +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == min)
                            System.out.println("-"+ key.toUpperCase());
                    }
                }
            }
            
            //Opérations sur les Units
            if( filtreUnit.size() > 0 ){
                Collection values = filtreUnit;
                System.out.println("----------------------\n Contient " + pourcUnit +"% de Unit dont: \n -----------------");
                //Calcul du pourcentage
                Map<String, Float> pourcentages = motif.pourcentage(values);
                for(String key: pourcentages.keySet()){
                    System.out.println(pourcentages.get(key) + "% de " + key.toUpperCase());
                }
                
                float max = motif.max(pourcentages.values());
                float min = motif.min(pourcentages.values());
                if(max != min){
                    System.out.println("Les valeurs les plus représentées sont (à "+ max +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == max)
                            System.out.println("-"+ key.toUpperCase());
                    }

                    System.out.println("Les valeurs les moins représentées sont (à "+ min +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == min)
                            System.out.println("-"+ key.toUpperCase());
                    }
                }
            }
            
            //Opérations sur les Versions
            if( filtreVersion.size() > 0 ){
                Collection values = filtreVersion;
                System.out.println("----------------------\n Contient " + pourcVersion +"% de Version dont: \n -----------------");
                //Calcul du pourcentage
                Map<String, Float> pourcentages = motif.pourcentage(values);
                for(String key: pourcentages.keySet()){
                    System.out.println(pourcentages.get(key) + "% de " + key.toUpperCase());
                }
                
                float max = motif.max(pourcentages.values());
                float min = motif.min(pourcentages.values());
                if(max != min){
                    System.out.println("Les valeurs les plus représentées sont (à "+ max +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == max)
                            System.out.println("-"+ key.toUpperCase());
                    }

                    System.out.println("Les valeurs les moins représentées sont (à "+ min +"%) :");
                    for(String key: pourcentages.keySet()){
                        if(pourcentages.get(key) == min)
                            System.out.println("-"+ key.toUpperCase());
                    }
                }
            }
            
            //Opérations sur les NotAvailables
            if( filtreNotAvailable.size() > 0 ){
                Collection values = filtreNotAvailable;
                System.out.println("----------------------\n N'est pas renseigné à " + pourcNotAvailable +"% \n -----------------");

            }
            
            
            
            /*
            System.out.println("************ tailleFiltreString "+ tailleFiltreString +" *************");
            System.out.println("************ tailleFiltreNumber "+ tailleFiltreNumber +" *************");
            System.out.println("************ tailleFiltreBoolean "+ tailleFiltreBoolean +" *************");
            System.out.println("************ tailleFiltreConditional "+ tailleFiltreConditional +" *************");
            System.out.println("************ tailleFiltreDate "+ tailleFiltreDate +" *************");
            System.out.println("************ tailleFiltreDimension "+ tailleFiltreDimension +" *************");
            System.out.println("************ tailleFiltreMultiple "+ tailleFiltreMultiple +" *************");
            System.out.println("************ tailleFiltreNotApplicable "+ tailleFiltreNotApplicable +" *************");
            System.out.println("************ tailleFiltrePartial "+ tailleFiltrePartial +" *************");
            System.out.println("************ tailleFiltreUnit "+ tailleFiltreUnit +" *************");
            System.out.println("************ tailleFiltreVersion "+ tailleFiltreVersion +" *************");
            System.out.println("************ tailleFiltreNotAvailable "+ tailleFiltreNotAvailable +" *************");
            System.out.println("++++++++++++ tailleTotal "+ tailleTotal +" --- Reste: "+ (tailleTotal - somme) +" +++++++++++++++ \n");
            */
        }
    }
    
}
