/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;
import summarizer.receiver.FiltreVisitor;

/**
 *
 * @author NOYAF-PC
 */
public class FiltreVisitorTest {
    
    FiltreVisitor fv;
    PCM pcm;
    
    public FiltreVisitorTest() throws IOException {
        // Définition du chemin d'accès au fichier pcm à manipuler
        File pcmFile = new File("pcms/example.pcm");
        //File pcmFile = new File("pcms/model/Comparison_of_Macintosh_models_1.pcm");
        
        PCMLoader loader = new KMFJSONLoader();
        List<PCMContainer> pcmContainers = loader.load(pcmFile);        
        this.pcm = pcmContainers.get(0).getPcm(); //Récupère le PCM
        
        this.fv = new FiltreVisitor();
    }
    

    @Test
    public void printTest() {
        fv.print(pcm);
    }
}
