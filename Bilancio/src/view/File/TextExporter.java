package view.File;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.table.TableModel;

import model.Voce;

public class TextExporter {
    public TextExporter(){

    }

    public void export(TableModel tableModel, List<Voce> voci, File fileName) throws IOException{
        FileWriter txtWriter;
        try {
            txtWriter = new FileWriter(fileName);

            // scrive l'intestazione del file CSV
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                txtWriter.append(tableModel.getColumnName(i));
                txtWriter.append(" ");
            }
            txtWriter.append("\n");

            // scrive i dati della tabella
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    txtWriter.append(tableModel.getValueAt(i, j).toString());
                    txtWriter.append(" ");
                }
                txtWriter.append("\n");
            }

            txtWriter.flush();
            txtWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
