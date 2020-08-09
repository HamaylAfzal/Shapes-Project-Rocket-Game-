
public class Shuttle
{ 
    private Triangle shuttle; 
    private Canvas canvas;
    private SpaceStation spaceStation;
    private String direction;    
    private int numberFuelCells; 

    
    public Shuttle(Canvas theCanvas, SpaceStation theSpaceStation)
    {
        canvas = theCanvas;
        spaceStation = theSpaceStation;
        shuttle = new Triangle(canvas);
        shuttle.moveTo(40, 150);
        shuttle.changeColor("yellow");
        shuttle.makeVisible();
        numberFuelCells = 5;
    }
   
    
    public void setDirection(String theDirection)
    {   direction = theDirection;
    }

   
    public void moveSmallDistance()
    {   if(direction == "up")
        {   shuttle.moveDirection(0, 10);
        }
        else if(direction == "down")
        {   shuttle.moveDirection(0, -10);
        }
    }
   
    public FuelCell launchCell()
    {
        FuelCell cell = null;
        if (numberFuelCells >0)
        {   cell = new FuelCell (canvas,spaceStation);
            cell.launch(shuttle.getXPosition(), shuttle.getYPosition());
            numberFuelCells--;
        }
        return cell;
    }    

   
     public void dock()
    {   while (shuttle.getXPosition() < spaceStation.getXPosition())
        {
            shuttle.moveDirection(10,0);
            canvas.wait(100);
        }
        shuttle.moveTo(spaceStation.getXPosition (),spaceStation.getYPosition ());
        canvas.wait(3000);
        while(shuttle.getXPosition () > 30)
        {   shuttle.moveDirection(-10,0);
            canvas.wait(100);
        }
    }
            
   
     public int getNumberFuelCells()
    {
        return numberFuelCells;
    }     
}