package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.converter.ControlTTypeConverter;
import com.three60t.fixatdl.converter.TypeConverterFactory;
import com.three60t.fixatdl.model.core.IntT;
import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.layout.DoubleSpinnerT;
import com.three60t.fixatdl.ui.common.element.FixDoubleSpinnerUiElement;
import com.three60t.fixatdl.ui.fx8.customelement.doublespinner.DoubleSpinner;
import com.three60t.fixatdl.utils.Utils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

public class FxFixDoubleSpinnerUiElement implements FixDoubleSpinnerUiElement<Pane, Double> {

    private DoubleSpinner doubleSpinner;
    private DoubleSpinnerT doubleSpinnerT;

    private GridPane gridPane;

    private ParameterT parameterT;

    private int nextColumn = 0;

    private ObjectProperty<String> controlIdEmitter = new SimpleObjectProperty<>();

    private Pair<Double, Double> limit;

    private ControlTTypeConverter<?> controlTTypeConverter;

    private Pair<Double, Double> extractRangeFromParameter() {
        return parameterT != null && (parameterT instanceof IntT)
                ? new Pair<>((((IntT) parameterT).getMinValue() == null ? 0.0 : ((IntT) parameterT).getMinValue()),
                (((IntT) parameterT).getMaxValue() == null ? Double.MAX_VALUE : ((IntT) parameterT).getMaxValue()))
                : new Pair<>(0.0, Double.MAX_VALUE);
    }

    @Override
    public Pane create() {
        if (this.doubleSpinnerT != null) {
            this.gridPane = new GridPane();

            controlTTypeConverter = TypeConverterFactory.createControlTypeConverter(doubleSpinnerT, parameterT);

            if (Utils.isNonEmpty(this.doubleSpinnerT.getLabel())) {
                this.gridPane.add(new Label(this.doubleSpinnerT.getLabel()),
                        this.nextColumn++, 0);
            }
            this.gridPane.setHgap(3);
            this.limit = extractRangeFromParameter();
            this.doubleSpinner = new DoubleSpinner(limit.getKey(),
                    limit.getValue(),
                    doubleSpinnerT.getInitValue() == null ? limit.getKey() : doubleSpinnerT.getInitValue(),
                    doubleSpinnerT.getInnerIncrement(),
                    doubleSpinnerT.getOuterIncrement() == null ? doubleSpinnerT.getInnerIncrement() : doubleSpinnerT.getOuterIncrement());
            this.doubleSpinner.setOnMouseClicked(event -> {
                setValue(getValue());
                controlIdEmitter.setValue(this.doubleSpinnerT.getID() + ":" + getValue());
            });
            this.gridPane.add(this.doubleSpinner, this.nextColumn, 0);
            return this.gridPane;
        }
        return null;
    }

    @Override
    public void setDoubleSpinner(DoubleSpinnerT doubleSpinnerT) {
        this.doubleSpinnerT = doubleSpinnerT;

    }

    @Override
    public void setParameters(List<ParameterT> parameterTList) {
        this.parameterT = parameterTList.get(0);

    }

    @Override
    public List<ParameterT> getParameters() {
        return Arrays.asList(this.parameterT);
    }

    @Override
    public ObjectProperty<String> listenChange() {
        return this.controlIdEmitter;
    }

    @Override
    public DoubleSpinnerT getControl() {
        return this.doubleSpinnerT;
    }

    @Override
    public Double getValue() {
        return this.doubleSpinner.getValue();
    }

    @Override
    public void setValue(Double s) {
        this.doubleSpinner.getValueFactory().setValue(s);
        setFieldValueToParameter(doubleSpinner.getValue(), parameterT);
    }

    @Override
    public void makeVisible(boolean visible) {
        this.gridPane.setVisible(visible);
    }

    @Override
    public void makeEnable(boolean enable) {
        this.gridPane.setDisable(!enable);
    }

    @Override
    public ControlTTypeConverter<?> getControlTTypeConverter() {
        return this.controlTTypeConverter;
    }
}
