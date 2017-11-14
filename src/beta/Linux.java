/*Authors: Pavan Bahuguni,Pooja Desai,Rahul Deshpande,Rajeshwari Rotgal
 * Classes: Linux(To capture the pattern)
 *          Paint(To show the mouse tail)
 *          UserI(User Interface to addd remove symbols)
 * API:jNativeHook.jar(API to handle global mouse events
 */
package beta;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;


/* Class that runs in background andcaptues the drawn mouse pattern*/
class Linux implements NativeMouseInputListener, Runnable {

    static int Right_Click = 0;//Button clickc status
    public static final double THRESHOLD = 0.85;//Threshold for string similarity 
    static int x_initial = 0, y_initial = 0;//To store intail mouse position
    static int x_alternate, y_alternate;//to capture current ouse position
    static int[] Stroke = new int[500];//Array to capture the strke pattern
    static int Stroke_Index = 0;//Indes of stroke array
    static int Alternate = 0;//Counter to keep track of alterate mouse points
    static int Frame_count = 0;//To create frame for only first drag
    static Paint frame;//Reference to new transparent frame to draw gesture
    static ArrayList al = new ArrayList();
    String path;//Variable to hold the program path
    static int reference;
    static String abc = new String("");
    static String referencea;
    static int referenceb;
    static String referencec;
    static String referenced;
    static String pth;
    static private String sCurrentLine;
    static public Thread t;

    public Linux() {

        try {
            GlobalScreen.registerNativeHook();//Registering api to global screen
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            
            System.exit(1);
        }
         t=new Thread(this); 
         t.start();
          System.out.println("Started");

        //Construct the example object and initialze native hook.
        GlobalScreen.getInstance().addNativeMouseMotionListener(this);
        GlobalScreen.getInstance().addNativeMouseListener(this);
        

    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
        //Not used  
    }

    @Override//Mouse pressed event handler for global screen
    public void nativeMousePressed(NativeMouseEvent e) {

        if (e.getButton() == 2) {
            Right_Click = 1;//To detect right click
            Frame_count = 1;
        } else {
            Right_Click = 0;
        }
    }

    @Override//Mouse released event handler for global screen 
    public void nativeMouseReleased(NativeMouseEvent e) {

        Right_Click = 0;//Reset the right click
        int c = Match_String(); //Matching the strings which returns the percentage similarity between strings
        frame.dispose();      //returns int value depending on the characted drawn with mouse
        reference = c;
        SetPath();
        Open_App();
        Stroke_Index = 0;

    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
               //Not used 

    }

    @Override //Mouse drag event for entire screen  
    public void nativeMouseDragged(NativeMouseEvent e) {

        try {
            Capture_stroke();
        } catch (Exception eaxcp) {

        }
    }

//Function to capture the mouse stroke         
    public static void Capture_stroke() {
        if (Right_Click == 1) //check if the mouse is right clicked
        {
            if (Frame_count == 1)//New frame is created only for the first mouse drag 
            {
                frame = new Paint();
                frame.setVisible(true);
                frame.setAlwaysOnTop(true);
                frame.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0001f));

            }

            Frame_count = 0;//Frame_cout is reset
            frame.repaint();
            
            if (Alternate == 0)//First mouse point is captured 
            {
                Point mousePt = MouseInfo.getPointerInfo().getLocation();//Mouse position is stored in temporary variable MousePt 
                //fetching the the initial X and Y points
                x_initial = Math.max(0, mousePt.x);
                y_initial = Math.max(0, mousePt.y);
                System.out.println("Initial:"+mousePt.x+" "+mousePt.y);
                frame.repaint();
            }
            frame.repaint();
            Alternate++;
            Point mousePt = MouseInfo.getPointerInfo().getLocation();
            x_alternate = Math.max(0, mousePt.x);
            y_alternate = Math.max(0, mousePt.y);
            System.out.println("alternate:"+mousePt.x+" "+mousePt.y);
            if (Alternate == 3)//Every 3rd mouse moved position is captured
            {
                Alternate = 0;
                double angle = Math.toDegrees(Math.atan2((y_alternate - y_initial), (x_alternate - x_initial)));
                /*Following if else Statements capture the stroke of the mouse depending on the 
                 mouse movement in the form of numbers ranging from 1 to 8.*/
                frame.repaint();
                if (angle > -112.5 && angle < -67.5) {

                    Stroke[Stroke_Index] = 1;//Mouse moved up
                    Stroke_Index++;
                    frame.repaint();
                } else if (angle > -67.5 && angle < -22.5) {
                    frame.repaint();
                    Stroke[Stroke_Index] = 2;//Mouse moved left bottom to right top.
                    Stroke_Index++;
                } else if (angle > -22.5 && angle < 22.5) {
                    Stroke[Stroke_Index] = 3;//Mouse moved right
                    Stroke_Index++;
                } else if (angle > 22.5 && angle < 67.5) {
                    Stroke[Stroke_Index] = 4;//Mouse moved left top to right bottom.
                    Stroke_Index++;
                } else if (angle < 112.5 && angle > 67.5) {

                    Stroke[Stroke_Index] = 5;//Mouse moved down
                    Stroke_Index++;
                } else if (angle > 112.5 && angle < 157.5) {
                    Stroke[Stroke_Index] = 6;//Mouse moved top right to left 
                    Stroke_Index++;
                } else if (angle > -157.5 && angle < -112.5) {
                    Stroke[Stroke_Index] = 8;//Mouse moved bottom right top left
                    Stroke_Index++;
                } else {
                    Stroke[Stroke_Index] = 7;////Mouse moved top left
                    Stroke_Index++;
                }

                mousePt = MouseInfo.getPointerInfo().getLocation();
                x_alternate = Math.max(0, mousePt.x);
                y_alternate = Math.max(0, mousePt.y);
                if (Stroke_Index > 490) {
                    frame.dispose();
                    Stroke_Index = 0;
                    return;
                }

            }
        }

    }

    /**
     * *** ENd of Capture_Stroke******
     */

    /**
     * ****Function to compare the captured stroke with stored strokes****
     */
   public static int Match_String()
     {
         int[] String_Array=new int[100];
         int j=0,l=0; 
         // The following string variables are references for string comparison//
         String Up="1";
         String lbrt="2";
         String right="3";
         String ltrb="4";
         String down="5";
         String trlb="6";
         String left="7";
         String rblt="8";
        
         String a2="2487";
         String a3="15187";
         String a4="1258";
         
         String b="13456734567";
         String b1="12345634578";
         
         String c="765432";
         String c2="65432";
         String c3="76543";
         
         String d="134567";
         String d3="14567";
         String d2="1456";
         
         String e="76543765432";
         
         String g="76543217";
         String g2="7654317";
         
         String L="53";
         
         String M="1425";
         String M4="143215";
         String M1="14515";
         //String M2="1415";
         String M3="8425";
         
         String N="141";
         
         String R="1234563741";
         String R2="12345675";
          String R3="3184564";
          String R4="12124564";
         
         
         String S="7654345678";
         
         String V="42";
         String V2="51";
         
         String U="54321";
         
         String W="5241";
         
         String Y="426";
         String Y2="515";
         String y3="516";
         String y4="415";
         /***End of declaration****///
         
         
         /*
          * Following while loop removes repeated patters.
          */
          while(j<Stroke_Index&&l<Stroke_Index)
          {
              if(Stroke[j]==Stroke[j+1])
              {   if(l!=0&&Stroke[j]!=String_Array[l-1]) 
                  {  String_Array[l]=Stroke[j];
                    l++;
                  }
                  else if(l==0)
                  {
                      String_Array[l]=Stroke[j];
                    l++;
                  }     
                  j=j+2;
              }
              else
              {
                  j++;
              }
             
          }
          String Stroke="";
          for(int Stroke_Index=0;Stroke_Index<l;Stroke_Index++)
          {
              Stroke=Stroke+String_Array[Stroke_Index];
              
          }
          System.out.println(Stroke);
         /**ENd of removing repeated pattern**/
          
          
          
          
         /* The Compare function returns the float value by comparing the reference and the stroke
            Depending on the complexity of drawing the alphabet threshold values are selected*/
          
          double ret=Compare(Stroke,l,Up);
          if(ret>0.85)
          {   
              
              return(1);
                
          }
          
          ret=Compare(Stroke,l,down);  
          if(ret>0.75)
          {   
              return(5);
          }
          ret=Compare(Stroke,l,right);    
          if(ret>0.85)
          {   
              return(3);
          }
          
          ret=Compare(Stroke,l,left);    
          if(ret>0.85)
          {   
              return(7);
          }
          
          ret=Compare(Stroke,l,lbrt);    
          if(ret>.85)
          {   
              return(2);
          }
          
          ret=Compare(Stroke,l,ltrb);    
          if(ret>.85)
          {    
              return(4);
          }
          
          ret=Compare(Stroke,l,trlb);    
          if(ret>.85)
          {    
              return(6);
          }
          
          ret=Compare(Stroke,l,rblt);    
          if(ret>.85)
          {    
              return(8);
          }
          
          ret=Compare(Stroke,l,c);    
          if(ret>.85)
          {   
              return(103);
          }
          
          ret=Compare(Stroke,l,c2);    
          if(ret>.85)
          {   
              return(103);
          }
          
          ret=Compare(Stroke,l,c3);    
          if(ret>.8)
          {   
              return(103);
          }
          
          ret=Compare(Stroke,l,a2);    
          if(ret>.8)
          {   
              return(101);
          }
          
          ret=Compare(Stroke,l,a4);    
          if(ret>.8)
          {   
              return(101);
          }
          
          ret=Compare(Stroke,l,a3);    
          if(ret>.8)
          {   
              return(101);
          }
         
          ret=Compare(Stroke,l,b1);    
          if(ret>.85)
          {   
              return(102);
          }
          
          ret=Compare(Stroke,l,b);    
          if(ret>.85)
          {   
              return(102);
          }
          ret=Compare(Stroke,l,d);    
          if(ret>.95)
          {   
              return(104);
          }
          
          ret=Compare(Stroke,l,d3);    
          if(ret>.85)
          {   
              return(104);
          }
          
          ret=Compare(Stroke,l,d2);    
          if(ret>.85)
          {   
              return(104);
          }
          
          ret=Compare(Stroke,l,e);    
          if(ret>.8)
          {   
              return(105);
          }
          
          ret=Compare(Stroke,l,g);    
          if(ret>.85)
          {   
              return(107);
          }
          
          ret=Compare(Stroke,l,g2);    
          if(ret>.85)
          {   
              return(107);
          }
          
          ret=Compare(Stroke,l,L);    
          if(ret>.75)
          {   
              return(112);
          }
          
          ret=Compare(Stroke,l,M);    
          if(ret>.75)
          {   
              return(113);
          }
          
          ret=Compare(Stroke,l,M1);    
          if(ret>.79)
          {   
              return(113);
          }
          
        /*  ret=Compare(Stroke,l,M2);    
          if(ret>.8)
          {   
              return(113);
          }
         */ 
          ret=Compare(Stroke,l,M3);    
          if(ret>.8)
          {   
              return(113);
          }
          ret=Compare(Stroke,l,N);    
          if(ret>.75)
          {   
              return(114);
          }
          
          ret=Compare(Stroke,l,R);    
          if(ret>.75)
          {   
              return(118);
          }
          
          ret=Compare(Stroke,l,R2);    
          if(ret>.78)
          {   
              return(118);
          }
          
          
          
          ret=Compare(Stroke,l,S);    
          if(ret>.85)
          {   
              return(119);
          }
          
          ret=Compare(Stroke,l,V);    
          if(ret>.75)
          {   
              return(123);
          }
          
          ret=Compare(Stroke,l,U);    
          if(ret>.85)
          {   
              return(122);
          }
          
          ret=Compare(Stroke,l,V2);    
          if(ret>.75)
          {   
              return(123);
          }
          
          ret=Compare(Stroke,l,W);    
          if(ret>.8)
          {   
              return(124);
          }
          
          ret=Compare(Stroke,l,Y);    
          if(ret>.75)
          {   
              return(126);
          }
          
          ret=Compare(Stroke,l,Y2);    
          if(ret>.75)
          {   
              return(126);
          }
          
          ret=Compare(Stroke,l,y3);    
          if(ret>.75)
          {   
              return(126);
          }
          
          ret=Compare(Stroke,l,y4);    
          if(ret>.75)
          {   
              return(126);
          }
          return(0);
    
  } 

    /**
     * End of Compare function**
     */

    /**
     * *Following set of functions check similarity between two given Strings**
     */
    static double Compare(String Stroke, int l, String Source) {
        int dist = editDistance(Stroke, Source);
        double sim = getSimilarity(Stroke, Source, dist);
        return (sim);

    }

    public static double getSimilarity(String s, String Right_Click, int d) {
        int len = s.length() + Right_Click.length();

        return 1 - (float) d / len;
    }

    public static int editDistance(String s, String Right_Click) {
        int n = s.length();
        int m = Right_Click.length();
        int d[][] = new int[n + 1][m + 1];

        for (int Stroke_Index = 0; Stroke_Index <= n; Stroke_Index++) {
            d[Stroke_Index][0] = Stroke_Index;
        }

        for (int j = 1; j <= m; j++) {
            d[0][j] = j;
        }

        for (int Stroke_Index = 1; Stroke_Index <= n; Stroke_Index++) {
            for (int j = 1; j <= m; j++) {
                int cost;
                if (s.charAt(Stroke_Index - 1) == Right_Click.charAt(j - 1)) {
                    cost = 0;
                } else {
                    cost = 1;
                }

                d[Stroke_Index][j] = Math.min(d[Stroke_Index - 1][j] + 1, // deletion
                        Math.min(d[Stroke_Index][j - 1] + 1, // insertion
                                d[Stroke_Index - 1][j - 1] + cost));   // substitution
            }
        }

        return d[n][m];
    }

    /**
     * End of similarity matching functions*
     */

    /**
     * Function dynamically assigns url or programm path by reading from file*
     */
    public static void SetPath() {

        switch (reference) {

            case 101://System.out.println("U typed A");
                abc = "A";
                break;

            case 102://System.out.println("U typed B");
                abc = "B";
                break;

            case 103://System.out.println("U typed C");
                abc = "C";
                break;

            case 104://System.out.println("U typed D");
                abc = "D";
                break;

            case 105://System.out.println("U typed E");
                abc = "E";
                break;

            case 107://System.out.println("U typed G");
                abc = "G";
                break;

            case 112://System.out.println("U typed L");
                abc = "c";
                break;

            case 113://System.out.println("U typed M");
                abc = "M";
                break;

            case 114://System.out.println("U typed N");
                abc = "N";
                break;

            case 118:
                System.out.println("U typed R");
                abc = "R";
                break;

            case 119://System.out.println("U typed S");
                abc = "S";
                break;

            case 122:
                System.out.println("U typed U");
                abc = "U";
                break;

            case 123://System.out.println("U typed V");
                abc = "V";
                break;

            case 124://System.out.println("U typed W");
                abc = "W";
                break;

            case 126://System.out.println("U typed Y");
                abc = "Y";
                break;

        }

        BufferedReader br = null;
        String a = new String();
        String b = new String();
        String c = new String();
        String d = new String();

        Temp obj = new Temp();
        try {

            br = new BufferedReader(new FileReader("Back_End.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                a = sCurrentLine;
                b = br.readLine();
                c = br.readLine();
                d = br.readLine();

                if (a.equals(abc)) {
                    obj.a = a;
                    obj.b = b;
                    obj.c = c;
                    obj.d = d;

                    referencea = a;
                    referenceb = Integer.parseInt(b);
                    referencec = c;
                    referenced = d;

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * End of function which assigns path dynamically*
     */

    /**
     * Function to open the program related to the gesture*
     */
    public static void Open_App() {
        int c = Match_String(); //Matching the strings which returns the percentage similarity between strings
        //returns int value depending on the characted drawn with mouse

        frame.dispose();//Frame is disposed when mouse drag is comleted

        /*the following switch statement performs required operations 
         depending on the returned int value from compare functions*/
        switch (c) {

            case 101:
                System.out.println("U typed A");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try { //Runs the specified website in default browser
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }
                break;

            case 102:
                System.out.println("U typed B");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try {
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }
                break;

            case 103:
                System.out.println("U typed C");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try {
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }
                break;
            case 104:
                System.out.println("U typed D");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try {
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }
                break;
            case 105:
                System.out.println("U typed E");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try {
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }
                break;
            case 107:
                System.out.println("U typed G");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try {
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }
                break;
            case 112:
                System.out.println("U typed L");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try {
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }
                break;
            case 113:
                System.out.println("U typed M");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try {
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }
                break;

            case 114:
                System.out.println("U typed N");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try {
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }
                break;
            case 118:
                System.out.println("U typed R***");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try {
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }
                break;
            case 119:
                System.out.println("U typed S");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try {
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }
                break;
            case 122:
                System.out.println("U typed U");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try {
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }
                break;
            case 123:
                System.out.println("U typed V");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try {
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }
                break;
            case 124:
                System.out.println("U typed W");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try {
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }
                break;

            case 126:
                System.out.println("U typed Y");
                if (referenceb == 2) {
                    try {    //Runs the specified application 
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec(referencec);
                    } catch (Exception e) {
                    }
                    break;
                } else if (referenceb == 1) {
                    try {
                        Process proc = Runtime.getRuntime().exec("firefox -new-tab "+referencec);
                    } catch (Exception e) {
                    }
                    break;

                }

        }
        referencec = "";
        referenceb = 0;
        frame.repaint();
    }

    @Override
    public void run() {
        while (true) { //for continuous running of the program
            try {

                Thread.sleep(100);
                BufferedReader br = null;
                try {//Reading enable status from file

                    br = new BufferedReader(new FileReader("Enable.txt"));

                   sCurrentLine = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(sCurrentLine);
            if (sCurrentLine.equals("1")) {
               GlobalScreen.getInstance().removeNativeMouseMotionListener(this);
                t.stop();
                
                
            }
        }
    }
}
//End of class

class Temp {

    String a;
    String b;
    String c;
    String d;
}
