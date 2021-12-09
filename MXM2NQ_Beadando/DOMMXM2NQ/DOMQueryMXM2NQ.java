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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryMXM2NQ {

    public static void main(String arg[])
            throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {

        //input file megnyitása
        File inputFile = new File("..//XMLMXM2NQ.xml");
        
        //dbuilder létrehozása
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        //dokumentum beolvasása
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        System.out.println("Miskolci kölcsönzők nevei");
        listKolcsonzokByCity("Miskolc", doc);
        System.out.println("");

        System.out.println("Denkinger Géza könyveinek címei");
        listKonyvCimekBySzerzo("Denkinger Géza", doc);
        System.out.println("");

    }

    private static void listKolcsonzokByCity(String city, Document doc) throws XPathExpressionException {
        // XPath fa létrehozása
        XPath xPath = XPathFactory.newInstance().newXPath();

        String expression = "//kolcsonzo[./cim/varos='" + city + "']/nev";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

        for (int j = 0; j < nodeList.getLength(); j++) {
            Node n = nodeList.item(j);

            if (n.getNodeType() != Node.ELEMENT_NODE)
                continue;

            System.out.println("Kölcsönző neve: " + n.getTextContent());
        }
    }

    private static void listKonyvCimekBySzerzo(String szerzoNev, Document doc) throws XPathExpressionException {
        // XPath fa létrehozása
        XPath xPath = XPathFactory.newInstance().newXPath();

        String expression = "//konyv[@ISBN=//irta[@szerzo=//szerzo[./nev='" +
                szerzoNev + "']/@szid]/@konyv]/cim";

        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        for (int j = 0; j < nodeList.getLength(); j++) {
            Node n = nodeList.item(j);

            if (n.getNodeType() != Node.ELEMENT_NODE)
                continue;

            System.out.println("Könyv címe: " + n.getTextContent());
        }
    }
}