package view.File;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FilterCSV extends FileFilter{

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

        if(estensione.equals("csv")){
            return true;
        }
        
        return false;
    }

    @Override
    public String getDescription() {
        return "File Bilancio (*.csv)";
    }
    
}
