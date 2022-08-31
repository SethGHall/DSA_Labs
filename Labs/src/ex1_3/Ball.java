package ex1_3;

/*************************************************************************
FILE:           Ball.java
AUTHER:         Seth Hall
DATE:           9 March 2019
DESCRIPTION:    Creates the class for the ball
*******************************************************************************/

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Ball implements Runnable
{
    private int width;
    private int height;
    public int x,y,dx,dy;
    private boolean stopRequest;
    Random generator;
    Color color;
    private int RADIUS = 80;
 
    public Ball(int width,int height)
    {
        this.height = height;
        this.width = width;
        generator = new Random();
        //start in center of panel
        x = width/2;
        y = height/2;
        //create a random postion for the movements
        RADIUS = generator.nextInt(RADIUS)+2;
        do
        {   dx = generator.nextInt(10)-5;
            dy = generator.nextInt(10)-5;
        }while(dx == 0 || dy ==0);
        //random colour
        color = new Color(generator.nextFloat(),generator.nextFloat(),generator.nextFloat());
    }
   
 
    
    public void run()
    {
        stopRequest = false;
        //loop until finished
        while(!stopRequest)
        {   
            moveBall();
               
            try
            {
                Thread.sleep(5);
            }
            catch(InterruptedException e){}
        }
    }
    public void requestStop()
    {
        stopRequest = true;
    }
    private void moveBall()
    {
        x += dx;
        y += dy;
        //if outside bounds of world flip directions
        if(x <= 0 || (x+RADIUS)>=width) 
        {    dx = -dx;
       //      float offY = generator.nextInt(3);
//                     if(dx <= 0)
//                         dx += offY;
//                     if(dx > 0)
//                         dx -= offY;
        }
        if(y <= 0 || (y+RADIUS)>=height) 
        {    dy = -dy;
//                    float off = generator.nextInt(2)-1;
//                     if(dy <= 0)
//                         dy += off;
//                     if(dy > 0)
//                         dy -= off;
        }
                 
    }
    public  void draw(Graphics g, int width , int height)
    {   //draw the ball
        g.setColor(color);
        g.fillOval((int)x,(int)y,RADIUS,RADIUS);
        this.width = width;
        this.height = height;
    }
}
