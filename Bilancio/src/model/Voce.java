package model;

import java.io.Serializable;

/*
 * Classe che gestisce il modello Voce
 */
public class Voce implements Serializable{
    private String data;
    private Double ammontare;
    private String descrizione;
    

    public Voce(String data, double ammontare, String descrizione) {
        this.data = data;
        this.ammontare = ammontare;
        this.descrizione = descrizione;
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
    public double getAmmontare() {
        return this.ammontare;
    }

    
    /** 
     * @param ammontare
     */
    public void setAmmontare(double ammontare) {
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
