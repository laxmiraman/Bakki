package com.toolset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;

/**
 * Swing UI class to load tools
 * 
 * @author Bakkiyalakshmi Ramanjulu
 *
 */
public class ToolSet extends JFrame {

	JTextField inputFile, outputFile;
	JButton inputBtn, outputBtn, RemoveBtn;
	ImageIcon img = new ImageIcon("strawberry.jpg");

	public ToolSet() {
		setTitle("Bakki-ToolSet (Amazon)");
		setIconImage(img.getImage());
		setSize(350, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		inputFile = new JTextField("", 10);
		outputFile = new JTextField("", 10);
		getContentPane().add(inputFile);
		inputBtn = new JButton("Select - Input");
		inputBtn.setBounds(50, 60, 80, 30);
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				inputFile.setText(FileChooser.selectFile());
			}
		});
		getContentPane().add(inputBtn);
		getContentPane().add(outputFile);
		outputBtn = new JButton("Select Output");
		outputBtn.setBounds(50, 60, 80, 30);
		outputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String fileordir = FileChooser.selectFileorDir();
				if (!fileordir.trim().equals("") && !fileordir.trim().equals("NOTHING")
						&& !Utility.isXMLFile(fileordir)) {
					fileordir = fileordir + "\\out.xml";
				} else if (fileordir.trim().equals("NOTHING")) {
					fileordir = outputFile.getText();
				}
				outputFile.setText(fileordir);
			}
		});
		getContentPane().add(outputBtn);
		RemoveBtn = new JButton(" Remove Duplicates ");
		RemoveBtn.setBounds(50, 60, 80, 30);
		RemoveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (inputFile.getText().trim().length() < 1 || outputFile.getText().trim().length() < 1) {
					JOptionPane.showMessageDialog(new JFrame(), "Oops !! You have not selected Input/Output File !!",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String[] input = new String[2];
					input[0] = inputFile.getText();
					input[1] = outputFile.getText();
					int dups = DuplicateRemover.deDuper(input);
					if (dups > 0) {
						JOptionPane.showMessageDialog(new JFrame(), "A new XML file has been successfully generated !!",
								"Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		getContentPane().add(RemoveBtn);
		// pack();
	}

	public static void main(String[] args) {
		ToolSet ex = new ToolSet();
		ex.setVisible(true);
	}
}