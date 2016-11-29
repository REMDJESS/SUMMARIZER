/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.io.File;
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
public class JsonExportTest {
    
    public JsonExportTest() {
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
     * Test of toJson method, of class JsonExport.
     */
    @Test
    public void testToJson() throws Exception {
        System.out.println("toJson");
        File pcmFile = null;
        JsonExport instance = new JsonExport();
        String expResult = "";
        String result = instance.toJson(pcmFile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of export method, of class JsonExport.
     */
    @Test
    public void testExport() throws Exception {
        System.out.println("export");
        File pcmFile = null;
        JsonExport instance = new JsonExport();
        instance.export(pcmFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
