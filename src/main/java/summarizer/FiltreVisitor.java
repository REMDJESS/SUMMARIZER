/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.util.ArrayList;
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
    
    List<List> liste;
    List booleans;
    List conditionals;
    List dateValues;
    List dimensions;
    List integers;
    List multiples;
    List notApplicables; 
    List notAvailables; 
    List partials;
    List realValues;
    List stringValues;
    List units;
    List versions;
    
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
        liste = new ArrayList();
        booleans = new ArrayList<>();
        conditionals = new ArrayList<>();
        dateValues = new ArrayList<>();
        dimensions = new ArrayList<>();
        integers = new ArrayList<>();
        multiples = new ArrayList<>();
        notApplicables = new ArrayList<>();
        notAvailables = new ArrayList<>();
        partials = new ArrayList<>();
        realValues = new ArrayList<>();
        stringValues = new ArrayList<>();
        units = new ArrayList<>();
        versions = new ArrayList<>();
    }
    
    public void filtre(PCM pcm) {
        //Vider les collections
        liste.clear();
        booleans.clear();
        conditionals.clear();
        dateValues.clear();
        dimensions.clear();
        integers.clear();
        multiples.clear();
        notApplicables.clear();
        notAvailables.clear();
        partials.clear();
        realValues.clear();
        stringValues.clear();
        units.clear();
        versions.clear();
        
        pcm.accept(this);

        liste.add(booleans);
        liste.add(conditionals);
        liste.add(dateValues);
        liste.add(dimensions);
        liste.add(integers);
        liste.add(multiples);
        liste.add(notApplicables);
        liste.add(notAvailables);
        liste.add(partials);
        liste.add(realValues);
        liste.add(stringValues);
        liste.add(units);
        liste.add(versions);
    }
    
    public void print(PCM pcm){
        filtre(pcm);
        for(List l: liste){
            for(Object v: l){
                System.out.println(v.toString());
            }
        }
    }
    
    public List<List> getListe(PCM pcm){
        filtre(pcm);
        return liste;
    }
    
    public List getBooleanList(){
        return booleans;
    }
    
    public List getConditionalList(){
        return conditionals;
    }
    
    public List getdateValueList(){
        return dateValues;
    }
    
    public List getDimensionList(){
        return dimensions;
    }
    
    public List getIntegerList(){
        return integers;
    }
    
    public List getMultipleList(){
        return multiples;
    }
    
    public List getNotAvailableList(){
        return notAvailables;
    }
    
    public List getNotApplicableList(){
        return notApplicables;
    }
    
    public List getPartialList(){
        return partials;
    }
    
    public List getRealValueList(){
        return realValues;
    }
    
    public List getStringValueList(){
        return stringValues;
    }
    
    public List getUnitList(){
        return units;
    }
    
    public List getVersionList(){
        return versions;
    }

    @Override
    public void visit(PCM pcm) {
        for (Feature feature: pcm.getConcreteFeatures()) {
            feature.accept(this);
        }
    }

    @Override
    public void visit(Feature ftr) {
        for (Cell cell : ftr.getCells()) {
            cell.accept(this);
        }
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
            integers.add(cell.getContent());
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
            realValues.add(cell.getContent());
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
