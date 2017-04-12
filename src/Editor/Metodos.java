/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author jafet
 */
public class Metodos extends JPanel{

int imagePanelWidth;
int imagePanelHeight;
private BufferedImage imagen;
private BufferedImage imagenTemp;
private BufferedImage imagenOriginal;
private Dimension tamaño;
private Dimension tamañoCuadro;
public static Dimension nuevoTamaño;
public static boolean detectar;
private int Dx,Dy,H,W;
public static int posX,posY,iniX,iniY;
private Color color;

    public Metodos(){
        this.imagePanelHeight = 700;
        this.imagePanelWidth = 1000;
        
    }

    public BufferedImage getImagen() {
        return imagen;
    }
    
    public void setColor(Color color) {
        this.color = color;
        System.out.println("entrecolor");
        repaint();
    }

    public Color getColor() {
        return color;
    }
    
    public void changeImage(File f){
        try {
            imagen = ImageIO.read(f);
            tamaño = new Dimension(imagen.getWidth(),imagen.getHeight());
            tamañoCuadro = new Dimension(imagePanelWidth, imagePanelHeight);
            nuevoTamaño = getScaledDimension(tamaño, tamañoCuadro);
            imagenTemp = imagen;
            imagenOriginal = imagen;
            Dx=nuevoTamaño.width/100;
            Dy=nuevoTamaño.height/100;
            H = nuevoTamaño.height;
            W = nuevoTamaño.width;
            color = Color.WHITE;
        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       
    
    public void saveImage(String destinationPath, String format ){
	if(imagen == null){
           JOptionPane.showMessageDialog(this, "Primero elige una imagen");
	   return;
        }
        try{		
         System.out.println(destinationPath+"."+format);	
	 ImageIO.write(imagen, format, new File(destinationPath+"."+format));
        }catch(Exception e){ }
    }
    
    public void resetImage(){
	imagen = imagenOriginal; //resets the imagen
	imagenTemp = imagenOriginal;
        repaint();
    }


    //resetting the dimension of the imagen
    public static Dimension getScaledDimension(Dimension tmnImagen, Dimension limite) {
                int originalAncho = tmnImagen.width;
                int originalAlto = tmnImagen.height;
                int limiteAncho = limite.width-30;
                int limiteAlto = limite.height-30;
                int nuevoAncho = originalAncho;
                int nuevoAltura = originalAlto;
                // first check if we need to scale width
        if (originalAncho > limiteAncho) {  
                nuevoAncho = limiteAncho;    //scale width to fit
                nuevoAltura = (nuevoAncho * originalAlto) / originalAncho; //scale height to maintain aspect ratio
        }
   
        if (nuevoAltura > limiteAlto) {  // then check if we need to scale even with the new height   
                nuevoAltura = limiteAlto;    //scale height to fit instead
                nuevoAncho = (nuevoAltura * originalAncho) / originalAlto; //scale width to maintain aspect ratio
        }   
        return new Dimension(nuevoAncho, nuevoAltura);	
    }
	
    public void aplicarEfecto(int opcion){
        if(imagen == null){
            JOptionPane.showMessageDialog(this, "No hay imagen");
            return;
        }
      // imagen = imagenTemp; //restaura la imagen
       nEfectos e = nEfectos.values()[opcion];
       switch(e){
           case Voltear_H: imagen=EfectosImagen.girarHorizontal(imagen);
               break;
           case Voltear_V: imagen=EfectosImagen.girarVertical(imagen);
               break;
           case Solarizar: imagen=EfectosImagen.solarizar(imagen);
               break;
           case EscalaGrises: imagen=EfectosImagen.escalaGrises(imagen);
               break;
           case Sepia: imagen=EfectosImagen.sepia(imagen);
               break;
           case Invertir: imagen=EfectosImagen.invertir(imagen);
               break;
           case Posterizar: imagen=EfectosImagen.posterizar(imagen);
               break;
           case Colores: new Colores().setVisible(true);
               break;
       }
      // aplicarEfectoPermanente();
       repaint();
    }
    
   
    public void zoomImagen(int zoomLevel){
       nuevoTamaño.width=W+(zoomLevel*Dx);
       nuevoTamaño.height=H+(zoomLevel*Dy);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
            if(imagen != null){
            super.paintComponent(g);
              Graphics2D g2d = (Graphics2D) g.create();
                  g2d.setXORMode(color);
                    if(detectar==false){
                         g2d.drawImage(imagen, getWidth()/2 - nuevoTamaño.width/2, getHeight()/2 - nuevoTamaño.height/2, nuevoTamaño.width, nuevoTamaño.height, this);
                    }else{
                         g2d.drawImage(imagen, posX-nuevoTamaño.width/2, posY-nuevoTamaño.height/2, nuevoTamaño.width, nuevoTamaño.height,this);
                    }
               g2d.dispose();
            }
    }

    @Override // Specify preferred size	
    public Dimension getPreferredSize() {		
    return new Dimension(imagePanelWidth, imagePanelHeight);	
    }


	
}
