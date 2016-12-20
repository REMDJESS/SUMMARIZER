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
     * Instancie un string builder et une r�gle
     */
    public JsExport(){
        builder = new StringBuilder("");
        rule = new Regle();
    }
    
    /**
     * Copie le resultat du formattage en Js dans le fichier sp�cifi�
     * 
     * @param listeFeatures Donn�es � formater puis � sauvegarder
     * @param resumeFile Fichier cible pour la sauvegarde des donn�es
     * @throws IOException 
     */
    public void export(HashMap<String, HashMap<String, List<Object>>> listeFeatures, File resumeFile) throws IOException{
        String dataFormated = toJs(listeFeatures);
        saveTo(dataFormated, resumeFile);
        System.out.println("Resum� g�n�r� !");
        System.out.println("Vous pourrez le visualiser en ouvrant le fichier � l'emplacement suivant:");
        System.out.println("src/main/java/IHM/public_html/index.html");
    }
    
    /**
     * Format la HashMap fournit en Js
     * 
     * @param listeFeatures HashMap contenant une liste de features
     * @return Chaine de caract�re au format Js
     */
    private String toJs(HashMap<String, HashMap<String, List<Object>>> listeFeatures){
    	//D�claration de la variable JavaScript
    	builder.append("var summarizerData = ");
    	
    	//D�finition des donn�es
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
     * Applique les r�gles n�cessaire � l'�dition du resum� selon le sous domaine en pr�sence
     * et formate le resultat en Js
     * 
     * @param listes Ensemble des listes des diff�rents type composant le feature
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
     * Recup�re le nombre total de cellule du feature
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
     * Sauvegarde les donn�es au format string dans un fichier sp�cifi�
     * 
     * @param data Chaines de caract�re � sauvegarder
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
