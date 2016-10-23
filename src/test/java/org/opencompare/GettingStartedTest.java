 package org.opencompare;

import org.junit.Test;
import org.opencompare.api.java.*;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.CSVExporter;
import org.opencompare.api.java.io.HTMLExporter;
import org.opencompare.api.java.io.PCMLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by gbecan on 02/12/15.
 */
public class GettingStartedTest {

    @Test
    public void testGettingStarted() throws IOException {

        // Define a file representing a PCM to load
        File pcmFile = new File("pcms/example.pcm");

        // Create a loader that can handle the file format
        PCMLoader loader = new KMFJSONLoader();

        // Load the file
        // A loader may return multiple PCM containers depending on the input format
        // A PCM container encapsulates a PCM and its associated metadata
        List<PCMContainer> pcmContainers = loader.load(pcmFile);

        for (PCMContainer pcmContainer : pcmContainers) {

            // Get the PCM
            PCM pcm = pcmContainer.getPcm();

            // Browse the cells of the PCM
            for (Product product : pcm.getProducts()) {
                for (Feature feature : pcm.getConcreteFeatures()) {

                    // Find the cell corresponding to the current feature and product
                    Cell cell = product.findCell(feature);

                    // Get information contained in the cell
                    String content = cell.getContent();
                    String rawContent = cell.getRawContent();
                    Value interpretation = cell.getInterpretation();

                    // Print the content of the cell
                    System.out.println("(" + product.getKeyContent() + ", " + feature.getName() + ") = " + content + " ---------- (rawContent: " + rawContent + "/// interpretation: " + interpretation + ")");
                }
            }

            // Export the PCM container to CSV
            /*CSVExporter csvExporter = new CSVExporter();
            String csv = csvExporter.export(pcmContainer);

            // Write CSV content to file
            Path outputFile = Files.createTempFile("oc-", ".csv");
            Files.write(outputFile, csv.getBytes());
            System.out.println("PCM exported to " + outputFile);*/
            
            
            // Export the PCM container to HTML
            HTMLExporter htmlExporter = new HTMLExporter();
            String html = htmlExporter.export(pcmContainer);

            // Write HTML content to file
            Path outputFile = Files.createTempFile("oc-", ".html");
            Files.write(outputFile, html.getBytes());
            System.out.println("PCM exported to " + outputFile);
            
        }

    }
}
