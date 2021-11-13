package com.jukusoft.opendmx.editor.app;

import com.jukusoft.opendmx.editor.controller.WindowController;
import com.jukusoft.opendmx.editor.utils.Version;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFXApplication extends Application {

    //path to window fxml file
    protected static final String FXML_PATH = "fxml/main.fxml";

    //JavaFX stage (window)
    protected Stage stage = null;

    //JavaFX scene
    protected Scene scene = null;

    //JavaFX root pane (AnchorPane)
    protected Pane rootPane = null;

    //JavaFX window controller (MVC principle)
    protected WindowController controller = null;

    public void start(Stage stage) {
        //create new stage (window)
        this.stage = stage;//new Stage();

        //set title, width and height
        this.stage.setTitle("Fixture Editor");

		//set editor icon
		this.stage.getIcons().add(new Image(JavaFXApplication.class.getResourceAsStream("/assets/icons/app_icon.png")));

        // load fxml file
        try {
            ClassLoader classLoader = Version.class.getClassLoader();
            //File fxmlFile = new File(classLoader.getResource(FXML_PATH).getFile());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(classLoader.getResource(FXML_PATH)/*fxmlFile.toURI().toURL()*/);

            //create and set controller
            this.controller = new WindowController();
            loader.setController(this.controller);

            //load and get root pane (AnchorPane)
            this.rootPane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        //create new scene with root Pane (AnchorPane)
        this.scene = new Scene(this.rootPane);

        //set scene to stage (window)
        stage.setScene(scene);

        //initialize controller
        controller.init(stage, scene, rootPane);

        //set window position to center and focus window
        this.stage.centerOnScreen();
        this.stage.requestFocus();

        //set window visible
        stage.show();
    }

}
