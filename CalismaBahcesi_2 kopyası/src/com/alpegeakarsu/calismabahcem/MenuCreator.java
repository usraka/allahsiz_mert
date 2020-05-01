/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpegeakarsu.calismabahcem;

import static com.alpegeakarsu.calismabahcem.Panel.level;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author alpegemac
 */
public class MenuCreator {
      /* ATTRIBUTES */
       private JMenuBar menuBar;
       private JMenu menu,menu2;
       private JMenuItem menuItem,menuItem2;
       private Panel panel;
    
    public void MenuCreator(){
        panel = new Panel();
    
    }
    
    public JMenuBar createMenuBar() {
       
        
        
        // create first menu 
        menuBar = new JMenuBar();
        menu = new JMenu("Level");
        menu2 = new JMenu("Help");
        // create menu items for first menu
        menuItem = new JMenuItem("Increase Level");
        menuItem2 = new JMenuItem("Start Page");
        menuItem.setAccelerator(KeyStroke.getKeyStroke('L', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        // add actionListeners for first menuItems
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemActionPerformed(evt);
            }
            public void menuItemActionPerformed(ActionEvent evt) {
               if(panel.level <= 2){ 
                   panel.level++;
                   panel.timer.setDelay(panel.delay = panel.delay -2);
                   panel.isPlaying = false;
                  if(level == 1){
                   String message = "***LEVEL 2*** \n"
                           + "In This Stage Ball Must Hit 2 Times To Break Bricks..\n"
                           +"PRESS RIGHT-LEFT BUTTONS TO CONTINUE ";
                   JOptionPane.showMessageDialog(null, message);
               }
                    if(level == 2){
                   String message = "***LEVEL 3*** \n"
                           + "In This Stage Ball Must Hit 2 Times To Break Bricks..\n"
                           +"PRESS RIGHT-LEFT BUTTONS TO CONTINUE ";
                   JOptionPane.showMessageDialog(null, message);
               }
                      if(level == 3){
                   String message = "***LEVEL 4*** \n"
                           + "This stage Similar To Level 1 Because Ball Is Too Fast Good Luck..\n"
                           +"PRESS RIGHT-LEFT BUTTONS TO CONTINUE ";
                   JOptionPane.showMessageDialog(null, message);
               }
               }
            }
        });
         menuItem2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem2ActionPerformed(evt);
            }

            public void menuItem2ActionPerformed(ActionEvent evt) {
                String message = "To Pause : SPACE \n"
                        + "To Continue : RIGHT-LEFT BUTTONS\n"
                        + "To Restart : ENTER\n"
                        +"To Increase Level : CTRL or COMMAND + L"   ;
                panel.isPlaying = false;
                JOptionPane.showMessageDialog(null, message);
                
            }
        });
      
        // add items to first menu 
        menu.add(menuItem);
        menu2.add(menuItem2);
        
        menuBar.add(menu);
        menuBar.add(menu2);
        return menuBar;
 
    }
}
