/*The following class extends the jave Jframe whose opacity is set to .5
 * The frrame is created whenever mouse is dragged holding down the right
 * Mouse Key.
 */


package beta;

/*****
 *
 * @author Pavan,Rahul,Pooja,Rajeshwari.
 *******/

//Import section

import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
//End of import section



class Paint extends JFrame{
   JPanel panel;
   
   int j=0;
   Point temp;
   int clr=0;
   ArrayList al=new ArrayList();
   int Thick=0;
   
    public Paint(){
        super("This is a test");
        
       setCOlour();
       setThickness();
       
        
        setUndecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);//setting the frame size to fullscreen.
        getContentPane().setLayout(new FlowLayout());//Frame Layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
         panel = new JPanel()  
         {  
            //Paint method of the component
            public void paintComponent(Graphics g)  
            {  
               Point p=new Point();
               p=MouseInfo.getPointerInfo().getLocation();
               (al).add(j,p);
              for(int i=0;i<al.size();i++)
              {  
                   
                  
                temp=(Point) al.get(i);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.Clear);
                 
                g.fillOval((int)(temp.getX()),(int)temp.getY(),Thick,Thick);
              }  
              j++;
            }
         }; 
        
        panel.setOpaque(false);//Panel is made transparent
        setGlassPane(panel);//Panel is made glasspane 
        getGlassPane().setVisible(false);
        //com.sun.awt.AWTUtilities.setWindowOpacity(this, .4f);
        setVisible(true);
    }

    @Override
     public void paint(Graphics g) {
        Point p=new Point();
        p=MouseInfo.getPointerInfo().getLocation();
        (al).add(j,p);
        for(int i=0;i<al.size();i++)
              {  
                   
                  
                temp=(Point) al.get(i);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.Clear);
                
                 //System.out.println(clr);
                switch(clr)
                {
                    case 0:g.setColor(Color.BLACK);
                           break;
                    case 1:g.setColor(Color.BLUE);
                           break;
                    case 2:g.setColor(Color.RED);
                           break;
                    case 3:g.setColor(Color.YELLOW);
                           break;
                    case 4:g.setColor(Color.ORANGE);
                           break;
                    case 5:g.setColor(Color.PINK);
                           break;
                    case 6:g.setColor(Color.MAGENTA);
                           break;
                    case 7:g.setColor(Color.GRAY);
                           break;
                    case 8:g.setColor(Color.CYAN);
                           break;
                    case 9:g.setColor(Color.GREEN);
                           break;
                     
                }
                
                
                
                
                
                
                
                
                g.fillOval((int)(temp.getX()),(int)temp.getY(),Thick,Thick);
              }  
              j++;
        
              panel.repaint();
        }

     //Function to set the mouse drag colour
     public void setCOlour()
    {
            
            
            BufferedReader br = null;
          try {
                String sCurrentLine;
                br = new BufferedReader(new FileReader("colour.txt"));
                while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
                  clr=Integer.parseInt(sCurrentLine);
                }
                
                
                        
 
          } catch (IOException e) {
			e.printStackTrace();
	  } finally {
	       try {
		       if (br != null)br.close();
		} catch (IOException ex) {
				ex.printStackTrace();
		}
	}
        
         
    } 
     
   //Function to set the mouse drag thickness
    public void setThickness()
    {
            
            
            BufferedReader br = null;
          try {
                String sCurrentLine = null;
                br = new BufferedReader(new FileReader("Thick.txt"));
                sCurrentLine=br.readLine();
				//System.out.println(sCurrentLine);
                  Thick=Integer.parseInt(sCurrentLine);
                
                
                
                        
 
          } catch (IOException e) {
			e.printStackTrace();
	  } finally {
	       try {
		       if (br != null)br.close();
		} catch (IOException ex) {
				ex.printStackTrace();
		}
	}
         
    
    
    } 
    
}