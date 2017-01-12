package summarizer.receiver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Renvoie le type d'un feature
 * @author NOYAF-PC
 *
 */
public class TypeFeatureVisitor implements PCMVisitor  {
	
	String type;
	List<String> listeType;
    
	/**
	 * 
	 */
    public TypeFeatureVisitor(){
    	type = "";
    	listeType = new ArrayList<>();
    }
    
    /**
     * 
     * @param feature
     * @return
     */
    public String getType(Feature feature){
    	String retour = "";
    	feature.accept(this);
    	Map<String, Integer> listOccurence = new HashMap<>();
    	
    	for(String value: listeType){
    		listOccurence.put(value, getOccurrence(listeType, value));
    	}
    	
    	Integer valeurMax = Collections.max(listOccurence.values());
    	for(String cle: listOccurence.keySet()){
    		if(listOccurence.get(cle) == valeurMax){
    			retour = cle;
    		}
    	}
    	
    	
    	return retour;
    }
	
	@Override
	public void visit(PCM pcm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Feature feature) {
        for (Cell cell : feature.getCells()) {        	
        	cell.accept(this);
        }
	}

	@Override
	public void visit(FeatureGroup featureGroup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Cell cell) {
        Value interpretation = cell.getInterpretation();
        
        if (interpretation != null) {
            interpretation.accept(this);
        }
	}

    @Override
    public void visit(BooleanValue bv) {
        type = "BooleanValue";
        listeType.add("BooleanValue");
    }

    @Override
    public void visit(Conditional cndtnl) {
        type = "Conditional";
        listeType.add("Conditional");
    }

    @Override
    public void visit(DateValue dv) {
        type = "DateValue";
        listeType.add("DateValue");
    }

    @Override
    public void visit(Dimension dmnsn) {
        type = "Dimension";
        listeType.add("Dimension");
    }

    @Override
    public void visit(IntegerValue iv) {
        type = "Number";
        listeType.add("Number");
    }

    @Override
    public void visit(Multiple mltpl) {
        type = "Multiple";
        listeType.add("Multiple");
    }

    @Override
    public void visit(NotApplicable na) {
        type = "NotApplicable";
        listeType.add("NotApplicable");
    }

    @Override
    public void visit(NotAvailable na) {
        type = "NotAvailable";
        listeType.add("NotAvailable");
    }

    @Override
    public void visit(Partial prtl) {
        type = "Partial";
        listeType.add("Partial");
    }

    @Override
    public void visit(RealValue rv) {
        type = "Number";
        listeType.add("Number");
    }

    @Override
    public void visit(StringValue sv) {
        type = "StringValue";
        listeType.add("StringValue");
    }

    @Override
    public void visit(Unit unit) {
        type = "Unit";
        listeType.add("Unit");
    }

    @Override
    public void visit(Version vrsn) {
        type = "Version";
        listeType.add("Version");
    }
    
    /**
     * Calcule le nombre d'occurence d'une valeur dans une liste
     * @param liste
     * @param valeur
     * @return
     */
    private int getOccurrence(List<String> liste, String valeur){
    	int cpt = 0;
    	for(String valeurCourante: liste){
    		if(valeurCourante.equals(valeur)){
    			cpt++;
    		}
    	}
    	return cpt;
    }
    

}
