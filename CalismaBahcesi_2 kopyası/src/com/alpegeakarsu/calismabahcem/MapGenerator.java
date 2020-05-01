/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpegeakarsu.calismabahcem;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author alpegemac
 */
public class MapGenerator {
    public int map[][]; 
    public int brickWidth;
    public int brickHeight;
    public int count[][];
    Panel panel;
    public MapGenerator(int row,int column){
        map = new int[row][column]; // yeni bir 2D matris oluşturduk
        for(int i=0; i< map.length; i++){ // bu kullanımdaki map.length 3'dür yani i 3 kere dönecek
        
            for(int j=0; j< map[0].length; j++){ // bu kullanımdaki map[0] ise length 7'dir. yani j 7 kere dönecek
                map[i][j] = 1;
                count[i][j] = 1;    // matrislerin dolu olduğunu göstermek için 1 ile işaretledik.
            }
        
        }
        brickWidth = 540/column; // 540/7 = 77 brickWidth = 77
        brickHeight = 150/row;  // 150/3 = 50 brickHeight = 50
    
    }
    
    public void draw(Graphics2D g){
      for(int i=0; i< map.length; i++){ // 3 kere dönecek
        
            for(int j=0; j< map[0].length; j++){  // 7 kere donecek
                if(map[i][j]>0){ // top did not intersects yet
                g.setColor(Color.white);  // this means box's color
                g.fillRect(j*brickWidth+80,i*brickHeight + 50 , brickWidth, brickHeight);
                 g.setStroke(new BasicStroke(3)); // Kutular arasındaki boşluk
            g.setColor(Color.black); // kutular arasındaki çizgilerin rengi
            g.drawRect(j*brickWidth+80,i*brickHeight + 50 , brickWidth, brickHeight);
                
                }
            }
           
        } 
    }
    public void setBrickValue(int value,int row,int column){
       panel = new Panel();
         if(panel.level == 0 || panel.level == 3){
       map[row][column] = value;
         panel.totalBricks--;
         }
         if(panel.level == 1 || panel.level == 2){
                
                if(count[row][column] == 1){  
                  map[row][column] = 1;
                  count[row][column] = 0;
                }
                 else{  
                map[row][column] = value;  
                panel.totalBricks--;
                }
        }
        
    }
    
    

    
 
       
        
        
        
        
        
    
    
    
}
