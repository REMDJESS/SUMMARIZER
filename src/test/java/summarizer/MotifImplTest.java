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
//import java.util.Date;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 *
 * @author usuario
 */
public class MotifImplTest {
  
    //Collection Collect;
    //Collection sousCollect;
    //Regle regle;
    
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
       // Collection<Float> CollectInt = new ArrayList<>(Arrays.asList(1,2,3,8,14,25,44));
       // ArrayList<String> collectString = new ArrayList<>(Arrays.asList("hp","ibm","acer","ibm","hp"));
      //  Collection<Float> collectFloat;
       // collectFloat = new ArrayList<>(Arrays.asList(1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f));
        
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of moyenne method, of class MotifImpl.
     */
    @Test
    public void testMoyenne() {
        Collection<Float> collectFloat;
        collectFloat = new ArrayList<>(Arrays.asList(10.0f,10.0f,10.0f,10.0f,10.0f,10.0f,10.0f));
    

        System.out.println("MotifImpl.moyenne :");

        MotifImpl myMotifImpl = new MotifImpl();
        float expResult = 10.0F;
        float result = myMotifImpl.moyenne(collectFloat);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of pourcentage method, of class MotifImpl.
     */
    
    @Test
    public void testPourcentage() {
        Collection<String> collectPourcentage;
        collectPourcentage = new ArrayList<>(Arrays.asList("primero","segundo","tercero","primero","segundo","primero"));
        HashMap<String, Float> expResult = new HashMap<>();
              expResult.put("primero", 50.0f);
              expResult.put("segundo", 33.333336f);
              expResult.put("tercero", 16.666668f);
     
                
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
        
        Collection<Float> collectFloat;
        collectFloat = new ArrayList<>(Arrays.asList(1.0f,2.4f,3.8f,8.0f,14.1f,25.9f,44.0f));
        
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
        Collection<Float> collectFloat;
        collectFloat = new ArrayList<>(Arrays.asList(1.0f,2.4f,3.8f,8.0f,14.1f,25.9f,44.0f));
        
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
        
        Collection<Float> collectFloat;
        collectFloat = new ArrayList<>(Arrays.asList(1.0f,2.4f,3.8f,8.0f,14.1f,25.9f,444.0f));
        
        System.out.println("MotifImpl.ecartType :");
        MotifImpl myMotifImpl = new MotifImpl();
        float expResult = 53.241f;
        float result = myMotifImpl.ecartType(collectFloat);
        assertEquals(expResult, result, 0.05);
       
        
    }
    
    
    
}
