package view;

import java.util.EventObject;

public class TableEvent extends EventObject {
    private int rowToDelete;

    public TableEvent(Object source) {
        super(source);
    }

    public TableEvent(Object source, int rowToDelete) {
        super(source);
        this.rowToDelete = rowToDelete;
    }


    public int getRowToDelete(){
        return this.rowToDelete;
    }

    public void setRowToDelete(int rowToDelete){
        this.rowToDelete = rowToDelete;
    }
    
    
}
