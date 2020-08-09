import java.awt.*;



public class Triangle
{
    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    private Canvas canvas;

    
   public Triangle(Canvas canvas)
    {
        height = 30;
        width = 40;
        xPosition = 50;
        yPosition = 15;
        color = "green";
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

    
   public void changeSize(int newHeight, int newWidth)
    {
        erase();
        height = newHeight;
        width = newWidth;
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
            Polygon myPoly = new Polygon();                     
            myPoly.addPoint(xPosition,yPosition);                       
            myPoly.addPoint(xPosition -height,yPosition + (width/2));  
            myPoly.addPoint(xPosition - height,yPosition - (width/2));  
            canvas.draw(this, color, myPoly);
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
    
    
   public int getWidth()
    {
        return width;
    }

    
   public int getHeight()
    {
        return height;
    }
}