/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer.receiver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.opencompare.api.java.Cell;

/**
 *
 * @author NOYAF-PC
 */
public class JsonExport {
    
    StringBuilder builder;
    Regle rule;
    
    /**
     * Constructeur
     * Instancie un string builder et une r�gle
     */
    public JsonExport(){
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
    public void export(HashMap<String, HashMap<String, List<Cell>>> listeFeatures, File resumeFile, String featureChoisi, String operateurChoisi) throws IOException{
        String dataFormated = toJson(listeFeatures, featureChoisi, operateurChoisi);
        saveTo(dataFormated, resumeFile);
    }
    
    /**
     * Formate la HashMap fournit en Json
     * 
     * @param listeFeatures HashMap contenant une liste de features
     * @return Chaine de caract�re au format Json
     */
    public String toJson(HashMap<String, HashMap<String, List<Cell>>> listeFeatures, String featureChoisi, String operateurChoisi){
    	//D�claration d'une variable JavaScript pour la �utilisation des donn�es
    	builder.append("var summarizerData = ");
    	
    	//D�finition des donn�es
        builder.append("{ \"features\": [ ");
        builder.append("{ \"featureChoisi\": \" ").append(featureChoisi).append("\"},");
        builder.append("{ \"operateurChoisi\": \" ").append(operateurChoisi).append("\"},");
        for(String key: listeFeatures.keySet()){
            String nomFeature = key;
            HashMap<String, List<Cell>> listeFeatureCells = listeFeatures.get(key);
            int TotalFeatureCells = getTotalFeatureCells(listeFeatureCells);
            
            //startFeature
            builder.append("{\"nom\": \"").append(nomFeature).append("\",");
            builder.append("\"types\": [ ");
            
            listeFeatureCellsToJson(listeFeatureCells, TotalFeatureCells);
            
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
    private void listeFeatureCellsToJson(HashMap<String, List<Cell>> listes, int TotalFeatureCells){
        for(String key: listes.keySet()){
            String nomSousDomaine = key;
            List<Cell> sousDomaine = listes.get(key);
            
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
    private int getTotalFeatureCells(HashMap<String, List<Cell>> valuesCellsFeature){
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
