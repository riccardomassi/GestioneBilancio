package view.Form;

import java.util.EventObject;

public class FormEvent extends EventObject{

    private String data;
    private Double ammontare;
    private String descrzione;

    public FormEvent(Object source) {
        super(source);
    } 
    public FormEvent(Object source, String data, double ammontare, String descrzione) {
        super(source);
        this.data = data;
        this.ammontare = ammontare;
        this.descrzione = descrzione;
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
        return this.descrzione;
    }

    
    /** 
     * @param descrizione
     */
    public void setDescrzione(String descrizione) {
        this.descrzione = descrizione;
    }  
}
