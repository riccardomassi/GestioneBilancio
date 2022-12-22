package controller;

import java.util.List;

import model.Database;
import model.Voce;

/*
 * Controller che si occupa di aggiungere le voci al Databse
 * (fa da intermezzo tra la parte View e il Database)
 */
public class Controller {

    Database database = new Database();

    /*
     * Metodo che aggiunge una Voce al Database
     */
    public void addVoce(String data, String ammontare, String descrizione){

        //creazione voce
        Voce voce = new Voce(data, ammontare, descrizione);

        //aggiungiamo la Voce al Database
        database.addVoce(voce);
    }

    /*
     * Metodo che ritorna la lista di tutte le Voci
     */
    public List<Voce> getVoci(){
        return database.getVoci();
    }
}