package com.jukusoft.opendmx.editor.utils;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.function.Consumer;

/**
 * a single row of the fixture property GridPane in {@link com.jukusoft.opendmx.editor.controller.TabController}.
 *
 * @author Justin Kuenzel
 */
public class FixturePropertyRow {

	public enum DataType {
		INTEGER,
		STRING,
		DOUBLE,
		BOOLEAN
	}

	/**
	 * the title of the property (left column).
	 */
	private String title;

	/**
	 * the text field of the property (value - right column).
	 */
	private TextField textField;

	/**
	 * the datatype of the value.
	 */
	private DataType dataType;

	/**
	 * a flag, if empty values are allowed.
	 */
	private boolean emptyValueAllowed = false;

	/**
	 * the constructor.
	 *
	 * @param title left column title
	 * @param currentValue right column value
	 * @param dataType data type of the value
	 */
	public FixturePropertyRow(String title, String currentValue, DataType dataType, Consumer<String> valueObserver) {
		this(title, currentValue, dataType, valueObserver, false);
	}

	/**
	 * the constructor.
	 *
	 * @param title left column title
	 * @param currentValue right column value
	 * @param dataType data type of the value
	 * @param emptyValueAllowed a flag, if empty values are allowed
	 */
	public FixturePropertyRow(String title, String currentValue, DataType dataType, Consumer<String> valueObserver, boolean emptyValueAllowed) {
		this.title = title;
		this.textField = new TextField(currentValue);
		this.dataType = dataType;
		this.setValueObserver(valueObserver);
		this.emptyValueAllowed = emptyValueAllowed;
	}

	public Label getTitleLabel() {
		return new Label(title);
	}

	public TextField getTextField() {
		return textField;
	}

	public boolean validate() {
		switch (dataType) {
			case INTEGER:
				//check, if the field is a number
				try {
					double number = Integer.parseInt(textField.getText());
					textField.setStyle("-fx-text-fill: black;");

					return true;
				} catch (Exception e) {
					//its not a number
					textField.setStyle("-fx-text-fill: red;");
					return false;
				}
			default:
				if (textField.getText().isEmpty() && !emptyValueAllowed) {
					textField.setStyle("-fx-background-color: red;");
					return false;
				}

				textField.setStyle("-fx-background-color: null;");
				textField.setStyle("-fx-text-fill: black;");
				return true;
		}
	}

	/**
	 * set the listener to track value changes.
	 *
	 * @param valueObserver value observer
	 */
	public void setValueObserver(Consumer<String> valueObserver) {
		this.textField.setOnKeyTyped(keyEvent -> valueObserver.accept(textField.getText()));
	}

}
