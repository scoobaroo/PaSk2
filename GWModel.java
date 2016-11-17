import java.util.ArrayList;

public class GWModel extends PSysModel {
	ArrayList <Sprite> sList;
	
    GWModel() {
	sList = new ArrayList<Sprite>();
    }
    
    void add(int rad, int x, int y, int vx, int vy, Image img) {
	sList.add(new Sprite(rad, x, y, vx, vy, img));
    }
    
    void update(int bw, int bh) {
	for (int i=0; i<sList.size(); i++) {
	    sList.get(i).update(bw, bh);
	}
    }
}
