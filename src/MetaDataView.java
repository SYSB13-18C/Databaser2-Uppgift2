import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

/**
 * Created by Filip on 2014-11-07.
 */
public class MetaDataView extends JPanel {
    JPanel centerPanel = new JPanel(new BorderLayout());
    JComboBox tablesMenu = new JComboBox();
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
    JTextArea resultArea = new JTextArea("Metadata");
    String selectedValue;
    JScrollPane centerPane = new JScrollPane(centerPanel);

    public MetaDataView(){
        setLayout(new BorderLayout());
        add(centerPane, BorderLayout.CENTER);
        add(tablesMenu, BorderLayout.NORTH);
        centerPanel.add(resultArea, BorderLayout.CENTER);
        tablesMenu.setModel(model);
        tablesMenu.addItem("Choose Table");
        tablesMenu.addItem("Employee");
        tablesMenu.addItem("Employee Absence");
        tablesMenu.addItem("Employee Portal Setup");
        tablesMenu.addItem("Employee Qualification");
        tablesMenu.addItem("Employee Relative");
        tablesMenu.addItem("Employee Statistics Group");
        tablesMenu.addItem("Employment Contract");
        resultArea.setEditable(false);

        tablesMenu.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    selectedValue = model.getSelectedItem().toString();
                    if(selectedValue.equals("Choose Table")){
                        resultArea.setText("Metadata");
                        //return Modell.getKeys();
                    }
                    else if(selectedValue.equals("Employee")){
                        try {
                            resultArea.setText(Modell.getEmployeeMetaData().toString());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else if(selectedValue.equals("Employee Absence")){
                        try {
                            resultArea.setText(Modell.getEmployeeAbsenceMetaData().toString());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else if(selectedValue.equals("Employee Portal Setup")){
                        try {
                            resultArea.setText(Modell.getEmployeePortalSetupMetaData().toString());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else if(selectedValue.equals("Employee Qualification")){
                        try {
                            resultArea.setText(Modell.getEmployeeQualificationMetaData().toString());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else if(selectedValue.equals("Employee Relative")){
                        try {
                            resultArea.setText(Modell.getEmployeeRelativeMetaData().toString());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else if(selectedValue.equals("Employee Statistics Group")){
                        try {
                            resultArea.setText(Modell.getEmployeeStatisticsGroupMetaData().toString());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else if(selectedValue.equals("Employment Contract")){
                        try {
                            resultArea.setText(Modell.getEmploymentContractMetaData().toString());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
