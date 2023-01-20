package view.Form;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import org.jdatepicker.impl.*;

import model.Voce;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
    private JTextField fieldVisualizza;

    private JButton aggiungi;
    private JButton modifica;

    private JLabel labelRicerca;
    private JTextField fieldRicerca;
    private JButton ricerca;

    private JLabel labelInizio;
    private JTextField fieldInizio;
    private JLabel labelFine;
    private JTextField fieldFine;

    private JButton visualizza;
    private JButton indietro;

    private AddListener formListener; 
    private ModifyListener modifyListener;

    public FormPanel(TablePanel tablePanel, List<Voce> voci){
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
        
        // Imposto label e field Ammontare
        labelAmmontare = new JLabel("Ammontare:");
        fieldAmmontare = new JTextField(25);
        
        // Imposto label e field Descrizione
        labelDescrizione = new JLabel("Descrizione:");
        fieldDescrizione = new JTextField(25);

        // Imposto bottone Aggiungi e Modifica
        aggiungi = new JButton("Aggiungi");
        modifica = new JButton("Modifica");

        //Giorno, Settimana, Mese, Anno da visualizzare
        labelVisualizza = new JLabel("Visualizza:");
        String[] boxOptions = {"Giorno", "Settimana", "Mese", "Anno", "Altro"};
        boxVisualizza = new JComboBox<>(boxOptions);

        // Imposto label e field Inizio e Fine 
        labelInizio = new JLabel("Inizio:");
        fieldInizio = new JTextField(10);
        labelFine = new JLabel("Fine:");
        fieldFine = new JTextField(10);
        //Defualt sono invisibili, diventano visibili quando l'utente vuole
        //visualizzare i dati in un range di tempo arbitrario
        labelInizio.setVisible(false);
        fieldInizio.setVisible(false);
        labelFine.setVisible(false);
        fieldFine.setVisible(false);

        // Imposto field Visualizza
        fieldVisualizza = new JTextField(20);
        fieldVisualizza.setVisible(true);

        // Imposto bottoni Visualizza e Indietro
        visualizza = new JButton("visualizza");
        indietro = new JButton("Indietro");

        // Imposto label, field e bottone Ricerca
        labelRicerca = new JLabel("Ricerca:");
        fieldRicerca = new JTextField(25);
        ricerca = new JButton("Ricerca");

        /*
         * Gestione bottone Aggiungi:
         * Viene creata una classe anonima ActionListener che implementa il metodo actionPerformed,
         * che permette di gestire cosa accade quando viene premuto il bottone
         */
        aggiungi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String data = datePicker.getJFormattedTextField().getText();
                double ammontare = Integer.parseInt(fieldAmmontare.getText());
                String descrizione = fieldDescrizione.getText();

                // Creo il FormEvent con i dati della voce appena inseriti
                AddEvent formEvent = new AddEvent(this, data, ammontare, descrizione);

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
         * Gestione bottone Modifica
         */
        modifica.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String data = datePicker.getJFormattedTextField().getText();
                double ammontare = Integer.parseInt(fieldAmmontare.getText());
                String descrizione = fieldDescrizione.getText();

                int rowToChange = tablePanel.getTable().getSelectedRow();
                Voce voceToChange = new Voce(data, ammontare, descrizione);

                ModifyEvent modifyEvent = new ModifyEvent(this, voceToChange, rowToChange);
                
                /*
                 * Se il formListener é stato settato nel Frame,
                 * gli passo il formEvent appena creato
                 */
                if(modifyListener != null){
                    modifyListener.modifyEventListener(modifyEvent);
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
                //chiamo il metodo che cerca il testo
                index = tablePanel.searchText(textToSearch, index);
                /*
                 * Se index = -1 stampo errore perchè significa che non ho trovato il testo,
                 */
                if (index == -1){
                    JOptionPane.showMessageDialog(FormPanel.this, "Testo non trovato", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /*
         * Set label e field per visualizzare i dati nella Tabella
         */
        boxVisualizza.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String boxChoose = boxVisualizza.getSelectedItem().toString();

                /*
                 * Se l'utente seleziona Altro devo mostrare
                 * i label e i field di Inizio e Fine per inserire una data arbitraria
                 * altrimenti mostro solamente i label e field Visualizza
                 */
                if (boxChoose.equals("Altro")){
                    fieldVisualizza.setVisible(false);
                    labelInizio.setVisible(true);
                    fieldInizio.setVisible(true);
                    labelFine.setVisible(true);
                    fieldFine.setVisible(true);
                }
                else{
                    fieldVisualizza.setVisible(true);
                    labelInizio.setVisible(false);
                    fieldInizio.setVisible(false);
                    labelFine.setVisible(false);
                    fieldFine.setVisible(false);
                }
            }
        });

        /*
         * Gestione bottone Visualizza
         */
        visualizza.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String boxChoose = boxVisualizza.getSelectedItem().toString();
                Date inizio = null;
                Date fine = null;

                switch(boxChoose){
                    /*
                     * Prendo la data inserita e la setto come
                     * inizio e fine range 
                     */
                    case "Giorno": {
                        String day = fieldVisualizza.getText();
                        try {
                            inizio = new SimpleDateFormat("dd/MM/yyyy").parse(day);
                            fine = new SimpleDateFormat("dd/MM/yyyy").parse(day);

                        } catch (ParseException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(FormPanel.this, "Formato data errato\n Scrivere data nel formato dd/MM/yyyy", 
                            "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }
                    
                    /*
                     * Prendo la data inserita e la setto come
                     * inizio range e poi aggiungo 6 giorni e setto
                     * la data di fine range
                     */
                    case "Settimana": {
                        String week = fieldVisualizza.getText();
                        try {
                            inizio = new SimpleDateFormat("dd/MM/yyyy").parse(week);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(inizio);
                            calendar.add(Calendar.DATE, 6);
                            fine = calendar.getTime();

                        } catch (ParseException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(FormPanel.this, "Formato data errato\n Scrivere data nel formato dd/MM/yyyy",
                             "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    } 

                    /*
                     * Nel field visualizza viene inserito mese e anno (MM/yyyy),
                     * vado a calcolare il primo e ultimo giorno del mese e 
                     * li setto come inizio e fine range
                     */
                    case "Mese": {
                        String date = fieldVisualizza.getText();
                        Calendar calendar = Calendar.getInstance();

                        try {
                            int month = Integer.parseInt(date.substring(0, 2));
                            int year = Integer.parseInt(date.substring(3));
                            // calendar conta i mesi da 0 a 11, quindi bisogna sottrarre 1
                            calendar.set(Calendar.MONTH, month - 1); 
                            calendar.set(Calendar.YEAR, year);
                            // impostare il primo giorno del mese
                            calendar.set(Calendar.DAY_OF_MONTH, 1);
                            // ottenere la data di inizio nel tipo Date
                            inizio = calendar.getTime(); 

                            // impostare l'ultimo giorno del mese
                            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                            // ottenere la data di fine nel tipo Date
                            fine = calendar.getTime(); 

                        } catch (NumberFormatException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(FormPanel.this, "Formato data errato\n Scrivere mese e anno nel formato MM/yyyy", 
                            "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }

                    /*
                     * Prendo l'anno inserito,
                     * setto il primo giorno e mese con 1 gennaio e setto inizio range
                     * setto il primo giorno e mese con 31 dicembre e setto fine range
                     */
                    case "Anno": {
                        int year = 0;
                        // Try Catch per controllare che l'anno sia giusto
                        try{
                            year = Integer.parseInt(fieldVisualizza.getText());
                        }catch (NumberFormatException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(FormPanel.this, "Anno errato\n Scrivere anno nel formato yyyy", 
                            "Errore", JOptionPane.ERROR_MESSAGE);
                        }

                        Calendar cal = Calendar.getInstance();
                        // set anno con quello inserito dall'utente
                        cal.set(Calendar.YEAR, year);
                        // set mese con Gennaio
                        cal.set(Calendar.MONTH, Calendar.JANUARY);
                        // set giorno 1
                        cal.set(Calendar.DAY_OF_MONTH, 1);
                        // ottenere la data di inizio nel tipo Date
                        inizio = cal.getTime();
                        
                        // set mese con Dicembre
                        cal.set(Calendar.MONTH, Calendar.DECEMBER);
                        // set giorno 31
                        cal.set(Calendar.DAY_OF_MONTH, 31);
                        // ottenere la data di fine nel tipo Date
                        fine = cal.getTime();
                        break;
                    }

                    /*
                     * Setto inizio e fine range con le date inserite
                     * nei rispettivi field
                     */
                    case "Altro": {
                        String inizioStr = fieldInizio.getText();
                        String fineStr = fieldFine.getText();
                        try {
                            inizio = new SimpleDateFormat("dd/MM/yyyy").parse(inizioStr);
                            fine = new SimpleDateFormat("dd/MM/yyyy").parse(fineStr);

                        } catch (ParseException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(FormPanel.this, "Formato data errato\n Scrivere data nel formato dd/MM/yyyy", 
                            "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }
                }

                ArrayList<Voce> newVoci = new ArrayList<Voce>();
                //Creo il nuovo array di appoggio con le voci comprese nelle date di inizio e fine
                for (Voce v : voci){
                    try {
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(v.getData());
                        if (date.compareTo(inizio) >= 0 && date.compareTo(fine) <= 0){
                            newVoci.add(v);
                        }
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }
                
                // Aggiorno la tabella coi nuovi dati
                tablePanel.setData(newVoci);
                tablePanel.aggiorna();
            }
        });

        /*
         * Gestione bottone Indietro
         */
        indietro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Torno alla tabella coi dati iniziali
                tablePanel.setData(voci);
                tablePanel.aggiorna();
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

        //gbc bottone Aggiungi
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 0, 0, 130);
        add(aggiungi, gbc);

        //gbc bottone Modifica
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(modifica, gbc);

        //gbc label Visualizza
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(labelVisualizza, gbc);
        //gbc ComboBox Visualizza
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(boxVisualizza, gbc);
        //gbc field Visualizza
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 110, 0, 0);
        add(fieldVisualizza, gbc);

        //gbc etichetta Inizio
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 0, 0, 0);
        add(labelInizio, gbc);
        //gbc field Inizio
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 40, 0, 0);
        add(fieldInizio, gbc);

        //gbc etichetta Fine
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(8, 90, 0, 0);
        add(labelFine, gbc);
        //gbc field Fine
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 125, 0, 0);
        add(fieldFine, gbc);

        //gbc bottone Visualizza
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.3;
        gbc.weighty = 0.3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 0, 0, 130);
        add(visualizza, gbc);

        //gbc bottone Indietro
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 0.3;
        gbc.weighty = 0.3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(indietro, gbc);

        //gbc etichetta Ricerca
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 25, 0, 0);
        add(labelRicerca, gbc);
        //gbc field Ricerca
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 100);
        add(fieldRicerca, gbc);

        //gbc bottone Ricerca
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 150, 0, 0);
        add(ricerca, gbc);
    }

    
    /** 
     * @param formListener
     * 
     * Metodo per impostare il FormListener
     */
    public void setFormListener(AddListener formListener){
        this.formListener = formListener;
    }

    /** 
     * @param formListener
     * 
     * Metodo per impostare il ModifyListener
     */
    public void setModifyListener(ModifyListener modifyListener){
        this.modifyListener = modifyListener;
    }
}
