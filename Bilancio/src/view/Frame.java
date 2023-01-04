package view;

import javax.swing.*;

import controller.Controller;
import view.File.FileChooserBilancio;
import view.File.FileFilterBilancio;
import view.File.Utils;
import view.Form.FormEvent;
import view.Form.FormListener;
import view.Form.FormPanel;
import view.Table.TableEvent;
import view.Table.TableListener;
import view.Table.TablePanel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame{

    private TablePanel tablePanel;
    private FormPanel formPanel;
    private Controller controller;
    private FileChooserBilancio fileChooser;

    private JTextField fieldTotale;

    public Frame(){
        /*
         * Set titolo, layout e menu
         */
        super("Conto Corrente");
        setLayout(new BorderLayout());
        setJMenuBar(creaBarraMenu());

        tablePanel = new TablePanel();
        formPanel = new FormPanel(tablePanel);
        controller = new Controller();

        fieldTotale = new JTextField(25);
        fieldTotale.setEditable(false);

        fileChooser = new FileChooserBilancio();
        fileChooser.addChoosableFileFilter(new FileFilterBilancio());
        fileChooser.setAcceptAllFileFilterUsed(false);

        /*
         * prendo la lista di Voci dal Database e la passo al Tabella attraverso il Controller
         */
        tablePanel.setData(controller.getVoci());

        /*
         * Metodo del FormPanel che permette di aggiungere i dati del FormPanel 
         * attraverso il FormEvent al Database attraverso il Controller, 
         * quando viene premuto il tasto Aggiungi
         */
        formPanel.setFormListener(new FormListener() {
            @Override
            public void formEventListener(FormEvent fe){
                String data = fe.getData();
                int ammontare = fe.getAmmontare();
                String descrizione = fe.getDescrizione();

                controller.addVoce(data, ammontare, descrizione);
                tablePanel.aggiorna();

                //gestione somma totale del bilancio
                fieldTotale.setText(controller.getTotale());
            }
        });

        /*
         * Metodo del TablePanel che permette di prendere la riga
         * della voce da eliminare, per poi passarla al controller e
         * eliminare la voce dal Database
         */
        tablePanel.setTableListener(new TableListener() {
            @Override
            public void tableEventListener(TableEvent te){
                int rowIndexDelete = te.getRowToDelete();

                controller.delVoce(rowIndexDelete);

                //gestione somma totale del bilancio
                fieldTotale.setText(controller.getTotale());
            }
        });

        /*
         * Gestione componenti in pannello centrale
         */
        JPanel pc = new JPanel();
        pc.setLayout(new BorderLayout());
        pc.add(tablePanel, BorderLayout.CENTER);
        pc.add(fieldTotale, BorderLayout.SOUTH);

        /*
         * Componenti
         */
        add(pc, BorderLayout.CENTER);
        add(formPanel, BorderLayout.LINE_START);

        /*
         * Impostazioni frame
         */
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    
    /** 
     * @return JMenuBar
     * 
     * MenuBar che si occupa di gestire il menu File
     */
    private JMenuBar creaBarraMenu(){
        JMenuBar barraMenu = new JMenuBar();

        /*
         * Menu File
         */
        JMenu menuFile = new JMenu("File");

        JMenuItem menuItemImporta = new JMenuItem("Importa");
        JMenuItem menuItemEsci = new JMenuItem("Esci");

        JMenu menuEsporta = new JMenu("Esporta");
        JMenuItem esportaFile = new JMenuItem("File");
        JMenuItem esportaCSV = new JMenuItem("CSV");
        JMenuItem esportaTesto = new JMenuItem("Testo");

        menuEsporta.add(esportaFile);
        menuEsporta.add(esportaTesto);
        menuEsporta.add(esportaCSV);

        menuFile.add(menuItemImporta);
        menuFile.add(menuEsporta);
        menuFile.addSeparator();
        menuFile.add(menuItemEsci);

        //Action event che mi permette di gestire l'importazione da file
        menuItemImporta.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (fileChooser.showOpenDialog(Frame.this) == JFileChooser.APPROVE_OPTION){
                    try {
                        controller.caricaDaFile(fileChooser.getSelectedFile());
                        tablePanel.aggiorna();
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(Frame.this, "Impossibile importare i dati da file", "Errore", JOptionPane.ERROR_MESSAGE);
                    }

                    //gestione somma totale del bilancio
                    fieldTotale.setText(controller.getTotale());
                }
            }
        });

        //Action event che mi permette di gestire l'esportazione su file
        esportaFile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (fileChooser.showSaveDialog(Frame.this) == JFileChooser.APPROVE_OPTION){
                    try {
                        File f = fileChooser.getSelectedFile();
                        //se il file non ha estensione o é diversa da .bil, viene inserita l'estensione .bil
                        if (Utils.getExtension(f) == null || !Utils.getExtension(f).equalsIgnoreCase("bil")) {
                            f = new File(f.toString() + ".bil");
                        }

                        controller.salvaSuFile(f);

                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(Frame.this, "Impossibile esportare i dati su file", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //Action event che permette di uscire dal programma se viene premuto esci nel menu File
        menuItemEsci.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(ABORT);
            }
        });
        
        barraMenu.add(menuFile);

        return barraMenu;
    }
}
