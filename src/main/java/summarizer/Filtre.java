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
     * Retourne une liste de nombres (float) à partir d'une liste
     * @return 
     */
    public Collection<Float> getNumberValues();

    /**
     * Retourne une liste de chaines de caractères à partir d'une liste
     * @return 
     */
    public Collection getStringValues();
    
    /**
     * Retourne une liste de valeures booleennes à partir d'une liste
     * @return 
     */
    public Collection getBooleanValues();
    
    /**
     * Retourne une liste de valeures conditionnelles à partir d'une liste
     * @return 
     */
    public Collection getConditionalValues();

    /**
     * Retourne une liste de valeurs non définies à partir d'une liste
     * @return 
     */
    public Collection getNotAvailableValues();
    
    /**
     * Retourne une liste de date à partir d'une liste
     * @return 
     */
    public Collection getDateValues();
    
    /**
     * Retourne une liste de dimension à partir d'une liste
     * @return 
     */
    public Collection getDimensionValues();
    
    /**
     * Retourne une liste de valeurs non applicable à partir d'une liste
     * @return 
     */
    public Collection getNotApplicableValues();
    
    /**
     * Retourne une liste de partial à partir d'une liste
     * @return 
     */
    public Collection getPartialValues();

    /**
     * Retourne une liste de unit à partir d'une liste
     * @return 
     */
    public Collection getUnitValues();

    /**
     * Retourne une liste de version à partir d'une liste
     * @return 
     */
    public Collection getVersionValues();

    /**
     * Retourne une liste de multiple à partir d'une liste
     * @return 
     */
    public Collection getMultipleValues();
    
}
