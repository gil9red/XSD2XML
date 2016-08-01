/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xsd2xml_example;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamResult;
import jlibs.xml.sax.XMLDocument;
import jlibs.xml.xsd.XSInstance;
import jlibs.xml.xsd.XSParser;
import org.apache.xerces.xs.XSModel;


/**
 *
 * @author ipetrash
 */
public class Xsd2xml_example {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        URL url = Xsd2xml_example.class.getResource("/resources/XmlSchema.xsd");
        String path = url.getFile();
        System.out.println("Xsd path: " + path);
                
        XSModel xsModel = new XSParser().parse(path);
        XSInstance xsInstance = new XSInstance();
        xsInstance.generateAllChoices = Boolean.TRUE;
        xsInstance.generateOptionalElements = Boolean.TRUE;
        xsInstance.generateOptionalAttributes = Boolean.TRUE;
        xsInstance.generateFixedAttributes = Boolean.TRUE;
        xsInstance.generateDefaultAttributes = Boolean.TRUE;
        
        QName rootElement = new QName("root");
        
        // Create a StringWriter for the output
        java.io.StringWriter outWriter = new java.io.StringWriter();
        XMLDocument sampleXml = new XMLDocument(new StreamResult(outWriter), true, 4, null);
        xsInstance.generate(xsModel, rootElement, sampleXml);
        
        System.out.println();
        System.out.println("Xml:");
        System.out.println(outWriter.getBuffer().toString());
    }
}
