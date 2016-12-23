/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import summarizer.receiver.Regle;

/**
 *
 * @author AVF
 */
public class RegleTest {
    
    public RegleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
        
        Collection<String> mySousDomaine = new ArrayList<>();
            mySousDomaine.add("LaPaz");
            mySousDomaine.add("Santiago");
            mySousDomaine.add("Quito");
            mySousDomaine.add("Lima");
           
        
        System.out.println("StringRule");

        String nomMySousDomaine = "mySousDomaine";
        float tailleDomaine = 4.0F;
        
        Regle myRegle = new Regle();
        
    /************** chargement du résultat attendu **********************/     
        StringBuilder builder = new StringBuilder();
            builder.append("{\"type\": \"mySousDomaine\",");
            builder.append("\"porcentageType\": 100.0,");
            builder.append("\"resume\": {");                
            builder.append("\"pourcentage\": {");
            builder.append("\"Santiago\": 25.0,");
            builder.append("\"Lima\": 25.0,");
            builder.append("\"LaPaz\": 25.0,");
            builder.append("\"Quito\": 25.0");
            builder.append("}");            
            builder.append("}");
            builder.append("},");
        String expResult = builder.toString();
        
        String result = myRegle.StringRule(mySousDomaine, nomMySousDomaine, tailleDomaine);
        
        assertEquals(expResult, result);
        
       
    }

    /**
     * Test of numberRule method, of class Regle.
     */
    @Test
    public void testNumberRule() {
        
        System.out.println("testNumberRule");
        
        Collection<Float> mySousDomaine = new ArrayList<>();
                mySousDomaine.add(12.5f);
                mySousDomaine.add(18.20f);
                mySousDomaine.add(40.50f);
                mySousDomaine.add(444.25F);
    
        String nomMySousDomaine = "mySousDomaine";
        float tailleDomaine = 4.0f;

        Regle myRegle = new Regle();
    
        /************** chargement du résultat attendu **********************/     
        StringBuilder builder = new StringBuilder();
            builder.append("{\"type\": \"mySousDomaine\",");
            builder.append("\"porcentageType\": 100.0,");
            builder.append("\"resume\": {");                
            builder.append("\"moyenne\": 128.8625,");
            builder.append("\"max\": 444.25,");
            builder.append("\"min\": 12.5,");
            builder.append("\"ecartType\": 78.84688");
            builder.append("}");            
            builder.append("},");
        String expResult = builder.toString();

        String result = myRegle.numberRule(mySousDomaine, nomMySousDomaine, tailleDomaine);
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of booleanRule method, of class Regle.
     */
    @Test
    public void testBooleanRule() {
        
       System.out.println("booleanRule");
       
        Collection<Boolean> mySousDomaine = new ArrayList<>();
                mySousDomaine.add(true);
                mySousDomaine.add(false);
                mySousDomaine.add(true);
                mySousDomaine.add(false);
    
        String nomMySousDomaine = "mySousDomaine";
        float tailleDomaine = 4.0f;

        Regle myRegle = new Regle();
    
        /************** chargement du résultat attendu **********************/
        StringBuilder builder = new StringBuilder();
            builder.append("{\"type\": \"mySousDomaine\",");
            builder.append("\"porcentageType\": 100.0");
            builder.append("\"resume\": {");
            builder.append("\"pourcentage\": ");
            builder.append("{}");
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
      
     Collection<String> mySousDomaine = new ArrayList<>();
                mySousDomaine.add("-");
                mySousDomaine.add("_");
                mySousDomaine.add("");
    
        String nomMySousDomaine = "mySousDomaine";
        float tailleDomaine = 4.0f;

        Regle myRegle = new Regle();
    
        /************** chargement du résultat attendu **********************/
        StringBuilder builder = new StringBuilder();
            builder.append("{\"type\": \"mySousDomaine\",");
            builder.append("\"porcentageType\": 75.0");
            builder.append("},");
        String expResult = builder.toString();
    
        String result = myRegle.notApplicableRule(mySousDomaine, nomMySousDomaine, tailleDomaine);
        assertEquals(expResult, result);
        
       
        
    } 
    
}
