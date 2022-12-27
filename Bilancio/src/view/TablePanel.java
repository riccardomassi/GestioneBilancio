package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Voce;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TablePanel extends JPanel {
    
    private JTable table;
    private TableModel tableModel;
    private JPopupMenu popupMenu;
    
    public TablePanel(){

        tableModel = new TableModel();
        table = new JTable(tableModel);

        /*
         * Gestione Popup Menu tasto destro per eliminare
         * una voce dalla tabella
         */
        popupMenu = new JPopupMenu();
        JMenuItem menuItemEliminaVoce = new JMenuItem("Elimina Voce");
        popupMenu.add(menuItemEliminaVoce);

        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                //Button3 = tasto destro mouse
                if(e.getButton() == MouseEvent.BUTTON3){
                    popupMenu.show(table, e.getX(), e.getY());
                }
            }
        });
        
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
