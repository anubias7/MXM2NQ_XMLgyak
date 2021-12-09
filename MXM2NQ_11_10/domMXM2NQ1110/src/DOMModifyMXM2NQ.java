import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
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
    public static void main(String arg[]) throws SAXException, IOException, ParserConfigurationException,
            XPathExpressionException, TransformerException {

        File inputFile = new File("../nyelvekMXM2NQ_1110.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        // Build XPath tree
        XPath xPath = XPathFactory.newInstance().newXPath();

        String expression_oracle_01 = "//nyelv_neve[text()='Oracle 01']";
        String expression_oracle_02 = "//nyelv_neve[text()='Oracle 02']";
        String expression_kliens_nyelvek = "//kliens_nyelvek";

        NodeList nodeList_oracle_01 = (NodeList) xPath.compile(expression_oracle_01).evaluate(doc,
                XPathConstants.NODESET);
        NodeList nodeList_oracle_02 = (NodeList) xPath.compile(expression_oracle_02).evaluate(doc,
                XPathConstants.NODESET);
        NodeList nodeList_kliens_nyelvek = (NodeList) xPath.compile(expression_kliens_nyelvek).evaluate(doc,
                XPathConstants.NODESET);

        changeTextValue(nodeList_oracle_01, "OOP");
        changeTextValue(nodeList_oracle_02, "Document-oriented DB");

        // Remove kliens nyelvek
        for (int i = 0; i < nodeList_kliens_nyelvek.getLength(); i++) {
            Node node = nodeList_kliens_nyelvek.item(i);
            node.getParentNode().removeChild(node);
        }

        System.out.println("----------------Módosított fájl----------------");
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        StreamResult console = new StreamResult(System.out);
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, console);
    }

    private static void changeTextValue(NodeList nodeList, String textValue) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            node.setTextContent(textValue);
        }
    }
}
