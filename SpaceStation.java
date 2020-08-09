import java.util.Random;


public class SpaceStation
{  
    private Diamond spaceStation;
    private Canvas canvas;
    private int initXPosition; 
    private int initYPosition; 
    private int xDistance;     
    private int yDistance;     
    private int orbitCount;    
    private Random r;          
    
    
   
    public SpaceStation(Canvas simCanvas)
    {
        canvas = simCanvas;
        spaceStation = new Diamond(simCanvas);
        spaceStation.changeSize(50,80);
        r = new Random();
        initXPosition = r.nextInt (200) + 500;
        initYPosition = 50;
        int OrbitCount;
        spaceStation.makeVisible ();
        spaceStation.moveTo (initXPosition, 50);
        spaceStation.changeColor ("green");   
    }

   
    public void moveSmallDistance()
   {   if (spaceStation.getXPosition() < canvas.getWidth () && 
       spaceStation.getYPosition() < canvas.getHeight () )
       {   spaceStation.moveDirection (xDistance, yDistance);
       }
       else if (spaceStation.getXPosition() >= canvas.getWidth ())
       {  initXPosition -= 80;
          if (initXPosition < 0)
          {   initXPosition = 0;
              initYPosition += 50;
          }
          if (orbitCount == 1)
          {   spaceStation.changeColor ("yellow");
          }
          if (orbitCount == 2)
          {   spaceStation.changeColor ("red");
          }
          spaceStation.moveTo (initXPosition, initYPosition);
          orbitCount+=1;
       }
   }
      
 
   public void reFuel()
     {
        spaceStation.changeColor("green");
        canvas.wait(3000);
       
     }
    
    
   public void setSpeed(char newSpeed)
    {   if (newSpeed == 'B')
        {   xDistance=3;
            yDistance=1;
        }
        else if (newSpeed == 'I')
        {   xDistance=5;
            yDistance=2;
        }
        else 
        {   xDistance=7;
            yDistance=3;
        }
    }
       
    
   public int getXPosition()
    {
        return spaceStation.getXPosition();
    }
   
    
   public int getYPosition()
    {
        return spaceStation.getYPosition();
    }
}
