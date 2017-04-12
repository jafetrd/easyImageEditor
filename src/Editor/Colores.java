/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Editor;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author jafet
 */
public final class Colores extends JFrame{

    JColorChooser jc;
    private Metodos m;
    public Colores(){
            componentes();
            inicio();    
       m = new Metodos();
    }
   
    public final void componentes(){
        setLayout(new BorderLayout());
       // Metodos a = new Metodos();
        jc = new JColorChooser();
        jc.setPreviewPanel(new JPanel());
        jc.getSelectionModel().addChangeListener((ChangeEvent arg0) -> {
            m.setColor(jc.getColor());
            super.repaint();
        });
      
        add(jc);
        pack();
    }
    
    public void inicio(){
          try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Colores.class.getName()).log(Level.SEVERE, null, ex);
        }
        Font f = new Font("sans-serif", Font.PLAIN, 20);
            UIManager.put("Menu.font", f);   
            this.setTitle("Colorizar");
            this.setSize(700,500);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setAlwaysOnTop(true);
    }
    
}
