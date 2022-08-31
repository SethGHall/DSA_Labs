/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex7_1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author sehall
 */
public class TableDisplayer extends JPanel implements ListSelectionListener{
    private static final String DB_URL 
      = "jdbc:mysql://raptor2.aut.ac.nz:3306/testUnrestricted";
   
    private JList tableList;
    private DefaultListModel<String> model;
    
    private JSplitPane splitpane;
    private Connection conn;
    private Statement stmt;
    
    
   public TableDisplayer()
   {
       super(new BorderLayout());
       
       model = new DefaultListModel<>();
       tableList = new JList(model);
       tableList.setPreferredSize(new Dimension(300,500));
       tableList.addListSelectionListener(this);
       splitpane = new JSplitPane();
       splitpane.setPreferredSize(new Dimension(500,500));
       splitpane.setLeftComponent(tableList);
       splitpane.setRightComponent(new JPanel());
       splitpane.setDividerLocation(0.25);
       add(splitpane, BorderLayout.CENTER);
       
   }
   
   public void populateList(String user, String password)
   {
        try {
            conn = DriverManager.getConnection(DB_URL, user, password);
            // create statement that will be sensitive to database changes
            // if supported by database
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        
            String command = "SHOW TABLES";
            ResultSet rs = stmt.executeQuery(command);
            while(rs.next())
            {
                String tableName = rs.getString(1);
                model.addElement(tableName);
                
            }
            rs.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(TableDisplayer.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
    public static void main(String[] args)
    {  // obtain user name and password from keyboard
        Scanner keyboardScanner = new Scanner(System.in);
        System.out.print("Please enter user name:");
        String userName = keyboardScanner.nextLine();
        System.out.print("Please enter password:");
        String password = keyboardScanner.nextLine();
        TableDisplayer td = new TableDisplayer();
        
        JFrame frame = new JFrame("TABLE DISPLAYER");
        frame.setDefaultCloseOperation
           (WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter()
        {  public void windowClosing(WindowEvent we)
          {  // close the database connection when frame closes
             System.out.println("Closing connection to raptor");
          }
       });
         frame.getContentPane().add(td);
         frame.pack();
         // position the frame in the middle of the screen
         Toolkit tk = Toolkit.getDefaultToolkit();
         Dimension screenDimension = tk.getScreenSize();
         Dimension frameDimension = frame.getSize();
         frame.setLocation((screenDimension.width-frameDimension.width)
            /2, (screenDimension.height-frameDimension.height)/2);
         frame.setVisible(true);
         
         td.populateList(userName, password);
      }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!tableList.isSelectionEmpty() && !tableList.getValueIsAdjusting())
        {
            System.out.println("YOU CLICKED ON "+tableList.getSelectedValue());
        }
    }
   }