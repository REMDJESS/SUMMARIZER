/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer.receiver;

import java.util.Collection;
import java.util.Map;

/**
 * Classe recensant l'ensembles des motifs qui permettent l'édition du resumé
 * @author NOYAF1er
 */
public interface Motif{
    
    /**
     * Calcule la moyenne de l'ensemble de valeur de la collection
     * @param data Collection des valeurs sur lesquelles s'appuie le calcul
     * @return Moyenne
     */
    public float moyenne(Collection<Float> data);
    
    /**
     * Calcule le pourcentage d'apparition des différentes valeurs de la collection
     * @param data Collection des valeurs sur lesquelles s'appuie le calcul
     * @return Clé: valeur de la collection / Valeur: pourcentage d'apparition
     */
    public Map<String, Float> pourcentage(Collection data);
    
    /**
     * Calcule le maximum de l'ensemble de valeur de la collection
     * @param data Collection des valeurs sur lesquelles s'appuie le calcul
     * @return La valeur maximale
     */
    public float max(Collection<Float> data);
    
    /**
     * Renvoie le minimum de l'ensemble de valeur
     * @param data Collection des valeurs sur lesquelles s'appuie le calcul
     * @return La valeur minimale
     */
    public float min(Collection<Float> data);
    
    /**
     * Calcule l'écart type de l'ensemble de valeur de la collection
     * @param data Collection des valeurs sur lesquelles s'appuie le calcul
     * @return L'écart type
     */
    public float ecartType(Collection<Float> data);
    
    
}
