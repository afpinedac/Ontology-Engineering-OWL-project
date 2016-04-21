package Controllers;

/**
 * Created by afpinedac on 4/21/16.
 */
public abstract  class BaseOntology {

    public String ontologyName;
    public String version;


    public abstract void displayOntologyOptions();
    public  abstract boolean callToOption(int option) ;


}
