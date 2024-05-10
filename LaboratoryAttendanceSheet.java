import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class LaboratoryAttendanceSheet extends JFrame {
    private JTextField nameField, sectionField, pcNumberField;
    private DefaultTableModel tableModel;
    private JTable table;

    public LaboratoryAttendanceSheet() {
        setTitle("Laboratory Attendance Sheet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        // Create labels and text fields
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel sectionLabel = new JLabel("Section:");
        sectionField = new JTextField(20);

        JLabel pcNumberLabel = new JLabel("PC Number:");
        pcNumberField = new JTextField(20);

        // Create buttons
        JButton saveButton = new JButton("Save");
        JButton viewTableButton = new JButton("View Table");

        // Add components to the content pane
        Container container = getContentPane();
        container.setLayout(new GridLayout(4, 2));

        container.add(nameLabel);
        container.add(nameField);
        container.add(sectionLabel);
        container.add(sectionField);
        container.add(pcNumberLabel);
        container.add(pcNumberField);
        container.add(saveButton);
        container.add(viewTableButton);

        // Initialize table model
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Section");
        tableModel.addColumn("PC Number");

        // Create table
        table = new JTable(tableModel);

        // Save button action listener
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        // View table button action listener
        viewTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTable();
            }
        });
    }

    private void saveData() {
        // Get data from text fields
        String name = nameField.getText();
        String section = sectionField.getText();
        String pcNumber = pcNumberField.getText();

        // Perform saving data logic here (e.g., save to a database)

        // Add data to table model
        Vector<String> rowData = new Vector<>();
        rowData.add(name);
        rowData.add(section);
        rowData.add(pcNumber);
        tableModel.addRow(rowData);

        // Ask user if they want to save the data to a text file
        int response = JOptionPane.showConfirmDialog(this, "Do you want to save the data to a text file?", "Save Data", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            // Create a file chooser to select the file location
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a file location");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int returnValue = fileChooser.showSaveDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                java.io.File file = fileChooser.getSelectedFile();

                // Save the data to the file
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("Name,Section,PC Number\n");
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        writer.write(tableModel.getValueAt(i, 0) + "," + tableModel.getValueAt(i, 1) + "," + tableModel.getValueAt(i, 2) + "\n");
                    }
                    writer.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        // Show confirmation message
        JOptionPane.showMessageDialog(this, "Data saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Clear text fields
        nameField.setText("");
        sectionField.setText("");
        pcNumberField.setText("");
    }

    private void viewTable() {
        // Create a new JFrame to display the table
        JFrame tableFrame = new JFrame("Attendance Table");
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableFrame.setSize(400, 300);

        // Add existing table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add scroll pane to table frame
        tableFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Add a button to open the text file
        JButton openFileButton = new JButton("Open Text File");
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the text file
                try {
                    File file = new File("attendance.txt");
                    if (file.exists()) {
                        Desktop.getDesktop().open(file);
                    } else {
                        JOptionPane.showMessageDialog(tableFrame, "File not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(tableFrame, "Error opening file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add the open file button to the table frame
        tableFrame.getContentPane().add(openFileButton, BorderLayout.SOUTH);

        // Make the table frame visible
        tableFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LaboratoryAttendanceSheet().setVisible(true);
            }
        });
    }
}