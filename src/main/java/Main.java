import App.App;
import Controllers.BaseOntology;
import Controllers.CourseOntology;
import View.ViewManager;

public class Main {

    public static void main(String[] args) {

        ViewManager viewManager = new ViewManager();
        viewManager.setOntology(new CourseOntology(true));
        viewManager.displayOntologyOptions();

    }
}
