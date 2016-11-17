import java.util.ArrayList;

public class GWModel extends PSysModel {
	
	ArrayList <Sprite> sList;
	protected Sprite player;
	
    GWModel() {
    	sList = new ArrayList<Sprite>();
    }
    
    void addBot(int rad, int x, int y, int vx, int vy, Image img) {
    	sprite = new Sprite(rad, x, y, vx, vy, img);
    	sList.add(sprite);
    	s.draw();
    }
    
    void update(int bw, int bh) {
    	for (int i=0; i<sList.size(); i++) {
	    	sList.get(i).update(bw, bh);
    	}
    }
    
    void addPlayer(int rad, int x, int y, int vx, int vy, Image pImg){
    	Sprite player = new Sprite(rad, x, y, vx, vy , pImg);
    	player.draw();
    }
    
    void keyPressed(KeyEvent e){
    	
    }
    
    void keyReleased(KeyEvent e){
    	
    }
    
    void orientBots(){
    	for (Sprite s: sList){
    		s.velX = player.velX;
    		s.velY = player.velY;
    	}
    }
    
    void checkCollide(){
    	for(int i=0; i<sList.size(); i++){
    		sList.get(i).x==sList.get(i+1).x && sList.get(i).y==sList.get(i+1).
    	}
    }
}
