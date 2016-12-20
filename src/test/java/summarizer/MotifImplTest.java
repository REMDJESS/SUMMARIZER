/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 *
 * @author AVF
 */
public class MotifImplTest {
  
    
    
    
    public MotifImplTest() {
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
     * Test of moyenne method, of class MotifImpl.
     */
    @Test
    public void testMoyenne() {
        Collection<Float> collectFloat = new ArrayList<>();
            collectFloat.add(12.5f);
            collectFloat.add(15.25f);
            collectFloat.add(19.10f);
            collectFloat.add(21.8f);
            collectFloat.add(14.33f);
            collectFloat.add(44.1f);
            collectFloat.add(17.0f);
       
        System.out.println("MotifImpl.moyenne :");

        MotifImpl myMotifImpl = new MotifImpl();
        float expResult = 20.5828F;
        float result = myMotifImpl.moyenne(collectFloat);
        assertEquals(expResult, result, 0.009);
        
    }

    /**
     * Test of pourcentage method, of class MotifImpl.
     */
    
    @Test
    public void testPourcentage() {
        //Collection<String> collectPourcentage = new ArrayList<>(Arrays.asList("Lima","Santiago","Quito","Lima","Santiago","Lima"));
        
        Collection<String> collectPourcentage = new ArrayList<>();
            collectPourcentage.add("Lima");
            collectPourcentage.add("Santiago");
            collectPourcentage.add("Quito");
            collectPourcentage.add("Lima");
            collectPourcentage.add("Santiago");
            collectPourcentage.add("Lima");
        
        HashMap<String, Float> expResult = new HashMap<>();
            expResult.put("Lima", 50.0f);
            expResult.put("Santiago", 33.333336f);
            expResult.put("Quito", 16.666668f);
                   
        System.out.println("MotifImpl.pourcentage :");

        MotifImpl myMotifImpl = new MotifImpl();
        Map<String, Float> result = myMotifImpl.pourcentage(collectPourcentage);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of max method, of class MotifImpl.
     */
    @Test
    public void testMax() {
               
        Collection<Float> collectFloat = new ArrayList<>();
            collectFloat.add(1.0f);
            collectFloat.add(2.4f);
            collectFloat.add(3.8f);
            collectFloat.add(8.0f);
            collectFloat.add(14.1f);
            collectFloat.add(25.9f);
            collectFloat.add(44.0f);
        
        System.out.println("MotifImpl.max : ");

        MotifImpl myMotifImpl = new MotifImpl();
        float expResult = 44.0F;
        float result = myMotifImpl.max(collectFloat);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of min method, of class MotifImpl.
     */
    @Test
    public void testMin() {
        Collection<Float> collectFloat = new ArrayList<>();
            collectFloat.add(1.0f);
            collectFloat.add(2.4f);
            collectFloat.add(3.8f);
            collectFloat.add(8.0f);
            collectFloat.add(14.1f);
            collectFloat.add(25.9f);
            collectFloat.add(44.0f);
        
        System.out.println("MotifImpl.min : ");
 
        MotifImpl myData = new MotifImpl();
        float expResult = 1.0F;
        float result = myData.min(collectFloat);
        assertEquals(expResult, result, 0.0);
        
        
    }

    /**
     * Test of ecartType method, of class MotifImpl.
     */
    @Test
    public void testEcartType() {
        
        Collection<Float> collectFloat = new ArrayList<>();
            collectFloat.add(1.0f);
            collectFloat.add(2.4f);
            collectFloat.add(3.8f);
            collectFloat.add(8.0f);
            collectFloat.add(14.1f);
            collectFloat.add(25.9f);
            collectFloat.add(444.0f);
        
        System.out.println("MotifImpl.ecartType :");
        MotifImpl myMotifImpl = new MotifImpl();
        float expResult = 53.241f;
        float result = myMotifImpl.ecartType(collectFloat);
        assertEquals(expResult, result, 0.05);
       
        
    }
    
    
    
}
