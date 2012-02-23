package org.n52.sos.importer.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.n52.sos.importer.interfaces.MissingComponentPanel;

/**
 * consists of an instruction panel, the table and 
 * a container panel for all missing components
 * (used for steps 5a, 5c, 6a, 6b) 
 * @author Raimund
 *
 */
public class Step5Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public Step5Panel(String description, List<MissingComponentPanel> missingComponentPanels) {
		super();
		JLabel descriptionLabel = new JLabel(description);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		descriptionPanel.add(descriptionLabel);
		this.add(descriptionPanel);
		
		TablePanel tablePanel = TablePanel.getInstance();
		this.add(tablePanel);
		
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.PAGE_AXIS));
		
		for (MissingComponentPanel mcp: missingComponentPanels)
			containerPanel.add(mcp);
		
		this.add(containerPanel);
	}
}