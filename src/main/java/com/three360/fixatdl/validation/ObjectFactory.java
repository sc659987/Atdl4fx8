//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.24 at 10:55:05 AM CST 
//

package com.three360.fixatdl.validation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.atdl4j.fixatdl.validation package.
 * <p>
 * An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups. Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StrategyEdit_QNAME = new QName("http://www.fixprotocol.org/FIXatdl-1-1/Validation", "StrategyEdit");
    private final static QName _EditRef_QNAME = new QName("http://www.fixprotocol.org/FIXatdl-1-1/Validation", "EditRef");
    private final static QName _Edit_QNAME = new QName("http://www.fixprotocol.org/FIXatdl-1-1/Validation", "Edit");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.atdl4j.fixatdl.validation
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EditRefT }
     */
    public EditRefT createEditRefT() {
        return new EditRefT();
    }

    /**
     * Create an instance of {@link EditT }
     */
    public EditT createEditT() {
        return new EditT();
    }

    /**
     * Create an instance of {@link StrategyEditT }
     */
    public StrategyEditT createStrategyEditT() {
        return new StrategyEditT();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrategyEditT }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.fixprotocol.org/FIXatdl-1-1/Validation", name = "StrategyEdit")
    public JAXBElement<StrategyEditT> createStrategyEdit(StrategyEditT value) {
        return new JAXBElement<StrategyEditT>(_StrategyEdit_QNAME, StrategyEditT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditRefT }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.fixprotocol.org/FIXatdl-1-1/Validation", name = "EditRef")
    public JAXBElement<EditRefT> createEditRef(EditRefT value) {
        return new JAXBElement<EditRefT>(_EditRef_QNAME, EditRefT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditT }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.fixprotocol.org/FIXatdl-1-1/Validation", name = "Edit")
    public JAXBElement<EditT> createEdit(EditT value) {
        return new JAXBElement<EditT>(_Edit_QNAME, EditT.class, null, value);
    }

}
