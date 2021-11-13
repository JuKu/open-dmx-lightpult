package com.jukusoft.opendmx.editor.controller;

import com.jukusoft.opendmx.commons.fixture.FixtureLibEntry;
import com.jukusoft.opendmx.commons.fixture.FixtureLibrary;
import com.jukusoft.opendmx.commons.utils.Dirs;
import com.jukusoft.opendmx.editor.utils.DialogUtils;
import com.jukusoft.opendmx.editor.utils.Version;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.ResourceBundle;

public class WindowController implements Initializable {

    @FXML
    protected Label versionLabel;

	@FXML
	protected TabPane tabPane;

	/**
	 * the file path to the open .fixture file (if null, no fixture is opened).
	 */
	private String currentFilePath;

	/**
	 * the main stage.
	 */
	private Stage mainStage;

    /**
     * initialize window, will be called after loading fxml file
     */
    public void init(Stage stage, Scene scene, Pane pane) {
        //Objects.requireNonNull(calcButton);
        Objects.requireNonNull(versionLabel);
        Objects.requireNonNull(tabPane);

		this.mainStage = stage;

		refreshVersion();

		//remove all tabs
		this.tabPane.getTabs().clear();
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
		// the user should choose a filename first
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose path for new fixture file");
		fileChooser.setInitialDirectory(new File(Dirs.DATA + "fixtures/"));

		//Set extension filter for text files
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fixture files (*.fixture)", "*.fixture");
		fileChooser.getExtensionFilters().add(extFilter);

		//Show save file dialog
		File file = fileChooser.showSaveDialog(this.mainStage);

		if (file != null) {
			FixtureLibEntry fixture = new FixtureLibEntry("", "", "");

			//TODO: store path and create new file
			saveFixture(file, fixture);
		}
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
		//Show save file dialog
	}

	protected void saveFixture(File file, FixtureLibEntry fixture) {
		FixtureLibrary library = new FixtureLibrary();
		try {
			library.save(file, fixture);
		} catch (IOException e) {
			DialogUtils.showExceptionDialog("Error!", "An exception occured while trying to save the fixture.", "", e);
		}
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
