import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Person {
	Color P_GREEN = new Color(202,255,191);
	Color P_BLUE = new Color(160,196,255);
	Color P_RED = new Color(255,173,173);
	Color P_ORANGE= new Color (255, 214, 165);
	int x, y;
	int vx, vy;
	int age;
	int status = 0;
	boolean isVaccinated = true;
	boolean isMasked = true;
	double deathChance; 
	double hygieneLevel;
	double contagiousness;
	int healingTime;
	// Integer that counts the total number of infected.
	static int numInfected = 0;
	static int numRecovered = 0;
	static int numDeceased = 0;
	//Constructor for the Person objects
	public Person(double iLevel, double mLevel, double hLevel, double cLevel, 
				  double lLevel, double sLevel, double vLevel, int timeSpan) {
		
		// Creates a starting coordinate for each Person object within window limits.
		deathChance = lLevel;
		hygieneLevel = hLevel;
		contagiousness = cLevel;
		x = (int)(Math.random()*790+0);
		y = (int)(Math.random()*580+0);
		age = (int)(Math.random()*(84+1)+16);
		if(Math.random()<0.01*vLevel) {
			isVaccinated = false;
			deathChance *= 0.05;

		}
		if(Math.random()<0.01*mLevel) {
			isMasked = false;
		}
		
		// Initiates a percentage of the starting population to be infected.
		if(Math.random()<(0.01*iLevel)) {
			status = 1;
			numInfected++;
		}
		// Social distancing is emulated by making a portion of the population have zeroed vx and vy values.
		// This causes a reduction in a objects chance of contraction but doesn't nullify it.
		if(Math.random()>(sLevel)) {
			vx  = (int)(Math.random()*(6+1)+-3);
			vy  = (int)(Math.random()*(6+1)+-3);
		}
		
		healingTime = (int)(Math.random()*(14000-3000+1)+3000)+(age*100);
		
	}
	


	public void collision(Person p2) {
		
		//Represent the Person objects asa Rectangles for simple collision detection
		Rectangle hitboxP1 = new Rectangle(p2.x,p2.y, 10,10);
		Rectangle hitboxP2 = new Rectangle(this.x,this.y, 10,10);
		
		//collision check
		if(hitboxP1.intersects(hitboxP2)) {
			//infection only happens if one person is infected and the other has never
			//been infected before
			if(this.status==1 && p2.status==0) {
				if(isMasked) {
					if(Math.random() < 0.5*(1+hygieneLevel)*(1+contagiousness) ) {
						p2.status = 1;
						numInfected++; //add to total count of infected people
					}
				}
			}
			else if(this.status==0 && p2.status==1) { //case person 2 is infected and person 1 is
				if(this.isMasked) {
					if(Math.random() < 0.5*(1+hygieneLevel)*(1+contagiousness)) {
						this.status = 1;
						numInfected++; //add to total count of infected people
					}
				}
			}			
		}

	}
		
	public void paint(Graphics g) {
		
		//set the color of the Person object based on the health status
		switch(status) {
			case 0: //normal
				g.setColor(P_GREEN);
				break;
			case 1: //infected
				g.setColor(P_RED);
				break;
			case 2: //recovered
				g.setColor(P_BLUE);
				vx=0;
				vy=0;
				break;
			case 3:
				g.setColor(Color.LIGHT_GRAY);
				vx=0;
				vy=0;
				break;
		}
		
		//If person is infected, they eventually recover so that they don't 
		//infect people forever. 
		if(status ==1) {
			//healingTime update
			healingTime-=16;
			
			//once the person has been given enough time, they will be considered recovered
			if(healingTime<=0) {
				if(Math.random() < (1-deathChance)) {
					status = 2;
					numInfected--;
					numRecovered++;
				}
				else {
					status = 3;
					numInfected--;
					numDeceased++;
				}
			}
		}
		
		//x and y components are updated based on their velocities
		x += vx;
		y += vy;
		
		//code to have the Person objects bounce off the borders
		if(x < 0 || x >= 790) {
			vx *= -1;
		}
		
		//bounce off the top and bottom
		if(y < 0 || y >= 590) {
			vy *= -1;
		}
		
		//draw the oval representign the Person object
		if(isVaccinated) {
			if(isMasked) {
				g.drawOval(x, y, 10, 10);
			}
			else {
				g.drawRect(x, y, 10, 10);
			}
		}
		else {
			if(isMasked) {
				g.fillOval(x, y, 10, 10);
			}
			else {
				g.fillRect(x, y, 10, 10);
			}
		}
		
	}
}
