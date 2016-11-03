/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.util.Collection;

/**
 *
 * @author NOYAF-PC
 */
public interface Filtre {
    
    /**
     * Retourne une liste de nombres (float) � partir d'une liste
     * @return 
     */
    public Collection<Float> getNumberValues();

    /**
     * Retourne une liste de chaines de caract�res � partir d'une liste
     * @return 
     */
    public Collection getStringValues();
    
    /**
     * Retourne une liste de valeures booleennes � partir d'une liste
     * @return 
     */
    public Collection getBooleanValues();
    
    /**
     * Retourne une liste de valeures conditionnelles � partir d'une liste
     * @return 
     */
    public Collection getConditionalValues();

    /**
     * Retourne une liste de valeurs non d�finies � partir d'une liste
     * @return 
     */
    public Collection getNotAvailableValues();
    
    /**
     * Retourne une liste de date � partir d'une liste
     * @return 
     */
    public Collection getDateValues();
    
    /**
     * Retourne une liste de dimension � partir d'une liste
     * @return 
     */
    public Collection getDimensionValues();
    
    /**
     * Retourne une liste de valeurs non applicable � partir d'une liste
     * @return 
     */
    public Collection getNotApplicableValues();
    
    /**
     * Retourne une liste de partial � partir d'une liste
     * @return 
     */
    public Collection getPartialValues();

    /**
     * Retourne une liste de unit � partir d'une liste
     * @return 
     */
    public Collection getUnitValues();

    /**
     * Retourne une liste de version � partir d'une liste
     * @return 
     */
    public Collection getVersionValues();

    /**
     * Retourne une liste de multiple � partir d'une liste
     * @return 
     */
    public Collection getMultipleValues();
    
}
