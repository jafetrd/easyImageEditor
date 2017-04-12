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
import javax.swing.filechooser.*;

/* FiltroChooser.java is used by FileChooserDemo2.java. */
public class FiltroChooser extends FileFilter {

    //Acepta todos los directorios y archivos gif, jpg,tiff o png.
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = UtilidadChooser.getExtension(f);
        if (extension != null) {
            if (extension.equals(UtilidadChooser.tiff) ||
                extension.equals(UtilidadChooser.tif) ||
                extension.equals(UtilidadChooser.gif) ||
                extension.equals(UtilidadChooser.jpeg) ||
                extension.equals(UtilidadChooser.jpg) ||
                extension.equals(UtilidadChooser.png)) {
                    return true;
            } else {
                return false;
            }
        }
        return false;
    }

    //La descripcion que aparece en el JFileChooser
    @Override
    public String getDescription() {
        return "Solo imagenes";
    }
}
