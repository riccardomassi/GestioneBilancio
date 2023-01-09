package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Database;
import model.Voce;

/*
 * Controller che si occupa di aggiungere le voci al Databse
 * (fa da intermezzo tra la parte View e il Database)
 */
public class Controller {

    Database database = new Database();

    
    /** 
     * @param data
     * @param ammontare
     * @param descrizione
     * 
     * Metodo che aggiunge una Voce al Database
     */
    public void addVoce(String data, double ammontare, String descrizione){
        //creazione voce
        Voce voce = new Voce(data, ammontare, descrizione);
        database.addVoce(voce);
    }

    /** 
     * @param int
     * 
     * Metodo che elimina una Voce dal Database
     */
    public void delVoce(int rowIndexDelete){
        database.delVoce(rowIndexDelete);
    }
    
    /** 
     * @return List<Voce>
     * 
     * Metodo che ritorna la lista di tutte le Voci
     */
    public List<Voce> getVoci(){
        return database.getVoci();
    }

    public String getTotale(){
        return database.getTotale();
    }

    public void salvaSuFile(File file) throws IOException{
        database.salvaSuFile(file);
    }

    public void caricaDaFile(File file) throws IOException{
        database.caricaDaFile(file);
    }
}
