package view.Form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JFormattedTextField.AbstractFormatter;

/*
 * Classe che serve per creare la stringa da visualizzare
 * dopo aver scelto la data nel calendario
 */
public class DateFormatter extends AbstractFormatter{

    @Override
    public Object stringToValue(String text) throws ParseException {
        return null;
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if(value != null){
            Calendar calendar = (Calendar)value;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String strDate = dateFormat.format(calendar.getTime());

            return strDate;
        }
        return null;
    }
    
}
