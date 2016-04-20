import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.*;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;

import java.io.File;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static String PROJECT_PATH  ="/Users/afpinedac/Dropbox/Universidad/Doctorado/1 semestre/pIW/";
    static String ONTOLOGY_NAME  ="ontologia_familia.owl";
    static String NEW_ONTOLOGY_NAME  ="new_ontologia_familia.owl";

    public static void main(String[] args) {

        try {

            //Ontology configuration
            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
            OWLOntology familiesOntology = manager.loadOntologyFromOntologyDocument(new File(Main.PROJECT_PATH + Main.ONTOLOGY_NAME));
            OWLDataFactory factory = manager.getOWLDataFactory();

            //Reasoner configuration
            ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
            OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
            OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();



            Scanner sc= new Scanner(System.in);

            while(true){

                Main.displayMenu();

            int option = Integer.parseInt(sc.nextLine());

            switch(option){

                case 1 :  // print the ontology

                    //TESTING

                    System.out.println(familiesOntology);
                    break;


                case 2: //get instances

                    String className = sc.nextLine();
                    OWLReasoner reasoner = reasonerFactory.createReasoner(familiesOntology, config);
                    Set<OWLClass> classes = familiesOntology.getClassesInSignature();

                    for (OWLClass classCreated : classes) {
                        String classDetected = classCreated.getIRI().getFragment();
                        if (classDetected.toLowerCase().equals(className)) {
                            NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(classCreated, false);
                            System.out.println(classCreated.getIRI().getFragment() +  " Found");

                            for (OWLNamedIndividual i : instances.getFlattened()) {
                                System.out.println(i.getIRI().getFragment());
                            }
                        }
                    }


                case 3 : //create a new individual

                    String iri= "http://www.semanticweb.org/afpinedac/ontologies/2016/2/familia#";
                    IRI ontologyIRI = IRI.create(iri);
                    OWLIndividual esposo1 = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "parejaH1"));
                    OWLIndividual esposa1 = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "parejaM2"));

                    OWLObjectProperty tieneEsposa = factory.getOWLObjectProperty(IRI
                            .create(ontologyIRI + "tienePareja"));

                    OWLObjectPropertyAssertionAxiom axiom1 = factory.getOWLObjectPropertyAssertionAxiom(tieneEsposa, esposo1, esposa1);

                    AddAxiom addAxiom1 = new AddAxiom(familiesOntology, axiom1);

                    manager.applyChange(addAxiom1);

                    File file = new File(Main.PROJECT_PATH  + Main.NEW_ONTOLOGY_NAME);
                    System.out.println(file.toURI());
                    manager.saveOntology(familiesOntology,IRI.create(file.toURI()));

                    break;

                case -1:
                    System.exit(0);
                    break;

                default:

                    break;
            }


            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void displayMenu(){
        System.out.println("1. Print the ontology ");
        System.out.println("2. Find individuals by class ");
        System.out.println("3. Create new individual, property and axiom");
        System.out.println("-1. exit");
    }
}
