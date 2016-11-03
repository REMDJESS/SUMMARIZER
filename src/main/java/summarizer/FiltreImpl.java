/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.util.ArrayList;
import java.util.Collection;
import org.opencompare.api.java.Cell;

/**
 * Permet de filtrer une collection de "Cell" et de constituer des sous-collection de chaque type de données possible 
 * @author NOYAF-PC
 */
public class FiltreImpl implements Filtre {
    
    /**
     * Retourne une liste de nombres (float) à partir d'une liste
     * @return 
     */
    public Collection<Float> getNumberValues(Collection<Cell> data){
        Collection<Float> filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("IntegerValue") || c.getInterpretation().toString().contains("RealValue")){
                filtre.add(Float.parseFloat(c.getContent()));
            }
        }
        return filtre;
    }

    /**
     * Retourne une liste de chaines de caractères à partir d'une liste
     * @return 
     */
    public Collection getStringValues(Collection<Cell> data){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("StringValue")){ 
                filtre.add(c.getContent());
            }
        }
        return filtre;    
    }
    
    /**
     * Retourne une liste de valeures booleennes à partir d'une liste
     * @return 
     */
    public Collection getBooleanValues(Collection<Cell> data){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("BooleanValue")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }
    
    /**
     * Retourne une liste de valeures conditionnelles à partir d'une liste
     * @return 
     */
    public Collection getConditionalValues(Collection<Cell> data){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("Conditional")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }

    /**
     * Retourne une liste de valeurs non définies à partir d'une liste
     * @return 
     */
    public Collection getNotAvailableValues(Collection<Cell> data){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("NotAvailable")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }
    
    /**
     * Retourne une liste de date à partir d'une liste
     * @return 
     */
    public Collection getDateValues(Collection<Cell> data){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("DateValues")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }
    
    /**
     * Retourne une liste de dimension à partir d'une liste
     * @return 
     */
    public Collection getDimensionValues(Collection<Cell> data){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("Dimension")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }
    
    /**
     * Retourne une liste de valeurs non applicable à partir d'une liste
     * @return 
     */
    public Collection getNotApplicableValues(Collection<Cell> data){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("NotApplicable")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }
    
    /**
     * Retourne une liste de partial à partir d'une liste
     * @return 
     */
    public Collection getPartialValues(Collection<Cell> data){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("Partial")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }

    /**
     * Retourne une liste de unit à partir d'une liste
     * @return 
     */
    public Collection getUnitValues(Collection<Cell> data){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("Unit")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }

    /**
     * Retourne une liste de version à partir d'une liste
     * @return 
     */
    public Collection getVersionValues(Collection<Cell> data){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("Version")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }

    /**
     * Retourne une liste de multiple à partir d'une liste
     * @return 
     */
    public Collection getMultipleValues(Collection<Cell> data){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("Multiple")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }
    
}
