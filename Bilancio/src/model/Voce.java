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
     * 
     * Metodo che restituisce la data della voce
     */
    public String getData() {
        return this.data;
    }
    
    /** 
     * @param data
     * 
     * Metodo che imposta la data della voce
     */
    public void setData(String data) {
        this.data = data;
    }
    
    /** 
     * @return String
     * 
     * Metodo che restituisce l'ammontare della voce
     */
    public double getAmmontare() {
        return this.ammontare;
    }

    
    /** 
     * @param ammontare
     * 
     * Metodo che imposta l'ammontare della voce
     */
    public void setAmmontare(double ammontare) {
        this.ammontare = ammontare;
    }

    
    /** 
     * @return String
     * 
     * Metodo che restituisce la descrizione della voce
     */
    public String getDescrizione() {
        return this.descrizione;
    }

    
    /** 
     * @param descrizione
     * 
     * Metodo che imposta la descrizione della voce
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
