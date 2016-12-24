/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

import static org.junit.Assert.*;

import summarizer.receiver.FiltreVisitor;
import summarizer.receiver.Regle;

/**
 *
 * @author AVF
 */
public class RegleTest {
	
	static HashMap<String, HashMap<String, List<Cell>>> dataFiltred;
	Regle myRegle = new Regle();
	
    public RegleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        // Définition du chemin d'accès au fichier pcm à manipuler
        File pcmFile = new File("pcms/model/Comparison_of_Macintosh_models_1.pcm");
        
        List<PCMContainer> pcmContainers = (new KMFJSONLoader()).load(pcmFile);        
        PCM pcm = pcmContainers.get(0).getPcm(); //Récuperation du PCM
        dataFiltred = (new FiltreVisitor()).filtreReduit(pcm); //Recuperation de l'ensemble des données de test
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of StringRule method, of class Regle.
     */
    @Test
    public void testStringRule() {
    	           
    	//Recupération des données de test
    	HashMap<String, List<Cell>> dataString = dataFiltred.get("Model");
    	List<Cell> mySousDomaine = dataString.get("stringValues");

        String nomMySousDomaine = "stringValues";
        float tailleDomaine = 15.0F;
        
        /************** chargement du résultat attendu ****************/     
        StringBuilder builder = new StringBuilder();
            builder.append("{\"type\": \"stringValues\",");
            builder.append("\"pourcentageType\": 100.0,");
            builder.append("\"resume\": {");                
            builder.append("\"pourcentage\": {");
            builder.append("\"Mac Pro Quad-Core\": 6.666667,");
            builder.append("\"iMac 21.5 in low-end\": 6.666667,");
            builder.append("\"MacBook Air 1.7Â GHz\": 6.666667,");
            builder.append("\"Mac mini Server\": 6.666667,");
            builder.append("\"MacBook Pro 15 in 2.0Â GHz\": 6.666667,");
            builder.append("\"Mac Pro 12-Core\": 6.666667,");
            builder.append("\"MacBook Pro 13 in 2.3Â GHz\": 6.666667,");
            builder.append("\"MacBook Air 1.6Â GHz\": 6.666667,");
            builder.append("\"iMac 27 in high-end\": 6.666667,");
            builder.append("\"iMac 27 in low-end\": 6.666667,");
            builder.append("\"Mac mini\": 6.666667,");
            builder.append("\"MacBook Pro 15 in 2.2Â GHz\": 6.666667,");
            builder.append("\"MacBook Pro 13 in 2.7Â GHz\": 6.666667,");
            builder.append("\"iMac 21.5 in high-end\": 6.666667,");
            builder.append("\"Mac Pro 8-Core\": 6.666667");
            builder.append("}");            
            builder.append("}");
            builder.append("},");
        String expResult = builder.toString();
        String result = myRegle.StringRule(mySousDomaine, nomMySousDomaine, tailleDomaine);
        
        assertEquals("String rule", expResult, result);
    }

    /**
     * Test of numberRule method, of class Regle.
     */
    @Test
    public void testNumberRule() {
        
    	//Recupération des données de test
    	HashMap<String, List<Cell>> dataString = dataFiltred.get("USB");
    	List<Cell> mySousDomaine = dataString.get("numbers");
    
        String nomMySousDomaine = "numbers";
        float tailleDomaine = 15.0f;
        
        /************** chargement du résultat attendu **********************/     
        StringBuilder builder = new StringBuilder();
            builder.append("{\"type\": \"numbers\",");
            builder.append("\"pourcentageType\": 100.0,");
            builder.append("\"resume\": {");                
            builder.append("\"moyenne\": 3.4,");
            builder.append("\"max\": 5.0,");
            builder.append("\"productWithMax\": [\"Mac Pro Quad-Core\",\"Mac Pro 8-Core\",\"Mac Pro 12-Core\"],");
            builder.append("\"min\": 2.0,");
            builder.append("\"productWithMin\": [\"MacBook Air 1.7Â GHz\",\"MacBook Pro 15 in 2.0Â GHz\",\"MacBook Pro 15 in 2.2Â GHz\",\"MacBook Pro 13 in 2.3Â GHz\",\"MacBook Pro 13 in 2.7Â GHz\",\"MacBook Air 1.6Â GHz\"],");
            builder.append("\"ecartType\": 0.039999995");
            builder.append("}");            
            builder.append("},");
        String expResult = builder.toString();

        String result = myRegle.numberRule(mySousDomaine, nomMySousDomaine, tailleDomaine);
        assertEquals("Numbers rule", expResult, result);
        
        
    }

    /**
     * Test of booleanRule method, of class Regle.
     */
    @Test
    public void testBooleanRule() {
        
    	//Recupération des données de test
    	HashMap<String, List<Cell>> dataString = dataFiltred.get("AirPort Extreme");
    	List<Cell> mySousDomaine = dataString.get("booleans");
    
        String nomMySousDomaine = "booleans";
        float tailleDomaine = 15.0f;

        /************** chargement du résultat attendu **********************/
        StringBuilder builder = new StringBuilder();
            builder.append("{\"type\": \"booleans\",");
            builder.append("\"pourcentageType\": 100.0,");
            builder.append("\"resume\": {");
            builder.append("\"pourcentage\": ");
            builder.append("{\"Yes\": 100.0}");
            builder.append("}");
            builder.append("},");
        String expResult = builder.toString();
 
        String result = myRegle.booleanRule(mySousDomaine, nomMySousDomaine, tailleDomaine);
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of notApplicableRule method, of class Regle.
     */
    @Test
    public void testNotApplicableRule() {
        
        
	    System.out.println("notApplicableRule");
 
        
    } 
    
}
