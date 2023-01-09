package view.File;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.table.TableModel;

import model.Voce;

public class CSVExporter extends TextExporter{
    public CSVExporter(){

    }
    
    @Override
    public void export(TableModel tableModel, List<Voce> voci, File fileName) throws IOException{
        FileWriter csvWriter;
        try {
            csvWriter = new FileWriter(fileName);

            // scrive l'intestazione del file CSV
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                csvWriter.append(tableModel.getColumnName(i));
                csvWriter.append(",");
            }
            csvWriter.append("\n");

            // scrive i dati della tabella
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    csvWriter.append(tableModel.getValueAt(i, j).toString());
                    csvWriter.append(",");
                }
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}