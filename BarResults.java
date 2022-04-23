
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class BarResults extends JPanel implements ActionListener {

    Component simframe;
    
    int healthy = 0;
    int recovered = 0;
    int infected = 0;
    int dead = 0;

    int population = 0;
    int spread_rate = 0;
    int day = 0;

    int current_day = -1;

    DetailedResults dr;
    
    ArrayList<DayData> all_dr;

    public BarResults(Component simframe, int population) {
        this.simframe = simframe;
        this.population = population;
    }

    public void setData(int healthy, int recovered, int infected, int dead, int spread_rate, int day) {
        this.healthy = healthy;
        this.recovered = recovered;
        this.infected = infected;
        this.dead = dead;
        this.spread_rate = spread_rate;
        this.day = day;

        // Update stored data
        if (this.day > current_day) { // record data
            current_day = this.day;
            all_dr.add(new DayData(this.healthy,this.recovered,this.infected,this.dead,this.spread_rate,this.day));
            
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawBarText(g2d);

        // Draw Bar seprating line
        g2d.drawLine(160, 20, 160, 145);

        drawBars(g2d, this.healthy, this.recovered, this.infected, this.dead, this.population);

        drawTextAR(g2d, "Starting population: " + this.population, 800, 45, Color.BLACK);
        drawTextAR(g2d, "Dead: " + this.dead, 800, 45 + 25, Color.BLACK);
        drawTextAR(g2d, "Spread rate: " + this.spread_rate + " per day", 800, 45 + (25 * 2), Color.BLACK);
        drawTextAR(g2d, "Day: " + this.day, 800, 45 + (25 * 3), Color.BLACK);
        

    }

    private void drawBars(Graphics2D g2d, int healthy, int recovered, int infected, int dead, int population) {
        int x = 160;
        int y = 25;

        int y_spacing = 30;

        int max_width = 300;
        int height = 25;

        g2d.setColor(Color.GREEN);
        g2d.fillRect(x, y, (int) ((float) healthy / (float) population * (float) max_width), height);
        drawTextAL(g2d, String.valueOf(healthy), x + (int) ((float) healthy / (float) population * (float) max_width) + 10, y + 17, Color.BLACK);
        g2d.setColor(Color.BLUE);
        g2d.fillRect(x, y + (y_spacing), (int) ((float) recovered / (float) population * (float) max_width), height);
        drawTextAL(g2d, String.valueOf(recovered), x + (int) ((float) recovered / (float) population * (float) max_width) + 10, y + 17 + (y_spacing), Color.BLACK);
        g2d.setColor(Color.RED);
        g2d.fillRect(x, y + (y_spacing * 2), (int) ((float) infected / (float) population * (float) max_width), height);
        drawTextAL(g2d, String.valueOf(infected), x + (int) ((float) infected / (float) population * (float) max_width) + 10, y + 17 + (y_spacing * 2), Color.BLACK);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x, y + (y_spacing * 3), (int) ((float) dead / (float) population * (float) max_width), height);
        drawTextAL(g2d, String.valueOf(dead), x + (int) ((float) dead / (float) population * (float) max_width) + 10, y + 17 + (y_spacing * 3), Color.BLACK);
    }

    private void drawBarText(Graphics2D g2d) {
        int x = 150;
        int y = 15;

        int y_spacing = 30;

        drawTextAR(g2d, "Healthy", x, y + y_spacing, Color.BLACK);
        drawTextAR(g2d, "Recovered", x, y + (y_spacing * 2), Color.BLACK);
        drawTextAR(g2d, "Infected", x, y + (y_spacing * 3), Color.BLACK);
        drawTextAR(g2d, "Dead", x, y + (y_spacing * 4), Color.BLACK);
    }

    /**
     * Draw Text (Aligned Right)
     *
     * @param g2d
     * @param textToWrite
     * @param x
     * @param y
     * @param colour
     */
    private void drawTextAR(Graphics2D g2d, String string, int x, int y, Color colour) {
        FontRenderContext frc = g2d.getFontRenderContext();
        Font font = new Font("Open Sans", Font.PLAIN, 22);
        Rectangle2D bounds1 = font.getStringBounds(string, frc);

        g2d.setColor(colour);
        g2d.setFont(font);
        g2d.drawString(string, x - (int) bounds1.getWidth(), y);
    }

    private void drawTextAL(Graphics2D g2d, String string, int x, int y, Color colour) {
        FontRenderContext frc = g2d.getFontRenderContext();
        Font font = new Font("Open Sans", Font.PLAIN, 22);
        Rectangle2D bounds1 = font.getStringBounds(string, frc);

        g2d.setColor(colour);
        g2d.setFont(font);
        g2d.drawString(string, x, y);
    }

    public void init() {
        JFrame frame = new JFrame("Epidemic simulator - Statistics");
        frame.add(this);
        frame.setSize(850, 230);
        frame.setLocation(this.simframe.getLocation().x + 
                (this.simframe.getWidth() / 2) - 
                (frame.getWidth() / 2),this.simframe.getLocation().y + this.simframe.getHeight() + 10);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        all_dr = new ArrayList<DayData>();

        //view results button
        this.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton viewDetailsButton = new JButton("View Details");
        buttonPanel.add(viewDetailsButton);
        this.add(buttonPanel, BorderLayout.SOUTH);
		Timer t2 = new Timer(10,this);
		t2.restart();
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dr != null) {
                    dr.destroy();
                }
                dr = new DetailedResults(all_dr,population);
            }
        });

        this.setBackground(Color.WHITE);
    }

    public void update() {
        this.repaint();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(BarResults.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

class DayData {
    
    private int healthy;
    private int recovered;
    private int infected;
    private int dead;
    private int spread_rate;
    private double r_number;
    private int day;
    
    public DayData(int healthy, int recovered, int infected, int dead, int spread_rate,int day){
        this.healthy = healthy;
        this.recovered = recovered;
        this.infected = infected;
        this.dead = dead;
        this.spread_rate = spread_rate;
        this.day = day;
    }

    public int getHealthy() {
        return healthy;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getInfected() {
        return infected;
    }

    public int getDead() {
        return dead;
    }

    public int getSpread_rate() {
        return spread_rate;
    }
    public double getR_number() {
    	return r_number;
    }

    public int getDay() {
        return day;
    }
    
    
}
