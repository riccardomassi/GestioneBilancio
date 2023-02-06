package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Database che si occupa di tenere memoria di tutte le voci inserite
 * nel Bilancio, attraverso una ArrayList
 */
public class Database {

    private ArrayList<Voce> voci;

    public Database(){
        voci = new ArrayList<>();
    }
    
    /** 
     * @param Voce
     * 
     * Metodo che aggiunge una Voce al Database
     */
    public void addVoce(Voce voce){
        voci.add(voce);
    }

    /** 
     * @param int
     * 
     * Metodo che elimina una Voce dal Database
     */
    public void delVoce(int rowIndex){
        voci.remove(rowIndex);
    }

    /** 
     * @return List<Voce>
     * 
     * Metodo che ritorna la lista delle Voci inserite nel Database
     */
    public List<Voce> getVoci(){
        return voci;
    }

    
    /** 
     * @param index indice riga da modificare
     * @param voceToChange voce da modificare
     * 
     * Metodo che modifica una voce
     */
    public void modifyVoce(int index, Voce voceToChange){
        voci.set(index, voceToChange);
    }
    
    /** 
     * @return String
     *
     * Metodo che ritorna la somma totale delle voci del bilancio
     */
    public String getTotale(){
        double totale = 0;
        for(int i = 0; i < voci.size(); i++){
            totale += voci.get(i).getAmmontare();
        }

        //Arrotondo a due cifre decimali
        totale = Math.round(totale*100.0)/100.0;
        
        return "Totale: "+totale;
    }

    /** 
     * @param file nome file su cui salvare
     * @throws IOException
     */
    public void salvaSuFile(File file) throws IOException{

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        //converto l'arrayList in un array normale
        Voce[] arrayVoce = voci.toArray(new Voce[voci.size()]); 

        //scrivo le voci nel file
        oos.writeObject(arrayVoce);

        oos.close();
        fos.close();
        
    }

    
    /** 
     * @param file nome file dal quale caricare
     * @throws IOException
     */
    public void caricaDaFile(File file) throws IOException{

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            //prendo le voci dal file
            Voce[] vociCaricate = (Voce[])ois.readObject();

            //svuoto l'arraylist voci
            voci.clear();
            //inserisco le voci lette dal File
            voci.addAll(Arrays.asList(vociCaricate));
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ois.close();
        fis.close();
    }

}
