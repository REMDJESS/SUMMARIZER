/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.util.Collection;
import org.opencompare.api.java.Cell;

/**
 *
 * @author NOYAF-PC
 */
public interface Filtre {
    
    /**
     * Retourne une collection de nombres (float) � partir d'une collection
     * @return 
     */
    public Collection<Float> getNumberValues(Collection<Cell> data);

    /**
     * Retourne une collection de chaines de caract�res � partir d'une collection
     * @return 
     */
    public Collection getStringValues(Collection<Cell> data);
    
    /**
     * Retourne une collection de valeures booleennes � partir d'une collection
     * @return 
     */
    public Collection getBooleanValues(Collection<Cell> data);
    
    /**
     * Retourne une collection de valeures conditionnelles � partir d'une collection
     * @return 
     */
    public Collection getConditionalValues(Collection<Cell> data);

    /**
     * Retourne une collection de valeurs non d�finies � partir d'une collection
     * @return 
     */
    public Collection getNotAvailableValues(Collection<Cell> data);
    
    /**
     * Retourne une collection de date � partir d'une collection
     * @return 
     */
    public Collection getDateValues(Collection<Cell> data);
    
    /**
     * Retourne une collection de dimension � partir d'une collection
     * @return 
     */
    public Collection getDimensionValues(Collection<Cell> data);
    
    /**
     * Retourne une collection de valeurs non applicable � partir d'une collection
     * @return 
     */
    public Collection getNotApplicableValues(Collection<Cell> data);
    
    /**
     * Retourne une collection de partial � partir d'une collection
     * @return 
     */
    public Collection getPartialValues(Collection<Cell> data);

    /**
     * Retourne une collection de unit � partir d'une collection
     * @return 
     */
    public Collection getUnitValues(Collection<Cell> data);

    /**
     * Retourne une collection de version � partir d'une collection
     * @return 
     */
    public Collection getVersionValues(Collection<Cell> data);

    /**
     * Retourne une collection de multiple � partir d'une collection
     * @return 
     */
    public Collection getMultipleValues(Collection<Cell> data);
    
}
