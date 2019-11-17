import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

/*
    ------- CLASE AGENT -------
*/
public class Agent extends MovingEntity {
    
   //Definimos una variable correspondiente al objetivo del agente
   protected Vec2D obj;
    
   
   //Constructor en el que decidimos cual es su objetivo
   public Agent(Vec2D p, Vec2D o, World wi) {
        super(p, wi);        
        obj = o;        
    }
   
   
   //Constructor en el que el objetivo se genera aleatóriamente
   public Agent(Vec2D p, World wi) {
        super(p, wi);        
        obj = w.randomPointInsideWorld();        
    }
   
   
   //Setter
   public void setObj(Vec2D o){
       obj=o;
   }
   
   //Getter
   public Vec2D getObj(){
       return obj;
   }
    
   
   //Funcion que se encarga de actualizar la posicion de la gente
   public void update(){
       
       
       dir.turnTo(getDirToObj());//Hacemos que la dirección cambie hasta que apunte al objetivo
       
       //Con las siguientes 6 lineas evitamos que los agentes "orbiten" en un punto determinado
       if(pos.dist(obj)<50)
            dir.turnTo(getDirToObj());
       if(pos.dist(obj)<30)
            dir.turnTo(getDirToObj());
       if(pos.dist(obj)<15)
            dir.turnTo(getDirToObj());
       
       //Con el siguiente if conseguimos que tanto los objetivos como los agentes no se salgan de nuestra pantalla
       if(pos.getX()>(w.getW()-50) || pos.getX()<50 || pos.getY()>(w.getH()-50) || pos.getY()<50){
           
           this.setObj(w.randomPointInsideWorld());
           
           for(int i=0;i<10; i++)
                dir.turnTo(getDirToObj());
           
       }
       
       //En las siguientes líneas se define cuándo se añade un misil
       if((int) (Math.random() * 100) == 0)      
           w.addProjectile(this);
    
       super.update();
       
   }
   
   
   //Función que devuelve la direccion hacia el objetivo
   public Vec2D getDirToObj(){
       Vec2D dobj = new Vec2D(obj.getX(), obj.getY());
       dobj.minus(pos);
       dobj.normalize();
       return dobj;
   }
   
   
   //Función booleana que indica si se ha llegado a un objetivo
   public boolean objReached(){
    boolean result = (pos.dist(obj)<10);
    return result;
    }
    
   
   //Función que devuelve una variable del tipo "MyPolygon" correspondiente a la forma del agente
   public MyPolygon getShape(){
       MyPolygon t = new MyPolygon();
       t.Triangle(dir);
       t.center();
       t.translate((int)pos.getX(), (int)pos.getY());
       
       return t;
   }
   
   
   //Función que dibuja el agente
   @Override
   public void draw(Graphics g) { 
       if(bCollides) g.setColor(Color.RED); else g.setColor(Color.BLUE);
        g.drawPolygon( getShape() );
        g.setColor(Color.BLUE);
        g.drawOval((int)(obj.getX() - 2), (int)(obj.getY() - 2), 4, 4);
    }
   
   
   //Función booleana que indica si el agente ha entrado en contacto con otra Entity cualquiera
   @Override
   public boolean collides (Entity e){
       
       if (e instanceof Obstacle) {
        Obstacle o = ( Obstacle ) e; 
        MyPolygon t = getShape ( );
        MyPolygon p = o.getObstacle();
        
        return p.intersects(t.getBounds2D());   
       }
       
       return super.collides(e);
   }
    
}