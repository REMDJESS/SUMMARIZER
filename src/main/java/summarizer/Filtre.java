/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.util.ArrayList;
import java.util.Collection;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.value.StringValue;

/**
 *
 * @author NOYAF-PC
 */
public class Filtre {
    
    private Collection<Cell> data;
    
    public Filtre(Collection<Cell> data){
        this.data = data;
    }
    
    /**
     * Retourne une liste de nombres (float) � partir d'une liste
     * @return 
     */
    public Collection<Float> getNumberValues(){
        Collection<Float> filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("IntegerValue") || c.getInterpretation().toString().contains("RealValue")){
                filtre.add(Float.parseFloat(c.getContent()));
            }
        }
        return filtre;
    }

    /**
     * Retourne une liste de chaines de caract�res � partir d'une liste
     * @return 
     */
    public Collection getStringValues(){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("StringValue")){ 
                filtre.add(c.getContent());
            }
        }
        return filtre;    
    }
    
    /**
     * Retourne une liste de valeures booleennes � partir d'une liste
     * @return 
     */
    public Collection getBooleanValues(){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("BooleanValue")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }
    
    /**
     * Retourne une liste de valeures conditionnelles � partir d'une liste
     * @return 
     */
    public Collection getConditionalValues(){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("Conditional")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }

    /**
     * Retourne une liste de valeurs non d�finies � partir d'une liste
     * @return 
     */
    public Collection getNotAvailableValues(){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("NotAvailable")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }
    
    /**
     * Retourne une liste de date � partir d'une liste
     * @return 
     */
    public Collection getDateValues(){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("DateValues")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }
    
    /**
     * Retourne une liste de dimension � partir d'une liste
     * @return 
     */
    public Collection getDimensionValues(){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("Dimension")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }
    
    /**
     * Retourne une liste de valeurs non applicable � partir d'une liste
     * @return 
     */
    public Collection getNotApplicableValues(){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("NotApplicable")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }
    
    /**
     * Retourne une liste de partial � partir d'une liste
     * @return 
     */
    public Collection getPartialValues(){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("Partial")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }

    /**
     * Retourne une liste de unit � partir d'une liste
     * @return 
     */
    public Collection getUnitValues(){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("Unit")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }

    /**
     * Retourne une liste de version � partir d'une liste
     * @return 
     */
    public Collection getVersionValues(){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("Version")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }

    /**
     * Retourne une liste de multiple � partir d'une liste
     * @return 
     */
    public Collection getMultipleValues(){
        Collection filtre = new ArrayList<>();
        for(Cell c: data){
            if(c.getInterpretation().toString().contains("Multiple")){ 
                filtre.add(c.getContent());
            }   
        }
        return filtre;
    }
    
}
