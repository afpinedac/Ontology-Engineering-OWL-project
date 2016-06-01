package Managers;

import App.Config;
import Controllers.CourseOntology;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import java.io.File;

/**
 * Created by afpinedac on 4/21/16.
 */
public class EntityManager {


    public EntityManager(){

    }


    public static void createCourses() throws Exception{

        String iri= "http://www.semanticweb.org/afpinedac/ontologies/2016/4/ticademia#";
        IRI ontologyIRI = IRI.create(iri);
        OWLIndividual course = CourseOntology.factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "Curso1"));


        OWLDataFactory df = CourseOntology.manager.getOWLDataFactory();

        //=======create a class =======



        /*
        OWLEntity entity = df.getOWLEntity(EntityType.CLASS, IRI.create("test"));

        OWLAxiom axiom = df.getOWLDeclarationAxiom(entity);

        CourseOntology.manager.addAxiom(CourseOntology.ontology, axiom);*/


        //==================== create a instance of a entity


        /*OWLClass test = CourseOntology.factory.getOWLClass(IRI.create(ontologyIRI
                + "#test"));

        OWLIndividual andres = df.getOWLNamedIndividual("andres", new DefaultPrefixManager(ontologyIRI.toString()));
        OWLClassAssertionAxiom axiom = df.getOWLClassAssertionAxiom(test, andres);
        CourseOntology.manager.addAxiom(CourseOntology.ontology,axiom);*/


       // CourseOntology.manager.applyChange(addAxiom1);
        //====================== create a instance with un data property



    //    OWLIndividual curso = df.getOWLNamedIndividual("programacion", new DefaultPrefixManager(ontologyIRI.toString()));
      //  OWLClassAssertionAxiom axiom = df.getOWLClassAssertionAxiom(test, curso);


        /*OWLDataProperty hasName = df.getOWLDataProperty(IRI.create(ontologyIRI + "#nombre"));


        OWLAxiom axiom = df.getOWLDataPropertyAssertionAxiom(hasName, course, "programacion_nombre");

        CourseOntology.manager.addAxiom(CourseOntology.ontology,axiom);*/




        //algorithm  (crear la object property ha enviado)

        //1. seleccionar un curso
        //2. Crear cada uno de sus modulos (fecha_inicio, fecha_fin)
        //3. Crear cada uno de los ejercicios y asignarlos como ejercicios
        //4. Seleccionar todos los estudiantes de ese curso (ingresarlos como estudiantes)
            //4.1 Por cada uno de los estudiantes entonces crear sus envios relacionando estudiante y ejercicio




        //seria cheevere identificar
        //estudiantes que no hicieron nada
        //estudiantes que lo hicieron todo
        //estudiantes que hicieron al menos x porcentaje de cada modulo



    }




    public static void createStudents(){

    }


}
