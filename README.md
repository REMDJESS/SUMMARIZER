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
4-JavaScript - A high-level, dynamic, object-oriented scripting language, commonly known as the scripting language of web pages.
5-Bootstrap - A free and open-source front-end web framework for designing websites and web applications
6-HTML (the Hypertext Markup Language) and CSS (Cascading Style Sheets): Two of the core technologies for building Web pages.
7-GitHub - A Web hosting and management software development service, using Git version management software.

## The architecture of the project
The project includes two packages: the summarizer package and the HMI package
The package summarizer contains the java procedure for generating the summary in JS format.  It integrates Visitor and Command design patterns.
The HTML is the package that processes the summary display in HTML format.
Tests were performed for both treatments in Java with JUnit and JS with QUnit.

### Using the API
```java
Scanning for projects...
                                                                        
------------------------------------------------------------------------
Building getting-started 0.7
------------------------------------------------------------------------

--- exec-maven-plugin:1.2.1:exec (default-cli) @ getting-started ---
Veuillez saisir le chemin d'acces a votre fichier (ex: pcms/example.pcm) : 
pcms/example.pcm
Avez-vous des choix de features particuliers ? (O/N) 
O
Liste des features
1) Viewfinder magnification
2) Weight (g)
3) Focus points
4) LCD monitor
5) ISO max (expanded)
6) ISO max
7) GPS
8) Image processor
9) Sensor type
10) Top continuous shooting speed (frames/sec)
11) Sensor format
12) ISO min (expanded)
13) Viewfinder coverage
14) Live view
15) Megapixel
16) Dimensions (mm)
17) Metering pixels
18) Sensor manufacturer
19) Storage media
20) Model
21) Wifi
22) ISO min
23) Movie mode
24) Release Date
Veuillez saisir le numero du feature choisie puis validez pour en choisir un autre.
Terminer en saisissant une lettre.
1
2
3
4
5
8
9
10
n
Voulez vous une valeur de features particulières ? (O/N) 
o
Liste des features
1) Viewfinder magnification
2) Weight (g)
3) Focus points
4) LCD monitor
5) ISO max (expanded)
6) Image processor
7) Sensor type
8) Top continuous shooting speed (frames/sec)
Veuillez saisir le numero du feature choisie puis validez.
1
Veuillez saisir votre valeur puis validez.
Sony
Resumé généré !
Vous pourrez le visualiser en ouvrant le fichier à l'emplacement suivant:
src/main/java/IHM/public_html/index.html
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


