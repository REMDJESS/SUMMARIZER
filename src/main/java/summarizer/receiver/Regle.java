/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer.receiver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.opencompare.api.java.Cell;

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
     * 
     * @param sousDomaine
     * @param nomSousDomaine
     * @return Objet json
     */
    public String StringRule(Collection<Cell> sousDomaine, String nomSousDomaine, float tailleDomaine){        
    	Collection<String> newSousDomaine = convertSousDomaineToStringList(sousDomaine);
    	if(sousDomaine.size() > 0){
            StringBuilder builder = new StringBuilder();
            float pourcentageSousDomaine = (sousDomaine.size() / tailleDomaine ) * 100;

            builder.append("{\"type\": \"").append(nomSousDomaine).append("\",");
            builder.append("\"pourcentageType\": ").append(pourcentageSousDomaine).append(",");
            builder.append("\"resume\": {");                

            //Calcul du pourcentage
            Map<String, Float> pourcentages = motif.pourcentage(newSousDomaine);
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
     * 
     * @param sousDomaine
     * @param nomSousDomaine
     * @return Objet json
     */
    public String numberRule(Collection<Cell> sousDomaine, String nomSousDomaine, float tailleDomaine){
    	Collection<Float> newSousDomaine = convertSousDomaineToFloatList(sousDomaine);
    	if(sousDomaine.size() > 0){
            StringBuilder builder = new StringBuilder();
            float pourcentageSousDomaine = (sousDomaine.size() / tailleDomaine ) * 100;

            float moyenne = motif.moyenne(newSousDomaine); //Calcul de la moyenne
            float max = motif.max(newSousDomaine); //Calcul du maximum
            float min = motif.min(newSousDomaine); //Calcul du minimum
            float ecartType = motif.ecartType(newSousDomaine); //Calcul de l'écart type

            builder.append("{\"type\": \"").append(nomSousDomaine).append("\",");
            builder.append("\"pourcentageType\": ").append(pourcentageSousDomaine).append(",");
            builder.append("\"resume\": {");

            builder.append("\"moyenne\": ").append(moyenne).append(",");
            builder.append("\"max\": ").append(max).append(",");
            builder.append("\"productWithMax\": [");
            for(Cell cell: sousDomaine){
            	if(Float.parseFloat(cell.getContent()) == max){
            		builder.append("\"").append(cell.getProduct().getKeyContent()).append("\",");
            	}
            }
            builder.deleteCharAt(builder.lastIndexOf(","));
            builder.append("],");
            builder.append("\"min\": ").append(min).append(",");
            builder.append("\"productWithMin\": [");
            for(Cell cell: sousDomaine){
            	if(Float.parseFloat(cell.getContent()) == min){
            		builder.append("\"").append(cell.getProduct().getKeyContent()).append("\",");
            	}
            }
            builder.deleteCharAt(builder.lastIndexOf(","));
            builder.append("],");
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
     * 
     * @param sousDomaine
     * @param nomSousDomaine
     * @return Objet json
     */
    public String booleanRule(Collection<Cell> sousDomaine, String nomSousDomaine, float tailleDomaine){
    	Collection<String> newSousDomaine = convertSousDomaineToStringList(sousDomaine);
    	if(sousDomaine.size() > 0){
            StringBuilder builder = new StringBuilder();
            float pourcentageSousDomaine = (sousDomaine.size() / tailleDomaine ) * 100;

            builder.append("{\"type\": \"").append(nomSousDomaine).append("\",");
            builder.append("\"pourcentageType\": ").append(pourcentageSousDomaine).append(",");
            builder.append("\"resume\": {");                

            //Calcul du pourcentage
            Map<String, Float> pourcentages = motif.pourcentage(newSousDomaine);
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
     * 
     * @param sousDomaine
     * @param nomSousDomaine
     * @return Objet json
     */
    public String notApplicableRule(Collection<Cell> sousDomaine, String nomSousDomaine, float tailleDomaine){
    	if(sousDomaine.size() > 0){
            StringBuilder builder = new StringBuilder();
            float pourcentageSousDomaine = (sousDomaine.size() / tailleDomaine ) * 100;

            builder.append("{\"type\": \"").append(nomSousDomaine).append("\",");
            builder.append("\"pourcentageType\": ").append(pourcentageSousDomaine);             
            builder.append("},");

            return builder.toString();
        }
        else{
            return "";
        }
    }
    
    /**
     * Convertie la liste de cellule en liste de chaine de caractère (valeurs des cellules)
     * @param liste
     * @return
     */
    private Collection<String> convertSousDomaineToStringList(Collection<Cell> liste){
    	List<String> newListe = new ArrayList<>();
    	for(Cell cell: liste){
    		newListe.add(cell.getContent());
    	}
    	return newListe;
    }
    
    /**
     * Convertie la liste de cellule en liste de float (valeurs des cellules)
     * @param liste
     * @return
     */
    private Collection<Float> convertSousDomaineToFloatList(Collection<Cell> liste){
    	List<Float> newListe = new ArrayList<>();
    	for(Cell cell: liste){
    		newListe.add(Float.parseFloat(cell.getContent()));
    	}
    	return newListe;
    }    
    
}
