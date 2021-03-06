
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class InputTable extends JFrame implements ActionListener {

    private Container c;
    private JLabel title;
    private JLabel populationLabel;
    private JTextField populationField;
    private JLabel infectedLabel;
    private JTextField infectedField;
    private JLabel hygieneLabel;
    private JRadioButton hygieneLow, hygieneMedium, hygieneHigh, hygieneAbsolute;
    private ButtonGroup hygieneGroup;
    private JLabel maskedLabel;
    private JTextField maskedField;
    private JLabel contagiousnessLabel;
    private JRadioButton contagiousnessLow, contagiousnessMedium, contagiousnessHigh, contagiousnessAbsolute;
    private ButtonGroup contagiousnessGroup;
    private JLabel lethality;
    private JRadioButton lethalityLow, lethalityMedium, lethalityHigh, lethalityAbsolute;
    private ButtonGroup lethalityGroup;
    private JLabel socialDistancing;
    private JRadioButton socialDistancingLow, socialDistancingMedium, socialDistancingHigh, socialDistancingAbsolute;
    private ButtonGroup socialDistancingGroup;
    private JLabel vaccinationLabel;
    private JTextField vaccinationField;
    private JLabel timeSpanLabel;
    private JTextField timeSpanField;
    private JButton simButton;
    private JButton resetButton;
    
    /* The following section declares default values for each text field input */
    private final String DEFAULT_POPULATION = "200";
    private final String DEFAULT_INFECTED = "8";
    private final String DEFAULT_MASKED = "100";
    private final String DEFAULT_VACCINATED = "100";
    private final String DEFAULT_DAYS = "30";

    public InputTable() {
        setTitle("Epidemic Simulation - Setup");
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setResizable(false);
        setBackground(Color.gray);
        c = getContentPane();
        c.setLayout(null);
        
        title = new JLabel("Simulation Settings");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(250, 30);
        c.add(title);

        populationLabel = new JLabel("Population Size:");
        populationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        populationLabel.setSize(200, 20);
        populationLabel.setLocation(100, 100);
        c.add(populationLabel);

        populationField = new JTextField();
        populationField.setFont(new Font("Arial", Font.PLAIN, 15));
        populationField.setSize(330, 20);
        populationField.setLocation(320, 100);
        populationField.setText(DEFAULT_POPULATION);
        c.add(populationField);

        infectedLabel = new JLabel("Intially Infected (%):");
        infectedLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        infectedLabel.setSize(200, 20);
        infectedLabel.setLocation(100, 130);
        c.add(infectedLabel);

        infectedField = new JTextField();
        infectedField.setFont(new Font("Arial", Font.PLAIN, 15));
        infectedField.setSize(330, 20);
        infectedField.setLocation(320, 130);
        infectedField.setText(DEFAULT_INFECTED);
        c.add(infectedField);

        maskedLabel = new JLabel("Masked (%):");
        maskedLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        maskedLabel.setSize(200, 20);
        maskedLabel.setLocation(100, 160);
        c.add(maskedLabel);

        maskedField = new JTextField();
        maskedField.setFont(new Font("Arial", Font.PLAIN, 15));
        maskedField.setSize(330, 20);
        maskedField.setLocation(320, 160);
        maskedField.setText(DEFAULT_MASKED);
        c.add(maskedField);

        hygieneLabel = new JLabel("Hygiene Level (est.):");
        hygieneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        hygieneLabel.setSize(225, 25);
        hygieneLabel.setLocation(100, 190);
        c.add(hygieneLabel);

        hygieneLow = new JRadioButton("Low");
        hygieneLow.setFont(new Font("Arial", Font.PLAIN, 15));
        hygieneLow.setSelected(true);
        hygieneLow.setActionCommand(".25");
        hygieneLow.setSize(75, 20);
        hygieneLow.setLocation(320, 190);
        c.add(hygieneLow);

        hygieneMedium = new JRadioButton("Medium");
        hygieneMedium.setFont(new Font("Arial", Font.PLAIN, 15));
        hygieneMedium.setActionCommand(".50");
        hygieneMedium.setSelected(false);
        hygieneMedium.setSize(125, 20);
        hygieneMedium.setLocation(390, 190);
        c.add(hygieneMedium);

        hygieneHigh = new JRadioButton("High");
        hygieneHigh.setFont(new Font("Arial", Font.PLAIN, 15));
        hygieneHigh.setActionCommand(".75");
        hygieneHigh.setSelected(false);
        hygieneHigh.setSize(75, 20);
        hygieneHigh.setLocation(495, 190);
        c.add(hygieneHigh);

        hygieneAbsolute = new JRadioButton("Absolute");
        hygieneAbsolute.setFont(new Font("Arial", Font.PLAIN, 15));
        hygieneAbsolute.setActionCommand("1.0");
        hygieneAbsolute.setSelected(false);
        hygieneAbsolute.setSize(125, 20);
        hygieneAbsolute.setLocation(565, 190);
        c.add(hygieneAbsolute);

        hygieneGroup = new ButtonGroup();
        hygieneGroup.add(hygieneLow);
        hygieneGroup.add(hygieneMedium);
        hygieneGroup.add(hygieneHigh);
        hygieneGroup.add(hygieneAbsolute);
        hygieneAbsolute.setSelected(true);

        contagiousnessLabel = new JLabel("Contagiousness (est.): ");
        contagiousnessLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        contagiousnessLabel.setSize(225, 25);
        contagiousnessLabel.setLocation(100, 220);
        c.add(contagiousnessLabel);

        contagiousnessLow = new JRadioButton("Low");
        contagiousnessLow.setFont(new Font("Arial", Font.PLAIN, 15));
        contagiousnessLow.setActionCommand("0.25");
        contagiousnessLow.setSelected(true);
        contagiousnessLow.setSize(75, 20);
        contagiousnessLow.setLocation(320, 220);
        c.add(contagiousnessLow);

        contagiousnessMedium = new JRadioButton("Medium");
        contagiousnessMedium.setFont(new Font("Arial", Font.PLAIN, 15));
        contagiousnessMedium.setActionCommand("0.50");
        contagiousnessMedium.setSelected(false);
        contagiousnessMedium.setSize(125, 20);
        contagiousnessMedium.setLocation(390, 220);
        c.add(contagiousnessMedium);

        contagiousnessHigh = new JRadioButton("High");
        contagiousnessHigh.setFont(new Font("Arial", Font.PLAIN, 15));
        contagiousnessHigh.setActionCommand("0.75");
        contagiousnessHigh.setSelected(false);
        contagiousnessHigh.setSize(75, 20);
        contagiousnessHigh.setLocation(495, 220);
        c.add(contagiousnessHigh);

        contagiousnessAbsolute = new JRadioButton("Absolute");
        contagiousnessAbsolute.setFont(new Font("Arial", Font.PLAIN, 15));
        contagiousnessAbsolute.setActionCommand("1.00");
        contagiousnessAbsolute.setSelected(false);
        contagiousnessAbsolute.setSize(125, 20);
        contagiousnessAbsolute.setLocation(565, 220);
        c.add(contagiousnessAbsolute);

        contagiousnessGroup = new ButtonGroup();
        contagiousnessGroup.add(contagiousnessLow);
        contagiousnessGroup.add(contagiousnessMedium);
        contagiousnessGroup.add(contagiousnessHigh);
        contagiousnessGroup.add(contagiousnessAbsolute);

        lethality = new JLabel("Lethality (est.): ");
        lethality.setFont(new Font("Arial", Font.PLAIN, 20));
        lethality.setSize(190, 20);
        lethality.setLocation(100, 250);
        c.add(lethality);

        lethalityLow = new JRadioButton("Low");
        lethalityLow.setFont(new Font("Arial", Font.PLAIN, 15));
        lethalityLow.setActionCommand("0.001");
        lethalityLow.setSelected(true);
        lethalityLow.setSize(75, 20);
        lethalityLow.setLocation(320, 250);
        c.add(lethalityLow);

        lethalityMedium = new JRadioButton("Medium");
        lethalityMedium.setFont(new Font("Arial", Font.PLAIN, 15));
        lethalityMedium.setActionCommand("0.01");
        lethalityMedium.setSelected(false);
        lethalityMedium.setSize(125, 20);
        lethalityMedium.setLocation(390, 250);
        c.add(lethalityMedium);

        lethalityHigh = new JRadioButton("High");
        lethalityHigh.setFont(new Font("Arial", Font.PLAIN, 15));
        lethalityHigh.setActionCommand("0.1");
        lethalityHigh.setSelected(false);
        lethalityHigh.setSize(75, 20);
        lethalityHigh.setLocation(495, 250);
        c.add(lethalityHigh);

        lethalityAbsolute = new JRadioButton("Absolute");
        lethalityAbsolute.setFont(new Font("Arial", Font.PLAIN, 15));
        lethalityAbsolute.setActionCommand("1.0");
        lethalityAbsolute.setSelected(false);
        lethalityAbsolute.setSize(125, 20);
        lethalityAbsolute.setLocation(565, 250);
        c.add(lethalityAbsolute);

        lethalityGroup = new ButtonGroup();
        lethalityGroup.add(lethalityLow);
        lethalityGroup.add(lethalityMedium);
        lethalityGroup.add(lethalityHigh);
        lethalityGroup.add(lethalityAbsolute);

        socialDistancing = new JLabel("S.D Level (est.): ");
        socialDistancing.setFont(new Font("Arial", Font.PLAIN, 20));
        socialDistancing.setSize(225, 20);
        socialDistancing.setLocation(100, 280);
        c.add(socialDistancing);

        socialDistancingLow = new JRadioButton("Low");
        socialDistancingLow.setFont(new Font("Arial", Font.PLAIN, 15));
        socialDistancingLow.setActionCommand("0.20");
        socialDistancingLow.setSelected(true);
        socialDistancingLow.setSize(75, 20);
        socialDistancingLow.setLocation(320, 280);
        c.add(socialDistancingLow);

        socialDistancingMedium = new JRadioButton("Medium");
        socialDistancingMedium.setFont(new Font("Arial", Font.PLAIN, 15));
        socialDistancingMedium.setActionCommand("0.40");
        socialDistancingMedium.setSelected(false);
        socialDistancingMedium.setSize(125, 20);
        socialDistancingMedium.setLocation(390, 280);
        c.add(socialDistancingMedium);

        socialDistancingHigh = new JRadioButton("High");
        socialDistancingHigh.setFont(new Font("Arial", Font.PLAIN, 15));
        socialDistancingHigh.setActionCommand("0.60");
        socialDistancingHigh.setSelected(false);
        socialDistancingHigh.setSize(75, 20);
        socialDistancingHigh.setLocation(495, 280);
        c.add(socialDistancingHigh);

        socialDistancingAbsolute = new JRadioButton("Absolute");
        socialDistancingAbsolute.setFont(new Font("Arial", Font.PLAIN, 15));
        socialDistancingAbsolute.setActionCommand("0.80");
        socialDistancingAbsolute.setSelected(false);
        socialDistancingAbsolute.setSize(125, 20);
        socialDistancingAbsolute.setLocation(565, 280);
        c.add(socialDistancingAbsolute);

        socialDistancingGroup = new ButtonGroup();
        socialDistancingGroup.add(socialDistancingLow);
        socialDistancingGroup.add(socialDistancingMedium);
        socialDistancingGroup.add(socialDistancingHigh);
        socialDistancingGroup.add(socialDistancingAbsolute);
        socialDistancingAbsolute.setSelected(true);

        vaccinationLabel = new JLabel("Vaccinated (%): ");
        vaccinationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        vaccinationLabel.setSize(225, 20);
        vaccinationLabel.setLocation(100, 310);
        c.add(vaccinationLabel);

        vaccinationField = new JTextField();
        vaccinationField.setFont(new Font("Arial", Font.PLAIN, 15));
        vaccinationField.setSize(330, 20);
        vaccinationField.setLocation(320, 310);
        vaccinationField.setText(DEFAULT_VACCINATED);
        c.add(vaccinationField);

        timeSpanLabel = new JLabel("Time span (days):");
        timeSpanLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        timeSpanLabel.setSize(225, 20);
        timeSpanLabel.setLocation(100, 340);
        c.add(timeSpanLabel);

        timeSpanField = new JTextField();
        timeSpanField.setFont(new Font("Arial", Font.PLAIN, 15));
        timeSpanField.setSize(330, 20);
        timeSpanField.setLocation(320, 340);
        timeSpanField.setText(DEFAULT_DAYS);
        c.add(timeSpanField);

        simButton = new JButton("Simulate");
        simButton.setFont(new Font("Arial", Font.PLAIN, 15));
        simButton.setSize(100, 20);
        simButton.setLocation(250, 400);
        simButton.addActionListener(this);
        c.add(simButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 15));
        resetButton.setSize(100, 20);
        resetButton.setLocation(400, 400);
        resetButton.addActionListener(this);
        c.add(resetButton);

        this.addListeners();
        this.validateInputs();

        setVisible(true);
    }

    private void addListeners() {
        DocumentListener dl = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInputs(); // Calls validation function. Every time a user-typed input is given.
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInputs(); 
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInputs();
            }
        };
        /* Each of the following lines is working to add a document listener 
        to each field so live error handling can work */
        this.populationField.getDocument().addDocumentListener(dl); 
        this.infectedField.getDocument().addDocumentListener(dl);
        this.maskedField.getDocument().addDocumentListener(dl);
        this.vaccinationField.getDocument().addDocumentListener(dl);
        this.timeSpanField.getDocument().addDocumentListener(dl);
    }

    private void validateInputs() {
        ArrayList<String> errMessages = new ArrayList<>();
        boolean invalidInput = false;
        
        int popSize = isNumeric(populationField.getText()) ? Integer.parseInt(populationField.getText()) : -1;
        double iLevel = isNumericD(infectedField.getText()) ? Double.parseDouble(infectedField.getText()) : -1d;
        double mLevel = isNumericD(maskedField.getText()) ? Double.parseDouble(maskedField.getText()) : -1d;
        double vLevel = isNumericD(vaccinationField.getText()) ? Double.parseDouble(vaccinationField.getText()) : -1d;
        int timeSpan = isNumericD(timeSpanField.getText()) ? Integer.parseInt(timeSpanField.getText()) : -1;

        if (popSize < 1) {
            invalidInput = true;
            errMessages.add("Population size needs to be greater than 1");
        }
        
        if (popSize > 1000) {
            invalidInput = true;
            errMessages.add("Population size can not be greater than 1000");
        }

        if (!isPercentage(iLevel)) {
            invalidInput = true;
            errMessages.add("Initially infected value is not a valid percentage");
        }
        
        if (!isPercentage(mLevel)) {
            invalidInput = true;
            errMessages.add("Masked value is not a valid percentage");
        }
        
        if (!isPercentage(vLevel)) {
            invalidInput = true;
            errMessages.add("Vaccinated value is not a valid percentage");
        }
        
        if (timeSpan < 1) {
            invalidInput = true;
            errMessages.add("Time span value needs to be greater than 0");
        }
        
        if(invalidInput){
            this.simButton.setEnabled(false);
            this.simButton.setToolTipText(getErrMessages(errMessages));
        }else{
            this.simButton.setEnabled(true);
            this.simButton.setToolTipText(null);
        }
        
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
    }
    
    private String getErrMessages(ArrayList<String> errMessages){
        String errMessage = "<html><font color=\"red\">";
        for(String s : errMessages){
            errMessage += " - "+s;
            errMessage += "<br>";
        }
        errMessage += "</font></html>";
        return errMessage;
    }

    private boolean isPercentage(double d){
        return !(d < 0.0d || d > 100.0d);
    }
    
    private boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean isNumericD(String str) {
        if (str == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == simButton) {
            int popSize = Integer.parseInt(populationField.getText());
            double iLevel = Double.parseDouble(infectedField.getText());
            double mLevel = Double.parseDouble(maskedField.getText());
            double hLevel = Double.parseDouble(hygieneGroup.getSelection().getActionCommand());
            double cLevel = Double.parseDouble(contagiousnessGroup.getSelection().getActionCommand());
            double lLevel = Double.parseDouble(lethalityGroup.getSelection().getActionCommand());
            double sLevel = Double.parseDouble(socialDistancingGroup.getSelection().getActionCommand());
            double vLevel = Double.parseDouble(vaccinationField.getText());
            int timeSpan = Integer.parseInt(timeSpanField.getText());
            SimFrame c = new SimFrame(popSize, iLevel, mLevel, hLevel,
                    cLevel, lLevel, sLevel, vLevel, timeSpan);
            setVisible(false);
            dispose();

        } else if (e.getSource() == resetButton) {
            populationField.setText("");
            infectedField.setText("");
            maskedField.setText("");
            hygieneGroup.clearSelection();
            contagiousnessGroup.clearSelection();
            lethalityGroup.clearSelection();
            socialDistancingGroup.clearSelection();
            timeSpanField.setText("");
        }
    }
}
