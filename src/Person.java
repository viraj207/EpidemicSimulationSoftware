import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Person {
	Color P_GREEN = new Color(202,255,191);
	Color P_BLUE = new Color(160,196,255);
	Color P_RED = new Color(255,173,173);
	Color P_ORANGE= new Color (255, 214, 165);
	int x, y; // Integers representing the X AND Y position of this Person object.
	int xVelocity, yVelocity; // Integers representing the magnitude of X AND Y movement. Allowing us to determine direction aswell. 
	int age; 
	int infectionStatus = 0; // 0 -> healthy, 1 -> infected, 2 -> recovered and 3 -> deceased.
	boolean isVaccinated = true , isMasked = true;
	double deathChance, hygieneLevel, contagiousness;
	int healingTime;
	static int numTotal = 0, numInfected = 0, numRecovered = 0, numDeceased = 0;
	
	public Person(int pLevel,double iLevel, double mLevel, double hLevel, double cLevel, 
				  double lLevel, double sLevel, double vLevel, int tLevel) {
		
		numTotal = pLevel; // Assigning of the population size. 
		deathChance = lLevel; // Assigning of the lethality. 
		hygieneLevel = hLevel; // Assigning of hygiene levels.
		contagiousness = cLevel; // Assigning of contagiousness.
		
		/* Randomises the position of referenced 
		Person object to within the screen limits */
		x = (int)(Math.random()*690+0);
		y = (int)(Math.random()*480+0);
		
		
		age = (int)(Math.random()*(84+1)+16); // Generates a random age for the Person between 16 and 85
		if(Math.random()<0.01*vLevel) {
			isVaccinated = false;
			deathChance *= 0.05;

		}
		if(Math.random()<0.01*mLevel) { // Generates a certain percentage of the population to be masked.
			isMasked = false;
		}
		
		if(Math.random()<(0.01*iLevel)) { // Generates a certain percentage of the population to be intially infected.
			infectionStatus = 1;
			numInfected++;
		}
		
		if(Math.random()>(sLevel)) { // Generates a certain starting velocity for the Person.
			xVelocity  = (int)(Math.random()*(6+1)+-3);
			yVelocity  = (int)(Math.random()*(6+1)+-3);
		}
		
		healingTime = (int)(Math.random()*(14000-3000+1)+3000)+(age*100); // Time of recovery is determined by randomness aswell as, age.
		
	}
	



	public void collision(Person p2) {
		
		// Hit boxs equaling the size of Person node are used to check for collisions. 
		Rectangle hitboxP1 = new Rectangle(p2.x,p2.y, 10,10);
		Rectangle hitboxP2 = new Rectangle(this.x,this.y, 10,10);
		
		/// Conditional check; if two squares intersect they're marked as collison. Each node is checked respectively.
		if(hitboxP1.intersects(hitboxP2)) {
			// Depending on masked status, virus contagiousness and hygiene level. Risks of infection is varied.
			if(this.infectionStatus==1 && p2.infectionStatus==0) {
				if(isMasked) {
					if(Math.random() < 0.5*(1+hygieneLevel)*(1+contagiousness) ) {
						p2.infectionStatus = 1;
						numInfected++; 
					}
				}
			}
			else if(this.infectionStatus==0 && p2.infectionStatus==1) {
				if(this.isMasked) {
					if(Math.random() < 0.5*(1+hygieneLevel)*(1+contagiousness)) {
						this.infectionStatus = 1;
						numInfected++; 
					}
				}
			}			
		}

	}
		
	public void paint(Graphics g2d) {
		// Switch statement to each classify status of infection and therefore, change colour indicators.
		switch(infectionStatus) {
			case 0: 
				g2d.setColor(P_GREEN);
				break;
			case 1: 
				g2d.setColor(P_RED);
				break;
			case 2: 
				g2d.setColor(P_BLUE);
				xVelocity=0;
				yVelocity=0;
				break;
			case 3:
				g2d.setColor(Color.LIGHT_GRAY);
				xVelocity=0;
				yVelocity=0;
				break;
		}
		
		if(infectionStatus ==1) {
			// If a person is infected, they immediately begin to heal. Each tick reducing their healingTime left.
			healingTime-=16;			
			if(healingTime<=0) {
				if(Math.random() < (1-deathChance)) {
					infectionStatus = 2;
					numInfected--;
					numRecovered++;
				}
				else {
					infectionStatus = 3;
					numInfected--;
					numDeceased++;
				}
			}
		}
		
		// X and Y position are changed by constant vector; representing linear movement.
		x += xVelocity;
		y += yVelocity;
		
		
		if(x < 0 || x >= 690) {
			xVelocity *= -1;
		}
		
		
		if(y < 0 || y >= 490) {
			yVelocity *= -1;
		}
		
		
		if(isVaccinated) {
			if(isMasked) {
				g2d.drawOval(x, y, 10, 10);
			}
			else {
				g2d.drawRect(x, y, 10, 10);
			}
		}
		else {
			if(isMasked) {
				g2d.fillOval(x, y, 10, 10);
			}
			else {
				g2d.fillRect(x, y, 10, 10);
			}
		}
		
	}
	public static int getTotal() {
		return numTotal;
	}
	public static int getInfected() {
		return numInfected;
	}
	public static int getDeceased() {
		return numDeceased;
	}
	public static int getRecovered() {
		return numRecovered;
	}
}
