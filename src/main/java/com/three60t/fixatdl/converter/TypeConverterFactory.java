package com.three60t.fixatdl.converter;

import com.three60t.fixatdl.model.core.*;
import com.three60t.fixatdl.model.layout.*;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Factory that creates the appropriate ParameterTypeConveter depending on the parameter
 * or creates the appropriate ControlTypeConveter depending upon the control type.
 * <p>
 * Note that all UI widgets in ATDL are strongly typed.
 */
public class TypeConverterFactory {

    /*
     * Create adapter based on ParameterT
     */
    public static ParameterTTypeConverter<?, ?> createParameterTypeConverter(ParameterT parameter) {
        if (parameter instanceof StringT || parameter instanceof CharT || parameter instanceof MultipleCharValueT
                || parameter instanceof MultipleStringValueT || parameter instanceof CurrencyT || parameter instanceof ExchangeT
                || parameter instanceof DataT) {
            return new StringConverter(parameter);
        } else if (parameter instanceof NumericT) {
            return new DecimalConverter(parameter); // Float field
        } else if (parameter instanceof IntT
                || parameter instanceof NumInGroupT
                || parameter instanceof SeqNumT
                || parameter instanceof TagNumT
                || parameter instanceof LengthT) {
            return new IntegerConverter(parameter); // Integer field
        } else if (parameter instanceof BooleanT) {
            return new BooleanConverter((BooleanT) parameter);
        } else if (parameter instanceof MonthYearT || parameter instanceof UTCTimestampT || parameter instanceof UTCTimeOnlyT
                || parameter instanceof LocalMktDateT || parameter instanceof UTCDateOnlyT) {
            return new DateTimeConverter(parameter);
        } else {
            throw new IllegalArgumentException("Unsupported ParameterT type: " + parameter.getClass().getName());
        }
    }

    /*
     * Create adapter based on ControlT (native type for each control)
     */
    public static ControlTTypeConverter<?> createControlTypeConverter(ControlT control, ParameterT parameterT) {

        if (parameterT == null)
            return null;

        if (control instanceof TextFieldT || control instanceof SingleSelectListT || control instanceof MultiSelectListT
                || control instanceof CheckBoxListT || control instanceof DropDownListT || control instanceof EditableDropDownListT
                || control instanceof RadioButtonListT || control instanceof HiddenFieldT || control instanceof LabelT) {
            return new StringConverter(parameterT);
        } else if (control instanceof SliderT) {
            return new StringConverter(parameterT);
        } else if (control instanceof SingleSpinnerT || control instanceof DoubleSpinnerT) {
            return new DecimalConverter(parameterT);
        } else if (control instanceof CheckBoxT || control instanceof RadioButtonT) {
            return new BooleanConverter(parameterT);
        } else if (control instanceof ClockT) {
            return new DateTimeConverter(parameterT);
        }
        return null;
    }

    /*
     * Returns an Object that is an instanceof the Parameter's base data type
     * (eg String, BigDecimal, DateTime, etc)
     */
    // 3/12/2010 Scott Atwell added
    public static Class<?> getParameterDatatype(ParameterT aParameter) {
        if (aParameter instanceof StringT || aParameter instanceof CharT
                || aParameter instanceof MultipleCharValueT
                || aParameter instanceof MultipleStringValueT
                || aParameter instanceof CurrencyT
                || aParameter instanceof ExchangeT
                || aParameter instanceof DataT) {
            return String.class;
        } else if (aParameter instanceof NumericT) {
            return BigDecimal.class; // Float field
        } else if (aParameter instanceof IntT
                || aParameter instanceof NumInGroupT
                || aParameter instanceof SeqNumT
                || aParameter instanceof TagNumT
                || aParameter instanceof LengthT) {
            return BigInteger.class; // Integer field
        } else if (aParameter instanceof BooleanT) {
            return Boolean.class;
        } else if (aParameter instanceof MonthYearT) {
            return String.class;
        } else if (aParameter instanceof UTCTimestampT
                || aParameter instanceof UTCTimeOnlyT
                || aParameter instanceof LocalMktDateT
                || aParameter instanceof UTCDateOnlyT) {
            return XMLGregorianCalendar.class;
        } else {
            throw new IllegalArgumentException("Unsupported ParameterT type: "
                    + aParameter.getClass().getName());
        }
    }
}
