//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.24 at 10:55:05 AM CST 
//

package com.three360.fixatdl.core;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Use_t.
 * <p>
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <p>
 * <p>
 * <pre>
 * &lt;simpleType name="Use_t">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="required"/>
 *     &lt;enumeration value="optional"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "Use_t")
@XmlEnum
public enum UseT {

    @XmlEnumValue("required")REQUIRED("required"),
    @XmlEnumValue("optional")OPTIONAL("optional");
    private final String value;

    UseT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UseT fromValue(String v) {
        for (UseT c : UseT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
