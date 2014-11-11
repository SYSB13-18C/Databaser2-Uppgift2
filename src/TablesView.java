import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

/**
 * Created by Filip on 2014-11-06.
 */
public class TablesView extends JPanel{
    JPanel centerPanel = new JPanel(new BorderLayout());
    JComboBox tablesMenu = new JComboBox();
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
    JTextArea resultArea = new JTextArea("Tables Content");
    JScrollPane centerPane = new JScrollPane(centerPanel);
    String selectedValue;

    public TablesView(){
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
                    resultArea.setText("Tables Content");
                    //return Modell.getKeys();
                }
                else if(selectedValue.equals("Employee")){
                    try {
                        resultArea.setText(Modell.getEmployeeInfo().toString());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                else if(selectedValue.equals("Employee Absence")){
                    try {
                        resultArea.setText(Modell.getEmployeeAbsenceInfo().toString());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                else if(selectedValue.equals("Employee Portal Setup")){
                    try {
                        resultArea.setText(Modell.getEmployeePortalSetupInfo().toString());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                else if(selectedValue.equals("Employee Qualification")){
                    try {
                        resultArea.setText(Modell.getEmployeeQualificationInfo().toString());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                else if(selectedValue.equals("Employee Relative")){
                    try {
                        resultArea.setText(Modell.getEmployeeRelativeInfo().toString());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                else if(selectedValue.equals("Employee Statistics Group")){
                    try {
                        resultArea.setText(Modell.getEmployeeStatisticsGroupInfo().toString());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                else if(selectedValue.equals("Employment Contract")){
                    try {
                        resultArea.setText(Modell.getEmploymentContractInfo().toString());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                }
            }
        });
    }
}
