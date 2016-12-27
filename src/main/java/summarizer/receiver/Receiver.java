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
		
	/**
	 * Choisit les produits pour lesquels les valeurs des cellules de certain features sont egaux ou compris 
	 * entre des valeurs prédéfini. Le choix des critères se fait sur un seul feature et selon un ordre de type
	 * données:
	 * booleans -> numbers -> stringValues -> multiples -> partials -> units 
	 * -> versions -> notApplicables -> dimensions -> conditionals -> notAvailables
	 */
	public void randomChoose(){
        Map<Feature, String> productsChoice = new HashMap<>();
        
        if(!chooseFeatureByType("booleans").isEmpty()){
        	Feature firstFeature = chooseFeatureByType("booleans").get(0);
            //Ajoute le premier feature et la valeur YES aux critères des produits à choisir
            productsChoice.put(firstFeature, "Yes");
        }
        else{
            if(!chooseFeatureByType("numbers").isEmpty()){
            	Feature firstFeature = chooseFeatureByType("numbers").get(0);
                FiltreVisitor filter = new FiltreVisitor();
                List<Cell> cellData = filter.filtreReduit(pcm).get(firstFeature.getName()).get("numbers");
                //Cast des valeur en float
            	List<Float> newListe = new ArrayList<>();
            	for(Cell cell: cellData){
            		newListe.add(Float.parseFloat(cell.getContent()));
            	}
            	//Calcul de la moyenne
            	float moyenne = (new MotifImpl()).moyenne(newListe);
                
                //Ajoute le premier feature et la valeur moyenne aux critères des produits à choisir
                productsChoice.put(firstFeature, Float.toString(moyenne));
            }
            else{
                if(!chooseFeatureByType("stringValues").isEmpty()){
                	Map<Feature, String> randomValues = stringTypeRandomChoose("stringValues");
                	
                	Feature firstFeature = null;
                	String mustRepresented = null;
                	for(Feature key: randomValues.keySet()){
                		firstFeature = key;
                		mustRepresented = randomValues.get(key);
                	}
                	
                    //Ajoute le premier feature et l'un des valeur les plus représentées aux critères des produits à choisir
                    productsChoice.put(firstFeature, mustRepresented);
                }
                else{
                    if(!chooseFeatureByType("multiples").isEmpty()){
                    	Map<Feature, String> randomValues = stringTypeRandomChoose("multiples");
                    	
                    	Feature firstFeature = null;
                    	String mustRepresented = null;
                    	for(Feature key: randomValues.keySet()){
                    		firstFeature = key;
                    		mustRepresented = randomValues.get(key);
                    	}
                    	
                        //Ajoute le premier feature et l'un des valeur les plus représentées aux critères des produits à choisir
                        productsChoice.put(firstFeature, mustRepresented);
                    }
                    else{
                        if(!chooseFeatureByType("conditionals").isEmpty()){
                        	Map<Feature, String> randomValues = stringTypeRandomChoose("conditionals");
                        	
                        	Feature firstFeature = null;
                        	String mustRepresented = null;
                        	for(Feature key: randomValues.keySet()){
                        		firstFeature = key;
                        		mustRepresented = randomValues.get(key);
                        	}
                        	
                            //Ajoute le premier feature et l'un des valeur les plus représentées aux critères des produits à choisir
                            productsChoice.put(firstFeature, mustRepresented);
                        }
                        else{
                            if(!chooseFeatureByType("dateValues").isEmpty()){
                            	Map<Feature, String> randomValues = stringTypeRandomChoose("dateValues");
                            	
                            	Feature firstFeature = null;
                            	String mustRepresented = null;
                            	for(Feature key: randomValues.keySet()){
                            		firstFeature = key;
                            		mustRepresented = randomValues.get(key);
                            	}
                            	
                                //Ajoute le premier feature et l'un des valeur les plus représentées aux critères des produits à choisir
                                productsChoice.put(firstFeature, mustRepresented);
                            }
                            else{
                                if(!chooseFeatureByType("dimensions").isEmpty()){
                                	Map<Feature, String> randomValues = stringTypeRandomChoose("dimensions");
                                	
                                	Feature firstFeature = null;
                                	String mustRepresented = null;
                                	for(Feature key: randomValues.keySet()){
                                		firstFeature = key;
                                		mustRepresented = randomValues.get(key);
                                	}
                                	
                                    //Ajoute le premier feature et l'un des valeur les plus représentées aux critères des produits à choisir
                                    productsChoice.put(firstFeature, mustRepresented);
                                }
                                else{
                                    if(!chooseFeatureByType("notApplicables").isEmpty()){
                                    	Map<Feature, String> randomValues = stringTypeRandomChoose("notApplicables");
                                    	
                                    	Feature firstFeature = null;
                                    	String mustRepresented = null;
                                    	for(Feature key: randomValues.keySet()){
                                    		firstFeature = key;
                                    		mustRepresented = randomValues.get(key);
                                    	}
                                    	
                                        //Ajoute le premier feature et l'un des valeur les plus représentées aux critères des produits à choisir
                                        productsChoice.put(firstFeature, mustRepresented);
                                    }
                                    else{
                                        if(!chooseFeatureByType("partials").isEmpty()){
                                        	Map<Feature, String> randomValues = stringTypeRandomChoose("partials");
                                        	
                                        	Feature firstFeature = null;
                                        	String mustRepresented = null;
                                        	for(Feature key: randomValues.keySet()){
                                        		firstFeature = key;
                                        		mustRepresented = randomValues.get(key);
                                        	}
                                        	
                                            //Ajoute le premier feature et l'un des valeur les plus représentées aux critères des produits à choisir
                                            productsChoice.put(firstFeature, mustRepresented);
                                        }
                                    }                                           
                                }                                  
                            }  
                        }  
                    }
                }
            }
        }
        
        reduceProduct(productsChoice);		
	}
	
	/**
	 * Retourne les features comprenant des cellules d'un type donnée
	 * 
	 * @param typeName Nom de l'interpretation/type souhaitée
	 * @return Liste des features correspondant
	 */
	private List<Feature> chooseFeatureByType(String typeName){
        FiltreVisitor filter = new FiltreVisitor();
        HashMap<String, HashMap<String, List<Cell>>> dataFiltered = filter.filtreReduit(pcm);
        List<Feature> listOfChoosedFeature = new ArrayList<>();
        
        for (Feature feature : pcm.getConcreteFeatures()) {
        	HashMap<String, List<Cell>> listeType = dataFiltered.get(feature.getName());
        	for(String key: listeType.keySet()){
	        	if(key.equals(typeName)){
	        		listOfChoosedFeature.add(feature);
	        	}
        	}
        }
		
		return listOfChoosedFeature;		
	}
	
	/**
	 * 
	 * @param type
	 */
	private Map<Feature, String> stringTypeRandomChoose(String type){
    	Feature firstFeature = chooseFeatureByType("stringValues").get(0);
    	Motif motif = new MotifImpl();
        FiltreVisitor filter = new FiltreVisitor();
        List<Cell> cellData = filter.filtreReduit(pcm).get(firstFeature.getName()).get("stringValues");
        //Cast des valeur en String
    	List<String> newListe = new ArrayList<>();
    	for(Cell cell: cellData){
    		newListe.add(cell.getContent());
    	}
        //Calcul des pourcentages d'apparition des valeurs des cellules
        Map<String, Float> pourcentages = motif.pourcentage(newListe);
        //Calcul du pourcentage maximal
        Float maxPourc = motif.max(pourcentages.values());
        
        //Recupère l'un des mots les plus representé
        String mustRepresented = "";
        for(String key: pourcentages.keySet()){
        	int comparateur = Float.compare(pourcentages.get(key), maxPourc);
        	if(comparateur == 0){
        		mustRepresented = key;
        	}
        }
        
        Map<Feature, String> retour = new HashMap<>();
        retour.put(firstFeature, mustRepresented);
        return retour;
	}
	
}
