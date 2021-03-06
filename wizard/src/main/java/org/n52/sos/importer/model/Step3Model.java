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
package org.n52.sos.importer.model;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Step3Model implements StepModel{
	
	private static final Logger logger = LoggerFactory.getLogger(Step3Model.class);
	
	private int markedColumn;
	private final HashMap<Integer, List<String>> columnAssignments;
	
	private final int firstLineWithData;
	private final boolean useHeader;
	
	public Step3Model(final int markedColumn,
			final int firstLineWithData,
			final boolean useHeader) {
		this.markedColumn = markedColumn;
		this.firstLineWithData = firstLineWithData;
		this.useHeader = useHeader;
		columnAssignments = new HashMap<Integer, List<String>>();
	}

	/**
	 * Saves the current selection of the radio button panel for the current
	 * marked column.<br />
	 * Is called during <code>Step3Model.back()</code> and 
	 * <code>Step3Controller.saveSettings()</code>.
	 * @param selection the selection by the user for the current column
	 */
	public boolean addSelection(final List<String> selection) {
		if (logger.isTraceEnabled()) {
			logger.trace("addSelection()");
		}
		columnAssignments.put(markedColumn, selection);
		final List<String> addedValue = columnAssignments.get(markedColumn);
		if (logger.isDebugEnabled()) {
			logger.debug("Next two values should be equal: " + 
					"addedValue: \"" + addedValue + "\"; " +
					"selection : \"" + selection + "\"; " +
					"aV==sel? " + (addedValue==selection) +
					"; aV.equals(sel)? " + addedValue.equals(selection));
		}
		return (addedValue == selection && addedValue.equals(selection));
	}
	/**
	 * Returns the stored selection (column assignment) for the given column
	 * @param colIndex index of the column
	 * @return a <code>List&lt;String&gt;</code>
	 */
	public List<String> getSelectionForColumn(final int colIndex){
		if (logger.isTraceEnabled()) {
			logger.trace("getSelectionForColumn(colIndex:=" + colIndex +")");
		}
		final List<String> value = columnAssignments.get(colIndex);
		if (logger.isDebugEnabled()) {
			logger.debug("found selection: " + value);
		}
		return value;
	}
	/**
	 * Returns all selections (column assignments) stored in this model
	 * @return a <code>HashMap&lt;Integer,List&lt;String&gt;&gt;</code>
	 */
	public HashMap<Integer,List<String>> getAllSelections(){
		if (logger.isTraceEnabled()) {
			logger.trace("getAllSelections()");
		}
		return columnAssignments;
	}
	/*
	 * 	simple getter and setter
	 */
	public int getFirstLineWithData() {	return firstLineWithData; }
	public int getMarkedColumn() { return markedColumn; }
	public void setMarkedColumn(final int colIndex) { markedColumn = colIndex; }
	public boolean getUseHeader() { return useHeader; }
	
	@Override
	public String toString() {
		final String s = "Step3Model" + 
				"[" + hashCode() + "]: " +
				"fLWD: " + firstLineWithData + ", " + 
				"uH: " + useHeader + ", " + 
				"mC: " + markedColumn + ", " +
				"cA: " + columnAssignments;
		return s;
	}
	
}
