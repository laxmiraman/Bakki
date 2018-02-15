package com.toolset;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class FileChooser {

	public static String selectFile() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Choose a file: ");
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			return selectedFile.getAbsolutePath();
		}
		return "";
	}

	public static String selectFileorDir() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Choose a directory or a file: ");
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			// if (jfc.getSelectedFile().isDirectory()) {
			return jfc.getSelectedFile().getAbsolutePath();
		} else if (returnValue == JFileChooser.CANCEL_OPTION) {
			return "NOTHING";
		} else if (returnValue == JFileChooser.ABORT) {
			return "NOTHING";
		}
		return "";
	}

}