package view.Table;

import javax.swing.*;

import java.awt.*;
import java.util.List;
import model.Voce;
import java.awt.event.*;

public class TablePanel extends JPanel {
    
    private JTable table;
    private TableModel tableModel;
    private JPopupMenu popupMenu;
    private TableListener tableListener;
    
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

        //Visualizzo il popup menu cliccando col tasto destro
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                //Button3 = tasto destro mouse
                if(e.getButton() == MouseEvent.BUTTON3){
                    popupMenu.show(table, e.getX(), e.getY());
                }
            }
        });

        //Cliccando su "Elimina Voce" viene eliminata la voce selezionata
        menuItemEliminaVoce.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int rowIndexDelete = table.getSelectedRow();
                //elimino la riga dalla tabella
                tableModel.fireTableRowsDeleted(rowIndexDelete, rowIndexDelete);

                TableEvent tableEvent = new TableEvent(this, rowIndexDelete);

                if(tableListener != null){
                    tableListener.tableEventListener(tableEvent);
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

    /*
     * Metodo che cerca la stringa passata a partire dall'indice di riga passato 
     * all'interno della Tabella, e se la trova viene evidenziata
     */
    public int searchText(String textToSearch, int currentIndex){
        currentIndex = findNextIndex(textToSearch, currentIndex + 1);
        if (currentIndex != -1){
            for (int i = currentIndex; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getRowCount(); j++) {
                    //Salvo il valore di ogni cella in una stringa
                    String descrizione = tableModel.getValueAt(i, j).toString();
                        if (descrizione.contains(textToSearch)) {
                            //Evidenzio la riga che contiene la stringa trovata
                            table.setRowSelectionInterval(i, i);
                            return i;
                        }
                }
            }
        }

        return -1;
    }

    /*
     * Metodo per la gestione dell'indice di riga di partenza
     * per la ricerca del testo nella tabella
     */
    private int findNextIndex(String textToSearch, int startIndex) {
        for (int i = startIndex; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getRowCount(); j++) {
                String descrizione = tableModel.getValueAt(i, j).toString();
                    if (descrizione.contains(textToSearch)) {
                        return i;
                    }
            }
        }
        return -1;
    }

    /*
     * Metodo per impostare il TableListener
     */
    public void setTableListener(TableListener tableListener){
        this.tableListener = tableListener;
    }
}
