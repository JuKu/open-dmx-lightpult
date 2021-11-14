package com.jukusoft.opendmx.editor.utils;

import javafx.scene.control.*;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * a single row of the fixture property GridPane in {@link com.jukusoft.opendmx.editor.controller.TabController}.
 *
 * @author Justin Kuenzel
 */
public class ModesCountPropertyRow extends FixturePropertyRow {

	/**
	 * the spinner.
	 */
	private Spinner<Integer> spinner = new Spinner<>();

	public ModesCountPropertyRow(String title, int currentValue, DataType dataType, Consumer<String> valueObserver) {
		this(title, currentValue, dataType, valueObserver, false);
	}

	public ModesCountPropertyRow(String title, int currentValue, DataType dataType, Consumer<String> valueObserver, boolean emptyValueAllowed) {
		super(title, currentValue + "", dataType, valueObserver, emptyValueAllowed);

		//configure spinner
		this.spinner = new Spinner<>();
		this.spinner.setEditable(true);

		//create new value factory
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, currentValue);
		this.spinner.setValueFactory(valueFactory);

		this.spinner.getValueFactory().setValue(currentValue);
		this.setValueObserver(valueObserver);
	}

	@Override
	public Control getTextField() {
		return spinner;
	}

	/**
	 * set the listener to track value changes.
	 *
	 * @param valueObserver value observer
	 */
	@Override
	public void setValueObserver(Consumer<String> valueObserver) {
		//skip this method, if the spinner was not initialized yets
		if (this.spinner == null) {
			return;
		}

		Objects.requireNonNull(this.spinner, "Spinner cannot be null");
		this.spinner.valueProperty().addListener((obs, old, newValue) -> valueObserver.accept(spinner.getValueFactory().getValue() + ""));
	}

}
