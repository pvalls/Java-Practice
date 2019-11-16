/*
    ------- CLASE ENTITY -------
*/

abstract class Entity implements Drawable {
    
    //Inicializamos variables de forma que sus extensiones las puedan usar
    protected Vec2D pos;
    protected int id;
    protected World w;
    protected boolean bCollides;
   
    
    //Constructor
    public Entity( Vec2D p, World wi ) {
        w = wi;
        bCollides = false;
        pos = new Vec2D( p.getX(), p.getY());    
       
    }
    
    
    //Permite dar valor al parámetro booleano bCollides
    public void setCollides(boolean t) { bCollides = t; }
    
    
    //Devueleve la posición de la Entity
    public Vec2D getPos() { return pos; }
    
    
    //Permite cambiar la posición de la Entity
    public void setPos(Vec2D p) { pos.setX(p.getX()); pos.setY(p.getY()); }

    
    //Función que detecta si otra Entity ha entrado en contacto con ésta
    public boolean collides(Entity e) {        
        Vec2D epos = e.getPos();
        if(pos.dist(epos) < 50) return true;
        else return false;        
    }
    
    
    public void update() {}

    
}
