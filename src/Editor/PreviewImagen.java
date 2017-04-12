/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template archivo, choose Tools | Templates
 * and open the template in the editor.
 */
package Editor;

/**
 *
 * @author jafet
 */
import javax.swing.*;
import java.beans.*;
import java.awt.*;
import java.io.File;

public class PreviewImagen extends JComponent implements PropertyChangeListener {
    ImageIcon vistaPrevio = null;
    File archivo = null;

    public PreviewImagen(JFileChooser fc) {
        setPreferredSize(new Dimension(500, 500));
        fc.addPropertyChangeListener(this);
    }

    public void cargarImagen() {
        if (archivo == null) {
            vistaPrevio = null;
            return;
        }
        //Don't use createImageIcon (which is a wrapper for getResource)
        //because the image we're trying to load is probably not one
        //of this program's own resources.
        ImageIcon imagenTemp = new ImageIcon(archivo.getPath());
        if (imagenTemp != null) {
            if (imagenTemp.getIconWidth() > 400) {
                vistaPrevio = new ImageIcon(imagenTemp.getImage(). getScaledInstance(400, -1,Image.SCALE_DEFAULT));
            } else { //no se necesita hacer pequeña la imagen
                vistaPrevio = imagenTemp;
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        boolean update = false;
        String prop = e.getPropertyName();
        //si el direcotorio cambia, no muestra una imagen.
        if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop)) {
            archivo = null;
            update = true;
        //si un archivo a sido seleccionado, encuentra cual lo fue. 
        } else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
            archivo = (File) e.getNewValue();
            update = true;
        }
        //Actualiza la vista de acuerdo a lo anterior 
        if (update) {
            vistaPrevio = null;
            if (isShowing()) {
                cargarImagen();
                repaint();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (vistaPrevio == null) { cargarImagen(); }
        if (vistaPrevio != null) {
            int x = getWidth()/2 - vistaPrevio.getIconWidth()/2;
            int y = getHeight()/2 - vistaPrevio.getIconHeight()/2;
            if (y < 0) {   y = 0; }
            if (x < 100) {   x = 100; }
            vistaPrevio.paintIcon(this, g, x, y);
        }
    }
}
