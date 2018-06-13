package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        System.setProperty("prism.lcdtext", "false");
        primaryStage.setTitle("JavaFX Welcome");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.setPadding(new Insets(25, 25, 25, 25));

        Text title = new Text("Welcome");
        title.setId("title");
        grid.add(title, 0, 0, 16, 1);

        for (int j = 0; j < 12; j++) {
            for (int i = 0; i < 16; i++) {
                Label dummyhex = new Label("ce");
                if (50 < (i + j * 16) && (i + j * 16) < 79) {
                    dummyhex.setId("highlighted-hex");
                } else {
                    dummyhex.setId("default-hex");
                }
                grid.add(dummyhex, i, j+1, 1, 1);
            }
        }

        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());

        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
