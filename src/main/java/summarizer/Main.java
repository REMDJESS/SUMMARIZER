/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer;

import java.io.IOException;

import summarizer.command.DefinePCMCommand;
import summarizer.command.ResumerCommand;
import summarizer.command.TrierAleatoirementFeatureCommand;
import summarizer.command.TrierFeatureCommand;
import summarizer.command.TrierProduitCommand;
import summarizer.invoker.IHM;
import summarizer.receiver.Receiver;

/**
 *
 * @author NOYAF-PC
 */
public class Main {

    public static void main(String[] args) throws IOException {
    	IHM ihm = new IHM();
    	Receiver receiver = new Receiver();
    	
    	ihm.addCommand("definePCM", new DefinePCMCommand(receiver, ihm));
    	ihm.addCommand("trierFeature", new TrierFeatureCommand(receiver, ihm));
    	ihm.addCommand("trierProduit", new TrierProduitCommand(receiver, ihm));
    	ihm.addCommand("randomFeatureChoose", new TrierAleatoirementFeatureCommand(receiver));
    	ihm.addCommand("jsonResume", new ResumerCommand(receiver));
    	
    	ihm.setPCMFile();
    	ihm.chooseFeatures(receiver.getPCM());
    	ihm.chooseProducts(receiver.getPCM());
    	ihm.randomFeatureChoose();
    	//ihm.jsonResume();
    }        
}
