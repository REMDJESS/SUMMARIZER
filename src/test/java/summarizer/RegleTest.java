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

/**
 *
 * @author NOYAF-PC
 */
public class RegleTest {
    
    Collection domaine;
    Collection sousDomaine;
    Regle regle;
    
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
        domaine = new ArrayList();       
        //Insertion des entiers
        domaine.add(1);
        domaine.add(2);
        domaine.add(3);
        domaine.add(4);
        domaine.add(5);        
        //Insertion des chaines de caractère
        domaine.add("a");
        domaine.add("b");
        domaine.add("c");
        domaine.add("d");
        domaine.add("e");
        domaine.add("f");        
        //Insertion des boolean
        domaine.add(false);
        domaine.add(true);
        domaine.add(false);        
        //Insertion des notAvailable
        domaine.add("-");
        domaine.add("-");
        domaine.add("-");
        domaine.add("-");
        domaine.add("-");
        domaine.add("-"); 
        
        regle = new Regle(domaine);
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Effectue le test avec
     * 1 - Une collection constitué de string,
     * 2 - Une collection vide
     */
    @Test
    public void StringRuleTest(){
        sousDomaine = new ArrayList();
        sousDomaine.add("a");
        sousDomaine.add("b");
        sousDomaine.add("c");
        sousDomaine.add("d");
        sousDomaine.add("e");
        sousDomaine.add("f");
        
        StringBuilder builder = new StringBuilder();
        builder.append("{");
            builder.append("\"type\": \"String\",");
            builder.append("\"porcentageType\": ").append(33.3333).append(",");
            builder.append("\"resume\": {");
                builder.append("\"pourcentage\": {");
                    builder.append("\"a\": ").append(16.666668).append(",");
                    builder.append("\"b\": ").append(16.666668).append(",");
                    builder.append("\"c\": ").append(16.666668).append(",");
                    builder.append("\"d\": ").append(16.666668).append(",");
                    builder.append("\"e\": ").append(16.666668).append(",");
                    builder.append("\"f\": ").append(16.666668).append("");
                builder.append("}");
            builder.append("}");
        builder.append("},");        
        assertEquals("Sous domaine de chaine de caractère", builder.toString(), regle.StringRule(sousDomaine, "String"));
        
        //----------------------------------------------------------------------
        
        sousDomaine = new ArrayList();
        builder = new StringBuilder();
        assertEquals("Sous domaine vide", builder.toString(), regle.StringRule(sousDomaine, "String"));
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
