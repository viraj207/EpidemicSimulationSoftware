import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SimFrame extends JPanel implements ActionListener{
	Color P_WHITE = new Color(255, 255, 252);
	//store multiple Person and point objects
	ArrayList<Person> population = new ArrayList<Person>(); //the moving Person objects (circles)
	
	int time = 0; 
	int day = 1; 
	int maxDay = 0;
	static int populationSize;
	public static void main(String[] arg) {
        InputTable f = new InputTable();
        BarResults br = new BarResults(populationSize);  
        br.init(); 
	}
	
	/* paint method for drawing the simulation and animation */
	public void paint(Graphics g) {
		time += 10;
		if(time % 1000 == 0) {
			day++;
			if(day == maxDay) {
				System.out.println("********************************");
				System.out.printf("Total number of people: %d\n",populationSize);
				System.out.printf("Final number of infected people (After %d days): \n",maxDay);
				System.out.printf("Final number of recovered people(After %d days): \n",maxDay);
				System.out.printf("Final number of deceased people (After %d days): \n",maxDay);
				System.out.println("program end.");
				//System.exit(0);
			}
		}
		//as time passes, add a new point that keeps a track of the number of infected population
		//at that time. (to be used for the "graph"
		
		super.paintComponent(g); // a necessary call to the parent paint method for proper screen refreshing
		
		//paint the Person objects!
		for(Person p: population) {
			p.paint(g); //recall that each Person object has a paint method. We're passing g as the argument
		}
		
		//check for collision by generating unique pairs of population
		for(int i =0; i < population.size();i++) {
			for(int j = i+1 ; j < population.size();j++){
				//for each unique pair invoke the collision detection code
				population.get(i).collision(population.get(j));
			}
		}
	}
	
	/* constructor will setup our main Graphic User Interface - a simple Frame! */
	public SimFrame(int popSize, double iLevel, double mLevel, double hLevel, double cLevel, double lLevel, 
					double sLevel, double vLevel, int timeSpan) {
		maxDay = timeSpan;
		populationSize = popSize;
		JFrame frame = new JFrame("Epidemic simulation");		
		frame.setSize(800,600); //set the size
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int i = 0; i < populationSize; i++) {
			population.add(new Person(iLevel,mLevel,hLevel,cLevel,lLevel,sLevel,vLevel,timeSpan));
		}
		Timer t = new Timer(10, this); 
		t.restart(); 
		frame.setContentPane(this);
		frame.getContentPane().setBackground(Color.gray);
		frame.setVisible(true);		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}	
}
