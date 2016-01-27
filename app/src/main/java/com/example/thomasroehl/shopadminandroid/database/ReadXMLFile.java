package com.example.thomasroehl.shopadminandroid.database;

/**
 * Created by katia on 17.01.16.
 */
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.HashMap;

public class ReadXMLFile {
///home/katia/StudioProjects/ShopAdminAndroid/app/src/main/java/com/example/thomasroehl/shopadminandroid/database/ReadXMLFile.java
///home/katia/StudioProjects/ShopAdminAndroid/app/src/main/res/databasexml/shop_category_xml_xml

    public HashMap<String, String> readXML() {
        HashMap<String, String> entryDicionary = new HashMap<String, String>();
        entryDicionary.put("rewe", "food");
        entryDicionary.put("aldi", "food");
        entryDicionary.put("penny", "food");
        entryDicionary.put("real", "food");
        entryDicionary.put("hit", "food");
        entryDicionary.put("globus", "food");
        entryDicionary.put("lidl", "food");
        entryDicionary.put("edeka", "food");
        entryDicionary.put("netto", "food");
        entryDicionary.put("dm", "cosmetics");
        entryDicionary.put("douglas", "cosmetics");
        entryDicionary.put("rossmann", "cosmetics");
        entryDicionary.put("primark", "clothes");
        return entryDicionary;
    }



      /* try {
///home/katia/StudioProjects/ShopAdminAndroid/app/src/main/java/com/example/thomasroehl/shopadminandroid/database
            //File fXmlFile = new File("app/src/main/res/databasexml/shop_category_xml_xml");
            //                        /home/katia/StudioProjects/ShopAdminAndroid/app/src/main/java/com/example/thomasroehl/shopadminandroid/database
            File fXmlFile = new File("@databasexml/shop_category.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("entry");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("shopname: " + eElement.getAttribute("shopname"));
                    System.out.println("category: " + eElement.getAttribute("category"));
                    entryDicionary.put(eElement.getAttribute("shopname"), eElement.getAttribute("category"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return entryDicionary;
    }*/

}


