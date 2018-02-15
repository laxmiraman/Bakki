package com.toolset;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Finds a duplicate data in the Inflections tag
 * @author Bakkiyalakshmi Ramanjulu 
 *
 */
public class DuplicateRemover {
	private static int removeDuplicates(String args[]) {
		try {
			int duplicates = 0;
			DocumentBuilderFactory dbFactory;
			DocumentBuilder dBuilder;
			Document original = null;
			try {
				dbFactory = DocumentBuilderFactory.newInstance();
				dBuilder = dbFactory.newDocumentBuilder();
				original = dBuilder.parse(new InputSource(new InputStreamReader(
						new FileInputStream(args[0]))));
			} catch (SAXException | IOException | ParserConfigurationException e) {
				throw new RuntimeException("Exception found in the system", e);
			}

			//original.getDocumentElement().normalize();
			// Entries
			NodeList entries = original.getElementsByTagName("Entry");
			Node entry = null;

			//Entry
			for (int iter = 0; iter < entries.getLength(); iter++) {				
				entry = original.getElementsByTagName("Entry").item(iter);
				// Head
				NodeList entry1child = entry.getChildNodes();
				String[] keyValue = new String[] { "", "" };
				// Word
				for (int i = 0; i < entry1child.getLength(); i++) {
					// Key, Phoenetics, Inflections
					NodeList nodeList = entry1child.item(i).getChildNodes();
					for (int j = 0; j < nodeList.getLength(); j++) {
						Node n1 = nodeList.item(j);
						NodeList l1 = n1.getChildNodes();
						for (int k = 0; k < l1.getLength(); k++) {
							Node n2 = l1.item(k);
							if (n2.getNodeName().equalsIgnoreCase("Key")) {
								keyValue[0] = n2.getTextContent();
								//System.out.println("<Inflection><Key>" + keyValue[0] + "</Inflection></Key>");
							} else if (n2.getNodeName().equalsIgnoreCase("Inflections")) {
								NodeList inflectionsList = n2.getChildNodes();
								for (int l = 0; l < inflectionsList.getLength(); l++) {
									NodeList n3 = inflectionsList.item(l).getChildNodes();
									Node n4 = n3.item(0);
									if (n4 != null) {
										NodeList n5List = n4.getChildNodes();
										keyValue[1] = n5List.item(0).getTextContent();
										if (keyValue[0].equalsIgnoreCase(keyValue[1])) {
											//System.out.println("Found a duplicate data");
											duplicates++;
											n4.getParentNode().removeChild(n4);
										}
									}
								}
							}
						}
					}
				}
			}

			// Creates a new XML file
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			StreamResult xmlOutput = new StreamResult(new File(args[1]));
			transformer.transform(new DOMSource(original), xmlOutput);
			// Uncomment the below code for debugging purpose
			// StreamResult result = new StreamResult(System.out);
			// transformer.transform(new DOMSource(original), result);
			//System.out.println("Number of duplicates found in this file is "+duplicates);
			//System.out.println("A new file '"+args[1]+"' has been created successfully");			
			return duplicates;
		} catch (Exception ex) {
			throw new RuntimeException("Exception found in the system", ex);
		}
	}

	public static int deDuper(String[] args) {
		if(args.length < 2){			
			System.err.println("Error: Input/Output file path/name is missing!!");
			return 0;
		}else{
			return removeDuplicates(args);
		}
	}
}