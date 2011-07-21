package org.n52.sos.importer.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.n52.sos.importer.model.Step2Model;
import org.n52.sos.importer.view.Step2Panel;

import au.com.bytecode.opencsv.CSVReader;

public class Step2Controller extends StepController {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(Step2Controller.class);

	private Step2Model step2Model;
	
	private Step2Panel step2Panel;
	
	public Step2Controller(Step2Model step2Model) {
		this.step2Model = step2Model;
	}
	
	@Override
	public String getDescription() {
		return "Step 2: Import CSV file"; 
	}
	
	@Override
	public boolean isFinished() {
		String columnSeparator = step2Panel.getSelectedColumnSeparator();
		if (columnSeparator == null || columnSeparator.equals(""))
			return false;
		String commentIndicator = step2Panel.getSelectedCommentIndicator();
		if (commentIndicator == null || commentIndicator.equals(""))
			return false;
		String textQualifier = step2Panel.getSelectedTextQualifier();
		if (textQualifier == null || textQualifier.equals(""))
			return false;
		
		return true;
	}
	
	@Override
	public StepController getNextStepController() {
		Object[][] content = parseCSVFile();
		TableController.getInstance().setContent(content);
		return new Step3aController();
	}
	
	@Override
	public void loadSettings() {
		step2Panel = new Step2Panel();
		
		String columnSeparator = step2Model.getSelectedColumnSeparator();
		step2Panel.setSelectedColumnSeparator(columnSeparator);
		String commentIndicator = step2Model.getSelectedCommentIndicator();
		step2Panel.setSelectedCommentIndicator(commentIndicator);
		String textQualifier = step2Model.getSelectedTextQualifier();
		step2Panel.setSelectedTextQualifier(textQualifier);
		String csvFileContent = step2Model.getCSVFileContent();
		step2Panel.setCSVFileContent(csvFileContent);
	}
	
	@Override
	public void saveSettings() {	
		String columnSeparator = step2Panel.getSelectedColumnSeparator();
		step2Model.setSelectedColumnSeparator(columnSeparator);
		String commentIndicator = step2Panel.getSelectedCommentIndicator();
		step2Model.setSelectedCommentIndicator(commentIndicator);
		String textQualifier = step2Panel.getSelectedTextQualifier();
		step2Model.setSelectedTextQualifier(textQualifier);
		String csvFileContent = step2Panel.getCSVFileContent();
		step2Model.setCSVFileContent(csvFileContent);
		
		step2Panel = null;
	}
	
	private Object[][] parseCSVFile() {
		Object[][] content = null;
		String csvFileContent = step2Model.getCSVFileContent();
		String separator = step2Model.getSelectedColumnSeparator();
		String quoteChar = step2Model.getSelectedCommentIndicator();
		String escape = step2Model.getSelectedTextQualifier();
		
		logger.info("Parse CSV file with " +
				"column separator '" + separator + "', " +
				"comment indicator '" + quoteChar + "' and " +
				"text qualifier '" + escape + "'.");
		
		try {	
			if (separator.equals("Tab")) separator = "\t"; 
			if (separator.equals("Space")) {
				separator = ";";
				csvFileContent = convertSpaceSeparatedText(csvFileContent, separator);
			}
			StringReader sr = new StringReader(csvFileContent);
			CSVReader reader = new CSVReader(sr, separator.charAt(0), quoteChar.charAt(0), escape.charAt(0));
			List<String[]> lines = reader.readAll();
			int rows = lines.size();
			String[] firstLine = lines.get(0);
			int columns = firstLine.length;
			content = new Object[rows][columns];

			for (int i = 0; i < rows; i++) {
				content[i] = lines.get(i);
			}
		} catch (IOException e) {
			logger.error("Error while parsing CSV file.", e);
		}
		return content;
	}
	
	public String convertSpaceSeparatedText(String text, String separator) {
		StringBuilder replacedText = new StringBuilder();
		StringReader sr = new StringReader(text);
		BufferedReader br = new BufferedReader(sr);
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				line = line.trim();
				line = replaceWhiteSpace(line, separator);
				replacedText.append(line + "\n");
			}
		} catch (IOException e) {
			logger.info("Error while parsing space-separated file", e);
		}
		return replacedText.toString();
	}
	
	/**
	 * replaces any whitespace in the text by the given separator
	 * @param text
	 * @param replacement
	 * @return
	 */
	public String replaceWhiteSpace(String text, String separator) {
		StringBuilder replacedText = new StringBuilder();
		boolean lastCharacterWasAWhiteSpace = false;
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if (Character.isWhitespace(ch)) {
				if (lastCharacterWasAWhiteSpace) continue;
				else {
					replacedText.append(separator);
					lastCharacterWasAWhiteSpace = true;
				}
			} else {
				replacedText.append(ch);
				lastCharacterWasAWhiteSpace = false;
			}
		}
		return replacedText.toString();
	}

	@Override
	public JPanel getStepPanel() {
		return step2Panel;
	}

	@Override
	public boolean isNecessary() {
		return true;
	}

	@Override
	public StepController getNext() {
		return null;
	}
}
