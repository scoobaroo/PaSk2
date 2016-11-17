import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GWView extends PSysView {
    GWView() {
    }

    void draw(GWModel gwm, Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(Color.RED);
	for (int i=0; i<gwm.sList.size(); i++) {
	    Sprite s = gwm.sList.get(i);
	    g2d.fillOval(s.x-s.radius, s.y-s.radius, s.radius*2, s.radius*2);
	}
    }

    void dump(GWModel gwm, int lc) {
	System.out.println("Frame " + lc);
	for (int i=0; i<gwm.sList.size(); i++) {
	    Sprite s = gwm.sList.get(i);
	    System.out.println(s.radius + " " + s.x + " " + s.y + " " + s.velX + " " + s.velY);
	}

    }
}
