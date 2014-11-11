import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

/**
 * Created by Filip on 2014-11-06.
 */
public class FunctionsView extends JPanel {
    JPanel centerPanel = new JPanel(new BorderLayout());
    JComboBox tablesMenu = new JComboBox();
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
    JTextArea resultArea = new JTextArea("Functions");
    String selectedValue;
    JScrollPane centerPane = new JScrollPane(centerPanel);
    JTable resultTable = new JTable();

    public FunctionsView(){
        setLayout(new BorderLayout());
        add(centerPane, BorderLayout.CENTER);
        add(tablesMenu, BorderLayout.NORTH);
        centerPanel.add(resultArea, BorderLayout.CENTER);
        tablesMenu.setModel(model);
        tablesMenu.addItem("Choose Function");
        tablesMenu.addItem("Show Columns");
        tablesMenu.addItem("Show Constraints");
        tablesMenu.addItem("Show Indexes");
        tablesMenu.addItem("Show Keys");
        tablesMenu.addItem("Show Tables");
        tablesMenu.addItem("Show The Table With The Most Rows");
        resultArea.setEditable(false);
        centerPanel.add(resultTable, BorderLayout.SOUTH);

        tablesMenu.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    selectedValue = model.getSelectedItem().toString();
                    if(selectedValue.equals("Choose Function")){
                        resultArea.setText("Functions");
                    }
                    else if(selectedValue.equals("Show Columns")){
                        try {
                            resultArea.setText(Modell.getColumns().toString());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else if(selectedValue.equals("Show Constraints")){
                        try {
                            resultArea.setText(Modell.getConstraints().toString());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else if(selectedValue.equals("Show Indexes")){
                        try {
                            resultArea.setText(Modell.getIndexes().toString());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else if(selectedValue.equals("Show Keys")){
                        try {
                            resultArea.setText(Modell.getKeys().toString());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else if(selectedValue.equals("Show Tables")){
                        try {
                            resultArea.setText(Modell.getTables().toString());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else if(selectedValue.equals("Show The Table With The Most Rows")){
                        try {
                            resultArea.setText(Modell.getMostRows().toString());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
