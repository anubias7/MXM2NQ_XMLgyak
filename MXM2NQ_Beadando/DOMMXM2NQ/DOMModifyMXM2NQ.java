import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyMXM2NQ {
    public static void main(String arg[]) throws IOException, ParserConfigurationException, SAXException,
            TransformerConfigurationException, TransformerException, XPathExpressionException {
        // Input out fileok
        File inputXMLFile = new File("../XMLMXM2NQ.xml");
        File outputXMLFile = new File("../XMLMXM2NQ.out.xml");
        // dbuilder létrehozása
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        // dokumentum beolvasása
        Document inputDoc = dBuilder.parse(inputXMLFile);
        inputDoc.getDocumentElement().normalize();

        // Módosítások végrehajtása
        // Végzettség hozzáadása az egyik dolgozóhoz
        addDolgozoVegzettseg("1", "Nyelvvizsga", inputDoc);
        // Kölcsönző házszámának a módosítása
        changeKolcsonzoHazszam("3", "666", inputDoc);

        // Write to file and console
        write(inputDoc, outputXMLFile);
    }

    private static void write(Document doc, File outputFile)
            throws TransformerException, UnsupportedEncodingException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();
        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amunt", "2");

        DOMSource source = new DOMSource(doc);

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(outputFile);

        transf.transform(source, console);
        transf.transform(source, file);
    }

    private static void addDolgozoVegzettseg(String dolgozoId, String vegzettseg, Document doc)
            throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//dolgozo[@did='" + dolgozoId + "']";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);

            Node vegzettsegNode = doc.createElement("vegzettseg");
            vegzettsegNode.setTextContent(vegzettseg);
            n.appendChild(vegzettsegNode);
        }
    }

    private static void changeKolcsonzoHazszam(String kolcsonzoId, String hazszam, Document doc)
            throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//kolcsonzo[@kkod='" + kolcsonzoId + "']/cim/hazszam";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);

            n.setTextContent(hazszam);
        }
    }
}