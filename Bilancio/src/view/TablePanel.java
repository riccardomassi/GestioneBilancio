package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Voce;

public class TablePanel extends JPanel {
    
    private JTable table;
    private TableModel tableModel;
    
    public TablePanel(){

        tableModel = new TableModel();
        table = new JTable(tableModel);
        
        //setto il layout della tabella
        setLayout(new BorderLayout());
        //aggiungo la tabella al centro del pannello
        add(new JScrollPane(table), BorderLayout.CENTER);

    }
    
    /** 
     * @param listaVoci
     * 
     * Metodo che mi permette di settare le Voci nel TableModel
     */
    public void setData(List<Voce> listaVoci){
        tableModel.setData(listaVoci);
    }

    /*
     * Metodo che aggiorna la Tabella coi nuovi dati aggiunti
     */
    public void aggiorna(){
        tableModel.fireTableDataChanged();
    }
}
