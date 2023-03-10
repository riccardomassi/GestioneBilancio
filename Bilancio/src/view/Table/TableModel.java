package view.Table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Voce;

/**
 * Classe che gestisce il modello della tabella
 */
public class TableModel extends AbstractTableModel{

    private List<Voce> listaVoci;
    private String[] nomiColonne = {"Data", "Ammontare", "Descrizione"};

    public TableModel(){

    }

    
    /** 
     * @param listaVoci lista delle voci della Tabella
     */
    public void setData(List<Voce> listaVoci){
        this.listaVoci = listaVoci;
    }

    
    /** 
     * @param column
     * @return String
     * 
     * metodo per inserire i titoli delle colonne
     */
    @Override
    public String getColumnName(int column){
        return nomiColonne[column];
    }

    
    /** 
     * @return int numero colonne
     */
    @Override
    public int getColumnCount() {
        return 3;
    }

    
    /** 
     * @return int numero righe
     */
    @Override
    public int getRowCount() {
       return listaVoci.size();
    }
    
    /** 
     * @param rowIndex indice riga
     * @param columnIndex indice colonna
     * @return Object
     * 
     * Metodo che ritorna il valore della riga e colonna passati
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        //seleziono la riga della Voce
        Voce voce = listaVoci.get(rowIndex);

        //seleziono la colonna corretta in base al columnIndex passato
        switch(columnIndex){
            case 0: return voce.getData();
            case 1: return voce.getAmmontare();
            case 2: return voce.getDescrizione();
            default: return "";
        }
    }
}
