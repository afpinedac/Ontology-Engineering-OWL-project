package Controllers;

import App.App;
import Helpers.Input;
import Helpers.Log;
import Managers.*;
import Model.OntologyModel;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import App.Config;

import java.io.File;
import java.util.ArrayList;

public class CourseOntology extends BaseOntology {

    boolean dataIsLoaded = false;
    EntityManager entityManager;
    AxiomManager axiomManager;
    DataPropertyManager dataPropertyManager;
    ObjectPropertyManager objectPropertyManager;
    ReasonerManager reasonerManager;
    OntologyModel ontologyModel;

    //ontology attributes

    public static OWLOntologyManager manager;
    public static OWLOntology ontology;
    public static OWLDataFactory factory;


    public CourseOntology(boolean doETL) throws Exception {

        manager = OWLManager.createOWLOntologyManager();
        ontology = manager.loadOntologyFromOntologyDocument(new File(Config.PROJECT_PATH + Config.ONTOLOGY_NAME));
        factory = manager.getOWLDataFactory();

    }


    private void createClassHierarchy() throws Exception {


    }


    private void createClassObjectProperties() {
        Log.message("to be implemented .. ");

    }

    private void createClassDataProperties() {
        Log.message("to be implemented .. ");

    }

    private void createIndividuals() throws Exception {
        EntityManager.createCourses();
    }


    private ArrayList<Integer> getIndividualsByClass(String individuals) {
        Log.message("to be implemented .. ");
        return new ArrayList<Integer>();
    }

    private void saveOntology() throws Exception {
        File file = new File(Config.PROJECT_PATH + Config.NEW_ONTOLOGY_NAME);
        System.out.println(file.toURI());
        manager.saveOntology(ontology, IRI.create(file.toURI()));
    }


    public void displayOntologyOptions() {
        Log.message("1. Load data from database");
        Log.message("2. Create class hierarchy");
        Log.message("3. Create ObjectProperties");
        Log.message("4. Create DataProperties");
        Log.message("5. Create Individuals");
        Log.message("6. Get individuals by class");
        Log.message("99. Save and create new Ontology file");
        Log.message("100. Exit");
    }


    private void loadDataFromDB() {
        Log.message("to be implemented .. ");
    }


    public boolean callToOption(int option) {
        try {

            switch (option) {

                case 1:
                    this.loadDataFromDB();
                    break;


                case 2:

                    this.createClassHierarchy();


                    break;

                case 3:
                    this.createClassObjectProperties();

                    break;

                case 4:
                    this.createClassDataProperties();
                    break;

                case 5:
                    this.createIndividuals();
                    this.saveOntology();

                    break;

                case 6:

                    Log.message("Enter the Class name");
                    this.getIndividualsByClass(Input.readLine());
                    break;

                case 99:
                    Log.message("Saving the ontology");
                    this.saveOntology();
                    break;

                case 100:
                    return false;


                default:

                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
