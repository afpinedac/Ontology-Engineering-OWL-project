import App.Config;
import Model.DB;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.vocab.XSDVocabulary;
import java.io.File;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.TreeSet;


class Course implements Comparable<Course> {
    String id;
    String name;
    String startDate;
    String endDate;
    float approvalPercentage;
    TreeSet<Module> modules;
    TreeSet<Student> students;


    public Course(String name, String startDate, String endDate, float approvalPercentage) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.modules = new TreeSet<Module>();
        this.students = new TreeSet<Student>();
        this.approvalPercentage = approvalPercentage;
    }

    public int compareTo(Course o) {
        return o.name.compareTo(this.name);
    }

    public String toString() {
        return this.id + "-" + this.name;
    }

    public Module addModule(Module m) {
        if (!this.hasModule(m)) {
            this.modules.add(m);
        }
        return getModule(m);
    }

    public Student addStudent(Student s) {
        if (!hasStudent(s)) {
            this.students.add(s);
        }

        return getStudent(s);

    }

    public boolean hasStudent(Student s) {
        return this.students.contains(s);
    }


    public Student getStudent(Student s) {
        Iterator<Student> iterator = this.students.iterator();

        while (iterator.hasNext()) {
            Student ss = iterator.next();
            if (ss.compareTo(s) == 0) {
                return ss;
            }
        }

        return null;
    }


    public Module getModule(Module m) {
        Iterator<Module> iterator = this.modules.iterator();
        while (iterator.hasNext()) {
            Module mm = iterator.next();
            if (mm.compareTo(m) == 0) {
                return mm;
            }
        }

        return null;
    }


    public boolean hasModule(Module m) {
        return this.modules.contains(m);
    }


}

class Module implements Comparable<Module> {
    String id;
    String name;
    String startDate;
    String endDate;
    TreeSet<Problem> problems;


    public Module(String id, String name, String startDate, String endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        problems = new TreeSet<Problem>();
    }


    public int compareTo(Module o) {
        return o.id.compareTo(this.id);
    }

    public void addProblem(Problem p) {
        this.problems.add(p);
    }

    public void hasProblem() {

    }
}

/**
 *
 */
class Problem implements Comparable<Problem> {
    String id;
    String name;


    public Problem(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public int compareTo(Problem o) {
        return o.id.compareTo(this.id);
    }
}

class Submission implements Comparable<Submission> {
    String id;
    String date;
    Problem problem;
    String response;


    public Submission(String id, String date, Problem problem, String response) {
        this.id = "Envio-" + id;
        this.date = date;
        this.problem = problem;
        this.response = response;
    }


    public int compareTo(Submission o) {
        return o.id.compareTo(this.id);
    }
}

class Student implements Comparable<Student> {
    String id;
    String name;
    TreeSet<Submission> submissions;

    public Student(String id, String date) {
        this.id = "Envio-" + id;
        this.name = date;
        this.submissions = new TreeSet<Submission>();
    }


    public void addSubmission(Submission s) {
        this.submissions.add(s);
    }


    public int compareTo(Student o) {
        return o.id.compareTo(this.id);
    }


    public String toString() {
        return this.id + "-" + this.name;
    }
}


public class Main {

    static OWLOntologyManager manager;
    static OWLOntology ontology;
    static OWLDataFactory factory;
    static String iri = "http://www.semanticweb.org/afpinedac/ontologies/2016/4/ticademia#";
    static String base = "http://www.semanticweb.org/afpinedac/ontologies/2016/4/ticademia#";
    static IRI ontologyIRI;

    public static void createCourseInstance(Course c) {
        PrefixManager pm = new DefaultPrefixManager(Main.base);

        OWLClass courseClass = factory.getOWLClass(":Curso", pm);
        OWLIndividual course = factory.getOWLNamedIndividual(c.name, pm);
        OWLClassAssertionAxiom axiom = factory.getOWLClassAssertionAxiom(courseClass, course);
        manager.addAxiom(ontology, axiom);

        //add properties

        OWLDataProperty hasApprovalPercentage = factory.getOWLDataProperty(":porcentageAprobacion", pm);

        OWLAxiom axioma = factory.getOWLDataPropertyAssertionAxiom(hasApprovalPercentage, course, c.approvalPercentage);
        manager.addAxiom(ontology, axioma);

    }

    public static void createModuleInstance(Course c, Module m) {

        PrefixManager pm = new DefaultPrefixManager(Main.base);

        OWLIndividual course = factory.getOWLNamedIndividual(c.name, pm);

        OWLClass moduleClass = factory.getOWLClass(":Modulo", pm);
        OWLIndividual module = factory.getOWLNamedIndividual(m.name, pm);
        OWLClassAssertionAxiom axiom = factory.getOWLClassAssertionAxiom(moduleClass, module);
        manager.addAxiom(ontology, axiom);

        //add object properties (has Module)
        OWLObjectProperty hasModule = factory.getOWLObjectProperty(":tieneModulo", pm);
        OWLAxiom axioma = factory.getOWLObjectPropertyAssertionAxiom(hasModule, course, module);
        manager.addAxiom(ontology, axioma);

        //add data properties
        OWLDataProperty hasStartDate = factory.getOWLDataProperty(":moduloFechaInicio", pm);
        OWLDataProperty hasEndDate = factory.getOWLDataProperty(":moduloFechaFin", pm);


        OWLLiteral literalStartDate = factory.getOWLLiteral(m.startDate,
                factory.getOWLDatatype(XSDVocabulary.DATE.getIRI()));

        OWLLiteral literalEndDate = factory.getOWLLiteral(m.endDate,
                factory.getOWLDatatype(XSDVocabulary.DATE.getIRI()));


        axioma = factory.getOWLDataPropertyAssertionAxiom(hasStartDate, module, literalStartDate);
        manager.addAxiom(ontology, axioma);

        axioma = factory.getOWLDataPropertyAssertionAxiom(hasEndDate, module, literalEndDate);
        manager.addAxiom(ontology, axioma);
    }

    public static void createProblemInstance(Module m, Problem p) {

        PrefixManager pm = new DefaultPrefixManager(Main.base);
        OWLClass problemClass = factory.getOWLClass(":EjercicioCodigo", pm);
        OWLIndividual problem = factory.getOWLNamedIndividual(p.name, pm);
        OWLClassAssertionAxiom axiom = factory.getOWLClassAssertionAxiom(problemClass, problem);
        manager.addAxiom(ontology, axiom);

        //find ticket parent

        OWLIndividual module = factory.getOWLNamedIndividual(m.name, pm);

        //link the problem to a module
        OWLObjectProperty hasProblem = factory.getOWLObjectProperty(":tieneEjercicio", pm);
        OWLAxiom axioma = factory.getOWLObjectPropertyAssertionAxiom(hasProblem, module, problem);
        manager.addAxiom(ontology, axioma);


        //add data property
        OWLDataProperty hasName = factory.getOWLDataProperty(":ejercicioNombre", pm);

        axioma = factory.getOWLDataPropertyAssertionAxiom(hasName, problem, p.name);
        manager.addAxiom(ontology, axioma);

    }

    public static void createStudentInstance(Course c, Student u) {

        PrefixManager pm = new DefaultPrefixManager(Main.base);
        //find the parent course
        OWLIndividual course = factory.getOWLNamedIndividual(c.name, pm);

        //create the student instance
        OWLClass studentClass = factory.getOWLClass(":Estudiante", pm);
        OWLIndividual student = factory.getOWLNamedIndividual(u.name, pm);
        OWLClassAssertionAxiom axiom = factory.getOWLClassAssertionAxiom(studentClass, student);
        manager.addAxiom(ontology, axiom);


        //link the student to a course
        OWLObjectProperty hasStudent = factory.getOWLObjectProperty(":tieneEstudiante", pm);
        OWLAxiom axioma = factory.getOWLObjectPropertyAssertionAxiom(hasStudent, course, student);
        manager.addAxiom(ontology, axioma);

        //add data properties
        OWLDataProperty hasName = factory.getOWLDataProperty(":estudianteNombre", pm);

        axioma = factory.getOWLDataPropertyAssertionAxiom(hasName, student, u.name);
        manager.addAxiom(ontology, axioma);

    }

    public static void createSubmissionsInstance(Problem p, Student s, Submission sub) {

        PrefixManager pm = new DefaultPrefixManager(Main.base);
        OWLClass envioClass = factory.getOWLClass(":Envio", pm);
        OWLIndividual envio = factory.getOWLNamedIndividual(sub.id, pm);
        OWLClassAssertionAxiom axiom = factory.getOWLClassAssertionAxiom(envioClass, envio);
        manager.addAxiom(ontology, axiom);


        //find the parent problem & student

        OWLIndividual problem = factory.getOWLNamedIndividual(p.name, pm);
        OWLIndividual student = factory.getOWLNamedIndividual(s.name, pm);


        //link the submissions to a problem
        OWLObjectProperty hasSubmissions = factory.getOWLObjectProperty(":tieneEnvio", pm);
        OWLAxiom axioma = factory.getOWLObjectPropertyAssertionAxiom(hasSubmissions, problem, envio);
        manager.addAxiom(ontology, axioma);


        //link the submissions to a student
        hasSubmissions = factory.getOWLObjectProperty(":realizaEnvio", pm);
        axioma = factory.getOWLObjectPropertyAssertionAxiom(hasSubmissions, student, envio);
        manager.addAxiom(ontology, axioma);


        //add data properties
        OWLDataProperty hasDate = factory.getOWLDataProperty(":envioFecha", pm);
        OWLLiteral literalDate = factory.getOWLLiteral(sub.date,
                factory.getOWLDatatype(XSDVocabulary.DATE.getIRI()));
        axioma = factory.getOWLDataPropertyAssertionAxiom(hasDate, envio, literalDate);
        manager.addAxiom(ontology, axioma);

        OWLDataProperty hasResponse = factory.getOWLDataProperty(":envioRespuesta", pm);

        axioma = factory.getOWLDataPropertyAssertionAxiom(hasResponse, envio, sub.response.toLowerCase());

        manager.addAxiom(ontology, axioma);

    }

    public static void saveOntology() throws Exception {
        File file = new File(Config.PROJECT_PATH + Config.NEW_ONTOLOGY_NAME);
        manager.saveOntology(ontology, IRI.create(file.toURI()));

    }

    public static void main(String[] args) throws Exception {


        manager = OWLManager.createOWLOntologyManager();
        ontology = manager.loadOntologyFromOntologyDocument(new File(Config.PROJECT_PATH + Config.ONTOLOGY_NAME));
        factory = manager.getOWLDataFactory();
        ontologyIRI = IRI.create(iri);


        System.out.println("=====================starting process");

        int courseID = 10;

        ResultSet result = DB.query("\n" +
                "select qa.id as attempt_id, q.id as problem_id ,q.name as quiz_name, u.id as user_id, u.first_name, u.last_name, qa.feedback, m.name as module_name, \n" +
                "qa.grade, m.id as module_id,  qa.created_at as attempt_date, m.start_date as inicio_modulo, m.description as module_description, m.end_date as fin_modulo\n" +
                "from users u, quiz_attempts qa, modules m, quizzes q\n" +
                "where u.id = qa.user_id and qa.quiz_id = q.id and q.module_id = m.id and m.course_id = " + courseID + "\n" +
                "order by qa.id");


        //find the course and create the instance
        ResultSet courseResult = DB.query1("SELECT s.name, c.start_date, c.end_date, c.approval_percentage FROM subjects s, courses c WHERE s.id = c.subject_id  and  c.id = " + courseID);
        courseResult.next();


        //#createOntology

        //create the ontology
        Course course = new Course(courseResult.getString("name"), courseResult.getString("start_date"), courseResult.getString("end_date"), Float.parseFloat(courseResult.getString("approval_percentage")));

        createCourseInstance(course);
        //saveOntology();

        while (result.next()) {

            Module module = new Module(result.getString("module_id"), result.getString("module_name") + " - " + result.getString("module_description"), result.getString("inicio_modulo"), result.getString("fin_modulo"));
            Problem problem = new Problem(result.getString("problem_id"), result.getString("quiz_name"));
            Student student = new Student(result.getString("user_id"), result.getString("first_name") + " " + result.getString("last_name"));
            Submission submission = new Submission(result.getString("attempt_id"), result.getString("attempt_date"), problem, result.getString("feedback"));
            course.addModule(module).addProblem(problem);
            course.addStudent(student).addSubmission(submission);
        }


        //save all the information


        for (Module module : course.modules) {
            createModuleInstance(course, module);
            for (Problem problem : module.problems) {
                createProblemInstance(module, problem);
            }
        }

        for (Student student : course.students) {
            createStudentInstance(course, student);

            for (Submission submission : student.submissions) {
                createSubmissionsInstance(submission.problem, student, submission);
            }
        }


        saveOntology();


    }
}
