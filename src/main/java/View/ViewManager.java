package View;


import Controllers.BaseOntology;
import Helpers.Input;
import Helpers.Log;

public class ViewManager {


     BaseOntology ontology;



    public  void setOntology(BaseOntology ontology){
        this.ontology = ontology;
    }

    public void displayOntologyOptions(){

        boolean display = true;
        while(display){
            Log.message("Enter the option:");
            this.ontology.displayOntologyOptions();
            display = this.ontology.callToOption(Input.readInt());
            Log.message("========================================0");
        }

    }





}
