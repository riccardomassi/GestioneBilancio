package view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Voce;

public class TableModel extends AbstractTableModel{

    private List<Voce> listaVoci;
    private String[] nomiColonne = {"Data", "Ammontare", "Descrizione"};

    public TableModel(){

    }

    public void setData(List<Voce> listaVoci){
        this.listaVoci = listaVoci;
    }

    @Override
    public String getColumnName(int column){
        return nomiColonne[column];
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
       return listaVoci.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Voce voce = listaVoci.get(rowIndex);

        //considero l'indice delle colonne da 0 a 2 e da sinistra verso destra
        switch(columnIndex){
            case 0: return voce.getData();
            case 1: return voce.getAmmontare();
            case 2: return voce.getDescrizione();
            default: return null;
        }
    }
    
}
