import java.awt.event.*;


public class Controller
    implements KeyListener
{
    private SpaceStation spaceStation;
    private FuelCell fuelCell;
    private Shuttle shuttle;
    private Canvas canvas;
    private boolean sim;             
    private boolean speedSet;        
    private boolean moveShuttle;     
    private boolean launchFuelCell;  
    private boolean fuelCellInMotion;
    private boolean inOrbit;         
    private Label label;             
    
    public Controller()
    {
        canvas = new Canvas("Space Station Fueling Simulator",800,600);
        canvas.setListener(this);
        spaceStation = new SpaceStation(canvas);
        shuttle = new Shuttle(canvas,spaceStation);
    }

    
     public void simulate()
    {
        speedSet = false;           
        label = new Label("Choose speed: B, I, or A", 350, 15,canvas);
        label.makeVisible();
        canvas.setVisible(true);

        moveShuttle = false;        
        launchFuelCell = false;    
        fuelCellInMotion = false;

        sim = true;
        while (sim)
       {    if (inOrbit)
            {
                spaceStation.moveSmallDistance(); 
            }
            if (moveShuttle)
            {
                shuttle.moveSmallDistance();
                moveShuttle = false;
            }
            if (launchFuelCell && shuttle.getNumberFuelCells()>0)
            {
                fuelCell = shuttle.launchCell();
                launchFuelCell = false;
                fuelCellInMotion = true;
            }          
            if (fuelCellInMotion) 
            {
                fuelCell.moveSmallDistance();
                if (fuelCell.status().equals("refueled"))
                {
                    shuttle.dock();
                    canvas.wait(2000);
                    sim = false;
                }
                if (!fuelCell.status().equals("moving"))
                {
                    fuelCellInMotion = false;
                }
            }      
            if (spaceStation.getYPosition()>=canvas.getHeight())
            {
                canvas.wait(2000);
                sim = false;
            }
            canvas.wait(20);
        }   
        canvas.setVisible(false);        
    }
   
   public void keyTyped(KeyEvent ke)
    {
        char myChar = Character.toUpperCase(ke.getKeyChar());
        if (myChar == 'K')
        {
            moveShuttle = true;
            shuttle.setDirection("down");
        }    
        else if(myChar == 'J')
        {
            moveShuttle = true;
            shuttle.setDirection("up");
        }   
        
        else if (myChar == ' ' && !fuelCellInMotion)
        {   
            launchFuelCell = true;
        }    
        else if (myChar == 'S' && speedSet)
        {
            label.setText("Down: K, Up: J, Launch: Space");
            inOrbit = true;
        }    
        else if (myChar == 'B' || myChar == 'I' || myChar == 'A')
        {
            spaceStation.setSpeed(myChar);
            speedSet = true;
            label.setText("To start: S, To Stop: X");
        }    
        else if (myChar == 'X')
        {
            sim = false;
        }    
    }

    
   public void keyReleased(KeyEvent ke)
    {
    }

   public void keyPressed(KeyEvent ke)
    {
    }
}  