package org.n52.sos.importer.test;

import javax.swing.JFrame;

import org.n52.sos.importer.controller.Step6bController;
import org.n52.sos.importer.controller.TableController;
import org.n52.sos.importer.model.ModelStore;
import org.n52.sos.importer.model.Step6bModel;
import org.n52.sos.importer.model.measuredValue.MeasuredValue;
import org.n52.sos.importer.model.measuredValue.NumericValue;
import org.n52.sos.importer.model.resources.FeatureOfInterest;
import org.n52.sos.importer.model.table.Column;


public class XMLTest {

	public static void main(String[] args) {

		JFrame f = new JFrame();
		Object[][] o = {{"bla", "bla2"},{"bla3", "bla4"},{"bla5", "bla6"}};
		TableController.getInstance().setContent(o);
		MeasuredValue mv = new NumericValue();
		mv.setTableElement(new Column(2));
		ModelStore.getInstance().addMeasuredValue(mv);
		Step6bModel step6aModel = new Step6bModel(new FeatureOfInterest());
		f.add(new Step6bController(step6aModel).getStepPanel());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);

		/*
		InsertObservationDocument i = InsertObservationDocument.Factory.newInstance();
		InsertObservation io = i.addNewInsertObservation();
		ObservationType ot = io.addNewObservation();
		StringOrRefType s = ot.addNewDescription();
		s.setStringValue("TEST");
		LocationPropertyType l = ot.addNewLocation();
*/
		/*
		RegisterSensorDocument r;
		try {
			r = RegisterSensorDocument.Factory.parse(new File("D://RegisterSensor_measurement.xml"));
			RegisterSensor rs = r.getRegisterSensor();	
			rs.setOffering("Test") ;
			r.save(new File("D://test.xml"));
		} catch (XmlException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		*/
	}
}
