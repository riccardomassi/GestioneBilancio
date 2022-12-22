package view;

import java.util.EventObject;

public class FormEvent extends EventObject{

    private String data;
    private String ammontare;
    private String descrzione;

    public FormEvent(Object source) {
        super(source);
    } 
    public FormEvent(Object source, String data, String ammontare, String descrzione) {
        super(source);
        this.data = data;
        this.ammontare = ammontare;
        this.descrzione = descrzione;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAmmontare() {
        return this.ammontare;
    }

    public void setAmmontare(String ammontare) {
        this.ammontare = ammontare;
    }

    public String getDescrizione() {
        return this.descrzione;
    }

    public void setDescrzione(String descrzione) {
        this.descrzione = descrzione;
    }  
}
