/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Editor;

/**
 *
 * @author jafet
 */



import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

/* VistaImagenArchivo.java is used by FileChooserDemo2.java. */
public class VistaImagenArchivo extends FileView {
    ImageIcon jpgIcon;
    ImageIcon gifIcon;
    ImageIcon tiffIcon;
    ImageIcon pngIcon;

    public VistaImagenArchivo() {
        this.pngIcon = UtilidadChooser.createImageIcon("pngIcon.png");
        this.tiffIcon = UtilidadChooser.createImageIcon("tiffIcon.gif");
        this.gifIcon = UtilidadChooser.createImageIcon("gifIcon.gif");
        this.jpgIcon = UtilidadChooser.createImageIcon("jpgIcon.gif");
    }

    @Override
    public String getName(File f) {
        return null; //let the L&F FileView figure this out
    }

    @Override
    public String getDescription(File f) {
        return null; //let the L&F FileView figure this out
    }

    @Override
    public Boolean isTraversable(File f) {
        return null; //let the L&F FileView figure this out
    }

    @Override
    public String getTypeDescription(File f) {
        String extension = UtilidadChooser.getExtension(f);
        String tipo = null;

        if (extension != null) {
            if (extension.equals(UtilidadChooser.jpeg) ||
                extension.equals(UtilidadChooser.jpg)) {
                tipo = "JPEG Image";
            } else if (extension.equals(UtilidadChooser.gif)){
                tipo = "GIF Image";
            } else if (extension.equals(UtilidadChooser.tiff) ||
                       extension.equals(UtilidadChooser.tif)) {
                tipo = "TIFF Image";
            } else if (extension.equals(UtilidadChooser.png)){
                tipo = "PNG Image";
            }
        }
        return tipo;
    }

    @Override
    public Icon getIcon(File f) {
        String extension = UtilidadChooser.getExtension(f);
        Icon icon = null;

        if (extension != null) {
            if (extension.equals(UtilidadChooser.jpeg) ||
                extension.equals(UtilidadChooser.jpg)) {
                icon = jpgIcon;
            } else if (extension.equals(UtilidadChooser.gif)) {
                icon = gifIcon;
            } else if (extension.equals(UtilidadChooser.tiff) ||
                       extension.equals(UtilidadChooser.tif)) {
                icon = tiffIcon;
            } else if (extension.equals(UtilidadChooser.png)) {
                icon = pngIcon;
            }
        }
        return icon;
    }
}
