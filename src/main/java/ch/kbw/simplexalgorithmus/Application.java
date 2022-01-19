package ch.kbw.simplexalgorithmus;

import ch.kbw.simplexalgorithmus.model.PivotTable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 450);
        stage.setTitle("Simplexalgorithmus Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        PivotTable pt = new PivotTable(2,3);
         double [][] ex_table = {

                /*
                {2, 2, 1, 0, 0, 16},
                {2, 2, 0, 1, 0, 15},
                {4, 6, 0, 0, 1, 36},
                {-80, -60, 0, 0, 0, 0}

                 */
                 {1.5, 3, 1, 0, 0, 360},
                 {4, 4, 0, 1, 0, 360},
                 {1.5, 6, 0, 0, 1, 360},
                 {3, 4, 0, 0, 0, 0}
        };
        pt.setPivotTable(ex_table);
        pt.cycle();


        launch();

    }
}