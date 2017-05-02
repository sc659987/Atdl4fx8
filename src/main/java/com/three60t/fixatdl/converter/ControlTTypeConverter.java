package com.three60t.fixatdl.converter;


import com.three60t.fixatdl.model.core.EnumPairT;
import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.layout.CheckBoxT;
import com.three60t.fixatdl.model.layout.ControlT;

/**
 * Created by sainik on 01/05/17.
 */
public interface ControlTTypeConverter<E extends Comparable<?>> {

    /****
     *
     * @param value
     * @return
     */
    Object convertControlValueToParameterValue(Object value);

    /***
     *
     * @param value
     * @param aControl
     * @return
     */
    E convertParameterValueToControlValue(Object value, ControlT aControl);

    /***
     *
     * @param value
     * @return
     */
    E convertControlValueToControlComparable(Object value);

    /***
     *
     * @param aString
     * @return
     */
    E convertStringToControlValue(String aString);


    default Object adjustParameterValueForEnumRefValue(Object aParameterValue, ParameterT aParameter, ControlT aControl) {
        //logger.debug("aParameterValue: " + aParameterValue + " aParameter: " + aParameter + " aControl: " + aControl);
        if ((aParameterValue != null) && (aParameter != null) && (aControl != null)) {
            if (aControl instanceof CheckBoxT) {
                CheckBoxT tempCheckBox = (CheckBoxT) aControl;

                EnumPairT tempCheckedEnumPair = getEnumPairForEnumID(aParameter, tempCheckBox.getCheckedEnumRef());
                EnumPairT tempUncheckedEnumPair = getEnumPairForEnumID(aParameter, tempCheckBox.getUncheckedEnumRef());
                String tempParameterValueString = aParameterValue.toString();
                //logger.debug("tempParameterValueString: " + tempParameterValueString + " tempCheckedEnumPair: " + tempCheckedEnumPair + " tempUncheckedEnumPair: " + tempUncheckedEnumPair);

                if ((tempCheckedEnumPair != null) && (tempParameterValueString.equals(tempCheckedEnumPair.getWireValue()))) {
                    return Boolean.TRUE.toString();
                } else if ((tempUncheckedEnumPair != null) && (tempParameterValueString.equals(tempUncheckedEnumPair.getWireValue()))) {
                    return Boolean.FALSE.toString();
                }
            }
        }

        return aParameterValue;
    }


    default EnumPairT getEnumPairForEnumID(ParameterT aParameter, String aEnumID) {
        for (EnumPairT enumPair : aParameter.getEnumPair()) {
            if (enumPair.getEnumID().equals(aEnumID)) {
                return enumPair;
            }
        }

        return null;
    }

}
