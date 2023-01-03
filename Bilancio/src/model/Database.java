package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
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

    /*
     * Metodo che ritorna la somma totale
     * delle voci del bilancio
     */
    public String getTotale(){
        int totale = 0;
        for(int i = 0; i < voci.size(); i++){
            totale += voci.get(i).getAmmontare();
        }

        return "Totale: "+totale;
    }

    public void salvaSuFile(File file) throws IOException{

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        //Converto l'arrayList in un array normale
        Voce[] arrayVoce = voci.toArray(new Voce[voci.size()]); 

        oos.writeObject(arrayVoce);

        oos.close();
        fos.close();
        
    }

    public void caricaDaFile(File file) throws IOException{

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
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
