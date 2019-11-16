
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;


/*
    ------- CLASE OBSTACLE -------
*/

public class Obstacle extends Entity{
    
    //Variable que guarda la forma del obstáculo
    protected MyPolygon p = new MyPolygon();
    
    
    //Constructor
    public Obstacle(Vec2D pos, World wi) {
        super(pos, wi);
        p.randomPolygon();
        p.center();
        p.translate((int)pos.getX(), (int)pos.getY());
    }
    
    
    //Devuelve la forma del obstaculo
    public MyPolygon getObstacle(){
        return p;
    }
    
    
    //Función que dibuja el obstáculo
    @Override
    public void draw(Graphics g) { 
       if(bCollides) g.setColor(Color.RED);
       else g.setColor(Color.ORANGE);
       g.drawPolygon(p);
   }
    
    
}
