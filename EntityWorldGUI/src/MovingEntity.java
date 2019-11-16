
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;
import java.util.Random;


/*
    ------- CLASE MOVINGENTITY -------
*/

public class MovingEntity extends Entity {
    
    //Definimos variables
    public Vec2D dir;
    public double speed=1;

    //Constructor
    public MovingEntity(Vec2D p, World wi) {
      super(p, wi);

      dir = new Vec2D(0,1);
    }

    
    //Damos un nuevo valor a la variable dir
    public void setDir(Vec2D d){
        dir = d;
    }

    
    //Devuelve la direccion de la Moving Entity
    public Vec2D getDir(){
        return dir;
    }

    
    //Permite dar un nuevo valor a la variable "speed"
    public void setSpeed(double s){
        speed = s;
    }

    
    //Devuelve el valor de la variable "speed"
    public double getSpeed(){
        return speed;
    }

    
    //Función que se encarga de actualizar la posición de la moving entity
    @Override
    public void update(){           

        pos.setX(speed * dir.getX() + pos.getX() ); 
        pos.setY( speed * dir.getY() + pos.getY() );
    }


    //Función que dibuja la moving Entity
    @Override
    public void draw(Graphics g) { 
    if(bCollides) g.setColor(Color.RED); //Si entra en contacto con otra entidad se dibuja de color rojo

    else g.setColor(Color.BLACK); //Si no entra en contacto con otra entidad se dibuja de color negro

    g.fillOval((int)(pos.getX()-3), (int)(pos.getY()-3), 6, 6);
    }


}
