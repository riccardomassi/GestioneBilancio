package view.File;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * Classe che implementa l'override del metodo approveSelection,
 * in modo che il programma chieda se si voglia sovrascrivere
 * un file esistente
 */
public class FileChooser extends JFileChooser{
    int typeChoose;
    /*
     * typeChoose serve per capire se il file che stiamo salvando è
     * 1-tipo Bilancio .bil
     * 2-tipo Testo .txt
     * 3-tipo CSV .csv
     */
    public FileChooser(int typeChoose){
        this.typeChoose = typeChoose;
    }

    @Override
    public void approveSelection(){
        File f;
        switch(typeChoose){
            case 1: f = new File(getSelectedFile().toString()+".bil"); break;

            case 2: f = new File(getSelectedFile().toString()+".txt"); break;

            case 3: f = new File(getSelectedFile().toString()+".csv"); break;

            case default: f = new File(getSelectedFile().toString()+".bil");
        }

        //Se il file esiste appare la pagina di popup che chiede se sovrascrivere il file oppure no
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
