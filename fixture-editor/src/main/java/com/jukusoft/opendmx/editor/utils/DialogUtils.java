package com.jukusoft.opendmx.editor.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

/*
 * This utility class creates and opens small dialogs (e.q. for warnings).
 *
 * @author Justin Kuenzel
 */
public class DialogUtils {

	/**
	 * private constructor, so noone can create an instance of this class.
	 */
	private DialogUtils() {
		//
	}

	/**
	 * show a warning dialog.
	 *
	 * @param title title of the dialog
	 * @param headerText header text of the dialog
	 * @param content content of the dialog
	 */
	public static void showWarningDialog(String title, String headerText, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(content);

		alert.showAndWait();
	}

	/**
	 * this methods creates and opens an exception dialog.
	 *
	 * @param title title of the dialog
	 * @param headerText header text of the dialog
	 * @param content content of the dialog
	 * @param ex the exeption to show
	 */
	public static void showExceptionDialog(String title, String headerText, String content, Exception ex) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(content);

		// Create expandable Exception, see also: https://code.makery.ch/blog/javafx-dialogs-official/
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace was:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// show extenable content (exception) in the dialog.
		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}

}
