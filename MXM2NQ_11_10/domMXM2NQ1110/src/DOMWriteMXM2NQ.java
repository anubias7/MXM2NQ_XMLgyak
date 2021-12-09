import java.io.File;
import java.io.IOException;

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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class DOMWriteMXM2NQ {
    public static void main(String arg[]) throws IOException, ParserConfigurationException, SAXException,
            TransformerConfigurationException, TransformerException {
        File outputXMLFile = new File("../user1MXM2NQ.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document outputDoc = dBuilder.newDocument();
        Element root = outputDoc.createElementNS("domMXM2NQ", "users");
        outputDoc.appendChild(root);

        root.appendChild(createUser(outputDoc, "1", "Pal", "Kiss", "programmer"));
        root.appendChild(createUser(outputDoc, "2", "Pirsoka", "Zold", "writer"));
        root.appendChild(createUser(outputDoc, "3", "Alma", "Gordon", "teacher"));

        // Copy input doc
        DOMSource source = new DOMSource(outputDoc);

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(outputXMLFile);

        TransformerFactory transfFactory = TransformerFactory.newInstance();
        Transformer transf = transfFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        transf.transform(source, console);
        transf.transform(source, file);
    }

    private static Node createUser(Document doc, String id, String fname, String lname, String prof) {
        Element user = doc.createElement("user");

        user.setAttribute("id", id);
        Element firstname = doc.createElement("firstname");
        firstname.setTextContent(fname);

        Element lastname = doc.createElement("lastname");
        lastname.setTextContent(lname);

        Element profession = doc.createElement("profession");
        profession.setTextContent(prof);

        user.appendChild(firstname);
        user.appendChild(lastname);
        user.appendChild(profession);

        return user;
    }
}
