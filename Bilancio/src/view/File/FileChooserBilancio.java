package view.File;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * Classe che implemente l'override del metodo approveSelection,
 * in modo che il programma chieda se si voglia sovrascrivere
 * un file esistente
 */
public class FileChooserBilancio extends JFileChooser{
    @Override
    public void approveSelection(){
        File f = new File(getSelectedFile().toString()+".bil");
        if(f.exists() && getDialogType() == SAVE_DIALOG){
            int result = JOptionPane.showConfirmDialog(this,"Il file esiste, sovrascriverlo?","File esistente",JOptionPane.YES_NO_CANCEL_OPTION);
            switch(result){
                case JOptionPane.YES_OPTION:
                    super.approveSelection();
                    return;
                case JOptionPane.NO_OPTION:
                    return;
                case JOptionPane.CLOSED_OPTION:
                    return;
                case JOptionPane.CANCEL_OPTION:
                    cancelSelection();
                    return;
            }
        }
        super.approveSelection();
    }        
}
