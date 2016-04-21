package Controllers;

import App.App;
import Helpers.Input;
import Helpers.Log;
import Managers.*;
import Model.OntologyModel;

import java.util.ArrayList;

public class CourseOntology extends BaseOntology {

    boolean dataIsLoaded = false;
    EntityManager entityManager;
    AxiomManager axiomManager;
    DataPropertyManager dataPropertyManager;
    ObjectPropertyManager objectPropertyManager;
    ReasonerManager reasonerManager;
    OntologyModel ontologyModel;

    public CourseOntology(boolean doETL){

    }


    private void createClassHierarchy(){

        Log.message("to be implemented .. ");

    }


    private void createClassObjectProperties(){
        Log.message("to be implemented .. ");

    }

    private void createClassDataProperties(){
        Log.message("to be implemented .. ");

    }

    private void createIndividuals(){
        Log.message("to be implemented .. ");
    }



    private ArrayList<Integer> getIndividualsByClass(String individuals){
        Log.message("to be implemented .. ");
        return new ArrayList<Integer>();
    }



    public void displayOntologyOptions(){
        Log.message("1. Load data from database");
        Log.message("2. Create classes");
        Log.message("3. Create ObjectProperties");
        Log.message("4. Create DataProperties");
        Log.message("5. Create Individuals");
        Log.message("6. Get individuals by class");
        Log.message("100. Exit");
    }


    private void loadDataFromDB(){
        Log.message("to be implemented .. ");
    }


    public boolean callToOption(int option) {


        switch(option){

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
                break;

            case 6:

                Log.message("Enter the Class name");
                this.getIndividualsByClass(Input.readLine());
                break;

            case 100:
                return false;


            default:

                break;

        }

        return true;
    }

}
