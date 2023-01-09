package view.File;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FilterText extends FileFilter{

    @Override
    public boolean accept(File file) {
        //se il file é una cartella deve essere visibile
        if(file.isDirectory()){
            return true;
        }

        String estensione = Utils.getExtension(file);

        if(estensione == null){
            return false;
        }

        if(estensione.equals("txt")){
            return true;
        }
        
        return false;
    }

    @Override
    public String getDescription() {
        return "File Bilancio (*.txt)";
    }
    
}