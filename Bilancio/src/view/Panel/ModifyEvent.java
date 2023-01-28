package view.Panel;

import java.util.EventObject;

import model.Voce;

/**
 * Classe che si occupa di gestire i dati quando avviene l'evento 
 * modifica voce nel Panel
 */
public class ModifyEvent extends EventObject{
    private Voce voceToChange;
    private int rowToChange;

    public ModifyEvent(Object source) {
        super(source);
    } 
    public ModifyEvent(Object source, Voce voceToChange, int rowToChange) {
        super(source);
        this.voceToChange = voceToChange;
        this.rowToChange = rowToChange;
    }

    
    /** 
     * @return Voce
     */
    public Voce getVoceToChange() {
        return this.voceToChange;
    }

    
    /** 
     * @param voceToChange
     */
    public void setVoceToChange(Voce voceToChange) {
        this.voceToChange = voceToChange;
    }

    
    /** 
     * @return int
     */
    public int getRowToChange() {
        return this.rowToChange;
    }

    
    /** 
     * @param rowToChange
     */
    public void setRowToChange(int rowToChange) {
        this.rowToChange = rowToChange;
    }

}

