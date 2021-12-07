import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMReadMXM2NQ {
    public static void main(String arg[]) throws SAXException, IOException, ParserConfigurationException {
        // Get file
        File xmlFile = new File("XMLMXM2NQ.xml");

        // Get DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        // Parse docuement and normalize
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        // gyökér elem
        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
        System.out.println("\n");

        // könyv beolvasasa
        NodeList nList = doc.getElementsByTagName("konyv");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() != Node.ELEMENT_NODE)
            {

            Element eElement = (Element) nNode;
            // Könyv adatai
            System.out.println("konyv Id: " + eElement.getAttribute("ISBN"));
            
              System.out.println("Könyv címe: " +
              eElement.getElementsByTagName("cim").item(0).getTextContent());
              System.out.println("Könyv kiadója: " +
              eElement.getElementsByTagName("kiado").item(0).getTextContent());
              System.out.println("Könyv megjelenési dátuma: " + 
              eElement.getElementsByTagName("megjelenes_datuma").item(0).getTextContent());
            }

        }
        System.out.println("________________________");

        
        NodeList nList2 = doc.getElementsByTagName("kolcsonzo");
        for(int j=0; j<nList2.getLength();j++){
          Node nNode = nList2.item(j);
          
          if(nNode.getNodeType() == Node.ELEMENT_NODE)
          {
          Element eElement = (Element) nNode;
          //Kölcsönző adatai
          System.out.println("kolcs Id: " + eElement.getAttribute("kkod"));
          System.out.println("Kölcsönző neve: " +
          eElement.getElementsByTagName("nev").item(0).getTextContent());
          System.out.println("Kölcsönző könyveinek száma: " +
          eElement.getElementsByTagName("kolcsonzesek_szama").item(0).getTextContent());
          System.out.println("Kölcsönző cime: " +
                            eElement.getElementsByTagName("cim").item(0).getTextContent());
        }
         
        System.out.println("________________________");
          
        NodeList nList3 = doc.getElementsByTagName("bor");
        for(int k=0; k<nList3.getLength();k++){
          Node mNode = nList3.item(k);
          
          if(mNode.getNodeType() == Node.ELEMENT_NODE)
          {
          Element eElement = (Element) mNode;
          //Bor adatai
          System.out.println("bor Id: " + eElement.getAttribute("Bkod"));
          System.out.println("Bor neve: " +
          eElement.getElementsByTagName("nev").item(0).getTextContent());
          System.out.println("Bor ara: " +
          eElement.getElementsByTagName("ar").item(0).getTextContent());
          System.out.println("Bor fajta: " +
          eElement.getElementsByTagName("fajta").item(0).getTextContent());
          }
        }
        System.out.println("________________________");
          
          NodeList nList4 = doc.getElementsByTagName("vevo");
          for(int l=0; l<nList4.getLength();l++)
          {
          Node oNode = nList4.item(l);
          
          if(oNode.getNodeType() == Node.ELEMENT_NODE)
          {
          Element eElement = (Element) oNode;
          //vevõ adatai
          System.out.println("vevo Id: " + eElement.getAttribute("Vkod"));
          System.out.println("vevo neve: " +
          eElement.getElementsByTagName("nev").item(0).getTextContent());
          System.out.println("Vevo telefonszama: " +
          eElement.getElementsByTagName("tszam").item(0).getTextContent());
          
          
          System.out.println("Vevo cime: " + "\n"+
          eElement.getElementsByTagName("varos").item(0).getTextContent()+" "+eElement.
          getElementsByTagName("irszam").item(0).getTextContent()
          +", "+eElement.getElementsByTagName("utca").item(0).getTextContent()+" "+
          eElement.getElementsByTagName("hszam").item(0).getTextContent()+"." );
          
          }
          }
         
    }

}

}