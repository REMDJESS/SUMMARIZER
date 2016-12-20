/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author NOYAF-PC
 */
public class JsExport {
    
    StringBuilder builder;
    Regle rule;
    
    /**
     * Constructeur
     * Instancie un string builder et une règle
     */
    public JsExport(){
        builder = new StringBuilder("");
        rule = new Regle();
    }
    
    /**
     * Copie le resultat du formattage en Js dans le fichier spécifié
     * 
     * @param listeFeatures Données à formater puis à sauvegarder
     * @param resumeFile Fichier cible pour la sauvegarde des données
     * @throws IOException 
     */
    public void export(HashMap<String, HashMap<String, List<Object>>> listeFeatures, File resumeFile) throws IOException{
        String dataFormated = toJs(listeFeatures);
        saveTo(dataFormated, resumeFile);
        System.out.println("Resumé généré !");
        System.out.println("Vous pourrez le visualiser en ouvrant le fichier à l'emplacement suivant:");
        System.out.println("src/main/java/IHM/public_html/index.html");
    }
    
    /**
     * Format la HashMap fournit en Js
     * 
     * @param listeFeatures HashMap contenant une liste de features
     * @return Chaine de caractère au format Js
     */
    private String toJs(HashMap<String, HashMap<String, List<Object>>> listeFeatures){
    	//Déclaration de la variable JavaScript
    	builder.append("var summarizerData = ");
    	
    	//Définition des données
        builder.append("{ \"features\": [ ");
        for(String key: listeFeatures.keySet()){
            String nomFeature = key;
            HashMap<String, List<Object>> listeFeatureCells = listeFeatures.get(key);
            int TotalFeatureCells = getTotalFeatureCells(listeFeatureCells);
            
            //startFeature
            builder.append("{\"nom\": \"").append(nomFeature).append("\",");
            builder.append("\"types\": [ ");
            
            listeFeatureCellsToJs(listeFeatureCells, TotalFeatureCells);
            
            builder.deleteCharAt(builder.lastIndexOf(","));
            builder.append("]"); //EndTypes            
            builder.append("},"); //EndFeature
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("]}"); //EndFeatures
        return builder.toString();
    }
    
    /**
     * Applique les règles nécessaire à l'édition du resumé selon le sous domaine en présence
     * et formate le resultat en Js
     * 
     * @param listes Ensemble des listes des différents type composant le feature
     * @param TotalFeatureCells Nombre total de cellule du feature
     */
    private void listeFeatureCellsToJs(HashMap<String, List<Object>> listes, int TotalFeatureCells){
        for(String key: listes.keySet()){
            String nomSousDomaine = key;
            List<Object> sousDomaine = listes.get(key);
            
            switch(nomSousDomaine){
                case "booleans": 
                    builder.append(rule.booleanRule(sousDomaine, nomSousDomaine, TotalFeatureCells));
                break;
                case "notApplicables": 
                    builder.append(rule.notApplicableRule(sousDomaine, nomSousDomaine, TotalFeatureCells));
                break;
                case "numbers": 
                    builder.append(rule.numberRule(sousDomaine, nomSousDomaine, TotalFeatureCells));
                break;
                default: 
                    builder.append(rule.StringRule(sousDomaine, nomSousDomaine, TotalFeatureCells));
                break;
            }
        }
    }
    
    /**
     * Recupère le nombre total de cellule du feature
     * 
     * @param valuesCellsFeature
     * @return 
     */
    private int getTotalFeatureCells(HashMap<String, List<Object>> valuesCellsFeature){
        int totalSize = 0;
        for(String key: valuesCellsFeature.keySet()){
            totalSize +=  valuesCellsFeature.get(key).size();
        }
        return totalSize;
    }
    
    /**
     * Sauvegarde les données au format string dans un fichier spécifié
     * 
     * @param data Chaines de caractère à sauvegarder
     * @param outputFile Fichier de sauvegarde
     * @throws IOException 
     */
    private void saveTo(String data, File outputFile) throws IOException{
        FileWriter fw;
        fw = new FileWriter(outputFile);
        fw.write(data);
        fw.close();
    }
    
}
