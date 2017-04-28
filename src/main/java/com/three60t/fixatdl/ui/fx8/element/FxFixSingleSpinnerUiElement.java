package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.model.core.IntT;
import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.layout.SingleSpinnerT;
import com.three60t.fixatdl.ui.common.element.FixSingleSpinnerUiElement;
import com.three60t.fixatdl.utils.Utils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.Collections;
import java.util.List;


public class FxFixSingleSpinnerUiElement implements FixSingleSpinnerUiElement<Pane, Double> {

    private Spinner<Double> singleSpinner;
    private SingleSpinnerT singleSpinnerT;

    private GridPane gridPane;

    private ParameterT parameterT;

    private int nextColumn = 0;

    private ObjectProperty<String> controlIdEmitter = new SimpleObjectProperty<>();

    private Pair<Double, Double> limit;

    @Override
    public Pane create() {
        if (this.singleSpinnerT != null) {
            this.gridPane = new GridPane();
            if (Utils.isNonEmpty(this.singleSpinnerT.getLabel())) {
                this.gridPane.add(new Label(this.singleSpinnerT.getLabel()),
                        this.nextColumn++, 0);
            }
            this.gridPane.setHgap(3);
            this.limit = extractRangeFromParameter();
            this.singleSpinner = new Spinner<>(limit.getKey(),
                    limit.getValue(),
                    singleSpinnerT.getInitValue() == null ? 0 : limit.getKey(),
                    this.singleSpinnerT.getIncrement() == null ? ((limit.getKey() == 0.0) ? 0.1 : limit.getKey()) : this.singleSpinnerT.getIncrement());

            this.singleSpinner.setOnMouseClicked(event -> {
                setValue(getValue());
                controlIdEmitter.setValue(singleSpinnerT.getID() + ":" + getValue());
            });


            if (this.singleSpinnerT.getInitValue() != null) {
                setValue(this.singleSpinnerT.getInitValue());
            }


            this.gridPane.add(this.singleSpinner, this.nextColumn, 0);
            return this.gridPane;
        }
        return null;
    }

    private Pair<Double, Double> extractRangeFromParameter() {
        return parameterT != null && (parameterT instanceof IntT)
                ? new Pair<>((((IntT) parameterT).getMinValue() == null ? 0.0 : ((IntT) parameterT).getMinValue()),
                (((IntT) parameterT).getMaxValue() == null ? Double.MAX_VALUE : ((IntT) parameterT).getMaxValue()))
                : new Pair<>(0.0, Double.MAX_VALUE);
    }

    @Override
    public void setSingleSpinner(SingleSpinnerT singleSpinnerT) {
        this.singleSpinnerT = singleSpinnerT;
    }

    @Override
    public void setParameters(List<ParameterT> parameterTList) {
        assert (parameterTList != null);
        this.parameterT = parameterTList.get(0);
    }

    @Override
    public List<ParameterT> getParameters() {
        List<ParameterT> parameterTS = Collections.emptyList();
        parameterTS.add(this.parameterT);
        return parameterTS;
    }

    @Override
    public ObjectProperty<String> listenChange() {
        return this.controlIdEmitter;
    }

    @Override
    public SingleSpinnerT getControl() {
        return this.singleSpinnerT;
    }

    @Override
    public Double getValue() {
        return this.singleSpinner.getValue();
    }

    @Override
    public void setValue(Double value) {
        this.singleSpinner.getValueFactory().setValue(value==null?limit.getKey():value);
        setFieldValueToParameter(value, parameterT);
    }

    @Override
    public void makeVisible(boolean visible) {
        this.singleSpinner.setVisible(visible);
    }

    @Override
    public void makeEnable(boolean enable) {
        this.singleSpinner.setDisable(!enable);
    }
}
