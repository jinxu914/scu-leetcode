import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

class ColorData extends Observable{	
	Color color = Color.black;
	
	public void setColor( String newColor ){
           
      if (newColor.equals("r"))
      		color = Color.red;
      else if (newColor.equals("g"))
      			color = Color.green;
      else if (newColor.equals("b"))
      			color = Color.blue;	
      else
      		color = Color.magenta;
      		
      // must call setChanged before notifyObservers to
      // indicate model has changed

      setChanged();
      // notify Observers that model has changed
      notifyObservers();
   }
   public Color getColor () { return color;}	
}
public class WindowWatcher extends JFrame implements ItemListener{

    private JButton Close;

    private JRadioButton red, green, blue;
    
    private ColorData model;
    
    private ButtonGroup bgr;


    public WindowWatcher() {

        super("WindowWatcher");
        
        model = new ColorData();

        JPanel buttonPanel = new JPanel();  
        
        buttonPanel.setLayout(new BorderLayout()); 
        
        createButtons();    
       
        addFlowLayout(buttonPanel);
        
        addListeners();      
        
        ColorPanel cpanel = new ColorPanel(model);
       
		
		this.setPreferredSize(new Dimension(500,500));
		getContentPane().add(buttonPanel,BorderLayout.NORTH);
		
		getContentPane().add(cpanel,BorderLayout.CENTER);
		
        pack();
        setVisible(true);
	}
	private void createButtons()
	{
		red = new JRadioButton("Red");
        red.setActionCommand("r");
      
        green = new JRadioButton("Green");
        green.setActionCommand("g");

        blue = new JRadioButton("Blue");
		blue.setActionCommand("b");
		//make all part of same button group

       bgr = new ButtonGroup();

        bgr.add(red);

        bgr.add(green);

        bgr.add(blue);		
	}	
	private void addListeners(){
		blue.addItemListener(this);         //listen for clicks

        red.addItemListener(this);          //on radion buttons

        green.addItemListener(this);	
		
	}
	public void addFlowLayout(JPanel panel)
	{
		panel.setLayout(new FlowLayout());
		
		panel.add(red);
		panel.add(green);
		panel.add(blue);		
	}	
	public void itemStateChanged(ItemEvent e) {

        //responds to radio button clicks

        //if the button is selected

        if (e.getStateChange() == ItemEvent.SELECTED){ 
            
            JRadioButton source = (JRadioButton)e.getSource();
            String action = source.getActionCommand();
          
            model.setColor(action);
        }
    }
       
    public static void main(String[] args) {

        new WindowWatcher();
    }
}
class ColorPanel extends JPanel implements Observer {

    private ColorData  model;

    private String message="Hello";

    private Font font;

    public ColorPanel(ColorData model) {
    	
        setBackground(Color.pink);
        setLayout(new BorderLayout());        
       
       this.model = model;
        model.addObserver(this);
        
        font = new Font("Sans Serif", Font.BOLD, 25);        
    }
	public void update(Observable o, Object o1)
	{
		setBackground(model.getColor());	
		repaint();		
	}
    public void paintComponent(Graphics gp) {
 		// ensure proper painting sequence
 		
      super.paintComponent( gp );
      
      // Cast the graphics objects to Graphics2D
      
         Graphics2D g = (Graphics2D) gp;
                 
         Dimension panelSize = getSize();
	   
        g.setFont(font);
       
        g.drawString(message, panelSize.width/2, panelSize.height/2);
        	
    }
}

