import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;

class InputTable extends JFrame implements ActionListener {

		// Components of the Form
		private Container c;
		private JLabel title;
		private JLabel populationSize;
		private JTextField tpopulationSize;
		private JLabel intiallyInfected;
		private JTextField tintiallyInfected;
		private JLabel hygieneLevel;
		private JRadioButton hLow, hMedium, hHigh, hAbsolute;
		private ButtonGroup hygieneGroup;
		private JLabel maskedPercentage;
		private JTextField tmaskedPercentage;
		private JLabel contagiousness;
		private JRadioButton cLow, cMedium, cHigh, cAbsolute;
		private ButtonGroup contagiousnessGroup;
		private JLabel lethality;
		private JRadioButton lLow, lMedium, lHigh, lAbsolute;
		private ButtonGroup lethalityGroup;
		private JLabel socialDistancing;
		private JRadioButton sLow, sMedium, sHigh, sAbsolute;
		private ButtonGroup socialDistancingGroup;
		private JLabel vaccinatedPercentage;
		private JTextField tvaccinatedPercentage;
		private JLabel timeSpan;
		private JTextField ttimeSpan;
		private JButton sim;
		private JButton reset;
		
		public InputTable()
		{
			setTitle("Epidemic Simulation - Setup");
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(800,500);
			setResizable(false);
			setBackground(Color.gray);
			c = getContentPane();
			c.setLayout(null);

			title = new JLabel("Simulation Settings");
			title.setFont(new Font("Arial", Font.PLAIN, 30));
			title.setSize(300, 30);
			title.setLocation(250, 30);
			c.add(title);

			populationSize = new JLabel("Population Size:");
			populationSize.setFont(new Font("Arial", Font.PLAIN, 20));
			populationSize.setSize(200, 20);
			populationSize.setLocation(100, 100);
			c.add(populationSize);

			tpopulationSize = new JTextField();
			tpopulationSize.setFont(new Font("Arial", Font.PLAIN, 15));
			tpopulationSize.setSize(330, 20);
			tpopulationSize.setLocation(320, 100);
			c.add(tpopulationSize);

			intiallyInfected = new JLabel("Intially Infected (%):");
			intiallyInfected.setFont(new Font("Arial", Font.PLAIN, 20));
			intiallyInfected.setSize(200, 20);
			intiallyInfected.setLocation(100, 130);
			c.add(intiallyInfected);

			tintiallyInfected = new JTextField();
			tintiallyInfected.setFont(new Font("Arial", Font.PLAIN, 15));
			tintiallyInfected.setSize(330, 20);
			tintiallyInfected.setLocation(320, 130);
			c.add(tintiallyInfected);

			maskedPercentage = new JLabel("Masked (%):");
			maskedPercentage.setFont(new Font("Arial", Font.PLAIN, 20));
			maskedPercentage.setSize(200, 20);
			maskedPercentage.setLocation(100, 160);
			c.add(maskedPercentage);

			tmaskedPercentage = new JTextField();
			tmaskedPercentage.setFont(new Font("Arial", Font.PLAIN, 15));
			tmaskedPercentage.setSize(330, 20);
			tmaskedPercentage.setLocation(320, 160);
			c.add(tmaskedPercentage);
	 
			
			hygieneLevel = new JLabel("Hygiene Level (est.):");
			hygieneLevel.setFont(new Font("Arial", Font.PLAIN, 20));
			hygieneLevel.setSize(225, 25);
			hygieneLevel.setLocation(100, 190);
	        c.add(hygieneLevel);
	 
	        hLow = new JRadioButton("Low");
	        hLow.setFont(new Font("Arial", Font.PLAIN, 15));
	        hLow.setSelected(true);
	        hLow.setActionCommand(".25");
	        hLow.setSize(75, 20);
	        hLow.setLocation(320, 190);
	        c.add(hLow);
	 
	        hMedium = new JRadioButton("Medium");
	        hMedium.setFont(new Font("Arial", Font.PLAIN, 15));
	        hMedium.setActionCommand(".50");
	        hMedium.setSelected(false);
	        hMedium.setSize(125, 20);
	        hMedium.setLocation(390, 190);
	        c.add(hMedium);
	        
	        hHigh = new JRadioButton("High");
	        hHigh.setFont(new Font("Arial", Font.PLAIN, 15));
	        hHigh.setActionCommand(".75");
	        hHigh.setSelected(false);
	        hHigh.setSize(75, 20);
	        hHigh.setLocation(495, 190);
	        c.add(hHigh);
	        
	        hAbsolute = new JRadioButton("Absolute");
	        hAbsolute.setFont(new Font("Arial", Font.PLAIN, 15));
	        hAbsolute.setActionCommand("1.0");
	        hAbsolute.setSelected(false);
	        hAbsolute.setSize(125, 20);
	        hAbsolute.setLocation(565, 190);
	        c.add(hAbsolute);
	        
	        hygieneGroup = new ButtonGroup();
	        hygieneGroup.add(hLow);
	        hygieneGroup.add(hMedium);
	        hygieneGroup.add(hHigh);
	        hygieneGroup.add(hAbsolute);
			
			contagiousness = new JLabel("Contagiousness (est.): ");
			contagiousness.setFont(new Font("Arial", Font.PLAIN, 20));
			contagiousness.setSize(225, 25);
			contagiousness.setLocation(100, 220);
			c.add(contagiousness);
			
			cLow = new JRadioButton("Low");
			cLow.setFont(new Font("Arial", Font.PLAIN, 15));
			cLow.setActionCommand("0.25");
			cLow.setSelected(true);
			cLow.setSize(75, 20);
			cLow.setLocation(320, 220);
	        c.add(cLow);
	 
	        cMedium = new JRadioButton("Medium");
	        cMedium.setFont(new Font("Arial", Font.PLAIN, 15));
			cMedium.setActionCommand("0.50");
	        cMedium.setSelected(false);
	        cMedium.setSize(125, 20);
	        cMedium.setLocation(390, 220);
	        c.add(cMedium);
	        
	        cHigh = new JRadioButton("High");
	        cHigh.setFont(new Font("Arial", Font.PLAIN, 15));
			cHigh.setActionCommand("0.75");
	        cHigh.setSelected(false);
	        cHigh.setSize(75, 20);
	        cHigh.setLocation(495, 220);
	        c.add(cHigh);
	        
	        cAbsolute = new JRadioButton("Absolute");
	        cAbsolute.setFont(new Font("Arial", Font.PLAIN, 15));
			cAbsolute.setActionCommand("1.00");
	        cAbsolute.setSelected(false);
	        cAbsolute.setSize(125, 20);
	        cAbsolute.setLocation(565, 220);
	        c.add(cAbsolute); 
	        
	        contagiousnessGroup = new ButtonGroup();
	        contagiousnessGroup.add(cLow);
	        contagiousnessGroup.add(cMedium);
	        contagiousnessGroup.add(cHigh);
	        contagiousnessGroup.add(cAbsolute);
	        
	        lethality = new JLabel("Lethality (est.): ");
	        lethality.setFont(new Font("Arial", Font.PLAIN, 20));
	        lethality.setSize(190, 20);
	        lethality.setLocation(100, 250);
			c.add(lethality);

			lLow = new JRadioButton("Low");
			lLow.setFont(new Font("Arial", Font.PLAIN, 15));
			lLow.setActionCommand("0.001");
			lLow.setSelected(true);
			lLow.setSize(75, 20);
			lLow.setLocation(320, 250);
	        c.add(lLow);
	 
	        lMedium = new JRadioButton("Medium");
	        lMedium.setFont(new Font("Arial", Font.PLAIN, 15));
			lMedium.setActionCommand("0.01");
	        lMedium.setSelected(false);
	        lMedium.setSize(125, 20);
	        lMedium.setLocation(390, 250);
	        c.add(lMedium);
	        
	        lHigh = new JRadioButton("High");
	        lHigh.setFont(new Font("Arial", Font.PLAIN, 15));
			lHigh.setActionCommand("0.1");
	        lHigh.setSelected(false);
	        lHigh.setSize(75, 20);
	        lHigh.setLocation(495, 250);
	        c.add(lHigh);
	        
	        lAbsolute = new JRadioButton("Absolute");
	        lAbsolute.setFont(new Font("Arial", Font.PLAIN, 15));
	        lAbsolute.setActionCommand("1.0");
	        lAbsolute.setSelected(false);
	        lAbsolute.setSize(125, 20);
	        lAbsolute.setLocation(565, 250);
	        c.add(lAbsolute); 
	        
	        lethalityGroup = new ButtonGroup();
	        lethalityGroup.add(lLow);
	        lethalityGroup.add(lMedium);
	        lethalityGroup.add(lHigh);
	        lethalityGroup.add(lAbsolute);
			
			socialDistancing = new JLabel("S.D Level (est.): ");
			socialDistancing.setFont(new Font("Arial", Font.PLAIN, 20));
			socialDistancing.setSize(225, 20);
			socialDistancing.setLocation(100, 280);
			c.add(socialDistancing);

			sLow = new JRadioButton("Low");
			sLow.setFont(new Font("Arial", Font.PLAIN, 15));
	        sLow.setActionCommand("0.20");
			sLow.setSelected(true);
			sLow.setSize(75, 20);
			sLow.setLocation(320, 280);
	        c.add(sLow);
	 
	        sMedium = new JRadioButton("Medium");
	        sMedium.setFont(new Font("Arial", Font.PLAIN, 15));
	        sMedium.setActionCommand("0.40");
	        sMedium.setSelected(false);
	        sMedium.setSize(125, 20);
	        sMedium.setLocation(390, 280);
	        c.add(sMedium);
	        
	        sHigh = new JRadioButton("High");
	        sHigh.setFont(new Font("Arial", Font.PLAIN, 15));
	        sHigh.setActionCommand("0.60");
	        sHigh.setSelected(false);
	        sHigh.setSize(75, 20);
	        sHigh.setLocation(495, 280);
	        c.add(sHigh);
	        
	        sAbsolute = new JRadioButton("Absolute");
	        sAbsolute.setFont(new Font("Arial", Font.PLAIN, 15));
	        sAbsolute.setActionCommand("0.80");
	        sAbsolute.setSelected(false);
	        sAbsolute.setSize(125, 20);
	        sAbsolute.setLocation(565, 280);
	        c.add(sAbsolute); 
	        
	        socialDistancingGroup = new ButtonGroup();
	        socialDistancingGroup.add(sLow);
	        socialDistancingGroup.add(sMedium);
	        socialDistancingGroup.add(sHigh);
	        socialDistancingGroup.add(sAbsolute);
	        
	        vaccinatedPercentage = new JLabel("Vaccinated (%): ");
	        vaccinatedPercentage.setFont(new Font("Arial", Font.PLAIN, 20));
	        vaccinatedPercentage.setSize(225, 20);
	        vaccinatedPercentage.setLocation(100, 310);
			c.add(vaccinatedPercentage);
			
			tvaccinatedPercentage = new JTextField();
			tvaccinatedPercentage.setFont(new Font("Arial", Font.PLAIN, 15));
			tvaccinatedPercentage.setSize(330, 20);
			tvaccinatedPercentage.setLocation(320, 310);
			c.add(tvaccinatedPercentage);
		
			timeSpan = new JLabel("Time span (days):");
			timeSpan.setFont(new Font("Arial", Font.PLAIN, 20));
			timeSpan.setSize(225, 20);
			timeSpan.setLocation(100, 340);
			c.add(timeSpan);
			
			ttimeSpan = new JTextField();
			ttimeSpan.setFont(new Font("Arial", Font.PLAIN, 15));
			ttimeSpan.setSize(330, 20);
			ttimeSpan.setLocation(320, 340);
			c.add(ttimeSpan);

			sim = new JButton("Simulate");
			sim.setFont(new Font("Arial", Font.PLAIN, 15));
			sim.setSize(100, 20);
			sim.setLocation(250, 400);
			sim.addActionListener(this);
			c.add(sim);

			reset = new JButton("Reset");
			reset.setFont(new Font("Arial", Font.PLAIN, 15));
			reset.setSize(100, 20);
			reset.setLocation(400, 400);
			reset.addActionListener(this);
			c.add(reset);
			
			setVisible(true);
		}

		// method actionPerformed()
		// to get the action performed
		// by the user and act accordingly
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == sim) {
				System.out.println("Simulate!!!");
				int popSize = Integer.parseInt(tpopulationSize.getText());
				double iLevel = Double.parseDouble(tintiallyInfected.getText());
				double mLevel = Double.parseDouble(tmaskedPercentage.getText());
				double hLevel = Double.parseDouble(hygieneGroup.getSelection().getActionCommand());
				double cLevel = Double.parseDouble(contagiousnessGroup.getSelection().getActionCommand());
				double lLevel = Double.parseDouble(lethalityGroup.getSelection().getActionCommand());
				double sLevel = Double.parseDouble(socialDistancingGroup.getSelection().getActionCommand());
				double vLevel = Double.parseDouble(tvaccinatedPercentage.getText());
				int timeSpan = Integer.parseInt(ttimeSpan.getText());
				SimFrame c = new SimFrame(popSize,iLevel,mLevel,hLevel,
										  cLevel, lLevel, sLevel, vLevel, timeSpan);
				setVisible(false);
				dispose(); 

			}

			else if (e.getSource() == reset) {
	            tpopulationSize.setText("");
	            tintiallyInfected.setText("");
	            tmaskedPercentage.setText("");
	            hygieneGroup.clearSelection();
	            contagiousnessGroup.clearSelection();
	            lethalityGroup.clearSelection();
	            socialDistancingGroup.clearSelection();
	            ttimeSpan.setText("");
			}
		}
}

