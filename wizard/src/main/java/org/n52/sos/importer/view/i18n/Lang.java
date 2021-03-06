/**
 * Copyright (C) 2011-2015 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 as published
 * by the Free Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of
 * the following licenses, the combination of the program with the linked
 * library is not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed
 * under the aforementioned licenses, is permitted by the copyright holders
 * if the distribution is compliant with both the GNU General Public
 * License version 2 and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 */
package org.n52.sos.importer.view.i18n;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

import org.n52.sos.importer.Constants;
import org.n52.sos.importer.model.resources.FeatureOfInterest;
import org.n52.sos.importer.model.resources.ObservedProperty;
import org.n52.sos.importer.model.resources.Resource;
import org.n52.sos.importer.model.resources.Sensor;
import org.n52.sos.importer.model.resources.UnitOfMeasurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author e.h.juerrens@52north.org
 *
 */
public abstract class Lang {

	private static HashMap<Locale,Lang> availableLocales = new HashMap<Locale, Lang>();

	private static Locale currentLocale = Locale.ENGLISH;// <-- default language

	private static Lang instance = null;

	private static final Logger logger = LoggerFactory.getLogger(Lang.class);

	// Add default locale En
	static {
		Lang.availableLocales.put(Locale.ENGLISH, new En());
		En.setCurrentLocale(Locale.ENGLISH);
		Lang.availableLocales.put(Locale.GERMAN, new De());
	}

	public static Locale[] getAvailableLocales() {
		final Set <Locale> locales = availableLocales.keySet();
		return locales.toArray(new Locale[locales.size()]);
	}

	/**
	 * @return the currentLocale
	 */
	public static Locale getCurrentLocale() {
		return currentLocale;
	}

	/**
	 * @return An instance of the subclass of Lang implementing the defined
	 * Locate.
	 */
	public static Lang l() {
		if(instance == null) {
			instance = availableLocales.get(currentLocale);
		}
		return instance;
	}
	/**
	 * @param newLocale the currentLocale to set
	 */
	public static void setCurrentLocale(final Locale newLocale) {
		if (logger.isTraceEnabled()) {
			logger.trace("setCurrentLocale(" + newLocale + ")");
		}
		// when the locale is changed, reset is and change instance object
		if(!Lang.currentLocale.equals(newLocale)) {
			Lang.currentLocale = newLocale;
			Lang.instance = availableLocales.get(newLocale);
		}
	}
	/**
	 * @return Altitude / Height
	 */
	public abstract String altitude();
	/**
	 * @return and
	 */
	public abstract String and();
	/**
	 * @return Back
	 */
	public abstract String backButtonLabel();
	/**
	 * @return Binding
	 */
	public abstract String binding();
	/**
	 * @return Code
	 */
	public abstract String code();
	/**
	 * @return column
	 */
	public abstract String column();
	/**
	 *
	 * @return "Data Preview"
	 */
	public abstract String dataPreview();
	/**
	 * @return Date
	 */
	public abstract String date();
	/**
	 * @return Day
	 */
	public abstract String day();
	/**
	 *
	 * @return Delete the selected item from the list
	 */
	public abstract String editableComboBoxDeleteItemButton();
	/**
	 *
	 * @return Add a new item to the list
	 */
	public abstract String editableComboBoxNewItemButton();
	/**
	 * @return EPSG-Code
	 */
	public abstract String epsgCode();
	/**
	 * @return The EPSG-Code has to be a natural number.
	 */
	public abstract String epsgCodeWarningDialogNaturalNumber();
	/**
	 * @return The EPSG-Code has to be in the range of 0 and 32767.
	 */
	public abstract String epsgCodeWarningDialogOutOfRange();
	/**
	 * @return Error
	 */
	public abstract String error();
	/**
	 * @return Error
	 */
	public abstract String errorDialogTitle();
	/**
	 * @return Example
	 */
	public abstract String example();
	/**
	 * @return Do you really want to exit?\n
	 */
	public abstract String exitDialogQuestion();
	/**
	 * @return Exit
	 */
	public abstract String exitDialogTitle();
	/**
	 * @return Feature of Interest
	 */
	public abstract String featureOfInterest();
	/**
	 * @return file
	 */
	public abstract String file();
	/**
	 * @return Finish
	 */
	public abstract String finishButtonLabel();
	/**
	 * @return Format
	 */
	public abstract String format();
	/**
	 * @return SOS Importer {@link org.n52.sos.importer.Constants#VERSION}
	 */
	public String frameTitle(){	return "SOS Importer " + Constants.VERSION; }
	/**
	 * @param file
	 * @param path
	 * @return - file: "<code>file</code>" (path: "<code>path</code>")
	 */
	public String frameTitleExtension(final String file, final String path) {
		return " - " + file() + ":\"" + file + "\" (" + path() + ": \"" + path + "\")";
	}
	/**
	 * @return generated
	 */
	public abstract String generated();
	public abstract Locale getLocale();
	/**
	 * @return Group
	 */
	public abstract String group();
	/**
	 * @return The height has to be a decimal number.
	 */
	public abstract String heightWarningDialogDecimalNumber();
	/**
	 * @return Hours
	 */
	public abstract String hours();
	/**
	 * @return Information
	 */
	public abstract String infoDialogTitle();
	/**
	 * @return The latitude/northing can only be a decimal number so far.
	 */
	public abstract String latitudeDialogDecimalValue();
	/**
	 * @return Latitude / Northing
	 */
	public abstract String latitudeNorthing();
	/**
	 * @return The longitude/easting can only be a decimal number so far.
	 */
	public abstract String longitudeDialogDecimalValue();
	/**
	 * @return Longitude / Easting
	 */
	public abstract String longitudeEasting();
	/**
	 * @return measured value
	 */
	public abstract String measuredValue();
	/**
	 *
	 * @return "Metadata"
	 */
	public abstract String metadata();
	/**
	 * @return Minutes
	 */
	public abstract String minutes();
	/**
	 * @return Month
	 */
	public abstract String month();
	/**
	 * @return Name
	 */
	public abstract String name();
	/**
	 * @return Next
	 */
	public abstract String nextButtonLabel();
	/**
	 * @return	Thousands separator
	 */
	public abstract String numValuePanelThousandsSeparator();
	/**
	 * @return Observation
	 */
	public abstract String observation();
	/**
	 * @return Observed Property
	 */
	public abstract String observedProperty();
	/**
	 *
	 * @return Offering
	 */
	public abstract String offering();
	/**
	 * @return path
	 */
	public abstract String path();
	/**
	 * @return Position
	 */
	public abstract String position();
	/**
	 * @return Reference System
	 */
	public abstract String referenceSystem();
	/**
	 * @return row
	 */
	public abstract String row();
	/**
	 * @return Seconds
	 */
	public abstract String seconds();
	/**
	 * @return Sensor
	 */
	public abstract String sensor();
	/**
	 * @return Sensor Observation Service
	 */
	public String sos() {
		return "Sensor Observation Service";
	}
	/**
	 * @return Space
	 */
	public abstract String spaceString();
	/**
	 *
	 * @return Specification Version
	 */
	public abstract String specificationVersion();
	/**
	 * @return Step
	 */
	public abstract String step();
	/**
	 * @return Select
	 */
	public abstract String step1BrowseButton();
	/**
	 * @return Step 1: Choose CSV file
	 */
	public abstract String step1Description();
	/**
	 * @return Remote Directory
	 */
	public abstract String step1Directory();
	/**
	 * @return Please select the input file encoding
	 */
	public abstract String step1EncodingLabel();
	/**
	 * @return CSV Feed Type
	 */
	public abstract String step1FeedTypeCSV();
	/**
	 * @return FTP Feed type
	 */
	public abstract String step1FeedTypeFTP();
	/**
	 * @return CSV File
	 */
	public abstract String step1File();
	/**
	 * @return Remote File Schema
	 */
	public abstract String step1FileSchema();
	/**
	 * @return FTP Server
	 */
	public abstract String step1FtpServer();
	/**
	 *
	 * @return Please select the CSV file
	 */
	public abstract String step1InstructionLabel();
	/**
	 *
	 * @return "Introduction"
	 */
	public abstract String step1Introduction();
	/**
	 * @return Password (FTP Server authentification)
	 */
	public abstract String step1Password();
	/**
	 * @return Regular Expression
	 */
	public abstract String step1Regex();
	/**
	 * @return Regular Expression Description
	 */
	public abstract String step1RegexDescription();
	/**
	 * @return Change language
	 */
	public abstract String step1SelectLanguage();
	/**
	 * @return User (FTP Server authentification)
	 */
	public abstract String step1User();
	/**
	 * @return Column separator
	 */
	public abstract String step2ColumnSeparator();
	/**
	 * @return Comment indicator
	 */
	public abstract String step2CommentIndicator();
	/**
	 * @return CSV-Data-Preview
	 */
	public abstract String step2DataPreviewLabel();
	/**
	 * @return Decimal separator
	 */
	public abstract String step2DecimalSeparator();
	/**
	 * @return Step 2: Import CSV file
	 */
	public abstract String step2Description();
	/**
	 * @return Ignore data until line
	 */
	public abstract String step2FirstLineWithData();
	/**
	 *
	 * @return Is data file sample based
	 */
	public abstract String step2IsSampleBased();
	/**
	 * @return Interpret Header
	 */
	public abstract String step2ParseHeader();

	/**
	 *
	 * @return Offset data
	 */
	public abstract String step2SampleBasedDataOffsetLabel();

	/**
	 *
	 * @return The offset in lines from sample beginning till the first lines
	 * 			with data.
	 */
	public abstract String step2SampleBasedDataOffsetToolTip();

	/**
	 * @return Regular Expression "Date Extraction"
	 */
	public abstract String step2SampleBasedDateExtractionRegExLabel();

	/**
	 * @return The regular expression to extract the date information from the
	 * 				line containing the date information of the current sample.
	 * 				The expression MUST result in ONE group. This group will be
	 * 				parsed to a {@link java.util.Date} using
	 * 				"sampleDatePattern" attribute.
	 */
	public abstract String step2SampleBasedDateExtractionRegExTooltip();

	/**
	 * @return Offset date information
	 */
	public abstract String step2SampleBasedDateOffsetLabel();

	/**
	 *
	 * @return The offset of the line containing the date of the
     *                      sample from the start line.
	 */
	public abstract String step2SampleBasedDateOffsetToolTip();

	/**
	 * @return Parse Pattern "Date Information"
	 */
	public abstract String step2SampleBasedDatePatternLabel();

	/**
	 * @return The pattern used to parse the date information of the
	 * 			current sample.
	 */
	public abstract String step2SampleBasedDatePatternTooltip();

	/**
	 *
	 * @return Offset sample size
	 */
	public abstract String step2SampleBasedSampleSizeOffsetLabel();

	/**
	 *
	 * @return The offset in lines from sample beginning till the line
	 * 			containing the sample size in lines with data.
	 */
	public abstract String step2SampleBasedSampleSizeOffsetToolTip();

	/**
	 * @return Regular Expression "Sample Size"
	 */
	public abstract String step2SampleBasedSampleSizeRegExLabel();

	/**
	 * @return The regular expression to extract the sample size. The regular
	 * 				expression MUST result in ONE group which contains an
	 * 				integer value.
	 */
	public abstract String step2SampleBasedSampleSizeRegExTooltip();

	/**
	 *
	 * @return Regular Expression "Sample Start"
	 */
	public abstract String step2SampleBasedStartRegExLabel();

	/**
	 *
	 * @return Used to identify the start of a new sample.\n
	 * 							MUST match the whole line.
	 */
	public abstract String step2SampleBasedStartRegExTooltip();

	/**
	 * @return Text qualifier
	 */
	public abstract String step2TextQualifier();

	/**
	 * @return Step 3a: Choose Metadata for the selected column
	 */
	public abstract String step3aDescription();

	/**
	 * @return You have to specify at least one measured value column.
	 */
	public abstract String step3aMeasureValueColMissingDialogMessage();

	/**
	 * @return Measured value column missing
	 */
	public abstract String step3aMeasureValueColMissingDialogTitle();

	/**
	 * @return 1 value not parseable.
	 */
	public abstract String step3aParseTest1Failed();

	/**
	 * @return All values parseable.
	 */
	public abstract String step3aParseTestAllOk();

	/**
	 * @param n
	 * @return <code>n</code> values not parseable.
	 */
	public abstract String step3aParseTestNFailed(int n);

	/**
	 * @return The type for this column is "undefined" please select one. Chose "Do Not Export" for skipping it.
	 */
	public abstract String step3aSelectedColTypeUndefinedMsg();

	/**
	 * @return Column Type is "undefined"
	 */
	public abstract String step3aSelectedColTypeUndefinedTitle();

	/**
	 * @return Step 3b: Choose metadata for rows
	 */
	public abstract String step3bDescription();

	/**
	 * @return Date & Time
	 */
	public abstract String step3ColTypeDateTime();

	/**
	 * @return Do not export
	 */
	public abstract String step3ColTypeDoNotExport();

	/**
	 * @return Measured Value
	 */
	public abstract String step3ColTypeMeasuredValue();

	/**
	 * @return Undefined
	 */
	public abstract String step3ColTypeUndefined();

	/**
	 * @return Combination
	 */
	public abstract String step3DateAndTimeCombination();

	/**
	 * @return UNIX time
	 */
	public abstract String step3DateAndTimeUnixTime();

	/**
	 * @return Boolean
	 */
	public abstract String step3MeasuredValBoolean();

	/**
	 * @return Count
	 */
	public abstract String step3MeasuredValCount();

	/**
	 * @return Numeric Value
	 */
	public abstract String step3MeasuredValNumericValue();

	/**
	 * @return Text
	 */
	public abstract String step3MeasuredValText();

	/**
	 * @return {@linkplain org.n52.sos.importer.view.i18n.En.step3DateAndTimeCombination()}
	 * 	<br />:= Combination
	 */
	public abstract String step3PositionCombination();

	/**
	 * @return Step 4a: Solve Date & Time ambiguities
	 */
	public abstract String step4aDescription();

	/**
	 * @return Date and Time are already set for this <code>En.measuredValue()</code>.
	 * @see {@link org.n52.sos.importer.view.i18n.En.measuredValue()}
	 */
	public abstract String step4aInfoDateAndTime();

	/**
	 * @param element
	 * @return This is not a <code>En.measuredValue()</code>.
	 * @see {@link org.n52.sos.importer.view.i18n.En.measuredValue()}
	 */
	public abstract String step4aInfoMeasuredValue();

	/**
	 * @param stringReplacer
	 * @return Select all measured value <code>Constants.STRING_REPLACER</code>s where the marked Date & Time group corresponds to.
	 */
	public abstract String step4aModelDescription();

	/**
	 * @return Step 4b: Solve ambiguities
	 */
	public abstract String step4bDescription();

	/**
	 * @return This is not a <code>En.measuredValue()</code>.
	 * @see {@link org.n52.sos.importer.view.i18n.En.measuredValue()}
	 * @see {@link org.n52.sos.importer.view.i18n.En.step4aInfoMeasuredValue()}
	 *
	 */
	public abstract String step4bInfoNotMeasuredValue();
	/**
	 * @return " already set for this "
	 */
	public abstract String step4bInfoResourceAlreadySetText();
	/**
	 * @param resource resource.className already set for this <code>En.measuredValue()</code>.
	 * @return
	 */
	public String step4bInfoResoureAlreadySet(final Resource resource) {
		String res = "RESOURCE NOT FOUND!";
		if (resource instanceof FeatureOfInterest) {
			res = featureOfInterest();
		} else if (resource instanceof ObservedProperty) {
			res = observedProperty();
		} else if (resource instanceof Sensor) {
			res = sensor();
		} else if (resource instanceof UnitOfMeasurement) {
			res = unitOfMeasurement();
		}
		return res + step4bInfoResourceAlreadySetText() + measuredValue();
	}
	/**
	 *
	 * List how to replace the
	 * {@link org.n52.sos.importer.Constants#STRING_REPLACER}
	 * in the correct order:
	 * <ol>
	 * 	<li>The table element type of the measured values, maybe "column"</li>
	 * 	<li>The resource type, that is linked to the measured value table element</li>
	 *  <li>Table element of element to be selected</li>
	 *  <li>Table element of element to be selected</li>
	 *  <li>The resource type, that is linked to the measured value table element</li>
	 *  <li>Table element of element to be selected</li>
	 * </ol>
	 * @param tableElementType
	 * @param tableElementType2
	 * @param resourceType
	 * @return Please click the <code>Constants.STRING_REPLACER</code> (not the title)
	 * containing the measured values for the marked
	 * <code>Constants.STRING_REPLACER</code> <code>Constants.STRING_REPLACER</code> that is
	 * marked. If several <code>Constants.STRING_REPLACER</code>s correspond to this
	 * <code>Constants.STRING_REPLACER</code> <code>Constants.STRING_REPLACER</code>, click
	 * all of them with pressed CTRL key.
	 */
	public abstract String step4bModelDescription();
	/**
	 * @return Step 5a: Complete time data
	 */
	public abstract String step5aDescription();
	/**
	 * @return Complete missing information for the marked date and time.
	 */
	public abstract String step5aModelDescription();
	/**
	 * @return Step 5c: Complete position data
	 */
	public abstract String step5cDescription();
	/**
	 * @return Complete missing information for the marked position.
	 */
	public abstract String step5cModelDescription();
	/**
	 * @return Step 6a: Add missing dates and times
	 */
	public abstract String step6aDescription();
	/**
	 * @return <html>What is the <u>Date & Time</u> for all measured values?</html>
	 */
	public abstract String step6aModelDescription();
	/**
	 * @return Please provide a String to used to link the values in the columns (Empty String is allowed).
	 */
	public abstract String step6bDefineConcatString();
	/**
	 * @return Step 6b: Add missing metadata
	 */
	public abstract String step6bDescription();

	/**
	 * Replacements: Resource &rarr; Orientation
	 * @return &lt;html&gt;What is the &lt;u&gt;<code>Constants.STRING_REPLACER</code>&lt;/u&gt; for the marked measured value <code>Constants.STRING_REPLACER</code>?&lt;/html&gt;
	 */
	public abstract String step6bModelDescription();

	/**
	 * @return Please select the columns to generate the name.<br />
	 * 			\nMultiple columns could be selected.
	 */
	public abstract String step6bSelectColumnsLabel();

	/**
	 * @return Step 6b (Special): Add missing sensors
	 */
	public abstract String step6bSpecialDescription();

	/**
	 * @return What is the sensor for
	 */
	public abstract String step6bSpecialModelDescription();

	/**
	 * @return Please provide a URI or a prefix if using the name as part of the URI.
	 */
	public abstract String step6bURIInstructions();

	/**
	 * @return Use Name after prefix?
	 */
	public abstract String step6bUseNameAfterPrefix();

	/**
	 * @return Step 6c: Add missing positions
	 */
	public abstract String step6cDescription();

	/**
	 *
	 * @return Set Position
	 */
	public abstract String step6cInfoToolName();

	/**
	 *
	 * @return Set the position by clicking on the map
	 */
	public abstract String step6cInfoToolTooltip();

	/**
	 * @return What is the position of
	 */
	public abstract String step6cModelDescription();

	/**
	 * @return Automatically Generate Identifier
	 */
	public abstract String step6Generation();

	/**
	 * @return Set Identifier Manually
	 */
	public abstract String step6ManualInput();

	/**
	 * @return Some User Input is missing. Please enter the required information.
	 */
	public abstract String step6MissingUserInput();

	/**
	 * @return No user input at all. Please fill in the required information.
	 */
	public abstract String step6NoUserInput();

	/**
	 * @return The selected config file folder <code>folder</code> is not
	 * 			accessible for the application.
	 */
	public abstract String step7ConfigDirNotDirOrWriteable(String folder);

	/**
	 * @return Choose configuration file export folder
	 */
	public abstract String step7ConfigFileButton();

	/**
	 * @return Folder
	 */
	public abstract String step7ConfigFileDialogTitel();

	/**
	 * @return Configuration file folder and name
	 */
	public abstract String step7ConfigFileLabel();

	/**
	 *
	 * @return Configuration File
	 */
	public abstract String step7ConfigurationFile();
	
	/**
	 * 
	 * @return Please set the folder for saving the configuration file.
	 */
	public abstract String step7ConfigFileInstructions();

	/**
	 * @return Step 7: Final Configuration
	 */
	public abstract String step7Description();

	/**
	 * @return Directly import data during next step
	 */
	public abstract String step7DirectImport();

	/**
	 * @return Import Strategy
	 */
	public abstract String step7ImportStrategyBorderLabel();

	/**
	 * @return Strategy
	 */
	public abstract String step7ImportStrategyLabel();

	/**
	 * @return Single Observation
	 */
	public abstract String step7ImportStrategySingleObservation();

	/**
	 * @return Hunk size
	 */
	public abstract String step7ImportStrategySweArrayHunksizeLabel();

	/**
	 * @return SweArrayObservation
	 */
	public abstract String step7ImportStrategySweArrayObservation();

	/**
	 * @return Send Buffer
	 */
	public abstract String step7ImportStrategySweArraySendBuffer();

	/**
	 * @return Generate Offering from Sensor name?
	 */
	public abstract String step7OfferingCheckBoxLabel();

	/**
	 * @return Please specify the offering name:
	 */
	public abstract String step7OfferingInputTextfieldLabel();

	/**
	 * @return Please specify the offering name or select to generate it.
	 */
	public abstract String step7OfferingNameNotGiven();

	/**
	 * The given offering "offeringName" is not valid. It should match XML-NCName specification.
	 * @param offeringName
	 * @return
	 */
	public abstract String step7OfferingNameNotValid(String offeringName);

	/**
	 * @return Save configuration to XML file
	 */
	public abstract String step7SaveConfig();

	/**
	 *
	 * @return Please specify the binding.
	 */
	public abstract String step7SosBindingInstructions();

	/**
	 * @return Binding
	 */
	public abstract String step7SosBindingLabel();

	/**
	 * @param strURL
	 * @return To start connection testing to URL "<code>strURL</code>" select YES.\n To change values select NO.
	 */
	public abstract String step7SOSConncetionStart(String strURL);

	/**
	 * @param strURL
	 * @param responseCode
	 * @return Could not connect to Sensor Observation Service: <code>strURL</code> . HTTP Response Code: <code>responseCode</code>
	 */
	public abstract String step7SOSconnectionFailed(String strURL,int responseCode);

	/**
	 * @param strURL
	 * @param message
	 * @param readTimeoutSeconds
	 * @param connectTimeoutSeconds
	 * @return Connection to Sensor Observation Service
	 * 			\n<code>strURL</code>\n
	 * 			failed after <code>connectTimeoutSeconds</code> seconds connect
	 * 			and <code>readTimeoutSeconds</code> seconds read timeout.\n
	 * 			Reason: <code>message</code>
	 */
	public abstract String step7SOSConnectionFailedException(String strURL,
			String message,
			int readTimeoutSeconds,
			int connectTimeoutSeconds);

	/**
	 *
	 * @return	Please specify the SOS URL including the endpoint like this "http://www.example.com/52nSOS/sos" .
	 */
	public abstract String step7SosUrlInstructions();

	/**
	 *
	 * @return Please specify the specification version that is implemented by the SOS instance specified by the URL in the field above.
	 */
	public abstract String step7SosVersionInstructions();

	/**
	 * @return Specification
	 */
	public abstract String step7SosVersionLabel();

	/**
	 * @return Open Configuration File
	 */
	public abstract String step8ConfigFileButton();

	/**
	 *
	 * @return "For taking a look at the generated configuration file, please click button."
	 */
	public abstract String step8ConfigurationFileInstructions();

	/**
	 * @return Step 8: Final Step - Summary of the Results
	 */
	public abstract String step8Description();

	/**
	 *
	 * @return For importing the content of the data file one time, just click + step8DirectImportStartButton()
	 */
	public abstract String step8DirectImportInstructions();

	/**
	 * @return Register Sensors and Insert Observations into Sensor Observation Service
	 */
	public abstract String step8DirectImportLabel();

	/**
	 *
	 * @return Start Import
	 */
	public abstract String step8DirectImportStartButton();

	/**
	 * @param i
	 * @return Errors: <code>i</code>
	 */
	public abstract String step8ErrorLable(int i);

	/**
	 *
	 * @param absolutePath
	 * @return Could not find jar file!\nPlease place it there:\n\"expectedAbsolutePathToFeederJar\"
	 */
	public abstract String step8FeederJarNotFound(String expectedAbsolutePathToFeederJar);

	/**
	 * @return Could not find jar file here:\n\"expectedAbsolutePathToFeederJar\". Click YES to select it.
	 */
	public abstract String step8FeederJarNotFoundSelectByUser(String pathToDirectoryWithFeederJar);

	/**
	 * @param i
	 * @return Insert <code>i</code> Observations...
	 */
	public abstract String step8InsertObservationLabel(int i);

	/**
	 *
	 * @return "Log File"
	 */
	public abstract String step8LogFile();

	/**
	 * @return Check log file
	 */
	public abstract String step8LogFileButton();

	/**
	 *
	 * @return "To check for additional information, please take a look at the log file created during the process."
	 */
	public abstract String step8LogFileInstructions();

	/**
	 * @param i
	 * @return Register <code>i</code> Sensors...
	 */
	public abstract String step8RegisterSensorLabel(int i);

	/**
	 * @param f The file which was used to save the XML model
	 * @return The configuration could not be saved to file
	 * 						<br />"<code>f</code>".
	 */
	public abstract String step8SaveModelFailed(File f);

	/**
	 * @param f The file which was used to save the XML model
	 * @param excpetionText A short String describing the exception cause
	 * 						during saving process
	 * @return The configuration could not be saved to file
	 * 						<br />"<code>f</code>".
	 * 						<br />An Exception occurred:
	 * 						<br /><code>exceptionText</code>.
	 * 						<br />Please check the log file for more details.
	 */
	public abstract String step8SaveModelFailed(File f, String exceptionText);

	/**
	 *
	 * @return "Start Import"
	 */
	public abstract String step8StartImportButton();

	/**
	 * @param i
	 * @return Successful: <code>i</code>
	 */
	public abstract String step8SuccessLabel(int i);

	/**
	 * @return Time
	 */
	public abstract String time();

	/**
	 * @return UTC offset
	 */
	public abstract String timeZone();

	/**
	 * @return Unit
	 */
	public abstract String unit();

	/**
	 * @return Unit of Measurement
	 */
	public abstract String unitOfMeasurement();

	/**
	 * @return URI
	 */
	public abstract String uri();

	/**
	 * @param uri The String with the wrong syntax entered by the user
	 * @return The entered URI "<code>uri</code>" is syntactically not correct.
	 */
	public abstract String uriSyntaxNotValidDialogMessage(String uri);

	/**
	 * @return URL
	 */
	public abstract String url();

	/**
	 * @return Version
	 */
	public abstract String version();

	/**
	 * @return Testing evaluation pattern for column
	 */
	public abstract String waitForParseResultsLabel();

	/**
	 * @return Warning
	 */
	public abstract String warningDialogTitle();

	/**
	 * @return Year
	 */
	public abstract String year();

}
