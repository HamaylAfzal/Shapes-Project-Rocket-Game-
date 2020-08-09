
public class FuelCell
{
    private Circle fuelCell;
    private SpaceStation spaceStation;    
    private Canvas canvas;                
    private String cellStatus;            
  
   
    public FuelCell(Canvas theCanvas, SpaceStation theSpaceStation)
    {
        canvas = theCanvas;
        spaceStation = theSpaceStation;
        fuelCell = new Circle(canvas);
        fuelCell.moveTo(canvas.getHeight()/2,canvas.getWidth()/2);
        fuelCell.makeInvisible();
        cellStatus = "moving";
    }
  
    
    public void launch(int xPosition, int yPosition)
    {
        fuelCell.moveTo(xPosition,yPosition); 
        fuelCell.makeVisible(); 
    }

    
    public void moveSmallDistance()
    {
         
        if ((Math.abs(spaceStation.getXPosition()-fuelCell.getXPosition())<=fuelCell.getDiameter()/2) &&
            (Math.abs(spaceStation.getYPosition()-fuelCell.getYPosition())<=fuelCell.getDiameter()/2))
        {
            fuelCell.moveTo(spaceStation.getXPosition(),spaceStation.getYPosition()); 
            canvas.wait(3000);
            fuelCell.makeInvisible();
            spaceStation.reFuel();
            cellStatus = "refueled";
        }
        else if ( fuelCell.getXPosition()<canvas.getWidth()) 
        {
            fuelCell.moveDirection(10,0);
        }
        else 
        {
            cellStatus = "missed";
            fuelCell.makeInvisible();
        }
    }    

    
    public String status()
    {
        return cellStatus;
    }
}