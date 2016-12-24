/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizer.receiver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NOYAF-PC
 */
public class FileTest {
    List<File> fichiers;
    File dossier;
    
    public FileTest(File dossier) {
        fichiers = new ArrayList<>();
        getAllFile(dossier);
    }
    
    public void getAllFile(File file){
        if(file.exists()){
            if(file.isFile()){
                fichiers.add(file);
            } else if(file.isDirectory()){
                File[] tabTmp = file.listFiles();
                for(File f : tabTmp){
                    getAllFile(f);
                }
            }
        }
    }
    
    public ArrayList<String> getFilesNames(){
        ArrayList<String> list = new ArrayList<>();
        for(File f: fichiers){
            list.add(f.getName());
        }
        return list;
    }
    
    @Override
    public String toString(){
        String s ="";
        for(File f: fichiers){
            s += f.getName() + "\n";
        }
        return s;
    }
    
    public static void main(String args[]){
        FileTest testFile = new FileTest(new File("pcms/"));
        System.out.println(testFile);
    }
    
    
}
