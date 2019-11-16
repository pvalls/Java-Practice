import java.util.*;
import java.awt.Color; 

/*
    ------- CLASE WORLD -------
*/

public class World implements Drawable {
    
    //Inicializamos variables
    int W,H,N;
    int margin;
    int NumAgents;
    int NumObstacles;
    int NumMovingEntities;
    int NumTotal;

    LinkedList entities = new LinkedList();
      
    
    //Constructor
    public World() {
        
        W = 800;
        H = 600;
        margin = 50;
        N=10;
                        
        NumAgents = 10;  
        NumObstacles = 3;
        NumMovingEntities = 5;
        
               
        //Llenamos la LinkedList
        for(int i=0; i<NumAgents; i++) {            
            Entity a = new Agent(randomPointInsideWorld(), this);
            entities.addLast( a );
        }
        
        for(int i=0; i<NumObstacles; i++) {            
            Entity o = new Obstacle(randomPointInsideWorld(), this);
            entities.addLast( o );
        }
        
        for (int i=0; i<NumMovingEntities; i++){           
            Entity me = new MovingEntity(randomPointInsideWorld(), this);
            entities.addLast(me);            
        }            
    }
    
    
    //Getters
    int getW() { return W; }
    int getH() { return H; }
    
    
    //Funcion "randomPointInsideWorld()", devuelve un punto aleatorio dentro de la ventana de visualización
    Vec2D randomPointInsideWorld() {        
        double x = margin + Math.random() * (W - 2*margin);
        double y = margin + Math.random() * (H - 2*margin); 
        
        Vec2D p = new Vec2D(x,y);
        
        if(x>W || x<0 || y>H || y<0)
            return randomPointInsideWorld();
       
        //Con el siguiente for loop evitamos que un objetivo aparezca dentro de un obstáculo
        for(int i=0; i<entities.size(); i++)
            if(entities.get(i) instanceof Obstacle)
                if(p.dist(((Obstacle)entities.get(i)).getPos())<70)                                    
                        return randomPointInsideWorld();         
        
        return p;
    }
    
    //Función "processCollisions()", se encarga de detectar si una Entity ha entrado en contacto con otra.
    void processCollisions() {        
          for(int i=0; i<entities.size(); i++) {
              Entity ei = (Entity) entities.get(i);
              ei.setCollides(false);
          }
          
          for(int i=0; i<entities.size(); i++) {
              Entity ei = (Entity) entities.get(i);
              ei.setCollides(false);
              
              for(int j=0; j<entities.size(); j++) {  
                  if(i == j) continue;
                  Entity ej = (Entity) entities.get(j);
                  
                  if (ei instanceof Agent){
                      
                      Agent a = (Agent) ei;
                      if(a.collides(ej)) 
                        ei.setCollides(true);
                     
                  }

                  else{
                      if(ei.collides(ej)) 
                       ei.setCollides(true);                                        
                   }
              }
          }
    }
    
    
    //Función que elimina las entities que se salen de la ventana de visualización
    void removeOutsiders() {
        for(int i=0; i<entities.size(); i++) {
            Entity ei = (Entity) entities.get(i);            
            if((ei.getPos().getX() < 0) ||
               (ei.getPos().getX() > W) ||
               (ei.getPos().getY() < 0) ||
               (ei.getPos().getY() > H))
            {
                entities.remove(ei);
            }
        }
    }
    
    
    //Función que actualiza la posición todas las entities
    void update() {              
        removeOutsiders();
        processCollisions();
        
        for(int i=0; i<entities.size(); i++) {
            Entity ei = (Entity) entities.get(i);
            ei.update();  
            if(ei instanceof Agent){
                if(((Agent) ei).objReached())
                    ((Agent) ei).obj=randomPointInsideWorld();
                else 
                    ((Agent)ei).update();
                
            }
        }
    }
        
    
    //Función que activa la actualizacion
    public void run(int steps) {
        for(int i = 0; i<steps; i++) update();
    }
    
    
    //Función que dibuja todas las Entities
    @Override
    public void draw( java.awt.Graphics g ) {
         
            for(int i=0; i<entities.size(); i++) {            
               Entity e = (Entity) entities.get(i);
               e.draw(g);

        }
            this.update();
                           
    }
    
    //Función que añade una moving entity que hace de proyectil para un agente deteminado 
    void addProjectile (Agent a){
        
        MovingEntity m = new MovingEntity(a.pos, this);
  
        final Vec2D dir = a.getDir();
        m.setDir(dir);
        m.setSpeed(a.getSpeed()*4);
               
        entities.addLast(m);
    }
    
}
