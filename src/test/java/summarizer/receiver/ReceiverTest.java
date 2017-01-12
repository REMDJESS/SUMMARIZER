/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer.receiver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.impl.io.KMFJSONLoader;

import summarizer.Operateur;

/**
 *
 * @author usuario
 */
public class ReceiverTest {
    
    PCM myPcm;
    File pcmFile;
    
    
    public ReceiverTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        
        //  Définition du chemin d'accès au fichier pcm à manipuler
        File pcmFile = new File("pcms/model/Comparison_of_Macintosh_models_1.pcm");
        List<PCMContainer> pcmContainers = (new KMFJSONLoader()).load(pcmFile);        
        PCM myPcm = pcmContainers.get(0).getPcm(); //Récuperation du PCM
       
       
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
     * Test of getPCM method, of class Receiver.
     */
    @Test
    public void testGetPCM() {
        Receiver myReceiver = new Receiver();
        System.out.println("getPCM");
        
       // Receiver instance = new Receiver();
        PCM expResult = myPcm;
        PCM result = myReceiver.getPCM();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of setPCM method, of class Receiver.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetPCM() throws Exception {
        Receiver myReceiver = new Receiver();
        System.out.println("setPCM");
        
        File newPcmFile = new File("pcms/model/Comparison_(grammar)_0.pcm");
        List<PCMContainer> pcmContains = (new KMFJSONLoader()).load(newPcmFile);        
        PCM myNewPcm = pcmContains.get(0).getPcm();

        myReceiver.setPCM(newPcmFile);
        
        PCM result = myReceiver.getPCM();
        PCM expResult = myNewPcm;
        
        assertEquals(expResult, result);
        
    }

    /**
     * Test of reduceFeature method, of class Receiver.
     * @throws java.io.IOException
     */
    
    @Test
    public void testReduceFeature() throws Exception {
        Receiver myReceiver = new Receiver();
        System.out.println("reduceFeature");
        
        ArrayList<Integer> featuresChooseList = new ArrayList<>();
               featuresChooseList.add(1);
          //     featuresChooseList.add(2);
               
              
        File newPcmFile = new File("pcms/model/Comparison_(grammar)_0.pcm");
        List<PCMContainer> pcmContains = (new KMFJSONLoader()).load(newPcmFile);        
        PCM myNewPcm = pcmContains.get(0).getPcm();

        
        myReceiver.setPCM(newPcmFile);
        myReceiver.reduceFeature(featuresChooseList);
        
        
        PCM result = myReceiver.getPCM();
        PCM expResult = myNewPcm;
        
        assertEquals(expResult, result);
        
    }

    
    /**
     * Test of reduceProduct method, of class Receiver.
     */
    
    @Test
    public void testReduceProduct() throws Exception {
        Receiver myReceiver = new Receiver();
        System.out.println("reduceProduct");
        Map<Feature, String> choix = new HashMap<>();
        
        
        File newPcmFile = new File("pcms/model/Comparison_of_Macintosh_models_1.pcm");
        List<PCMContainer> pcmContains = (new KMFJSONLoader()).load(newPcmFile);        
        PCM myNewPcm = pcmContains.get(0).getPcm();
        Feature feat = pcmContains.get(0).getPcm().getConcreteFeatures().get(7);
        choix.put(feat, "7");
         
        /***************  résultat attendu  *******************/
        List<Product> productsListe = myNewPcm.getProducts();             
        for(Product produit: productsListe){
            Cell cellule = produit.findCell(feat);
            if(!feat.equals(cellule.getContent())){
                   myNewPcm.removeProduct(produit);
            }
        }
        PCM expResult = myNewPcm;
        
        /*********** résultat ******************/
        myReceiver.setPCM(newPcmFile);
        myReceiver.reduceProduct(choix, Operateur.EQUAL);
        PCM result = myReceiver.getPCM();
          
        assertEquals(expResult, result);
        
       
    }


    
}
