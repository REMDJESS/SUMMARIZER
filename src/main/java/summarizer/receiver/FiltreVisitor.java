/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer.receiver;

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
    
    private HashMap<String, List<Cell>> listes;
    private HashMap<String, HashMap<String, List<Cell>>> listeFeatures;

    private Boolean isBooleanValue;
    private Boolean isConditional;
    private Boolean isDateValue;
    private Boolean isDimension;
    private Boolean isIntegerValue;
    private Boolean isMultiple;
    private Boolean isNotApplicable;
    private Boolean isNotAvailable;
    private Boolean isPartial;
    private Boolean isRealValue;
    private Boolean isStringValue;
    private Boolean isUnit;
    private Boolean isVersion;
    
    public FiltreVisitor(){
        listeFeatures = new HashMap<>();
        listes = new HashMap<>();
        
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
    }
    
    /**
     * 
     * @param pcm
     * @return Une HasMap des features contenant l'HashMap des cellules regroupées par interpretation
     */
    public HashMap<String, HashMap<String, List<Cell>>> filtre(PCM pcm) {        
        pcm.accept(this);
        return listeFeatures;
    }

    /**
     * 
     * @param pcm
     * @return Une HasMap des features contenant l'HashMap des cellules avec l'interpretation la plus representée
     */
    public HashMap<String, HashMap<String, List<Cell>>> filtreReduit(PCM pcm) {        
        pcm.accept(this);
        HashMap<String, HashMap<String, List<Cell>>> listeFeaturesReduced = new HashMap<>();
        
        for(String key: listeFeatures.keySet()){
            List<Cell> bigger = new ArrayList<>();
            String biggerName = "";
        	HashMap<String, List<Cell>> current = listeFeatures.get(key);
        	
        	for(String cle: current.keySet()){
        		if(bigger.isEmpty()){
        			bigger = current.get(cle);
        			biggerName = cle;
        		}
        		else{
            		//Compare la taille des listes et effectue la permutation si condition remplie
            		if( (current.get(cle)).size() > bigger.size() ){
            			bigger = current.get(cle);
            			biggerName = cle;
            		}
        		}        		
        	}
        	
        	HashMap<String, List<Cell>> biggerMap = new HashMap<>();
        	biggerMap.put(biggerName, bigger);
        	listeFeaturesReduced.put(key, biggerMap);
        }
        
        return listeFeaturesReduced;
    }
    
    /**
     * Affiche les données des cellules par feature puis par types
     * 
     * @param pcm
     */
    public void print(PCM pcm){
    	HashMap<String, HashMap<String, List<Cell>>> lf = filtre(pcm);
        for(String nomFeature: lf.keySet()){
        	HashMap<String, List<Cell>> listeCellsValuesByType = lf.get(nomFeature);
        	System.out.println("\n/********* " + nomFeature + " *********/");
        	for(String nomType: listeCellsValuesByType.keySet()){
            	List<Cell> listeValue = listeCellsValuesByType.get(nomType);
            	System.out.println("/---------- " + nomType + " ----------/");
                for(Cell value: listeValue){
                	System.out.println(value.getContent());
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
    
    /**
     * Ajoute à la HashMap des features le nom du feature et la HashMap de ses cellules
     * 
     * @param ftr
     */
    @Override
    public void visit(Feature ftr) {
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
    
    /**
     * Ajoute la cellule dans la liste correspondant à son interpretation (type)
     * 
     * @param cell
     */
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
            	listes.get("booleans").add(cell);
        	}
        	else{
        		List<Cell> liste = new ArrayList<>();
        		liste.add(cell);
        		listes.put("booleans", liste);
        	}
        }
        if(isConditional){
        	if(listes.containsKey("conditionals")){
            	listes.get("conditionals").add(cell);
        	}
        	else{
        		List<Cell> liste = new ArrayList<>();
        		liste.add(cell);
        		listes.put("conditionals", liste);
        	}        
        }
        if(isDateValue){
        	if(listes.containsKey("dateValues")){
            	listes.get("dateValues").add(cell);
        	}
        	else{
        		List<Cell> liste = new ArrayList<>();
        		liste.add(cell);
        		listes.put("dateValues", liste);
        	}        
        }
        if(isDimension){
        	if(listes.containsKey("dimensions")){
            	listes.get("dimensions").add(cell);
        	}
        	else{
        		List<Cell> liste = new ArrayList<>();
        		liste.add(cell);
        		listes.put("dimensions", liste);
        	}        
        }
        if(isIntegerValue){
        	if(listes.containsKey("numbers")){
            	listes.get("numbers").add(cell);
        	}
        	else{
        		List<Cell> liste = new ArrayList<>();
        		liste.add(cell);
        		listes.put("numbers", liste);
        	}        
        }
        if(isMultiple){
        	if(listes.containsKey("multiples")){
            	listes.get("multiples").add(cell);
        	}
        	else{
        		List<Cell> liste = new ArrayList<>();
        		liste.add(cell);
        		listes.put("multiples", liste);
        	}        
        }
        if(isNotApplicable){
        	if(listes.containsKey("notApplicables")){
            	listes.get("notApplicables").add(cell);
        	}
        	else{
        		List<Cell> liste = new ArrayList<>();
        		liste.add(cell);
        		listes.put("notApplicables", liste);
        	}        
        }
        if(isNotAvailable){
        	if(listes.containsKey("notAvailables")){
            	listes.get("notAvailables").add(cell);
        	}
        	else{
        		List<Cell> liste = new ArrayList<>();
        		liste.add(cell);
        		listes.put("notAvailables", liste);
        	}       
        }
        if(isPartial){
        	if(listes.containsKey("partials")){
            	listes.get("partials").add(cell);
        	}
        	else{
        		List<Cell> liste = new ArrayList<>();
        		liste.add(cell);
        		listes.put("partials", liste);
        	}       
        }
        if(isRealValue){
        	if(listes.containsKey("numbers")){
            	listes.get("numbers").add(cell);
        	}
        	else{
        		List<Cell> liste = new ArrayList<>();
        		liste.add(cell);
        		listes.put("numbers", liste);
        	}       
        }
        if(isStringValue){
        	if(listes.containsKey("stringValues")){
            	listes.get("stringValues").add(cell);
        	}
        	else{
        		List<Cell> liste = new ArrayList<>();
        		liste.add(cell);
        		listes.put("stringValues", liste);
        	}       
        }
        if(isUnit){
        	if(listes.containsKey("units")){
            	listes.get("units").add(cell);
        	}
        	else{
        		List<Cell> liste = new ArrayList<>();
        		liste.add(cell);
        		listes.put("units", liste);
        	}       
        }
        if(isVersion){
        	if(listes.containsKey("versions")){
            	listes.get("versions").add(cell);
        	}
        	else{
        		List<Cell> liste = new ArrayList<>();
        		liste.add(cell);
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