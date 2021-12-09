package XsdValidationMXM2NQ1020;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stax.StAXSource;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XsdValidationMXM2NQ {
    public static void main(String[] args) {
        if (validateXMLSchema()) {
            System.out.println("XSD Validation Succesful!");
        } else {
            System.out.println("XSD Validation error!");
        }
    }

    private static boolean validateXMLSchema() {
        try {

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = new FileInputStream("MXM2NQ_1020/macskakMXM2NQ.xml");
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("MXM2NQ_1020/macskakMXM2NQ.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StAXSource(eventReader));
        } catch (IOException err) {
            System.out.println("Exception: " + err.getMessage());
            return false;
        } catch (SAXException err) {
            System.out.println("SAX Exception: " + err.getMessage());
            return false;
        } catch (XMLStreamException err) {
            System.out.println("XMLStreamException: " + err.getMessage());
            return false;
        }
        return true;
    }
}
