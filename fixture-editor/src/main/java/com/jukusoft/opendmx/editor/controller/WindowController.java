package com.jukusoft.opendmx.editor.controller;

import com.jukusoft.opendmx.editor.utils.Version;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.ResourceBundle;

public class WindowController implements Initializable {

    @FXML
    protected Label versionLabel;

    /**
     * initialize window, will be called after loading fxml file
     */
    public void init(Stage stage, Scene scene, Pane pane) {
        //Objects.requireNonNull(calcButton);
        Objects.requireNonNull(versionLabel);

		refreshVersion();

		//TODO: add code here
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //
    }

    public void refreshVersion() {
        Version version = Version.getCurrentVersion();
        versionLabel.setText("Version: " + version.getVersionName() + " Build: " + version.getBuildNumber() + " Build Time: " + version.getBuildTime());
    }

	/**
	 * create a new fixture - this method is called from the menu.
	 */
	public void newFixture() {
		// TODO: add code here
	}

	/**
	 * open a fixture file - this method is called from the menu.
	 */
	public void openFixtureDialog() {
		//
	}

	/**
	 * save a fixture file - this method is called from the menu.
	 */
	public void saveFixtureDialog() {
		//
	}

	/**
	 * save as fixture file - this method is called from the menu.
	 */
	public void saveAsFixtureDialog() {
		//
	}

	/**
	 * close the application - this method is called from the menu.
	 */
	public void closeApplication() {
		System.exit(0);
	}

	/**
	 * show the about dialog - this method is called from the menu.
	 */
	public void showAboutDialog(){
		//TODO: add code here
	}

}
