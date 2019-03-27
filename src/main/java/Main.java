package main.java;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main extends Application {

    HexGrid hexGrid = new HexGrid();
    ScrollPane hexScrollPane = new ScrollPane();
    GridPane masterGrid = new GridPane();

    private void openFile(File file) {
        try {
            byte[] data = Files.readAllBytes(file.toPath());
//            for (int i = 0; i < 256; i++) {
//                System.out.println(i + ": " + byteToHex(data[i]));
//            }
            loadHexGrid(new HexGrid(data));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadHexGrid(HexGrid grid){
        hexScrollPane.setContent(grid);
    }

    @Override
    public void start(Stage primaryStage) {
        //init stuff
        System.setProperty("prism.lcdtext", "false");
        primaryStage.setTitle("Stickyhex");

        //master grid
        masterGrid.setAlignment(Pos.CENTER);
        masterGrid.setHgap(10);
        masterGrid.setVgap(10);
        masterGrid.setPadding(new Insets(25, 25, 25, 25));

        //toolbar
        HBox toolbar = new HBox(8);
        Button openButton = new Button("Open");
        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Open File as Hex Data");
                File file = chooser.showOpenDialog(primaryStage);
                if (file != null) {
                    openFile(file);
                }
            }
        });
        Button saveButton = new Button("Save");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Save");
            }
        });
        toolbar.getChildren().addAll(openButton, saveButton);
        masterGrid.add(toolbar, 0, 0);

        //title
        Text title = new Text("Stickyhex");
        title.setId("title");
        masterGrid.add(title, 0, 1, 16, 1);

        //hex grid
        //GridPane hexGrid = new main.HexGrid();

        //scroll pane for hex grid
        hexScrollPane.setContent(this.hexGrid);
        hexScrollPane.setPrefViewportWidth(600);
        masterGrid.add(hexScrollPane, 0, 2);

        //finish up
        Scene scene = new Scene(masterGrid, 800, 600);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("css/style.css");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
