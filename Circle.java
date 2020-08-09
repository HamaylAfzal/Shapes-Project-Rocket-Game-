import java.awt.*;
import java.awt.geom.*;


public class Circle
{
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    private Canvas canvas;
    

   public Circle(Canvas canvas)
    {
        diameter = 30;
        xPosition = 50;
        yPosition = 50;
        color = "blue";
        isVisible = false;
        this.canvas = canvas;
      }

    
   public void makeVisible()
    {
        isVisible = true;
        draw();
    }
    
   
   public void makeInvisible()
    {
        erase();
        isVisible = false;
    }
    
     
    public void moveDirection(int xDistance, int yDistance)
    {
       erase();
       yPosition += yDistance;
       xPosition += xDistance;
       draw();
   }
        
    
    public void moveTo(int newXPosition, int newYPosition)
    {
       erase();
       yPosition = newYPosition;
       xPosition = newXPosition;
       draw();
   }

    
   public void changeSize(int newDiameter)
    {
        erase();
        diameter = newDiameter;
        draw();
    }

    
   public void changeColor(String newColor)
    {
        color = newColor;
        draw();
    }

  
   private void draw()
    {
        if(isVisible) {
            canvas.draw(this, color, new Ellipse2D.Double(xPosition-diameter/2, yPosition-diameter/2, diameter, diameter));
        }
    }

    
   public void erase()
    {
        if(isVisible) {
            canvas.erase(this);
        }
    }

    
   public int getXPosition()
    {
        return xPosition;
    }
    
    
   public int getYPosition()
    {
        return yPosition;
    }

   
   public int getDiameter()
    {
        return diameter;
    }
}