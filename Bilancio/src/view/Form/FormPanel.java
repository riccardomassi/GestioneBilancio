package view.Form;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import org.jdatepicker.impl.*;

import view.Table.TablePanel;

public class FormPanel extends JPanel{

    private JLabel labelData;
    private UtilDateModel dateModel;
    private Properties properties;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private DateFormatter dateFormatter;

    private JLabel labelAmmontare;
    private JTextField fieldAmmontare;

    private JLabel labelDescrizione;
    private JTextField fieldDescrizione;

    private JLabel labelVisualizza;
    private JComboBox boxVisualizza;

    private JButton aggiungi;

    private JLabel labelRicerca;
    private JTextField fieldRicerca;
    private JButton ricerca;

    private FormListener formListener; 

    public FormPanel(TablePanel tablePanel){
        /*
         * Set layout e border
         */
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Inserimento Dati"));

        /*
         * Componenti
         */
        labelData = new JLabel("Data:");
        //le prossime 9 righe di codice servono per la creazione del calendario
        dateModel = new UtilDateModel();
        properties = new Properties();
        properties.put("text.today","Today");
        properties.put("text.month","Month");
        properties.put("text.year","Year");
        dateModel.setSelected(true); //metodo per settare la data odierna di default
        datePanel = new JDatePanelImpl(dateModel, properties);
        dateFormatter = new DateFormatter();
        datePicker = new JDatePickerImpl(datePanel, dateFormatter);
        
        labelAmmontare = new JLabel("Ammontare:");
        fieldAmmontare = new JTextField(25);
        
        labelDescrizione = new JLabel("Descrizione:");
        fieldDescrizione = new JTextField(25);

        aggiungi = new JButton("Aggiungi");

        //Relezione Giorno, Mese, Anno da visualizzare
        labelVisualizza = new JLabel("Visualizza:");
        String[] opzioniSeleziona = {"Giorno", "Mese", "Anno"};
        boxVisualizza = new JComboBox<>(opzioniSeleziona);

        //Ricerca
        labelRicerca = new JLabel("Ricerca:");
        fieldRicerca = new JTextField(25);
        ricerca = new JButton("Ricerca");

        /*
         * Gestione bottone aggiungi:
         * Viene creata una classe anonima ActionListener che implementa il metodo actionPerformed,
         * che permette di gestire cosa accade quando viene premuto il bottone
         */
        aggiungi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String data = datePicker.getJFormattedTextField().getText();
                int ammontare = Integer.parseInt(fieldAmmontare.getText());
                String descrizione = fieldDescrizione.getText();

                FormEvent formEvent = new FormEvent(this, data, ammontare, descrizione);

                /*
                 * Se il formListener é stato settato nel Frame,
                 * gli passo il formEvent appena creato
                 */
                if(formListener != null){
                    formListener.formEventListener(formEvent);
                }
            }
        });

        /*
         * Gestione bottone Ricerca
         */
        ricerca.addActionListener(new ActionListener(){
            int index = -1;
            @Override
            public void actionPerformed(ActionEvent e){
                String textToSearch = fieldRicerca.getText().toString();
                //Se il text field è vuoto esco
                if (textToSearch.equals("")){
                    return;
                }
                /*
                 * Se index = -1 stampo errore perchè significa che non ho trovato il testo
                 * altrimenti chiamo il metodo che cerca il testo
                 */
                if (index != -1){
                    index = tablePanel.searchText(textToSearch, index);
                }
                else{
                    JOptionPane.showMessageDialog(FormPanel.this, "Testo non trovato", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /*
         * Layout
         */
        GridBagConstraints gbc = new GridBagConstraints();

        //gbc etichetta Data
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(labelData, gbc);
        //gbc field Data
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(datePicker, gbc);

        //gbc etichetta Ammontare
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 0, 0, 5);
        add(labelAmmontare, gbc);
        //gbc field Ammontare
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 0, 0, 0);
        add(fieldAmmontare, gbc);

        //gbc etichetta Descrizione
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 0, 0, 5);
        add(labelDescrizione, gbc);
        //gbc field Descrizione
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 0, 0, 0);
        add(fieldDescrizione, gbc);

        //gbc label Visualizza
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 0, 0, 5);
        add(labelVisualizza, gbc);
        //gbc ComboBox Visualizza
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 0, 0, 0);
        add(boxVisualizza, gbc);

        //gbc bottone Aggiungi
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(aggiungi, gbc);

        //gbc etichetta Ricerca
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 25, 0, 0);
        add(labelRicerca, gbc);
        //gbc field Ricerca
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 100);
        add(fieldRicerca, gbc);

        //gbc bottone Ricerca
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 110, 0, 0);
        add(ricerca, gbc);
    }

    
    /** 
     * @param formListener
     * 
     * Metodo per impostare il FormListener
     */
    public void setFormListener(FormListener formListener){
        this.formListener = formListener;
    }
}
