package model;

import java.util.ArrayList;
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
    
    public void addVoce(Voce voce){
        voci.add(voce);
    }

    public List<Voce> getVoci(){
        return voci;
    }

}
