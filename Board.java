package PaSkCode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private final int DELAY = 20;

    private int bdWidth = 400;
    private int bdHeight = 300;
    private int radius = 10;

    private int px;
    private int py;

    private int velX;
    private int velY;

    private int frame;
    private int logCycle;

    //GWModel gameWorld; // GameWorld model
    //GWView gameWorldView; // GameWorld view

    PSysModel pSystem; // Particle system model
    PSysView pSysView; // Particle system view

    public Board(int bw, int bh, ArrayList <String> pl) {

        initBoard(bw, bh, pl);
    }
    
    private void initBoard(int bw, int bh,
			   ArrayList <String> pl) {
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

	bdWidth = bw;
	bdHeight = bh;
	px = bdWidth/2;
	py = bdHeight/2;
	frame = 0;

	setupPS(pl);
        timer = new Timer(DELAY, this);
        timer.start();        
    }

    @Override
	public void actionPerformed(ActionEvent e) {

	if (frame % 30 == 0) {
	    System.out.println("Frame: " + frame);
	    //gameWorld.orientBots(); // point bots toward player
	}
	frame++;
	// all simulation state change code should be wrapped here
	if (frame == logCycle) {
	    //pSysView.dump(pSystem, logCycle);
	}
	update();
        repaint();
	//gameWorld.checkCollide();
    }

    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);

	// all drawing code should be wrapped here
        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void update() {
	//gameWorld.update(bdWidth, bdHeight);
	pSystem.update(bdWidth, bdHeight);
    }

    private void doDrawing(Graphics g) {
	//gameWorldView.draw(gameWorld, g);
	pSysView.draw(pSystem, g);
	Graphics2D g2d = (Graphics2D) g;
    }

    private class TAdapter extends KeyAdapter {

        @Override
	    public void keyReleased(KeyEvent e) {
            //when key is released, run this code
	    //gameWorld.keyReleased(e);
        }

        @Override
	    public void keyPressed(KeyEvent e) {
            //when key is pressed, run this code
	    //gameWorld.keyPressed(e);
        }
    }

    void setupPS(ArrayList <String> pl) {
	logCycle = Integer.parseInt(pl.get(0));
	System.out.println("Log: " + logCycle);

//	gameWorld = new GWModel();
	pSystem = new PSysModel();

	Image img = null;
	img = getImage("../Images/Bot.jpg");

	for (int i=1; i<pl.size(); i++) {
	    String [] tokens = pl.get(i).split(" ");

	    if (tokens.length != 5) {
		System.out.println("Illegal file format; exit "
				   + tokens.length);
		break;
	    }
	    int rad = (int) (Double.parseDouble(tokens[0]) * bdWidth);
	    int npx = (int) (Double.parseDouble(tokens[1]) * bdWidth);
	    int npy = (int) (Double.parseDouble(tokens[2]) * bdHeight);
	    int nvx = (int) (Double.parseDouble(tokens[3]) * bdWidth);
	    int nvy = (int) (Double.parseDouble(tokens[4]) * bdHeight);

	    // add bot to gameWorld
	    if (img != null) {
		Image sImg = img.getScaledInstance(rad*2, rad*2, Image.SCALE_DEFAULT);
		//gameWorld.addBot(rad, npx, npy, nvx, nvy, sImg);
	    }

	    pSystem.add(rad, npx, npy, nvx, nvy);

	}

	// add player to gameWorld
	img = getImage("../Images/Skull.jpg");

	if (img != null) {
	    int playerRad = 25;
	    Image pImg = img.getScaledInstance(playerRad*2, playerRad*2,
					       Image.SCALE_DEFAULT);
	    //gameWorld.addPlayer(playerRad, bdWidth/2, bdHeight/2, 0, 0, pImg);
	}

	// add image of tombstone to gameWorld
	img = getImage("../Images/Stone.jpg");
	
	if (img != null) {
	    //gameWorld.addGraveStone(img);
	}

	//gameWorldView = new GWView();
	pSysView = new PSysView();

    }

    // Image getImage(String fp)
    // fp: file path for image file
    // loads image from image file
    Image getImage(String fp) {
	Image img = null;
	try {
	    img =
		ImageIO.read(this.getClass().getResource(fp));
	} catch (IOException ex) {
	}
	return img;
    }
    
}
