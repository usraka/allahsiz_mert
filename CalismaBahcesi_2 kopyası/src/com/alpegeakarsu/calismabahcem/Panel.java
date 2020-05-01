
package com.alpegeakarsu.calismabahcem;
/*    IMPORTS      */   
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
 

        

/*    ATTRIBUTES      */   
public class Panel extends JPanel  implements KeyListener,ActionListener {
    public static boolean isPlaying = false;
    private int score = 0;
    public static int totalBricks = 21;
    public static Timer timer;
    private int totalTime;
    public static int delay = 8;
    private int playerX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    public static int level = 0;
    private Image img;
    private MapGenerator map;
    
     
    
   /* DEFAULT CONSTRUCTOR */
    public Panel(){
        map = new MapGenerator(3,7); // kaça kaçlık bir matris oluşturmak isteniyorsa... 3 satır 7 sütun 
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
        System.out.println(delay);
    }
    
    /*    PAINT METHOD TO PREPARE PANEL      */    
    @Override
    public void paint(Graphics g){
    // background
    g.setColor(Color.black);
    g.fillRect(1,1, 692, 592); 
    // drawing map
    map.draw((Graphics2D)g);
    // borders
    g.setColor(Color.black);
    g.fillRect(0,0,3,592);
    g.fillRect(0,0,692,3);
    g.fillRect(691,0,3,592);
    // scores
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,25));
    g.drawString(""+score,590,30);
    // the paddle
    g.setColor(Color.green);
    g.fillRect(playerX, 550, 100, 8);
    // the ball
    g.setColor(Color.yellow);
    g.fillOval(ballPosX, ballPosY, 20, 20);
    // if there no bricks left
    if(totalBricks <= 0){
        isPlaying = false;
        ballXdir = 0;
        ballYdir = 0;
        g.setColor(Color.RED);
        g.setFont(new Font("serif",Font.BOLD,30));
        g.drawString("You Won,Total score:" + score,260,300);
        
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString("Press enter the Restart",230,350);
    
    }
    // if ball dont hit paddle and falls
    if(ballPosY > 570){
        isPlaying = false;
        ballXdir = 0;
        ballYdir = 0;
        g.setColor(Color.RED);
        g.setFont(new Font("serif",Font.BOLD,30));
        g.drawString("Game over,Total score:"+ score,190,300);
        
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString("Press enter the Restart",230,350);
       
    }
    g.dispose();
    }
    
    /*    FUNCTION TO CREATE MENU      */   
   
   
 
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            }else moveRight();
           
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
             if(playerX < 10){
                playerX = 10;
            }else moveLeft();
               
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!isPlaying){
                isPlaying = true;
                ballPosX = 120;
                ballPosY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(3,7);
                repaint();
            }
        
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
           isPlaying = false;
      
           
        if(e.getKeyCode()== KeyEvent.VK_Q){
            timer.stop();
            System.exit(0);
        }
        

        
    }
    
    public void moveRight(){
       if(level == 0){
           isPlaying = true;
           playerX += 20;
       }
       if(level == 1){
           isPlaying = true;
           playerX += 30;
       }
       if(level == 2){
           isPlaying = true;
           playerX += 40;
       }
        if(level == 3){
           isPlaying = true;
           playerX += 60;
       }
    }
    public void moveLeft(){
       if(level == 0){
           isPlaying = true;
           playerX -= 20;
       }
       if(level == 1){
           isPlaying = true;
           playerX -= 30;
       }
       if(level == 2){
           isPlaying = true;
           playerX -= 40;
       }
        if(level == 3){
           isPlaying = true;
           playerX -= 60;
       }
       
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        repaint(); // its call paint method again and draw everything again. to use moveright and moveleft functions. (to see on program actually)
        if(isPlaying){
           if(new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(playerX,550,100,8))){ // 27:43 // bu kısım gelen topun rakete vurması için yazılıyor
    
               ballYdir = -ballYdir;
               
           }
           A: for(int i=0; i<map.map.length; i++){
               for(int j=0; j<map.map[0].length; j++){
                   if(map.map[i][j] > 0 ){
                       int brickX = j* map.brickWidth + 80;
                       int brickY = i* map.brickHeight + 50;
                       int brickWidth = map.brickWidth;
                       int brickHeight = map.brickHeight;
                       
                       Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                       Rectangle ballRect = new Rectangle(ballPosX,ballPosY,20,20);
                       Rectangle brickRect = rect;
                       if(ballRect.intersects(brickRect)){   
                           map.setBrickValue(0, i, j);
                           
                        
                           if(level == 0) score+= 5;
                           if(level == 1) score+=10;
                           if(level == 2) score+=15;
                           if(level == 3) score+=20;
                           
                           if(ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width){
                           ballXdir = -ballXdir;
                           }else{
                               ballYdir = -ballYdir;
                           }
                           break A;
                       }
                       
                       
                   }
                   
               }
           
           }
            ballPosX += ballXdir; 
            ballPosY += ballYdir;
            if(ballPosX < 0){           // this is for the left border
                ballXdir = -ballXdir;   
            }
            if(ballPosY < 0){       // this is for the top border
                ballYdir = -ballYdir;
            }
            if(ballPosX > 670){       // this is for the right border
                ballXdir = -ballXdir;
            }
        
        
        
        }
        repaint();
        
        
    }
    
}
