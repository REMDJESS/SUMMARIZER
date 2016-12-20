# Getting SUMMARIZER

OpenCompare is a free software allowing the exploitation of the matrices of comparison coming from the web (Wikipedia, Best Buy ...).  This publisher is a project of the IRISA of the University of Rennes1.
SUMMARIZER is a module of the Open Compare project of the IRISA laboratory of the University of Rennes 1. It allows to generate a summary from a PCM matrix entered by the user.

## Definition of abstract generation rules
We will generate a summary making a comparison on the different features.  The features are grouped into four types of value:  Booleans, String, Not available and Numbers.
We will classify the features by hierarchies from the least relevant to the most relevant
-	Features composed of several types of interpretations (non-coherent features)
-	Features composed only of the NotAvailable type and of another type (pseudo-coherent features).
-	Features composed of a single type (coherent features).
The reliable features to remember for the overall summary are the consistent and inconsistent features.

## Built With
1-Maven - A build automation tool used primarily for Java projects
2-JUnit - An open source framework designed for the purpose of writing and running tests in the Java programming language.
3-QUnit - A powerful JavaScript unit testing framework that helps you to debug code.
3-JavaScript - A high-level, dynamic, object-oriented scripting language, commonly known as the scripting language of web pages.
4-Bootstrap - A free and open-source front-end web framework for designing websites and web applications
5-HTML (the Hypertext Markup Language) and CSS (Cascading Style Sheets): Two of the core technologies for building Web pages.
6-GitHub - A Web hosting and management software development service, using Git version management software.

## The architecture of the project
The project includes two packages: the summarizer package and the HMI package
The package summarizer contains the java procedure for generating the summary in JS format.  It integrates Visitor and Command design patterns.
The HTML is the package that processes the summary display in HTML format.
Tests were performed for both treatments in Java with JUnit and JS with QUnit.

### Using the API
```java
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
    System.out.println("(" + product.getName() + ", " + feature.getName() + ") = " + content);
  }
}
```
### Using a visitor
See src/test/java/org.opencompare/VisitorTest.java

## Export
If we want to export or serialize the PCM to a specific format, we can use a PCMExporter.
In this example, we export our PCM to a CSV file.

```java
CSVExporter csvExporter = new CSVExporter();
String csv = csvExporter.export(pcmContainer);
```

## Import 

If we want to import a PCM from a CSV file, we can use a CSVLoader.

```java
CSVLoader csvL = new CSVLoader(
                new PCMFactoryImpl(),
                new CellContentInterpreter(new PCMFactoryImpl()));
List<PCMContainer> pcms = csvL.load(new File("pcms/pokemon.csv"));
...
```


