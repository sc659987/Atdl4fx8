//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.24 at 10:55:05 AM CST 
//

package com.three360.fixatdl.layout;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for RadioButton_t complex type.
 * <p>
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <p>
 * 
 * <pre>
 * &lt;complexType name="RadioButton_t">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fixprotocol.org/FIXatdl-1-1/Layout}Control_t">
 *       &lt;attribute name="radioGroup" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="initValue" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="checkedEnumRef" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="uncheckedEnumRef" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RadioButton_t")
public class RadioButtonT
		extends ControlT {

	@XmlAttribute
	protected String radioGroup;
	@XmlAttribute
	protected Boolean initValue;
	@XmlAttribute
	protected String checkedEnumRef;
	@XmlAttribute
	protected String uncheckedEnumRef;

	/**
	 * Gets the value of the radioGroup property.
	 *
	 * @return possible object is
	 *         {@link String }
	 */
	public String getRadioGroup() {
		return radioGroup;
	}

	/**
	 * Sets the value of the radioGroup property.
	 *
	 * @param value
	 *            allowed object is
	 *            {@link String }
	 */
	public void setRadioGroup(String value) {
		this.radioGroup = value;
	}

	/**
	 * Gets the value of the initValue property.
	 *
	 * @return possible object is
	 *         {@link Boolean }
	 */
	public Boolean isInitValue() {
		return initValue;
	}

	/**
	 * Sets the value of the initValue property.
	 *
	 * @param value
	 *            allowed object is
	 *            {@link Boolean }
	 */
	public void setInitValue(Boolean value) {
		this.initValue = value;
	}

	/**
	 * Gets the value of the checkedEnumRef property.
	 *
	 * @return possible object is
	 *         {@link String }
	 */
	public String getCheckedEnumRef() {
		return checkedEnumRef;
	}

	/**
	 * Sets the value of the checkedEnumRef property.
	 *
	 * @param value
	 *            allowed object is
	 *            {@link String }
	 */
	public void setCheckedEnumRef(String value) {
		this.checkedEnumRef = value;
	}

	/**
	 * Gets the value of the uncheckedEnumRef property.
	 *
	 * @return possible object is
	 *         {@link String }
	 */
	public String getUncheckedEnumRef() {
		return uncheckedEnumRef;
	}

	/**
	 * Sets the value of the uncheckedEnumRef property.
	 *
	 * @param value
	 *            allowed object is
	 *            {@link String }
	 */
	public void setUncheckedEnumRef(String value) {
		this.uncheckedEnumRef = value;
	}

}
