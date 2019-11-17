import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.*;


/*
    ------- CLASE MYPOLYGON -------
*/

public class MyPolygon extends Polygon {
    
   
    MyPolygon() {
       super();
    }
    
    void Triangle(Vec2D din) {
       din.normalize();
       Vec2D d = new Vec2D(15*din.getX(),15*din.getY());
       Vec2D d90 = new Vec2D(-15*din.getY(),15*din.getX());
       addPoint((int) (-d.getX() + d90.getX()),(int) (-d.getY() + d90.getY()));
       addPoint((int) (-d.getX() - d90.getX()),(int) (-d.getY() - d90.getY()));
       addPoint((int) (d.getX()*2),(int) (d.getY()*2) );
    }
     
    
    void randomPolygon() {
        int sides = (int) (3 + Math.random() * 9) ;
        AffineTransform trans = new AffineTransform();        
        for (int i = 0; i < sides; i++) {
          int outsideRadius = 40 + (int) (Math.random() * 20); 
          int insideRadius = 20 + (int) (Math.random() * 20);
          trans.rotate(Math.PI * 2 / (float) sides / 2);
          Point2D out = trans.transform(new Point2D.Float(0, outsideRadius), null);
          addPoint((int) out.getX(), (int) out.getY());
          trans.rotate(Math.PI * 2 / (float) sides / 2);
          if (insideRadius > 0) {
                Point2D in = trans.transform(new Point2D.Float(0, insideRadius), null);
                addPoint((int) in.getX(), (int) in.getY());
          }
        }
    }

    
    Vec2D getCentroid() {
        Vec2D p = new Vec2D(0,0);
        for(int i=0;i<npoints;i++) {
            p.setX( p.getX() +  xpoints[i] );
            p.setY( p.getY() +  ypoints[i] );
        }
        p.setX( p.getX() / npoints );
        p.setY( p.getY() / npoints );
        return p;
    }
    
    void center() {
        Vec2D c = getCentroid();
        translate((int) -c.getX(), (int) -c.getY());
    }    
     
}
