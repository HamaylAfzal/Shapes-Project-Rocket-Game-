

public class Label
{
    
    private String label;
    private int xCoord;
    private int yCoord;
    private String color;
    private boolean isVisible;
    private Canvas canvas;

    
    public Label(Canvas canvas)
    {
        
        label = "";
        xCoord = 0;
        yCoord = 0;
        color = "black";
        isVisible = false;
        this.canvas = canvas;
    }

   
    public Label(String label, int xCoord, int yCoord, Canvas canvas)
    {
        
        this.label = label;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        color = "black";
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
    
    
    public void moveTo(int newXCoord, int newYCoord)
    {
       erase();
       yCoord = newYCoord;
       xCoord = newXCoord;
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
            canvas.drawString(this, color, label, xCoord, yCoord);
        }
    }

    
    public void erase()
    {
        if(isVisible) {
            canvas.erase(this);
        }
    }

     
    public void setText(String label)
    {
        this.label = label;
        draw();
    }
    
    
     public int getXCoord()
    {
        return xCoord;
    }
    
    
    public int getYCoord()
    {
        return yCoord;
    }
}
