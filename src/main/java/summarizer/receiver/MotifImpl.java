/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer.receiver;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Classe recensant l'ensembles des motifs qui permettent l'édition du resumé
 * @author NOYAF1er
 */
public class MotifImpl implements Motif{
    
    /**
     * Calcule la moyenne de l'ensemble de valeur de la collection
     * @param data Collection des valeurs sur lesquelles s'appuie le calcul
     * @return Moyenne
     */
    public float moyenne(Collection<Float> data){
        float total = 0;
        try{
            for(float d: data){
                total = total + d;
            }
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
        }
        return (total /(float) data.size());
    }
    
    /**
     * Calcule le pourcentage d'apparition des différentes valeurs de la collection
     * @param data Collection des valeurs sur lesquelles s'appuie le calcul
     * @return Clé: valeur de la collection / Valeur: pourcentage d'apparition
     */
    public Map<String, Float> pourcentage(Collection data){
        float dataSize = data.size();
        float compteur;
        float fraction;
        Map<String, Float> tabPourc = new HashMap<>();
        Set criteres = new HashSet<>(data); //Recupère les éléments sans redondances
        
        try{
            for(Object critere: criteres){
                compteur = 0;
                for(Object d: data){
                    if(d.equals(critere))
                        compteur++;
                }
                fraction = compteur/dataSize;
                tabPourc.put((String) critere, fraction*100);
            }
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
        }
        return tabPourc;
    }
    
    /**
     * Calcule le maximum de l'ensemble de valeur de la collection
     * @param data Collection des valeurs sur lesquelles s'appuie le calcul
     * @return La valeur maximale
     */
    public float max(Collection<Float> data){
        return Collections.max(data);
    }
    
    /**
     * Renvoie le minimum de l'ensemble de valeur
     * @param data Collection des valeurs sur lesquelles s'appuie le calcul
     * @return La valeur minimale
     */
    public float min(Collection<Float> data){
        return Collections.min(data);
    }
    
    /**
     * Calcule l'écart type de l'ensemble de valeur de la collection
     * @param data Collection des valeurs sur lesquelles s'appuie le calcul
     * @return L'écart type
     */
    public float ecartType(Collection<Float> data){
        float taille = data.size();
        float moyenne = this.moyenne(data);
        float ecartType = 0;
        float somme = 0;
        
        try{
            for(float d: data){
                somme =+ (float) Math.pow((d-moyenne), 2);
            }
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
        }        
        
        ecartType = (float) Math.sqrt(somme)/taille;
        return ecartType;
    }
}
