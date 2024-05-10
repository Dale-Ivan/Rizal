import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MyWindow extends JFrame implements ActionListener{
	private JToolBar jtbTool;
	private JTextArea jtaText;				
	private JButton jbtOpen, jbtSave, jbtForeground, jbtBackground;	
	private Icon iOpen, iSave, iForeground, iBackground;

	public MyWindow(){
		setVisible(true);
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("My win");
		setLayout(new BorderLayout());

		jtbTool = new JToolBar("System ToolBar");
		jtaText = new JTextArea();


		jbtOpen = new JButton();
		jbtSave = new JButton();			
		jbtForeground = new JButton();
		jbtBackground = new JButton();

		iSave = new ImageIcon("C:/Users/User/Desktop/JAVA/img/save.PNG");
		iOpen = new ImageIcon("C:/Users/User/Desktop/JAVA/img/open.PNG");
		iBackground = new ImageIcon("C:/Users/User/Desktop/JAVA/img/bg.png");
		iForeground = new ImageIcon("C:/Users/User/Desktop/JAVA/img/fg.PNG");	
		
		add(jtbTool,BorderLayout.NORTH);
		add(jtaText,BorderLayout.CENTER);

		jtbTool.add(jbtOpen);
		jtbTool.add(jbtSave);
		jtbTool.add(jbtForeground);
		jtbTool.add(jbtBackground);
		
		jbtOpen.setIcon(iOpen);
		jbtSave.setIcon(iSave);
		jbtForeground.setIcon(iForeground);
		jbtBackground.setIcon(iBackground);

		jbtOpen.setToolTipText("Open");
		jbtSave.setToolTipText("Save");
	        jbtForeground.setToolTipText("Font Color");
		jbtBackground.setToolTipText("Set Background Color");

		jbtOpen.addActionListener(this);
		jbtSave.addActionListener(this);
		jbtForeground.addActionListener(this);
		jbtBackground.addActionListener(this);
	}   


	 public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == jbtSave) {
            JFileChooser saveFileChooser = new JFileChooser();
            int option = saveFileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File fileToSave = saveFileChooser.getSelectedFile();
                try (PrintWriter writer = new PrintWriter(fileToSave)) {
                    writer.print(jtaText.getText());
                    JOptionPane.showMessageDialog(this, "File saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error occurred while saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (source == jbtOpen) {
            JFileChooser openFileChooser = new JFileChooser();
            int option = openFileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File fileToOpen = openFileChooser.getSelectedFile();
                try (BufferedReader reader = new BufferedReader(new FileReader(fileToOpen))) {
                    StringBuilder text = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        text.append(line).append("\n");
                    }
                    jtaText.setText(text.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error occurred while opening the file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (source == jbtForeground) {
            Color color = JColorChooser.showDialog(null, "Select Foreground Color", Color.BLACK);
            jtaText.setForeground(color);
        }
        if (source == jbtBackground) {
            Color color = JColorChooser.showDialog(null, "Select Background Color", Color.PINK);
            jtaText.setBackground(color);
        }
    }

    public static void main(String[] args) {
        MyWindow sbFrame = new MyWindow();
    }
}
