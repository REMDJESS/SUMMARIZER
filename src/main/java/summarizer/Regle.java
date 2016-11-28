/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author NOYAF-PC
 */
public class Regle {
    
    Motif motif;    
    
    /**
     * Constructeur
     * Instancie un motif
     */
    public Regle(){
        motif = new MotifImpl();
    }
    
    /**
     * Edite le resumé et le formate au format json
     * Le resumé etant constitué 
     * du pourcentage des items du sous domaine dans le domaine
     * du porcentage de chaque item (distinct), 
     * du maximum/minimum des pourcentages et les items correspondant
     * @param sousDomaine
     * @param nomSousDomaine
     * @return Objet json
     */
    public String StringRule(Collection sousDomaine, String nomSousDomaine, float tailleDomaine){        
        if(sousDomaine.size() > 0){
            StringBuilder builder = new StringBuilder();
            float pourcentageSousDomaine = (sousDomaine.size() / tailleDomaine ) * 100;

            builder.append("{\"type\": \"").append(nomSousDomaine).append("\",");
            builder.append("\"porcentageType\": ").append(pourcentageSousDomaine).append(",");
            builder.append("\"resume\": {");                

            //Calcul du pourcentage
            Map<String, Float> pourcentages = motif.pourcentage(sousDomaine);
            builder.append("\"pourcentage\": {");
            for(String key: pourcentages.keySet()){
                key = key.replace("\"", "\\\"");
                builder.append("\"").append(key).append("\": ").append(pourcentages.get(key)).append(",");
            }
            builder.deleteCharAt(builder.lastIndexOf(","));
            builder.append("},");

            float max = motif.max(pourcentages.values());
            float min = motif.min(pourcentages.values());
            if(max != min){
                builder.append("\"max\": {");
                for(String key: pourcentages.keySet()){
                    if(pourcentages.get(key) == max){
                        key = key.replace("\"", "\\\"");
                        builder.append("\"").append(key).append("\": ").append(max).append(",");
                    }
                }
                builder.deleteCharAt(builder.lastIndexOf(","));
                builder.append("},");

                builder.append("\"min\": {");
                for(String key: pourcentages.keySet()){
                    if(pourcentages.get(key) == min){
                        key = key.replace("\"", "\\\"");
                        builder.append("\"").append(key).append("\": ").append(min).append(",");
                    }
                }
                builder.deleteCharAt(builder.lastIndexOf(","));
                builder.append("},");
            }
            builder.deleteCharAt(builder.lastIndexOf(","));
            
            builder.append("}");
            builder.append("},");

            return builder.toString();
        }
        else{
            return "";
        }
    }
    
    /**
     * Edite le resumé et le formate au format json
     * Le resumé etant constitué 
     * du pourcentage des items du sous domaine dans le domaine
     * de la moyenne des items, 
     * du maximum/minimum
     * de l'écart type
     * @param sousDomaine
     * @param nomSousDomaine
     * @return Objet json
     */
    public String numberRule(Collection sousDomaine, String nomSousDomaine, float tailleDomaine){
        if(sousDomaine.size() > 0){
            StringBuilder builder = new StringBuilder();
            float pourcentageSousDomaine = (sousDomaine.size() / tailleDomaine ) * 100;

            float moyenne = motif.moyenne(sousDomaine); //Calcul de la moyenne
            float max = motif.max(sousDomaine); //Calcul du maximum
            float min = motif.min(sousDomaine); //Calcul du minimum
            float ecartType = motif.ecartType(sousDomaine); //Calcul de l'écart type

            builder.append("{\"type\": \"").append(nomSousDomaine).append("\",");
            builder.append("\"porcentageType\": ").append(pourcentageSousDomaine).append(",");
            builder.append("\"resume\": {");

            builder.append("\"moyenne\": ").append(moyenne).append(",");
            builder.append("\"max\": ").append(max).append(",");
            builder.append("\"min\": ").append(min).append(",");
            builder.append("\"ecartType\": ").append(ecartType);
            
            builder.append("}");
            builder.append("},");

            return builder.toString();
        }
        else{
            return "";
        }
    }
    
    /**
     * Edite le resumé et le formate au format json
     * Le resumé etant constitué 
     * du pourcentage des items du sous domaine dans le domaine
     * du porcentage de chaque item (distinct)
     * @param sousDomaine
     * @param nomSousDomaine
     * @return Objet json
     */
    public String booleanRule(Collection sousDomaine, String nomSousDomaine, float tailleDomaine){
        if(sousDomaine.size() > 0){
            StringBuilder builder = new StringBuilder();
            float pourcentageSousDomaine = (sousDomaine.size() / tailleDomaine ) * 100;

            builder.append("{\"type\": \"").append(nomSousDomaine).append("\",");
            builder.append("\"porcentageType\": ").append(pourcentageSousDomaine).append(",");
            builder.append("\"resume\": {");                

            //Calcul du pourcentage
            Map<String, Float> pourcentages = motif.pourcentage(sousDomaine);
            builder.append("\"pourcentage\": {");
            for(String key: pourcentages.keySet()){
                key = key.replace("\"", "\\\"");
                builder.append("\"").append(key).append("\": ").append(pourcentages.get(key)).append(",");
            }
            builder.deleteCharAt(builder.lastIndexOf(","));
            builder.append("}");
            
            builder.append("}");
            builder.append("},");

            return builder.toString();
        }
        else{
            return "";
        }
    }
    
    /**
     * Edite le resumé et le formate au format json
     * Le resumé etant constitué 
     * du pourcentage des items du sous domaine dans le domaine
     * @param sousDomaine
     * @param nomSousDomaine
     * @return Objet json
     */
    public String notApplicableRule(Collection sousDomaine, String nomSousDomaine, float tailleDomaine){
        if(sousDomaine.size() > 0){
            StringBuilder builder = new StringBuilder();
            float pourcentageSousDomaine = (sousDomaine.size() / tailleDomaine ) * 100;

            builder.append("{\"type\": \"").append(nomSousDomaine).append("\",");
            builder.append("\"porcentageType\": ").append(pourcentageSousDomaine);             
            builder.append("},");

            return builder.toString();
        }
        else{
            return "";
        }
    }
    
}
