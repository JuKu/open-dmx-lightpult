package com.jukusoft.opendmx.editor.controller;

import com.jukusoft.opendmx.commons.fixture.FixtureLibEntry;
import com.jukusoft.opendmx.commons.fixture.FixtureLibrary;
import com.jukusoft.opendmx.editor.utils.DialogUtils;
import com.jukusoft.opendmx.editor.utils.FixturePropertyRow;
import com.jukusoft.opendmx.editor.utils.ModesCountPropertyRow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

	@FXML
	protected TabPane modesTabPane;

	/**
	 * this flag is set, if there are unsaved changes.
	 */
	private boolean unsavedChanges = false;

	/**
	 * the current tab.
	 */
	private Tab currentTab;

	/**
	 * current fixture (for this tab).
	 */
	private FixtureLibEntry fixture;

	/**
	 * the current .fixture file.
	 */
	private File currentFile;

	/**
	 * the rows for the properties.
	 */
	private List<FixturePropertyRow> propertyRows = new ArrayList<>();

	/**
	 * the number of modes for this fixture (default: 1).
	 */
	private int modesCount = 0;

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
		Objects.requireNonNull(modesTabPane);

		this.currentTab = tab;
		this.fixture = fixture;
		this.currentFile = file;
		this.modesCount = fixture.getModes().size();

		this.fixtureLabel.setText("Fixture Path: " + file.getName());

		propertyRows.add(new FixturePropertyRow("Short Name:", fixture.getShortName(), FixturePropertyRow.DataType.STRING, newValue -> {
			this.fixture.setShortName(newValue);
			trackUnsavedChanges();
		}));
		propertyRows.add(new FixturePropertyRow("Long Name:", fixture.getLongName(), FixturePropertyRow.DataType.STRING, newValue -> {
			fixture.setLongName(newValue);
			trackUnsavedChanges();
		}));
		propertyRows.add(new FixturePropertyRow("Manufacturer:", fixture.getManufacturer(), FixturePropertyRow.DataType.STRING, newValue -> {
			fixture.setManufacturer(newValue);
			trackUnsavedChanges();
		}));
		propertyRows.add(new ModesCountPropertyRow("DMX Modes:", fixture.getModes().size(), FixturePropertyRow.DataType.INTEGER, newValue -> {
			if (newValue == null || newValue.isEmpty()) {
				//skip empty values
				return;
			}

			this.modesCount = Integer.parseInt(newValue);
			refreshModesTabs();

			trackUnsavedChanges();
		}));

		for (int row = 0; row < propertyRows.size(); row++) {
			gridPane.addRow(row + 1, propertyRows.get(row).getTitleLabel(), propertyRows.get(row).getTextField());

			//set listener to track unsaved changes
			//propertyRows.get(row).setValueObserver(newValue -> trackUnsavedChanges());
		}

		//set max height of all rows
		for (RowConstraints rowConstraints : gridPane.getRowConstraints()) {
			rowConstraints.setMaxHeight(30);
		}

		//remove all tabs from modes tabs
		this.modesTabPane.getTabs().clear();
	}

	public void save() {
		FixtureLibrary fixtureLibrary = new FixtureLibrary();

		//check all values
		for (FixturePropertyRow row : propertyRows) {
			if (!row.validate()) {
				DialogUtils.showWarningDialog("Warning", "Cannot save fixture!", "The value for row '" + row.getTitleLabel().getText() + "' is not correct.");
				return;
			}
		}

		try {
			fixtureLibrary.save(currentFile, fixture);
		} catch (IOException e) {
			DialogUtils.showExceptionDialog("Error!", "An exception occured while trying to save the fixture.", "", e);
		}

		this.unsavedChanges = false;
		refreshTabTitle();
	}

	/**
	 * refresh the tabs in the modesTabPanel.
	 */
	protected void refreshModesTabs() {
		//remove too much tabs
		while (this.modesCount < modesTabPane.getTabs().size()) {
			//remove last tab
			modesTabPane.getTabs().remove(modesTabPane.getTabs().size() - 1);
		}

		int index = this.modesCount;

		//add new tabs
		while (this.modesCount > modesTabPane.getTabs().size()) {
			//add new tab
			modesTabPane.getTabs().add(new Tab("Mode " + index));

			index++;
		}
	}

	protected void trackUnsavedChanges() {
		this.unsavedChanges = true;
		refreshTabTitle();
	}

	protected void refreshTabTitle() {
		String titleSuffix = "";

		if (this.unsavedChanges) {
			titleSuffix = "*";
		}

		this.currentTab.setText(this.currentFile.getName().replace(".fixture", "") + titleSuffix);
	}

}
