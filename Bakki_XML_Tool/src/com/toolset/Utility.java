package com.toolset;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Utility {
	public static boolean isXMLFile(String fileName) {
		String[] tokens = fileName.split("[\\\\|/]");
		return tokens[tokens.length - 1].contains(".xml");
	}

	public static String getFilePath(String fileName) {
		Path p = Paths.get(fileName);
		return p.getParent().toString();
	}
}
