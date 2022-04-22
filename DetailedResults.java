/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author eboat
 */
public final class DetailedResults {
    
    JFrame frame;
    
    public DetailedResults(ArrayList<DayData> data, int population){
        frame = new JFrame("");
        frame.setSize(400, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JTextArea resultsArea = new JTextArea(5,40);
        resultsArea.setEditable(false);
        resultsArea.setText(dataToString(data,population));
        frame.add(new JScrollPane(resultsArea,
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save...");
        buttonPanel.add(saveButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile(dataToString(data,population));
            }
        });
    }
    
   public void saveFile(String data){
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");  
       LocalDateTime now = LocalDateTime.now();  
       
       JFileChooser fc = new JFileChooser();
       fc.setDialogTitle("Save"); 
       fc.setSelectedFile(new File("VirusSimulation-"+dtf.format(now)+".txt"));
       
       int userSelection = fc.showSaveDialog(this.frame);
       
       if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fc.getSelectedFile();
            
           try {
               BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave.getAbsolutePath()));
               writer.write(data);
               writer.close();
           } catch (IOException ex) {
               System.err.println("COULDN'T SAVE TO FILENAME SPECIFIED!!");
           }
        }
   }
    
    public String dataToString(ArrayList<DayData> data, int population){
        String s = "";
        s += "Starting Population: "+population;
        s += "\n\n";
        for(DayData d : data){
            s += "Day: "+d.getDay()+"\n";
            s += "    Healthy: "+d.getHealthy()+"\n";
            s += "    Infected: "+d.getInfected()+"\n";
            s += "    Recovered: "+d.getRecovered()+"\n";
            s += "    Spread Rate: "+d.getSpread_rate()+"\n";
            s += "    Dead: "+d.getDead()+"\n";
            s += "\n";
        }
        return s;
    }
    
    public void destroy(){
        frame.dispose();
    }
    
}
