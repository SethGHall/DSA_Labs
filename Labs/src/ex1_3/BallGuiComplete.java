package ex1_3;

/*******************************************************************************************
FILE:           BallGUI.java
AUTHER:         Seth Hall
DATE:           March 2019
DESCRIPTION:    Creates the class to hold the balls
*******************************************************************************************/
                      //useful imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.*;                             //imports for files
import java.util.List;
import javax.swing.*;                         //for components
import javax.swing.Timer;                     //timer inport

import java.awt.event.*;                      //for the listeners
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.*;                       //for dataformatexception
import javax.swing.event.*;
import javax.swing.border.LineBorder;         //for borders


public class BallGuiComplete extends JPanel implements ActionListener
{
    private List<Ball> list;
    private Timer timer;
    private JButton addButton,stopButton,priority;
    JPanel ballPanel;
    InnerClass innerclass;
    boolean setPriority;

    public BallGuiComplete()
    { super();
      setLayout(new BorderLayout());
      try
         {  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         }
         catch(Exception e){}
         list = new ArrayList<Ball>();
       // list = Collections.synchronizedList(list);
        int delay = 10;                     //initial value of delay
        timer = new Timer(delay,this);

        //setPreferredSize(new Dimension(800,800));


        innerclass = new InnerClass();
        addButton = new JButton(" add ball ");
        stopButton = new JButton(" stop ");
        priority = new JButton(" Add 100 ");
        addButton.addActionListener(this);
        stopButton.addActionListener(this);
        priority.addActionListener(this);
        timer.start();
        setPriority = false;

        JPanel panel = new JPanel();
        panel.add(addButton);
        panel.add(stopButton);
        panel.add(priority);
        //panel.setPreferredSize(new Dimension(200,800));

        add(panel,BorderLayout.SOUTH);
        add(innerclass,BorderLayout.CENTER);
    }


    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        if(source == addButton)
        {
            Ball ball = new Ball(innerclass.getWidth(),innerclass.getHeight());
            Thread thread = new Thread(ball);
            //thread.setPriority(Thread.MIN_PRIORITY);
            list.add(ball);
            thread.start();
        }
        if(source == stopButton)
        {
            for(Ball ball:list)
                ball.requestStop();
        }
        if(source == timer)
        {
            innerclass.repaint();
        }
        if(source == priority)
        {
            for(int i=0;i<100;i++)
            {
               Ball ball = new Ball(innerclass.getWidth(),innerclass.getHeight());
               Thread thread = new Thread(ball);
               list.add(ball);
               thread.start();
            }
        }
    }


    public class InnerClass extends JPanel
    {
        public InnerClass()
        {
            setPreferredSize(new Dimension(600,600));
            setBackground(Color.WHITE);
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            for(Ball ball:list)
                ball.draw(g, getWidth(), getHeight());
        }
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("THE BALL BOUNCER");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BallGuiComplete());
        frame.pack();                                      //pack frame
        frame.setVisible(true);                                      //show the frame
    }
}
