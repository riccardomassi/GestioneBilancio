package model;

public class Voce {
    private static int contatore = 0;
    private int id;
    private String data;
    private String ammontare;
    private String descrizione;
    

    public Voce(String data, String ammontare, String descrizione) {
        id = contatore;
        this.data = data;
        this.ammontare = ammontare;
        this.descrizione = descrizione;
        contatore++;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

}
