package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FormPanel extends JPanel{

    private JLabel labelData;
    private JLabel labelAmmontare;
    private JLabel labelDescrizione;

    private JTextField fieldData;
    private JTextField fieldAmmontare;
    private JTextField fieldDescrizione;

    private JButton aggiungi;
    private JButton rimuovi;

    private FormListener formListener; 

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
        fieldData = new JTextField(25);
        
        labelAmmontare = new JLabel("Ammontare:");
        fieldAmmontare = new JTextField(25);
        
        labelDescrizione = new JLabel("Descrizione:");
        fieldDescrizione = new JTextField(25);

        aggiungi = new JButton("Aggiungi");
        rimuovi = new JButton("Rimuovi");

        /*
         * Gestione bottoni
         */
        aggiungi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String data = fieldData.getText();
                String ammontare = fieldAmmontare.getText();
                String descrizione = fieldDescrizione.getText();

                FormEvent formEvent = new FormEvent(this, data, ammontare, descrizione);

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
        add(fieldData, gbc);

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

        //gbc bottone Rimuovi
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(rimuovi, gbc);

    }

    //metodo per impostare il FormListener, che viene settato nel Frame
    public void setFormListener(FormListener formListener){
        this.formListener = formListener;
    }
    
}
