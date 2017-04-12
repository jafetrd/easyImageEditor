/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Editor;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.EventQueue.invokeLater;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jafet
 */
public class nuevoMain extends JFrame {
   
    private JSlider zoom;
    private JLabel lupa;
    private JMenuBar menu;
    private JMenu menu1,menu2,menu3,menu4;
    private JMenuItem I1,I2,I3,I4,I5,I6,I7,I8,I9,I10,I11,I12;
    private String ruta;
    private EfectosImagen efecto;
    private JFileChooser elegirImagen;
    private Metodos metodo;
    
    public nuevoMain(){
      componentes();
       metodo = new Metodos();
       efecto = new EfectosImagen();
       add(metodo);
       addMouseMotionListener(new MouseAdapter() {
            @Override
             public void mouseDragged(MouseEvent e){
                 //detectar=rec.contains(e.getPoint());
                 Metodos.posX=e.getX();
                 Metodos.posY=e.getY();
                 Metodos.detectar=true;
                 repaint();
             }

            @Override
             public void mouseReleased(MouseEvent e){
                 Metodos.detectar=false;   
             }
             
            @Override
             public void mousePressed(MouseEvent e){
                 Metodos.iniX=e.getX();
                 Metodos.iniY=e.getY();
             }
        });

       zoom.setVisible(false);
       lupa.setVisible(false);
    }

    private void zoomS(){
        metodo.zoomImagen(zoom.getValue());
        lupa.setText(zoom.getValue()+"%");
        repaint();
    }

    
    private void abrirImagen(){
      if (elegirImagen == null) {
                elegirImagen = new JFileChooser();
                // Add a custom file filter and disable the default
                // (Accept All) file filter.
                elegirImagen.addChoosableFileFilter(new FiltroChooser());
                elegirImagen.setAcceptAllFileFilterUsed(false);
                // Add custom icons for file types.
                elegirImagen.setFileView(new VistaImagenArchivo());
                // Add the preview pane.
                elegirImagen.setAccessory(new PreviewImagen(elegirImagen));
            }
            // Show it.
            int valorReturn = elegirImagen.showDialog(metodo,null);
            // Process the results.
            if (valorReturn == JFileChooser.APPROVE_OPTION) {
                File file = elegirImagen.getSelectedFile();
            try {
                ruta=file.getCanonicalPath();
                System.out.println(ruta);
            } catch (IOException ex) {
                Logger.getLogger(nuevoMain.class.getName()).log(Level.SEVERE, null, ex);
            }
                metodo.changeImage(file);
                zoom.setVisible(true);
                lupa.setVisible(true);
        }else{}
        // Reset the file chooser for the next time it's shown.
        elegirImagen.setSelectedFile(null);
        repaint();
        
    }
    
    private void sobreEscribir(){
         if(metodo.getImagen() == null){
            JOptionPane.showMessageDialog(metodo, "No hay imagen que guardar");
            return;
         }
         int n = JOptionPane.showConfirmDialog(this,"¿Deseas sobreescribir la imagen?","Sobreescribir",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
         System.out.println(n+"");
         if(n==0){
         int index = ruta.lastIndexOf('.');
         String ruta = this.ruta.substring(0, index);
         String formato= this.ruta.substring(index+1,this.ruta.length());
         metodo.saveImage(ruta, formato);
         }
    }
    
    private void guardarImagen(){
        if(metodo.getImagen() == null){
            JOptionPane.showMessageDialog(metodo, "No hay imagen que guardar");
            return;
        }

        elegirImagen = new JFileChooser();
        elegirImagen.setAcceptAllFileFilterUsed(false);
        elegirImagen.addChoosableFileFilter(new FileNameExtensionFilter("PNG", ".png"));
        elegirImagen.addChoosableFileFilter(new FileNameExtensionFilter("JPEG", ".jpg"));
        elegirImagen.setSelectedFile(new File("image"));
        // fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal =  elegirImagen.showSaveDialog(metodo);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file =  elegirImagen.getSelectedFile();
            System.out.println(elegirImagen.getFileFilter().getDescription());
            System.out.println(UtilidadChooser.getExtension(file));
            String path = null;
            String format = null;
            try {
                if (UtilidadChooser.getExtension(file) != null) {
                    path = file.getCanonicalPath();
                    int index = path.lastIndexOf('.');
                    path = path.substring(0, index);
                } else {
                    path = file.getCanonicalPath();
                }

                if ( elegirImagen.getFileFilter().getDescription().equals("PNG")) {
                    format = "png";
                } else {
                    format = "jpg";
                }

                System.out.println(path + "---" + format);
                metodo.saveImage(path,format);
            } catch (IOException e1) {
            }

        } else {
        }
        elegirImagen=null;
    }
    
    private void componentes(){
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        lupa = new JLabel();
        zoom = new JSlider();
        menu = new JMenuBar();
        menu1 = new JMenu();
        menu2 = new JMenu();
        menu3 = new JMenu();
        menu4 = new JMenu();
        I1 = new JMenuItem();
        I2 = new JMenuItem();
        I3 = new JMenuItem();
        I4 = new JMenuItem();
        I5 = new JMenuItem();
        I6 = new JMenuItem();
        I7 = new JMenuItem();
        I8 = new JMenuItem();
        I9 = new JMenuItem();
        I10 = new JMenuItem();
        I11 = new JMenuItem();
        I12 = new JMenuItem();
        menu.setBorder(null);
        menu.setToolTipText("");
        
        lupa.setIcon(new ImageIcon(getClass().getResource("/iconos/zoom.gif"))); 

        zoom.setBackground(new java.awt.Color(255, 255, 255));
        zoom.setForeground(new java.awt.Color(255, 255, 255));
        zoom.setMajorTickSpacing(1);
        zoom.setMinimum(-50);
        zoom.setMaximum(200);
        zoom.setToolTipText("");
        zoom.addChangeListener((javax.swing.event.ChangeEvent evt) -> {zoomS();});
        
        //submenu de archivo abrir, guardar, guardar como...
        menu1.setText("Archivo");
        I1.setIcon(new ImageIcon(getClass().getResource("/iconos/Abrir.gif"))); 
        I1.setText("Abrir");
        I1.addActionListener((ActionEvent ae) -> {abrirImagen();});
        I2.setIcon(new ImageIcon(getClass().getResource("/iconos/guardar.gif")));
        I2.setText("Guardar");
        I2.addActionListener((ActionEvent evt) -> { sobreEscribir();});
        I3.setIcon(new ImageIcon(getClass().getResource("/iconos/guardar2.gif")));
        I3.setText("Guardar como...");
        I3.addActionListener((ActionEvent evt) -> { guardarImagen(); });
        menu1.add(I1);
        menu1.add(I2);
        menu1.add(I3);
        menu.add(menu1);
        
        //submenu de editar 
        menu2.setText("Editar");
        I4.setIcon(new ImageIcon(getClass().getResource("/iconos/eliminar.gif"))); 
        I4.setText("Descartar cambios");
        I4.addActionListener((ActionEvent evt) -> {metodo.resetImage();});
        menu2.add(I4);
        menu.add(menu2);
       
        //submenu de filtros
        menu3.setText("Filtros");
        I5.setIcon(new ImageIcon(getClass().getResource("/iconos/horizontal.gif")));
        I5.setText("Espejo horizontal");
        I5.addActionListener((ActionEvent ae) -> {metodo.aplicarEfecto(0);});
        I6.setIcon(new ImageIcon(getClass().getResource("/iconos/vertical.gif")));
        I6.setText("Espejo vertical");
        I6.addActionListener((ActionEvent ae) -> {metodo.aplicarEfecto(1);});
        I7.setIcon(new ImageIcon(getClass().getResource("/iconos/sun.gif")));
        I7.setText("Solarizar");
        I7.addActionListener((ActionEvent ae) -> {metodo.aplicarEfecto(6);});
        I8.setIcon(new ImageIcon(getClass().getResource("/iconos/gris.png")));
        I8.setText("Escala de grises");
        I8.addActionListener((ActionEvent ae) -> {metodo.aplicarEfecto(3);});
        I9.setIcon(new ImageIcon(getClass().getResource("/iconos/sepia.png")));
        I9.setText("Sepia");
        I9.addActionListener((ActionEvent ae) -> {metodo.aplicarEfecto(4);});
        I10.setIcon(new ImageIcon(getClass().getResource("/iconos/negativo.gif")));
        I10.setText("Invertir");
        I10.addActionListener((ActionEvent ae) -> {metodo.aplicarEfecto(5);});
        I11.setIcon(new ImageIcon(getClass().getResource("/iconos/post.gif")));
        I11.setText("Posterizar");
        I11.addActionListener((ActionEvent ae) -> {metodo.aplicarEfecto(7);});
        menu3.add(I5);
        menu3.add(I6);
        menu3.add(I7);
        menu3.add(I8);
        menu3.add(I9);
        menu3.add(I10);
        menu3.add(I11);
        menu.add(menu3);
        //menu de colores
        menu4.setText("Color");
        I12.setIcon(new ImageIcon(getClass().getResource("/iconos/color2.png")));
        I12.setText("Colorizar");
        I12.addActionListener((ActionEvent ae) -> { metodo.aplicarEfecto(8);});
        menu4.add(I12);
        menu.add(menu4);
        panel.setBackground(Color.WHITE);
        setJMenuBar(menu);
        panel.add(zoom);
        panel.add(lupa);
        add(panel,BorderLayout.SOUTH);
        pack();
    }
    
    public static void main(String[]args){
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(nuevoMain.class.getName()).log(Level.SEVERE, null, ex);
        }
            Font f = new Font("sans-serif", Font.PLAIN, 20);
            UIManager.put("Menu.font", f);   
        invokeLater(() -> {
            nuevoMain n = new nuevoMain();
            n.setTitle("Editor de imagenes");
            n.setSize(1250, 800);
            n.setLocationRelativeTo(null);
            n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            n.setVisible(true);
        });
    }
    
}
