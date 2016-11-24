package PaSkCode;
import java.util.ArrayList;
import java.awt.event.*;

public class GWModel extends PSysModel {
	
	ArrayList <Sprite> botList;
	ArrayList <Sprite> projectileList;
	protected Sprite player;

    GWModel() {
    	sList = new ArrayList<Sprite>();
    }
    
    void update(int bw, int bh) {
    	for (int i=0; i<sList.size(); i++) {
	    	sList.get(i).update(bw, bh);
    	}
    }
    
    void addBot(int rad, int x, int y, int vx, int vy, Image img) {
    	sprite = new Sprite(rad, x, y, vx, vy, img);
    	botList.add(sprite);
    	sprite.draw();
    }
    
    void addPlayer(int rad, int x, int y, int vx, int vy, Image pImg){
    	Sprite player = new Sprite(rad, x, y, vx, vy , pImg);
    	player.draw();
    }
    
    void addGraveStone(){
    	Sprite gravestone = new Sprite(rad, x, y, vx, vy , gImg);
    	gravestone.draw();
    }
    
    void addProjectile(){
    	Sprite projectile = new Sprite(Sprite.radius, Sprite.x+f(w)*(Sprite.radius+10),Sprite.y+f(w)*(Sprite.radius + 10), Sprite.velX, Sprite.velY, projectileImg);
    	projectileList.add(projectile);
    	projectile.draw();
    }
    
    void keyPressed(KeyEvent e){
    	
    }
    
    void keyReleased(KeyEvent e){
    	
    }
    
    void orientBots(){
    	for (Sprite b: botList){
    		b.velX = player.velX;
    		b.velY = player.velY;
    	}
    }
    
    void checkCollide(){
       for (Sprite p: projectileList)
    	  isOverlap(player, p);
    	  for (Sprite b: botList){
    		  isOverlap(b,p);
    	  }
    }
    
    boolean isOverlap(Particle p1, Particle p2) {
        int diffX = Math.abs(p1.x - p2.x);
        int diffY = Math.abs(p1.y - p2.y);
        if (diffX < p1.radius + p2.radius
            && diffY < p1.radius + p2.radius)
            return true;
        else
            return false;
    }

    
}
