package com.nolevelcap.tools.box2d;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

public class VerticeLoader {
	
	Vector2[] verticesA;
	
	public Vector2[] getVertexInfo(FileHandle info) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(info.read());
			Gdx.app.log("XML", "File Loaded:" + info.file().getName());
			NodeList nList = doc.getElementsByTagName("fixture");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				
				
				
				Node nNode = nList.item(temp);
				
				//Gdx.app.log("XML", "Current Element :" + nNode.getTextContent());
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					NodeList vertices = eElement.getElementsByTagName("vertice");
					verticesA = new Vector2[vertices.getLength()];
					for (int verti = 0; verti < vertices.getLength(); verti++) {
						Element vert = (Element) vertices.item(verti);
						
						Gdx.app.log("XML", "Vertice NO."+verti);
						Gdx.app.log("XML", "-----------------------------------------------------");
						Gdx.app.log("XML", "X:" + vert.getElementsByTagName("x").item(0).getTextContent());
						Gdx.app.log("XML", "Y:" + vert.getElementsByTagName("y").item(0).getTextContent());
						Gdx.app.log("XML", "-----------------------------------------------------");
						verticesA[verti] = new Vector2(Integer.parseInt(vert.getElementsByTagName("x").item(0).getTextContent()),Integer.parseInt(vert.getElementsByTagName("y").item(0).getTextContent()));
					}
				}
			}
			return verticesA;
		}catch (Exception e) {
			Gdx.app.error("GeneralException", e.getMessage());
		}
		return null;
	}
}
