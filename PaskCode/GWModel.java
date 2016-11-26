package PaSkCode;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.event.*;

public class GWModel extends PSysModel {
	
	ArrayList <Sprite> botList;
	ArrayList <Sprite> projectileList;
	protected Sprite player;
	Image tombstoneImage;
	Image projectileImage;

    GWModel() {
    	botList = new ArrayList<Sprite>();
    	projectileList = new ArrayList <Sprite>();
    	player = new Sprite();
    }
    
    void addBot(int rad, int x, int y, int vx, int vy, Image botImg) {
    	bot = new Sprite(rad, x, y, vx, vy, botImg);
    	botList.add(bot);
    	bot.draw();
    }
    
    void addPlayer(int rad, int x, int y, int vx, int vy, Image playerImg){
    	Sprite player = new Sprite(rad, x, y, vx, vy , playerImg);
    	player.draw();
    }
    
    void addGraveStone(Image gImg){
    	tombstoneImage = gImg;
    }
    
    void addProjectileImg(Image pImg){
    	projectileImage = pImg;
    }
    
    int f(int vel){
    	if( Sprite.velX > 0 || Sprite.velY> 0){
    		return -1;
    	}
    	else if( Sprite.velX < 0 || Sprite.velY< 0){
    		return 1;
    	}
    	else
    		return 0;
    }
    
    void addProjectile(){
    	Sprite projectile = new Sprite(Sprite.radius, Sprite.x+f(w)*(Sprite.radius+10),Sprite.y+f(w)*(Sprite.radius + 10), Sprite.velX, Sprite.velY, projectileImage);
    	projectileList.add(projectile);
    	projectile.draw();
    }
    
    void keyPressed(KeyEvent e){
    	if(e == VK_UP){
    		player.velY += 5
    	}
    	else if(e == VK_DOWN){
    		player.velY -= 5
    	}
    	else if(e == VK_LEFT){
    		player.velX -= 5
    	}
    	else if(e == VK_RIGHT){
    		player.velX += 5
    	}
    }
    
    void keyReleased(KeyEvent e){
    	player.velX = 0;
    	player.velY = 0;
    }
    
    void orientBots(){
    	for (Sprite bot: botList){
    		if(bot.x > player.x){
    			bot.velX = -1;
    		}
    		if(bot.x < player.x){
    			bot.velX = 1;
    		}
    		if(bot.y > player.x){
    			bot.velY = -1;
    		}
    		if(bot.y < player.x){
    			bot.velY = 1;
    		}
    	}
    }
    
    void checkCollide(){
  	  
    	for (Sprite bot: botList){
		  if ( isOverlap(bot, player) ){
	   		  bot = new Sprite(bot.radius, bot.x, bot.y, bot.velX, bot.velY , tombstoneImage);
    		  bot.velX = 0;
    		  bot.velY = 0;
       		  player = new Sprite(player.radius, player.x, player.y, player.velX, player.velY , tombstoneImage);
    		  player.velX = 0;
    		  player.velY = 0;
		  };
    	}
  	  
       for (Sprite proj: projectileList){
    	  if( isOverlap(player, proj)){
    		  player = new Sprite(player.radius, player.x, player.y, player.velX, player.velY , tombstoneImage);
    		  player.velX = 0;
    		  player.velY = 0;
    		  proj = new Sprite(proj.radius, proj.x, p.y, proj.velX, proj.velY , tombstoneImg);
    		  proj.velX = 0;
    		  proj.velY = 0;
    		  
    	  };
    	  
    	  for (Sprite bot: botList){
    		  if ( isOverlap(bot, proj) ){
    	   		  bot = new Sprite(bot.radius, bot.x, bot.y, bot.velX, bot.velY , tombstoneImage);
        		  bot.velX = 0;
        		  bot.velY = 0;
        		  projectileList.remove(proj);
    		  };
    	  }
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
