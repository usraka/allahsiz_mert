
package com.alpegeakarsu.calismabahcem;

import javax.swing.JFrame;



public class Main{
   
    
    public static void main(String[] args){
    
    JFrame frame = new JFrame();
    Panel panel = new Panel();
    frame.setBounds(10,10,700,605);
    frame.setTitle("SimpleTry");
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    MenuCreator menubar = new MenuCreator();
    frame.setJMenuBar(menubar.createMenuBar());
   
    frame.add(panel);
     frame.setVisible(true);
    
     
    }
    
    
    
    
}

