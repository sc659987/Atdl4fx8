//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.24 at 10:55:05 AM CST 
//

package com.three60t.fixatdl.model.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Derived parameter type corresponding to the FIX "Boolean" type defined in the FIX specification.
 * <p>
 * <p>
 * Java class for Boolean_t complex type.
 * <p>
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <p>
 * <p>
 * <pre>
 * &lt;complexType name="Boolean_t">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fixprotocol.org/FIXatdl-1-1/Core}Parameter_t">
 *       &lt;attribute name="trueWireValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="falseWireValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="constValue" type="{http://www.fixprotocol.org/FIXatdl-1-1/Core}Boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Boolean_t")
public class BooleanT
        extends ParameterT {

    // Sainik : this has been deprecated
    @XmlAttribute
    protected String trueWireValue;
    // Sainik: this has been deprecated
    @XmlAttribute
    protected String falseWireValue;
    @XmlAttribute
    protected String constValue;

    /**
     * Gets the value of the trueWireValue property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTrueWireValue() {
        return trueWireValue;
    }

    /**
     * Sets the value of the trueWireValue property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTrueWireValue(String value) {
        this.trueWireValue = value;
    }

    /**
     * Gets the value of the falseWireValue property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFalseWireValue() {
        return falseWireValue;
    }

    /**
     * Sets the value of the falseWireValue property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFalseWireValue(String value) {
        this.falseWireValue = value;
    }

    /**
     * Gets the value of the constValue property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getConstValue() {
        return constValue;
    }

    /**
     * Sets the value of the constValue property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConstValue(String value) {
        this.constValue = value;
    }

    @Override
    public int getTag959() {
        return 13;
    }
}
