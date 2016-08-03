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
    public static 
    java.util.List<javax.xml.namespace.QName> getGlobalElements(URL path) throws Exception {
        // http://xmlbeans.apache.org/docs/2.4.0/reference/org/apache/xmlbeans/SchemaTypeSystem.html
        org.apache.xmlbeans.SchemaTypeSystem sts = org.apache.xmlbeans.XmlBeans.compileXsd(
            new org.apache.xmlbeans.XmlObject[] { 
                org.apache.xmlbeans.XmlObject.Factory.parse(path) 
            },
            org.apache.xmlbeans.XmlBeans.getBuiltinTypeSystem(),
            null
        );
        
        java.util.List<javax.xml.namespace.QName> qnames = new java.util.ArrayList();
        for (org.apache.xmlbeans.SchemaGlobalElement el: sts.globalElements()) {
            qnames.add(el.getName());
        }
        
        return qnames;
    }
    
    /**
     * @param args the command line arguments
     */
    public static 
    void main(String[] args) throws Exception {
        URL url = Xsd2xml_example.class.getResource("/resources/XmlSchema.xsd");
        String path = url.getFile();
        System.out.println("Xsd path: " + path);

        java.util.List<javax.xml.namespace.QName> globalElements = getGlobalElements(url);
        if (globalElements.isEmpty()) {
            throw new Exception("В схеме корневые элементы не были найдены.");
        }
        System.out.println("Корневых элементов: " + globalElements.size());
        for (javax.xml.namespace.QName root: globalElements) {
            System.out.println("  " + root);
        }
        
        // TODO: должны быть на выбор, первый попавшийся -- пока временное решение
        javax.xml.namespace.QName rootElement = globalElements.iterator().next();
        System.out.println("Первый корневой элемент: " + rootElement);
        
        XSModel xsModel = new XSParser().parse(path);
        XSInstance xsInstance = new XSInstance();
        xsInstance.generateAllChoices = Boolean.TRUE;
        xsInstance.generateOptionalElements = Boolean.TRUE;
        xsInstance.generateOptionalAttributes = Boolean.TRUE;
        xsInstance.generateFixedAttributes = Boolean.TRUE;
        xsInstance.generateDefaultAttributes = Boolean.TRUE;

        // Create a StringWriter for the output
        java.io.StringWriter outWriter = new java.io.StringWriter();
        XMLDocument sampleXml = new XMLDocument(new StreamResult(outWriter), true, 4, null);
        xsInstance.generate(xsModel, rootElement, sampleXml);
        
        System.out.println();
        System.out.println("Xml:");
        System.out.println(outWriter.getBuffer().toString());
    }
}
