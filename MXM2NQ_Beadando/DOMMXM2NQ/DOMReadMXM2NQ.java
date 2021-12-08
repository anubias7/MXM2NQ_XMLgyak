import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMReadMXM2NQ {
  public static void main(String arg[])
      throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
    // Get file
    File xmlFile = new File("../XMLMXM2NQ.xml");

    // Get DocumentBuilder
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = factory.newDocumentBuilder();

    // Parse docuement and normalize
    Document doc = dBuilder.parse(xmlFile);
    doc.getDocumentElement().normalize();

    // gyökér elem
    System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
    System.out.println("\n");

    XPath xPath = XPathFactory.newInstance().newXPath();

    // könyv beolvasasa
    NodeList nList = doc.getElementsByTagName("konyv");

    for (int i = 0; i < nList.getLength(); i++) {
      Node nNode = nList.item(i);

      if (nNode.getNodeType() != Node.ELEMENT_NODE)
        continue;

      Element eElement = (Element) nNode;
      String ISBN = eElement.getAttribute("ISBN");
      // Könyv adatai
      System.out.println("konyv Id: " + ISBN);

      System.out.println("Könyv címe: " +
          eElement.getElementsByTagName("cim").item(0).getTextContent());
      System.out.println("Könyv kiadója: " +
          eElement.getElementsByTagName("kiado").item(0).getTextContent());
      System.out.println("Könyv megjelenési dátuma: " +
          eElement.getElementsByTagName("megjelenes_datuma").item(0).getTextContent());

      String szerzo_expression = "//szerzo[@szid=//irta[@konyv=" + ISBN + "]/@szerzo]/nev";
      NodeList szerzo_nevek = (NodeList) xPath.compile(szerzo_expression).evaluate(doc, XPathConstants.NODESET);

      for (int j = 0; j < szerzo_nevek.getLength(); j++) {
        Node n = szerzo_nevek.item(j);

        if (n.getNodeType() != Node.ELEMENT_NODE)
          continue;

        System.out.println("szerzo: " + n.getTextContent());
      }

      System.out.println();

    }
    System.out.println("________________________");

    NodeList nList2 = doc.getElementsByTagName("kolcsonzo");
    for (int i = 0; i < nList2.getLength(); i++) {
      Node nNode = nList2.item(i);

      if (nNode.getNodeType() != Node.ELEMENT_NODE)
        continue;

      Element eElement = (Element) nNode;
      // Kölcsönző adatai
      System.out.println("kolcs Id: " + eElement.getAttribute("kkod"));
      System.out.println("Kölcsönző neve: " +
          eElement.getElementsByTagName("nev").item(0).getTextContent());
      System.out.println("Kölcsönző könyveinek száma: " +
          eElement.getElementsByTagName("kolcsonzesek_szama").item(0).getTextContent());
      System.out.println("Kölcsönző cime: " +
          eElement.getElementsByTagName("cim").item(0).getTextContent());

      System.out.println();
    }

    System.out.println("________________________");

  }
}