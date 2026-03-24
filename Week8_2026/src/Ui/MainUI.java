package Ui;

import Service.SchoolService;
import Model.Student;

import javax.swing.*;
import java.awt.*;

public class MainUI {
	
	private SchoolService service = new SchoolService();
	private JTextArea displayArea;
	
	public void start() {
		JFrame frame = new JFrame("School System");
		frame.setSize(500, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		//zvvn Panel//
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(5, 1, 5, 5));
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JLabel nameLabel = new JLabel("Student Name:");
		JTextField nameField = new JTextField();
		
		JLabel gpaLabel = new JLabel("GPA:");
		JTextField gpaField = new JTextField();
		
		JButton addBtn = new JButton("Add Student");
		
		formPanel.add(nameLabel);
		formPanel.add(nameField);
		formPanel.add(gpaLabel);
		formPanel.add(gpaField);
		formPanel.add(addBtn);
		
		//Baruun Panel//
		
		displayArea = new JTextArea();
		displayArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(displayArea);
		
		//Button Action//
		
		addBtn.addActionListener(e -> {
			String name = nameField.getText();
			String gpaText = gpaField.getText();
			
			if (name.isEmpty() || gpaText.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please fill all fields!");
				return;
			}
			try {
				double gpa = Double.parseDouble(gpaText);
				
				Student s = new Student();
				s.setName(name);
				s.setGpa(gpa);
				
				service.addStudent(s);
				updateDisplay();
				
				nameField.setText("");
				gpaField.setText("");
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Invalid GPA!");
			}
			});
		//Add frame//
	frame.add(formPanel, BorderLayout.WEST);
	frame.add(scrollPane, BorderLayout.CENTER);
	
	frame.setVisible(true);
	}

	private void updateDisplay() {
		displayArea.setText("");
		for (Student s : service.getStudents()) {
			displayArea.append(s.toString() + "\n");
		}
		
	}
			
}
			
		
	

