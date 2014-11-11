import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Filip on 2014-11-06.
 */
public class View extends JFrame{
    JPanel menuPanel = new JPanel(new GridLayout());
    JButton tables = new JButton("Tables");
    JButton functions = new JButton("Functions");
    JButton metaData = new JButton("Metadata");
    JPanel container = new JPanel(new GridLayout());
    JPanel panel = new TablesView();
    JPanel panel2 = new FunctionsView();
    JPanel panel3 = new MetaDataView();

    public View(){
        super("");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());
        setResizable(true);

        menuPanel.add(tables);
        menuPanel.add(metaData);
        menuPanel.add(functions);
        container.add(new JLabel("Press a Button in the Menu"));
        add(menuPanel, BorderLayout.NORTH);
        add(container, BorderLayout.CENTER);

        tables.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                container.removeAll();
                container.add(panel);
                container.revalidate();
                container.repaint();
            }
        });

        functions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                container.removeAll();
                container.add(panel2);
                container.revalidate();
                container.repaint();
            }
        });

        metaData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent g) {
                container.removeAll();
                container.add(panel3);
                container.revalidate();
                container.repaint();
            }
        });
        setVisible(true);
    }
}
