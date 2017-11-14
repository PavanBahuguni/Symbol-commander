/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beta;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.EOF;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.UIManager;

////
/**
 *
 * @author SONY
 */
public class UserInterface extends javax.swing.JFrame {
 Vector va;
 Vector  vv;

 int countA=0;
 String added[]=new String[30];
 String option;
 int cr=0;
 
 
    private char[] c;
    /**
     * Creates new form NewJFrame
     */
    public UserInterface() {
        try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         } catch (Exception e) {
              // exit application, log  or ignore exception
          }
        
        //Dynamically updating to be added symbols list
          
        initComponents();
        isExist();
        UpdateAddList();
        UpdateViewList();
        int temp;
        temp=getColour();
        jComboBox1.setSelectedIndex(temp);
        lpath.setVisible(false);
        lname.setVisible(false);
        path.setVisible(false);
        name.setVisible(false);
        jList2.setSelectedIndex(0);
        initImage();
        jButton3.setVisible(false);
        setEnable();
        setThickness();
    }
    void isExist()
    {
        File F = new File("added.txt");
        if(!F.exists())
        {
            try{
            FileWriter Fw = new FileWriter(F);
         
            }catch(Exception E){
                E.printStackTrace();
            }
        }
        
        F = new File("Back_End.txt");
        if(!F.exists())
        {
            try{
            FileWriter Fw = new FileWriter(F);
            
            
            }catch(Exception E){
                E.printStackTrace();
            }
        }
        
         F = new File("colour.txt");
        if(!F.exists())
        {
            try{
            FileWriter Fw = new FileWriter(F);
       
            BufferedWriter bw=new BufferedWriter(Fw);
            bw.write("1");
            bw.close();
            }catch(Exception E){
                E.printStackTrace();
            }
        }
             F = new File("Thick.txt");
        if(!F.exists())
        {
            try{
            FileWriter Fw = new FileWriter(F);
           
            BufferedWriter bw=new BufferedWriter(Fw);
            bw.write("20");
            bw.close();
            }catch(Exception E){
                E.printStackTrace();
            }
        }
        F = new File("Enable.txt");
        if(!F.exists())
        {
            try{
            FileWriter Fw = new FileWriter(F);
            BufferedWriter bw=new BufferedWriter(Fw);
            bw.write("0");
            bw.close();
            }catch(Exception E){
                E.printStackTrace();
            }
        }
        F = new File("ToBeAdded.txt");
        if(!F.exists())
        {
            try{
            FileWriter Fw = new FileWriter(F);
           
            BufferedWriter bw=new BufferedWriter(Fw);
            bw.write("A");
            bw.newLine();
            bw.write("B");
            bw.newLine();
            bw.write("C");
            bw.newLine();
            bw.write("D");
            bw.newLine();
            bw.write("E");
            bw.newLine();
            bw.write("G");
            bw.newLine();
            bw.write("L");
            bw.newLine();
            bw.write("M");
            bw.newLine();
            bw.write("N");
            bw.newLine();
            bw.write("R");
            bw.newLine();
            bw.write("S");
            bw.newLine();
            bw.write("U");
            bw.newLine();
            bw.write("V");
            bw.newLine();
            bw.write("W");
            bw.newLine();
            bw.write("Y");
            bw.close();
            }catch(Exception E){
                E.printStackTrace();
            }
        }
        
        
        
        
    }
    void initImage()
    {
          int xyz=jList2.getSelectedIndex();
          
          xyz++;
   
          System.out.println(""+xyz);
          String sCurrentLine = null;
          
          BufferedReader br=null;
       
        try {

            
            br = new BufferedReader(new FileReader("added.txt"));
            boolean flag=false;
            for(int i=0;i<xyz;i++)
            {
                sCurrentLine = br.readLine();
                System.out.println(sCurrentLine);
                if(sCurrentLine==null)
                {
                    flag=true;
                }
                br.readLine();
                
                 
               
            }
            
          if(sCurrentLine!=null){
            Image img = ImageIO.read(getClass().getResource(sCurrentLine+".jpeg"));
            
            jLabel4.setIcon(new ImageIcon(img));
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
     
          System.out.println(1);
         
        try {
          if(sCurrentLine!=null)
          {
            Image img = ImageIO.read(getClass().getResource(sCurrentLine+".jpeg"));
            jLabel4.setIcon(new ImageIcon(img));
          }
        } catch (IOException ex) {
        }
        
       
        
        
        xyz=jList2.getSelectedIndex();
          
          xyz++;
   
          System.out.println(""+xyz);
          
          
          
       
        try {

            
            br = new BufferedReader(new FileReader("ToBeAdded.txt"));
            for(int i=0;i<xyz;i++)
            {
                sCurrentLine = br.readLine();
             
                System.out.println(sCurrentLine);
                
            }
            System.out.println(sCurrentLine+".jpg");
            if(sCurrentLine!=null)
          {
            jLabel4.setIcon(new ImageIcon(sCurrentLine+".jpg"));
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
         
        
        try {
           if(sCurrentLine!=null)
          {
            Image img = ImageIO.read(getClass().getResource(sCurrentLine+".jpeg"));
          
            jLabel5.setIcon(new ImageIcon(img));
          }
          } catch (IOException ex) {
        }
       
    } 
    
    
    void setEnable()
    {
        BufferedReader br=null;
        String sCurrentLine = null;
        try {

            
            br = new BufferedReader(new FileReader("Enable.txt"));
            sCurrentLine=br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    
        if(sCurrentLine.equals("0"))
        {
            jCheckBox1.setSelected(true);
            Linux a=new Linux();
           
            
        }
    }
    int getColour()
    {
        BufferedReader br = null;
          try {
                String sCurrentLine;
                br = new BufferedReader(new FileReader("colour.txt"));
                sCurrentLine=br.readLine();
                return(Integer.parseInt(sCurrentLine));
     
               
              
          } catch (IOException e) {
			e.printStackTrace();
	  } finally {
              
	       try {
		       if (br != null)br.close();
		} catch (IOException ex) {
				ex.printStackTrace();
		}
	}
     return 0;
    }
    
    void setThickness()
    {
        BufferedReader br = null;
        String sCurrentLine ="";
          try {
                
                br = new BufferedReader(new FileReader("Thick.txt"));
                sCurrentLine=br.readLine();
                
     
               
              
          } catch (IOException e) {
			e.printStackTrace();
	  } finally {
              
	       try {
		       if (br != null)br.close();
		} catch (IOException ex) {
				ex.printStackTrace();
		}
	}
        jComboBox2.setSelectedItem(""+sCurrentLine);
    }
    public void UpdateAddList()
    {
          va=new Vector();
          BufferedReader br = null;
          try {
                String sCurrentLine;
                br = new BufferedReader(new FileReader("ToBeAdded.txt"));
                while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
                                va.add(sCurrentLine);
                                countA++;
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
           jList1.setListData(va);
    }

    
  
    
    
    public void UpdateViewList()
    {     
          vv=new Vector();
          int count=0;
          String One="";
          
          
          BufferedReader br = null;
          try {
                String sCurrentLine;
                br = new BufferedReader(new FileReader("Added.txt"));
     
                while ((sCurrentLine = br.readLine()) != null) {
			
                    //System.out.println(sCurrentLine);
                    if(count==0)
                    {
                        One=sCurrentLine;
                        count++;
                    }
                    else
                    {
                        sCurrentLine=One+": "+sCurrentLine;
                        vv.add(sCurrentLine);
                        count=0;
                    }
                         
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
        jList2.setListData(vv);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel5 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
        jSlider1 = new javax.swing.JSlider();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lpath = new javax.swing.JLabel();
        path = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        lname = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(100, 100, 100));
        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseEntered(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(64, 64, 64));

        jLabel1.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SYMBOL IMAGE:");

        jList2.setFont(new java.awt.Font("Kristen ITC", 1, 12)); // NOI18N
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jList2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jList2FocusGained(evt);
            }
        });
        jScrollPane4.setViewportView(jList2);

        jButton1.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        jButton1.setLabel("REMOVE");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/beta/A.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(90, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("View Symbols", jPanel1);

        jPanel2.setBackground(new java.awt.Color(64, 64, 64));

        lpath.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        lpath.setForeground(new java.awt.Color(255, 255, 255));
        lpath.setText("Program Path :");

        path.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        path.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        jButton2.setLabel("ADD");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SYMBOL IMAGE:");

        jList1.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setAutoscrolls(false);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        lname.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        lname.setForeground(new java.awt.Color(255, 255, 255));
        lname.setText("Application Name:");

        name.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        rb1.setBackground(new java.awt.Color(64, 64, 64));
        buttonGroup1.add(rb1);
        rb1.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        rb1.setForeground(new java.awt.Color(255, 255, 255));
        rb1.setText("ADD WEBSITE");
        rb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rb1MouseClicked(evt);
            }
        });
        rb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb1ActionPerformed(evt);
            }
        });

        rb2.setBackground(new java.awt.Color(64, 64, 64));
        buttonGroup1.add(rb2);
        rb2.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        rb2.setForeground(new java.awt.Color(255, 255, 255));
        rb2.setText("ADD APPLICATION");
        rb2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rb2MouseClicked(evt);
            }
        });
        rb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb2ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/beta/A.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(rb1)
                                        .addGap(10, 10, 10)
                                        .addComponent(rb2))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lpath)
                                    .addComponent(lname))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rb1)
                            .addComponent(rb2)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lpath))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Add Symbols", jPanel2);

        jPanel3.setBackground(java.awt.Color.darkGray);
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Colours :");

        jComboBox1.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BLACK", "BLUE", "RED ", "YELLOW", "ORANGE", "PINK ", "MAGENTA", "GRAY", "CYAN", "GREEN" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jCheckBox1.setBackground(java.awt.Color.darkGray);
        jCheckBox1.setFont(new java.awt.Font("Kristen ITC", 1, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("ENABLE");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        jButton3.setText("TUTORIAL (VIDEO)");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("THICKNESS :");

        jComboBox2.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "10", "15", "20" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jCheckBox1)
                .addGap(94, 94, 94)
                .addComponent(jButton3)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Settings", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jTabbedPane1MouseEntered

    private void rb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb2ActionPerformed
        // TODO add your handling code here:
        option=evt.getActionCommand();
        System.out.println(option);

    }//GEN-LAST:event_rb2ActionPerformed

    private void rb2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb2MouseClicked
        // TODO add your handling code here:
        // System.out.println("Button two clicked");
        lpath.setText("PROGRAM PATH:");
        lname.setText("PROGRAM NAME:");
        lpath.setVisible(true);
        lname.setVisible(true);
        path.setVisible(true);
        name.setVisible(true);
    }//GEN-LAST:event_rb2MouseClicked

    private void rb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb1ActionPerformed
        // TODO add your handling code here:
        option=evt.getActionCommand();
        System.out.println(option);
    }//GEN-LAST:event_rb1ActionPerformed

    private void rb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb1MouseClicked
        // TODO add your handling code here:
        //System.out.println("Button one clicked");
        lpath.setText("WEBSITE URL:");
        lname.setText("WEBSITE NAME:");
        lpath.setVisible(true);
        lname.setVisible(true);
        path.setVisible(true);
        name.setVisible(true);
    }//GEN-LAST:event_rb1MouseClicked

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:

        if(evt.getButton()==1)//Mouse left clicked
        {
            String source=(String)jList1.getSelectedValue();
            String Name=name.getText();
            String Path=path.getText();
            //To remove a Symbol from add List
            BufferedReader br = null;
            BufferedWriter bw=null;

            try {

                String sCurrentLine;
                br = new BufferedReader(new FileReader("ToBeAdded.txt"));
                while ((sCurrentLine = br.readLine()) != null) {

                    if(source.equals(sCurrentLine))
                    {
                        va.remove((String)source);
                    }
                    else
                    {
                        continue;
                    }
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

            try {

                bw = new BufferedWriter(new FileWriter("ToBeAdded.txt"));
                for(int i=0;i<va.size();i++)
                {
                    if(i<countA)
                    { bw.write((String)va.get(i));
                        bw.newLine();
                    }
                    else
                    va.remove(i);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null)bw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            UpdateAddList();
            //End Of Removing Symbol from add list

            //Add removed symbol to the view list

            try {

                bw = new BufferedWriter(new FileWriter("Added.txt",true));
                bw.write(source);
                bw.newLine();
                bw.write(Name);
                bw.newLine();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null)bw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            UpdateViewList();
            name.setText("");
            path.setText("");

            //End of adding removed symbol to view list//

            //Adding Sybol and Path of the program to the file

            try {

                bw = new BufferedWriter(new FileWriter("Back_End.txt",true));
                bw.write(source);
                bw.newLine();
                if(option.equals("ADD WEBSITE"))
                {
                    bw.write(""+1);
                    bw.newLine();
                }
                else if((option.equals("ADD APPLICATION")) )
                {
                    bw.write(""+2);
                    bw.newLine();
                }
                bw.write(Path);
                bw.newLine();
                bw.write(Name);
                bw.newLine();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null)bw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void pathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pathActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        BufferedWriter bw=null;
        //bw = new BufferedWriter(new FileWriter("Back_End.txt"));
        try {

            bw = new BufferedWriter(new FileWriter("colour.txt"));
            bw.write(""+jComboBox1.getSelectedIndex());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
     
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        BufferedWriter bw=null;
        String source=(String)jList2.getSelectedValue();
        int abc=0;
        c=new char[100];

        String temp="",end="";
        source.getChars(0,source.length(), c,0);
        for(int k=0;k<source.length();k++)
        {
            if(c[k]==':')
            {
                abc=1;
                k++;
            }
            else if(abc==0)
            {
                temp=temp+c[k];
            }
            else
            {
                end=end+c[k];
            }
        }
        String refer=new String();
        refer = temp;

        try {

            bw = new BufferedWriter(new FileWriter("ToBeAdded.txt",true));
            bw.write(temp);
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        UpdateAddList();
        // System.out.println();

        vv.remove((String)source);

        abc=0;
        char[] r=new char[100];
        temp="";end="";
        try {

            bw = new BufferedWriter(new FileWriter("Added.txt"));
            for(int t=0;t<vv.size();t++)
            {
                source=(String)vv.get(t);
                temp="";
                end="";
                source.getChars(0,source.length(),r,0);
                for(int k=0;k<source.length();k++)
                {

                    if(r[k]==':')
                    {
                        abc=1;
                        k++;
                    }
                    else if(abc==0)
                    {
                        temp=temp+r[k];
                    }
                    else
                    {
                        end=end+r[k];
                    }
                }

                //  System.out.println(temp);
                // System.out.println(end);
                // System.out.println();
                abc=0;
                bw.write(temp);
                bw.newLine();
                bw.write(end);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        UpdateViewList();

        /** Remove item from Back_End file**/

        BufferedReader br=null;
        Vector restore=new Vector();
        Temp obj;
        try {

            String sCurrentLine;
            br = new BufferedReader(new FileReader("Back_End.txt"));
            while ((sCurrentLine = br.readLine()) != null) {
                obj=new Temp();
                obj.a=sCurrentLine;
                obj.b=br.readLine();
                obj.c=br.readLine();
                obj.d=br.readLine();
                restore.add(obj);

                System.out.println(obj.a);
                System.out.println(obj.b);
                System.out.println(obj.c);
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

        for(int i=0;i<restore.size();i++)
        {
            obj=new Temp();
            obj=(Temp)restore.get(i);
            if(obj.a.equals(refer))
            {

                System.out.println(obj.a);
                System.out.println(obj.b);
                System.out.println(obj.c);
                restore.remove(i);
                break;
            }
        }

        try {

            bw = new BufferedWriter(new FileWriter("Back_End.txt"));
            for(int i=0;i<restore.size();i++)
            {
                obj=(Temp)restore.get(i);
                bw.write(obj.a);
                bw.newLine();
                bw.write(obj.b);
                bw.newLine();
                bw.write(obj.c);
                bw.newLine();
                bw.write(obj.d);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jList2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jList2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jList2FocusGained

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        // TODO add your handling code here:
          int xyz=jList2.getSelectedIndex();
          
          xyz++;
   
          System.out.println(""+xyz);
          String sCurrentLine = null;
          
          BufferedReader br=null;
       
        try {

            
            br = new BufferedReader(new FileReader("added.txt"));
            for(int i=0;i<xyz;i++)
            {
                sCurrentLine = br.readLine();
                br.readLine();
                System.out.println(sCurrentLine);
                
            }
            System.out.println(sCurrentLine+".jpg");
            jLabel4.setIcon(new ImageIcon(sCurrentLine+".jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
     
          
         
        try {
            Image img = ImageIO.read(getClass().getResource(sCurrentLine+".jpeg"));
            jLabel4.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
        }
        
    }//GEN-LAST:event_jList2MouseClicked

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        
        int xyz=jList1.getSelectedIndex();
          
          xyz++;
   
          System.out.println(""+xyz);
          String sCurrentLine = null;
          
          BufferedReader br=null;
       
        try {

            
            br = new BufferedReader(new FileReader("ToBeAdded.txt"));
            for(int i=0;i<xyz;i++)
            {
                sCurrentLine = br.readLine();
               
                System.out.println(sCurrentLine);
                
            }
            System.out.println(sCurrentLine+".jpg");
            //jLabel4.setIcon(new ImageIcon(sCurrentLine+".jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
     
          
         
        try {
            Image img = ImageIO.read(getClass().getResource(sCurrentLine+".jpeg"));
            jLabel5.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
        }
        
    }//GEN-LAST:event_jList1MouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
      String sCurrentLine = null;
          
          BufferedReader br=null;
       
        try {

            
            br = new BufferedReader(new FileReader("Enable.txt"));
            sCurrentLine=br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
      int flag=Integer.parseInt(sCurrentLine);
      
      BufferedWriter bw=null;
      try {

            bw = new BufferedWriter(new FileWriter("Enable.txt"));
            if(flag==0)
             bw.write("1");
            else 
              bw.write("0");  
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
           }
      }
      
      
      
      
      
      if(flag==1)    
      {   
          Linux a=new Linux();
          
      }    
      
     
     
    
     
      
      
     
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        
        
        BufferedWriter bw=null;
        //bw = new BufferedWriter(new FileWriter("Back_End.txt"));
        try {

            bw = new BufferedWriter(new FileWriter("Thick.txt"));
            bw.write(""+jComboBox2.getSelectedItem());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
    /* try {
         // TODO add your handling code here:

         Desktop.getDesktop().open(new File("C:\\Users\\SONY\\Documents\\NetBeansProjects\\UserI\\Beta.wmv"));
     } catch (IOException ex) {
         Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
     }*/
     
    }//GEN-LAST:event_jButton3MouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
       
        /* Create and display the form */
        
                new UserInterface().setVisible(true);
     
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lname;
    private javax.swing.JLabel lpath;
    private javax.swing.JTextField name;
    private javax.swing.JTextField path;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    // End of variables declaration//GEN-END:variables
}


