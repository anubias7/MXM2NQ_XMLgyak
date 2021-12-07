import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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

        File inputFile = new File("XMLMXM2NQ.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        // Build XPath tree
        XPath xPath = XPathFactory.newInstance().newXPath();

        int inputCode = 0;
        System.out.println("Menu: ");
        System.out.println("1) - A miskolci kölcsönzők nevei");
        System.out.println("2) - A 2018-ban és attól később született állatok lekérdezése");
        System.out.println("3) - December havi nyitvatartás lekérdezése");
        System.out.println("4) - Az utolsó 3 takarítás lekérdezése");
        System.out.println("Más billentyű) - Kilépés");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        try {
            inputCode = Integer.parseInt(input);
        } catch (Exception e) {
            inputCode = 0;
        }

        String expression = "";

        switch (inputCode) {
            case 1:
                expression = "//kolcsonzo/nev[../varos='Miskolc']";
                break;
            case 2:
                expression = "//allat[number(translate(./szuletes-ideje,'-','')) > 20180101]";
                break;
            case 3:
                expression = "//nyitvatartas[@honap=12]";
                break;
            case 4:
                expression = "//takaritas[position() > last() - 3]";
                break;
            default:
                System.out.println("Bye");
                return;
        }

        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
        }
    }
}