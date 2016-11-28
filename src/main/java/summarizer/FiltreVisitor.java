/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.FeatureGroup;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.util.PCMVisitor;
import org.opencompare.api.java.value.BooleanValue;
import org.opencompare.api.java.value.Conditional;
import org.opencompare.api.java.value.DateValue;
import org.opencompare.api.java.value.Dimension;
import org.opencompare.api.java.value.IntegerValue;
import org.opencompare.api.java.value.Multiple;
import org.opencompare.api.java.value.NotApplicable;
import org.opencompare.api.java.value.NotAvailable;
import org.opencompare.api.java.value.Partial;
import org.opencompare.api.java.value.RealValue;
import org.opencompare.api.java.value.StringValue;
import org.opencompare.api.java.value.Unit;
import org.opencompare.api.java.value.Version;

/**
 *
 * @author NOYAF-PC
 */
public class FiltreVisitor implements PCMVisitor {
    
    HashMap<String, List> listes;
    HashMap<String, HashMap<String, List>> listeFeatures;
    
    List booleans;
    List conditionals;
    List dateValues;
    List dimensions;
    List multiples;
    List notApplicables; 
    List notAvailables; 
    List partials;
    List stringValues;
    List units;
    List versions;
    List numbers;
    
    
    Boolean isBooleanValue;
    Boolean isConditional;
    Boolean isDateValue;
    Boolean isDimension;
    Boolean isIntegerValue;
    Boolean isMultiple;
    Boolean isNotApplicable;
    Boolean isNotAvailable;
    Boolean isPartial;
    Boolean isRealValue;
    Boolean isStringValue;
    Boolean isUnit;
    Boolean isVersion;
    
    public FiltreVisitor(){
        listeFeatures = new HashMap();
        listes = new HashMap();
        
        booleans = new ArrayList<>();
        conditionals = new ArrayList<>();
        dateValues = new ArrayList<>();
        dimensions = new ArrayList<>();
        numbers = new ArrayList<>();
        multiples = new ArrayList<>();
        notApplicables = new ArrayList<>();
        notAvailables = new ArrayList<>();
        partials = new ArrayList<>();
        stringValues = new ArrayList<>();
        units = new ArrayList<>();
        versions = new ArrayList<>();
    }
    
    public HashMap<String, HashMap<String, List>> filtre(PCM pcm) {        
        pcm.accept(this);
        return listeFeatures;
    }
    
    public void print(PCM pcm){
        filtre(pcm);
        for(String key: listes.keySet()){
            for(Object o: listes.get(key)){
                System.out.println(o.toString());
            }
        }
    }

    @Override
    public void visit(PCM pcm) {
        //Vider les collections
        listeFeatures.clear();
        
        for (Feature feature: pcm.getConcreteFeatures()) {
            feature.accept(this);
        }
    }

    @Override
    public void visit(Feature ftr) {
        
        //Vider les collections
        listes.clear();
        booleans.clear();
        conditionals.clear();
        dateValues.clear();
        dimensions.clear();
        numbers.clear();
        multiples.clear();
        notApplicables.clear();
        notAvailables.clear();
        partials.clear();
        stringValues.clear();
        units.clear();
        versions.clear();
        
        for (Cell cell : ftr.getCells()) {
            cell.accept(this);            
        }
        
        //Récupération des valeurs par type
        listes.put("booleans", booleans);
        listes.put("conditionals", conditionals);
        listes.put("dateValues", dateValues);
        listes.put("dimensions", dimensions);
        listes.put("numbers", numbers);
        listes.put("multiples", multiples);
        listes.put("notApplicables", notApplicables);
        listes.put("notAvailables", notAvailables);
        listes.put("partials", partials);
        listes.put("stringValues", stringValues);
        listes.put("units", units);
        listes.put("versions", versions);
        
        //Enregistrement du nom du feature et sa collection de valeurs
        listeFeatures.put(ftr.getName(), listes);   
    }

    @Override
    public void visit(FeatureGroup fg) {}

    @Override
    public void visit(Product prdct) {
        for (Cell cell : prdct.getCells()) {
            cell.accept(this);
        }
    }

    @Override
    public void visit(Cell cell) {
        Value interpretation = cell.getInterpretation();
        
        isBooleanValue = false;
        isConditional = false;
        isDateValue = false;
        isDimension = false;
        isIntegerValue = false;
        isMultiple = false;
        isNotApplicable = false;
        isNotAvailable = false;
        isPartial = false;
        isRealValue = false;
        isStringValue = false;
        isUnit = false;
        isVersion = false;
        
        if (interpretation != null) {
            interpretation.accept(this);
        }
        if(isBooleanValue){
            booleans.add(cell.getContent());
        }
        if(isConditional){
            conditionals.add(cell.getContent());
        }
        if(isDateValue){
            dateValues.add(cell.getContent());
        }
        if(isDimension){
            dimensions.add(cell.getContent());
        }
        if(isIntegerValue){
            numbers.add(cell.getContent());
        }
        if(isMultiple){
            multiples.add(cell.getContent());
        }
        if(isNotApplicable){
            notApplicables.add(cell.getContent());
        }
        if(isNotAvailable){
            notAvailables.add(cell.getContent());
        }
        if(isPartial){
            partials.add(cell.getContent());
        }
        if(isRealValue){
            numbers.add(cell.getContent());
        }
        if(isStringValue){
            stringValues.add(cell.getContent());
        }
        if(isUnit){
            units.add(cell.getContent());
        }
        if(isVersion){
            versions.add(cell.getContent());
        }        
    }

    @Override
    public void visit(BooleanValue bv) {
        isBooleanValue = true;
    }

    @Override
    public void visit(Conditional cndtnl) {
        isConditional = true;
    }

    @Override
    public void visit(DateValue dv) {
        isDateValue = true;
    }

    @Override
    public void visit(Dimension dmnsn) {
        isDimension = true;
    }

    @Override
    public void visit(IntegerValue iv) {
        isIntegerValue = true;
    }

    @Override
    public void visit(Multiple mltpl) {
        isMultiple = true;
    }

    @Override
    public void visit(NotApplicable na) {
        isNotApplicable = true;
    }

    @Override
    public void visit(NotAvailable na) {
        isNotAvailable = true;
    }

    @Override
    public void visit(Partial prtl) {
        isPartial = true;
    }

    @Override
    public void visit(RealValue rv) {
        isRealValue = true;
    }

    @Override
    public void visit(StringValue sv) {
        isStringValue = true;
    }

    @Override
    public void visit(Unit unit) {
        isUnit = true;
    }

    @Override
    public void visit(Version vrsn) {
        isVersion = true;
    }
    
}