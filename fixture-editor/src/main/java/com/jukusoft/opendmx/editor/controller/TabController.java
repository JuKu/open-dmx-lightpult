package com.jukusoft.opendmx.editor.controller;

import com.jukusoft.opendmx.commons.fixture.FixtureLibEntry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/*
 * The JavaFX controller for a single fixture tab in the TabPane (see also {@link WindowController}).
 *
 * @author Justin Kuenzel
 */
public class TabController implements Initializable {

	@FXML
	protected Label fixtureLabel;

	@FXML
	protected GridPane gridPane;

	/**
	 * this flag is set, if there are unsaved changes.
	 */
	private boolean unsavedChanges = false;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		//
	}

	/**
	 * initialize window, will be called after loading fxml file
	 */
	public void init(Stage stage, Tab tab, Pane pane, File file, FixtureLibEntry fixture) {
		Objects.requireNonNull(fixtureLabel);
		Objects.requireNonNull(gridPane);

		this.fixtureLabel.setText("Fixture Path: " + file.getName());
	}

}
