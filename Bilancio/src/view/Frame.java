package view;

import javax.swing.*;

import controller.Controller;

import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame{

    private TablePanel tablePanel;
    private FormPanel formPanel;
    private Controller controller;

    public Frame(){
        /*
         * Set titolo, layout e menu
         */
        super("Conto Corrente");
        setLayout(new BorderLayout());
        setJMenuBar(creaBarraMenu());

        tablePanel = new TablePanel();
        formPanel = new FormPanel();
        controller = new Controller();

        //prendo la lista di Voci dal Database e la passo al TablePanel attraverso il Controller
        tablePanel.setData(controller.getVoci());

        formPanel.setFormListener(new FormListener() {
            @Override
            public void formEventListener(FormEvent fe){
                String data = fe.getData();
                String ammontare = fe.getAmmontare();
                String descrizione = fe.getDescrizione();

                controller.addVoce(data, ammontare, descrizione);
                tablePanel.aggiorna();
            }
        });

        /*
         * Componenti
         */
        add(tablePanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.LINE_START);

        /*
         * Impostazioni frame
         */
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JMenuBar creaBarraMenu(){
        JMenuBar barraMenu = new JMenuBar();

        /*
         * Menu File
         */
        JMenu menuFile = new JMenu("File");

        JMenuItem menuItemImporta = new JMenuItem("Importa", new ImageIcon("./images/import.png"));
        JMenuItem menuItemEsporta = new JMenuItem("Esporta", new ImageIcon("./images/export.png"));
        JMenuItem menuItemEsci = new JMenuItem("Esci");

        menuFile.add(menuItemImporta);
        menuFile.add(menuItemEsporta);
        menuFile.addSeparator();
        menuFile.add(menuItemEsci);

        //action event che mi permette di uscire dal programma se premo esci
        menuItemEsci.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(ABORT);
            }
        });

        /*
         * Menu Ricerca
         */
        JMenu menuCerca = new JMenu("Ricerca");

        JMenuItem menuItemCerca = new JMenuItem("Cerca");

        menuCerca.add(menuItemCerca);

       //implementa la pagina di popup per eseguire la ricerca
        
        barraMenu.add(menuFile);
        barraMenu.add(menuCerca);

        return barraMenu;
    }
    
}
