package summarizer.receiver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencompare.api.java.AbstractFeature;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

public class Receiver {
	
	PCM pcm;
	
	public Receiver(){
		this.pcm = null;
	}
	
	/**
	 * 
	 * @return pcm
	 */
	public PCM getPCM(){
		return pcm;
	}
	
	/**
	 * Définit le pcm à partir du fichier de pcm
	 * 
	 * @param pcmFile
	 * @throws IOException
	 */
	public void setPCM(File pcmFile) throws IOException{
        PCMLoader loader = new KMFJSONLoader();
        List<PCMContainer> pcmContainers = loader.load(pcmFile);        
        this.pcm = pcmContainers.get(0).getPcm();	
	}
	
	/**
	 * Reduit le pcm en y supprimant les feature pas choisis par l'utilisateur
	 * 
	 * @param featuresChooseList Liste des features retenus
	 */
	public void reduceFeature(List<Integer> featuresChooseList){
        HashMap<Integer, Feature> listeFeatures = new HashMap<>();
        int featCpt = 0;
        for (Feature feature : pcm.getConcreteFeatures()) {
        	featCpt++;
        	listeFeatures.put(featCpt, feature);
        }
                
        if(featuresChooseList.size() > 0){
            //Supprimer les features choisis par l'utilisateur de la liste des features
            for(int i: featuresChooseList){
            	listeFeatures.remove(i);
            }
            
            //Supprimer les features restant sur la liste du pcm
            for(int key: listeFeatures.keySet()){
            	pcm.removeFeature((AbstractFeature) listeFeatures.get(key));
            }
        }		
	}
	
	/**
	 * Reduit le pcm en y supprimant les feature de manière aléatoire
	 * 
	 * @param featuresChooseList Liste des features retenus
	 */
	public void randomReduceFeature(){
        List<Integer> featuresList = new ArrayList<>();
        
        FiltreVisitor filter = new FiltreVisitor();
        HashMap<String, HashMap<String, List<Cell>>> dataFiltered = filter.filtreReduit(pcm);
        
        int featCpt = 0;
        for (Feature feature : pcm.getConcreteFeatures()) {
        	featCpt++;
        	
        	HashMap<String, List<Cell>> listeType = dataFiltered.get(feature.getName());
        	for(String key: listeType.keySet()){
	        	if(key.equals("booleans")){
		        	featuresList.add(featCpt);
	        	}
        	}
        	
        	

        	
        }
		
		reduceFeature(featuresList);		
	}	
	/**
	 * Reduit la liste des produits du PCM sur la base du choix des valeurs
	 * particulières de certains features
	 * 
	 *  @param choix Liste des features et leurs valeurs particulières
	 */
	public void reduceProduct(Map<Feature, String> choix){
		List<Product> productsListe = pcm.getProducts();
		for(Product produit: productsListe){
			Boolean test = true;
			for(Feature feature: choix.keySet()){
				Cell cellule = produit.findCell(feature);
				if(choix.get(feature).equals(cellule.getContent())){
					test = test && true;
				}
				else{
					test = test && false;
				}
			}
			
			if(!test){
				pcm.removeProduct(produit);
			}
		}
		////////////////////////////
//		System.out.println("/*************  Apres ***********/");
//		System.out.println(pcm);
		//////////////////////////
	}
	
	/**
	 * Export le PCM au format JSON
	 * @throws IOException 
	 */
	public void jsonExport() throws IOException{
		////////////////////////////
//		System.out.println("/*************  Avant export ***********/");
//		System.out.println(pcm);
		//////////////////////////
		
        //Filtre
        FiltreVisitor filter = new FiltreVisitor();
        HashMap<String, HashMap<String, List<Cell>>> dataFiltered = filter.filtreReduit(pcm);
        
        // Export
        File resumeFile = new File("src/main/java/IHM/public_html/json/summarizer.js"); //fichier cible
        JsonExport exporter = new JsonExport();
        exporter.export(dataFiltered, resumeFile);		
	}
}
