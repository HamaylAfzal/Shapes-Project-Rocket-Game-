import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.awt.event.*;


public class Canvas
{
    private JFrame frame;
    private DrawingSurface canvas;
    private Graphics2D graphic;
    private Color backgroundColour;
    private Image canvasImage;
    private List objects;
    private HashMap shapes;
    
    
    public Canvas(String title, int width, int height, Color bgColour )
    {
        frame = new JFrame();
        canvas = new DrawingSurface();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColour = bgColour;
        frame.pack();
        objects = new ArrayList();
        shapes = new HashMap();
    }

    
    public Canvas(String title, int width, int height)
    {
        frame = new JFrame();
        canvas = new DrawingSurface();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColour = new Color(255, 255, 255);    
        frame.pack();
        objects = new ArrayList();
        shapes = new HashMap();
    }

    
    public Canvas(String title)
    {
        frame = new JFrame();
        canvas = new DrawingSurface();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        canvas.setPreferredSize(new Dimension(300, 300));
        backgroundColour = new Color(255, 255, 255);    
        frame.pack();
        objects = new ArrayList();
        shapes = new HashMap();
    }

    
    public Canvas()
    {
        frame = new JFrame();
        canvas = new DrawingSurface();
        frame.setContentPane(canvas);
        frame.setTitle("CSC121 Canvas");
        canvas.setPreferredSize(new Dimension(300, 300));
        backgroundColour = new Color(255, 255, 255);    
        frame.pack();
        objects = new ArrayList();
        shapes = new HashMap();
    }

    
    public void setListener(KeyListener keylistener)
    { frame.addKeyListener(keylistener);
    }    

    
    public void setVisible(boolean visible)
    {
        if(graphic == null) {
            
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColour);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(visible);
        
    }

   
    public boolean getVisible()
    {
        return frame.isVisible();
    }

    
    public void drawString(Object referenceObject, String color, String label, int xCoord, int yCoord)
    {
        objects.remove(referenceObject);   
        objects.add(referenceObject);      
        shapes.put(referenceObject, new LabelDescription(label, xCoord, yCoord, color));
        redraw();
    }
        
  
    public void draw(Object referenceObject, String color, Shape shape)
    {
        objects.remove(referenceObject);   
        objects.add(referenceObject);      
        shapes.put(referenceObject, new ShapeDescription(shape, color));
        redraw();
    }
 
    
     public void erase(Object referenceObject)
    {
        objects.remove(referenceObject);   
        shapes.remove(referenceObject);
        redraw();
    }

    
     public void setForegroundColor(String colorString)
    {
        if(colorString.equals("red"))
            graphic.setColor(Color.red);
        else if(colorString.equals("black"))
            graphic.setColor(Color.black);
        else if(colorString.equals("blue"))
            graphic.setColor(Color.blue);
        else if(colorString.equals("yellow"))
            graphic.setColor(Color.yellow);
        else if(colorString.equals("green"))
            graphic.setColor(Color.green);
        else if(colorString.equals("magenta"))
            graphic.setColor(Color.magenta);
        else if(colorString.equals("white"))
            graphic.setColor(Color.white);
        else
            graphic.setColor(Color.black);
    }

    
     public void wait(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } 
        catch (Exception e)
        {
            
        }
    }

    
    public int getWidth()
    {
        return canvas.getWidth();
    }
    
    
    public int getHeight()
    {
        return canvas.getHeight();
    }
    
   
    private void redraw()
    {
        String objectType;
        Object object;
        
        erase();
        for(Iterator i=objects.iterator(); i.hasNext(); ) {
            object = shapes.get(i.next());
            if ( ((object.getClass()).toString()).indexOf("Label")==-1 ){
                ((ShapeDescription)object).draw(graphic);
            }
            else{
                ((LabelDescription)object).draw(graphic);
            }   
        }
        canvas.repaint();
    }
       
    
    public void eraseAll()
    {
        objects.clear();
        shapes.clear();
    }
       
    
     private void erase()
    {
        if (!getVisible()){
            setVisible(false);
        }
        Color original = graphic.getColor();
        graphic.setColor(backgroundColour);
        Dimension size = canvas.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }


    
    private class DrawingSurface extends JComponent
    {
        public DrawingSurface()
        {
            this.setDoubleBuffered(true);
        }
        public void paint(Graphics g)
        {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }
    
    
    private class ShapeDescription
    {
        private Shape shape;
        private String colorString;

         public ShapeDescription(Shape shape, String color)
        {
            this.shape = shape;
            colorString = color;
        }

        public void draw(Graphics2D graphic)
        {
            setForegroundColor(colorString);
            graphic.fill(shape);
        }
    }

    
    private class LabelDescription
    {
        private String label;
        private int xCoord;
        private int yCoord;
        private String colorString;

       public LabelDescription(String label, int xCoord, int yCoord, String color)
        {
            this.label = label;
            this.xCoord = xCoord;
            this.yCoord = yCoord;
            this.colorString = color;
        }
        
       public void draw(Graphics2D graphic)
        {
            setForegroundColor(colorString);
            graphic.drawString(label,xCoord,yCoord);
        }
    }
}