package com.three60t.fixatdl.converter;

import com.three60t.fixatdl.model.core.ParameterT;

import java.lang.reflect.Field;

/****
 *
 * @param <E>
 * @param <F>
 */
public interface ParameterTTypeConverter<E extends Comparable<?>, F extends ParameterT> {

    /***
     *
     * @return
     */
    E convertParameterConstToComparable();

    /***
     *
     * @return
     */
    F getParameter();

    /***
     *
     * @return
     */
    String convertParameterConstToFixWireValue();

    /***
     *
     * @param aFixWireValue
     * @return
     */
    Object convertFixWireValueToParameterConst(String aFixWireValue);


    default Object getConstFieldOfParam() {
        try {
            Field field = getParameter().getClass().getDeclaredField("constValue");
            field.setAccessible(true);
            return field.get(getParameter());
        } catch (Exception e) {
            return null;
        }
    }

    default Class<?> getParameterDatatype(Class<?> classIfNull) {
        if (getParameter() != null) {

            ParameterT tempParameter = getParameter();

            if (tempParameter != null) {
                return TypeConverterFactory.getParameterDatatype(tempParameter);
            } else {
                return null;
            }
        } else {
            return classIfNull;
        }
    }
}
