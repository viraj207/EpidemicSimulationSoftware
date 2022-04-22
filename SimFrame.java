import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SimFrame extends JPanel implements ActionListener{
	Color P_WHITE = new Color(255, 255, 252); 
	ArrayList<Person> peopleList = new ArrayList<Person>(); // ArrayList containing each Person object.
	BarResults br; // BarResults object that draws the graphed data.
	int currTick = 0; // This is a clock that is incremented every 10ms. Allows us measure time spans.
	int currDay = 1; // This integer keeps track of the current day. Incremented in a accordance with the currTick
	int maxDay = 0; // This integer tells the program, how many days the simulation should run. This is user-determined.
	static int populationSize; // This integer keeps track of the total size of the population.
	boolean isSimOver = false; // This boolean flags the program "TRUE". if currDay >= maxDay.
	JFrame simFrame; // Initialisation of the JFrame responsible for the simulation.
	public static void main(String[] arg) {
        new InputTable(); // Starts the program by creating "Settings" / "InputTable".
	}
	
	public void paint(Graphics g2d) {
		currTick += 10; // Increment clock tick
		if(currTick % 1000 == 0) { // For each 100 ticks. Increment currentDay by one. 100 ticks == 1 day
			if(!isSimOver) {currDay++;} // Conditional check; if maxDay is NOT reached, increment.
			if(currDay == maxDay) { // Otherwise, flag the simulation over.
				isSimOver = true;
				
			}
		}
		/*
		 * Each individual Person object is responsible for keeping track of its own
		 * position in space as well as, its projection onto the simulation Window. This
		 * is done my for-each loop calling paint object on each Person object in the
		 * appropriate ArrayList.
		 */
		for(Person p: peopleList) {
			p.paint(g2d);
		}
		
		/*
		 * Each individual Person object is also responsible for checking for collision
		 * with other nodes. Therefore, a nest loop is used to compare each unique set
		 * of Person object and conditional check if they have collided.
		 */
		for(int i =0; i < peopleList.size();i++) {
			for(int j = i+1 ; j < peopleList.size();j++){
				peopleList.get(i).collision(peopleList.get(j));
			}
		}
	}
	
	/* constructor will setup our main Graphic User Interface - a simple Frame! */
	public SimFrame(int pLevel, double iLevel, double mLevel, double hLevel, double cLevel, double lLevel, 
					double sLevel, double vLevel, int tLevel) {
		maxDay = tLevel;
		populationSize = pLevel;
		br = new BarResults(populationSize);
		br.init();
		simFrame = new JFrame("Epidemic simulation");
		simFrame.setSize(700,500); 
		simFrame.setResizable(false);
		simFrame.setLocationRelativeTo(null);
		simFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int i = 0; i < populationSize; i++) {
			peopleList.add(new Person(populationSize,iLevel,mLevel,hLevel,cLevel,lLevel,sLevel,vLevel,tLevel));
		}
		Timer clock = new Timer(10, this); 
		clock.restart();
		simFrame.setContentPane(this);
		simFrame.getContentPane().setBackground(Color.gray);
		simFrame.setVisible(true);		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		new Random();
		Person p = peopleList.get(0);
		int healthy = p.getTotal() - p.getInfected() - p.getDeceased();
		int infectionRate = p.getInfected() / currDay;
		br.update();
		br.setData(healthy,p.getRecovered(),p.getInfected(),p.getDeceased(),infectionRate,currDay);
	}	
}
