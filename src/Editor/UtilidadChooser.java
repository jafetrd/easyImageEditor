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
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/* UtilidadChooser.java is used by FileChooserDemo2.java. */
public class UtilidadChooser {
    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String png = "png";

    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    //regresa un imageicon, o null si la ruta no es valida
    protected static ImageIcon createImageIcon(String path) {
    	try {
	     return new ImageIcon(ImageIO.read(new File(path)));
	} catch (IOException e) {
             return null;
	}
    }
}
