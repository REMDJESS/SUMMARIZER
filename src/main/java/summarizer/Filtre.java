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
     * Retourne une collection de nombres (float) à partir d'une collection
     * @return 
     */
    public Collection<Float> getNumberValues(Collection<Cell> data);

    /**
     * Retourne une collection de chaines de caractères à partir d'une collection
     * @return 
     */
    public Collection getStringValues(Collection<Cell> data);
    
    /**
     * Retourne une collection de valeures booleennes à partir d'une collection
     * @return 
     */
    public Collection getBooleanValues(Collection<Cell> data);
    
    /**
     * Retourne une collection de valeures conditionnelles à partir d'une collection
     * @return 
     */
    public Collection getConditionalValues(Collection<Cell> data);

    /**
     * Retourne une collection de valeurs non définies à partir d'une collection
     * @return 
     */
    public Collection getNotAvailableValues(Collection<Cell> data);
    
    /**
     * Retourne une collection de date à partir d'une collection
     * @return 
     */
    public Collection getDateValues(Collection<Cell> data);
    
    /**
     * Retourne une collection de dimension à partir d'une collection
     * @return 
     */
    public Collection getDimensionValues(Collection<Cell> data);
    
    /**
     * Retourne une collection de valeurs non applicable à partir d'une collection
     * @return 
     */
    public Collection getNotApplicableValues(Collection<Cell> data);
    
    /**
     * Retourne une collection de partial à partir d'une collection
     * @return 
     */
    public Collection getPartialValues(Collection<Cell> data);

    /**
     * Retourne une collection de unit à partir d'une collection
     * @return 
     */
    public Collection getUnitValues(Collection<Cell> data);

    /**
     * Retourne une collection de version à partir d'une collection
     * @return 
     */
    public Collection getVersionValues(Collection<Cell> data);

    /**
     * Retourne une collection de multiple à partir d'une collection
     * @return 
     */
    public Collection getMultipleValues(Collection<Cell> data);
    
}
