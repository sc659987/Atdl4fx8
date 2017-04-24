package com.three360.fixatdl.wire;

import com.three360.fixatdl.core.ParameterT;
import com.three360.fixatdl.core.StrategiesT;
import com.three360.fixatdl.core.StrategyT;

import java.lang.reflect.Field;

public class WireValueManipulator implements WireValueGenerator, WireValueInterpreter {

    // -- Repeating Group count --
    public static final String TAG_NO_STRATEGY_PARAMETERS = "957";

    public static final String TAG_STRATEGY_PARAMETER_NAME = "958";

    public static final String TAG_STRATEGY_PARAMETER_TYPE = "959";

    public static final String TAG_STRATEGY_PARAMETER_VALUE = "960";

    private static final char DELIMITER = '|';

    public static final char EQUAL = '=';

    private WireValueManipulator() {
    }

    private static WireValueManipulator _singleTon;

    public static synchronized WireValueManipulator getInstance() {
        return _singleTon == null ? _singleTon = new WireValueManipulator() : _singleTon;
    }

    public Object getConstantValueFromParameter(ParameterT parameterT) {
        try {
            Field field = parameterT.getClass().getDeclaredField("constValue");
            field.setAccessible(true);
            return field != null ? field.get(parameterT) : null;
        } catch (Exception e) {
        }
        return null;
    }


    public String generateWireValue(StrategiesT strategies, StrategyT selectedStrategy) {
        final StringBuilder wireBuilder = new StringBuilder();
        final CountIncrement repeatCount = new CountIncrement();
        selectedStrategy.getParameter().forEach(parameterT -> {
            Object constValue = getConstantValueFromParameter(parameterT);
            if (constValue != null && !constValue.toString().isEmpty()) {
                if (strategies.isTag957Support())
                    wireBuilder
                            .append(DELIMITER)
                            .append(TAG_STRATEGY_PARAMETER_NAME)
                            .append(EQUAL)
                            .append(parameterT.getName())
                            .append(DELIMITER)
                            .append(TAG_STRATEGY_PARAMETER_TYPE)
                            .append(EQUAL)
                            .append(parameterT.getTag959())
                            .append(DELIMITER)
                            .append(TAG_STRATEGY_PARAMETER_VALUE)
                            .append(EQUAL)
                            .append(constValue.toString());
                else
                    wireBuilder.append(parameterT.getFixTag().intValue())
                            .append(EQUAL)
                            .append(constValue.toString());
                repeatCount.incrementByOne();
            }
        });

        String preambleWire = strategies.getStrategyIdentifierTag().toString()
                + EQUAL
                + selectedStrategy.getWireValue()
                + DELIMITER
                + (strategies.isTag957Support() ? (TAG_NO_STRATEGY_PARAMETERS + EQUAL + repeatCount.count)
                : "");
        return preambleWire + wireBuilder.toString();
    }

    @Override
    public void consumeWireString(String aa, StrategyT strategyT, StrategiesT strategiesT) {
        // TODO to be done

    }

    private static class CountIncrement {
        int count = 0;

        public void incrementByOne() {
            count++;
        }

        public int getValue() {
            return count;
        }
    }
}
