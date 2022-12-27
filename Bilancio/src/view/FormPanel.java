package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import org.jdatepicker.impl.*;

public class FormPanel extends JPanel{

    private JLabel labelData;
    private JLabel labelAmmontare;
    private JLabel labelDescrizione;

    private JTextField fieldAmmontare;
    private JTextField fieldDescrizione;

    private JButton aggiungi;

    private FormListener formListener; 

    private UtilDateModel dateModel;
    private Properties properties;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private DateFormatter dateFormatter;

    public FormPanel(){
        /*
         * Set layout e border
         */
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Inserimento Dati"));

        /*
         * Componenti
         */
        labelData = new JLabel("Data:");
        //le prossime 8 righe di codice servono per la creazione del calendario
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
         * Layout
         */
        GridBagConstraints gbc = new GridBagConstraints();

        //gbc etichetta Data
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(10, 0, 0, 5);
        add(labelData, gbc);
        //gbc field Data
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(datePicker, gbc);

        //gbc etichetta Ammontare
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 0, 0, 5);
        add(labelAmmontare, gbc);
        //gbc field Ammontare
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 0, 0, 0);
        add(fieldAmmontare, gbc);

        //gbc etichetta Descrizione
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 0, 0, 5);
        add(labelDescrizione, gbc);
        //gbc field Descrizione
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 0, 0, 0);
        add(fieldDescrizione, gbc);

        //gbc bottone Aggiungi
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(aggiungi, gbc);
    }

    
    /** 
     * @param formListener
     * 
     * Metodo per impostare il FormListener, che verra usato nel Frame
     */
    public void setFormListener(FormListener formListener){
        this.formListener = formListener;
    }
}
