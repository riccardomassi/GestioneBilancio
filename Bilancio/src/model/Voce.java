package model;

import java.io.Serializable;

/*
 * Classe che gestisce il modello Voce
 */
public class Voce implements Serializable{
    private static int contatore = 0;
    private int id;
    private String data;
    private int ammontare;
    private String descrizione;
    

    public Voce(String data, int ammontare, String descrizione) {
        id = contatore;
        this.data = data;
        this.ammontare = ammontare;
        this.descrizione = descrizione;
        contatore++;
    }
    
    /** 
     * @return int
     */
    public int getId() {
        return this.id;
    }

    
    /** 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /** 
     * @return String
     */
    public String getData() {
        return this.data;
    }

    
    /** 
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    
    /** 
     * @return String
     */
    public int getAmmontare() {
        return this.ammontare;
    }

    
    /** 
     * @param ammontare
     */
    public void setAmmontare(int ammontare) {
        this.ammontare = ammontare;
    }

    
    /** 
     * @return String
     */
    public String getDescrizione() {
        return this.descrizione;
    }

    
    /** 
     * @param descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
