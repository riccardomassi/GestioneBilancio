package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Database;
import model.Voce;

/*
 * Classe che fa da intermezzo tra la parte View e la parte Database
 */
public class Controller {

    Database database = new Database();

    
    /** 
     * @param data data da inserire nella voce
     * @param ammontare valore ammontare da inserire nella voce
     * @param descrizione descrizione da inserire nella voce
     * 
     * Metodo che aggiunge una Voce al Database
     */
    public void addVoce(String data, double ammontare, String descrizione){
        //creazione voce
        Voce voce = new Voce(data, ammontare, descrizione);
        // richiamo il metodo che inserisce una voce nel database
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

    
    /** 
     * @return String
     */
    public String getTotale(){
        return database.getTotale();
    }

    
    /** 
     * @param file
     * @throws IOException
     */
    public void salvaSuFile(File file) throws IOException{
        database.salvaSuFile(file);
    }

    
    /** 
     * @param file
     * @throws IOException
     */
    public void caricaDaFile(File file) throws IOException{
        database.caricaDaFile(file);
    }
}
