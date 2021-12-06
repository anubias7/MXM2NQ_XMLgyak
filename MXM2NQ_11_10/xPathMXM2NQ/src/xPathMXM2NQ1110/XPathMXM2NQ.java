package xPathMXM2NQ1110;

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

public class XPathMXM2NQ {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        File inputFile = new File("../studentMXM2NQ.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        // Build XPath tree
        XPath xPath = XPathFactory.newInstance().newXPath();

         String expression = "class";
        // String expression = "class/student";
        // String expression = "//student[@id='01']";
        // String expression = "//student";
        // String expression = "/class/student[position()=2]";
        // String expression = "/class/student[last()]";
        // String expression = "/class/student[position() = last() - 1]";
        // String expression = "/class/student[position() < 2]";
        // String expression = "/class/*";
        // String expression = "//student[@*]";
        // String expression = "//*";
        // String expression = "//class/*[kor > 20]";

        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
System.out.println(nodeList.getLength());
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            System.out.println("Aktuális elem: " + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
                Element element = (Element) node;
              
                System.out.println("Hallgató ID: " + element.getAttribute("id"));
                System.out.println("Keresznév: " + element.getElementsByTagName("keresztnév").item(0).getTextContent());
                System.out.println("Vezetéknév: " + element.getElementsByTagName("vezetéknév").item(0).getTextContent());
                System.out.println("Becenév: " + element.getElementsByTagName("becenév").item(0).getTextContent());
                System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());

            }
            
            System.out.println("");

        }

	}

}
