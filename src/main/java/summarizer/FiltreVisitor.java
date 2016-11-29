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
import org.opencompare.api.java.value.*;

/**
 *
 * @author NOYAF-PC
 */
public class FiltreVisitor implements PCMVisitor {
    
    HashMap<String, List<String>> listes;
    HashMap<String, HashMap<String, List<String>>> listeFeatures;

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
        listeFeatures = new HashMap<>();
        listes = new HashMap<>();
    }
    
    public HashMap<String, HashMap<String, List<String>>> filtre(PCM pcm) {        
        pcm.accept(this);
        return listeFeatures;
    }
    
    public void print(PCM pcm){
    	HashMap<String, HashMap<String, List<String>>> lf = filtre(pcm);
        for(String nomFeature: lf.keySet()){
        	HashMap<String, List<String>> listeCellsValuesByType = lf.get(nomFeature);
        	System.out.println("\n/********* " + nomFeature + " *********/");
        	for(String nomType: listeCellsValuesByType.keySet()){
            	List<String> listeValue = listeCellsValuesByType.get(nomType);
            	System.out.println("/---------- " + nomType + " ----------/");
                for(String value: listeValue){
                	System.out.println(value);
                }
            }
        }
    }

    @Override
    public void visit(PCM pcm) {
        //Nouvelle collection
        listeFeatures = new HashMap<>();
        
        for (Feature feature: pcm.getConcreteFeatures()) {
            feature.accept(this);
        }
    }

    @Override
    public void visit(Feature ftr) {
        //Nouvelle collection
        listes = new HashMap<>();
        
        for (Cell cell : ftr.getCells()) {        	
        	cell.accept(this);
        }
        
        //Enregistrement du nom du feature et sa collection de valeurs
        listeFeatures.put(ftr.getName(), listes);
    }

    @Override
    public void visit(FeatureGroup fg) {}

    @Override
    public void visit(Product prdct) {}

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
        	if(listes.containsKey("booleans")){
            	listes.get("booleans").add(cell.getContent());
        	}
        	else{
        		List<String> liste = new ArrayList<>();
        		liste.add(cell.getContent());
        		listes.put("booleans", liste);
        	}
        }
        if(isConditional){
        	if(listes.containsKey("conditionals")){
            	listes.get("conditionals").add(cell.getContent());
        	}
        	else{
        		List<String> liste = new ArrayList<>();
        		liste.add(cell.getContent());
        		listes.put("conditionals", liste);
        	}        
        }
        if(isDateValue){
        	if(listes.containsKey("dateValues")){
            	listes.get("dateValues").add(cell.getContent());
        	}
        	else{
        		List<String> liste = new ArrayList<>();
        		liste.add(cell.getContent());
        		listes.put("dateValues", liste);
        	}        
        }
        if(isDimension){
        	if(listes.containsKey("dimensions")){
            	listes.get("dimensions").add(cell.getContent());
        	}
        	else{
        		List<String> liste = new ArrayList<>();
        		liste.add(cell.getContent());
        		listes.put("dimensions", liste);
        	}        
        }
        if(isIntegerValue){
        	if(listes.containsKey("numbers")){
            	listes.get("numbers").add(cell.getContent());
        	}
        	else{
        		List<String> liste = new ArrayList<>();
        		liste.add(cell.getContent());
        		listes.put("numbers", liste);
        	}        
        }
        if(isMultiple){
        	if(listes.containsKey("multiples")){
            	listes.get("multiples").add(cell.getContent());
        	}
        	else{
        		List<String> liste = new ArrayList<>();
        		liste.add(cell.getContent());
        		listes.put("multiples", liste);
        	}        
        }
        if(isNotApplicable){
        	if(listes.containsKey("notApplicables")){
            	listes.get("notApplicables").add(cell.getContent());
        	}
        	else{
        		List<String> liste = new ArrayList<>();
        		liste.add(cell.getContent());
        		listes.put("notApplicables", liste);
        	}        
        }
        if(isNotAvailable){
        	if(listes.containsKey("notAvailables")){
            	listes.get("notAvailables").add(cell.getContent());
        	}
        	else{
        		List<String> liste = new ArrayList<>();
        		liste.add(cell.getContent());
        		listes.put("notAvailables", liste);
        	}       
        }
        if(isPartial){
        	if(listes.containsKey("partials")){
            	listes.get("partials").add(cell.getContent());
        	}
        	else{
        		List<String> liste = new ArrayList<>();
        		liste.add(cell.getContent());
        		listes.put("partials", liste);
        	}       
        }
        if(isRealValue){
        	if(listes.containsKey("numbers")){
            	listes.get("numbers").add(cell.getContent());
        	}
        	else{
        		List<String> liste = new ArrayList<>();
        		liste.add(cell.getContent());
        		listes.put("numbers", liste);
        	}       
        }
        if(isStringValue){
        	if(listes.containsKey("stringValues")){
            	listes.get("stringValues").add(cell.getContent());
        	}
        	else{
        		List<String> liste = new ArrayList<>();
        		liste.add(cell.getContent());
        		listes.put("stringValues", liste);
        	}       
        }
        if(isUnit){
        	if(listes.containsKey("units")){
            	listes.get("units").add(cell.getContent());
        	}
        	else{
        		List<String> liste = new ArrayList<>();
        		liste.add(cell.getContent());
        		listes.put("units", liste);
        	}       
        }
        if(isVersion){
        	if(listes.containsKey("versions")){
            	listes.get("versions").add(cell.getContent());
        	}
        	else{
        		List<String> liste = new ArrayList<>();
        		liste.add(cell.getContent());
        		listes.put("versions", liste);
        	}                   
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