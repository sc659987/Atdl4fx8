package com.three60t.ui.fx8.element;

import com.three60t.fixatdl.core.ParameterT;
import com.three60t.fixatdl.layout.ControlT;
import com.three60t.fixatdl.layout.DoubleSpinnerT;
import com.three60t.ui.common.element.FixDoubleSpinnerUiElement;
import com.three60t.ui.fx8.customelement.doublespinner.DoubleSpinner;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.List;

public class FxFixDoubleSpinnerUiElement implements FixDoubleSpinnerUiElement<Pane, String> {

    private DoubleSpinner doubleSpinner;
    private DoubleSpinnerT doubleSpinnerT;

    private GridPane gridPane;

    private ParameterT parameterT;

    private int nextColumn = 0;

    private ObjectProperty<String> controlIdEmitter = new SimpleObjectProperty<>();

    private Pair<Double, Double> limit;

    private Pair<Double, Double> extractRangeFromParameter() {
        return null;
    }

    @Override
    public Pane create() {
        return null;
    }

    @Override
    public void setDoubleSpinner(DoubleSpinnerT doubleSpinnerT) {

    }

    @Override
    public void setParameters(List<ParameterT> parameterTList) {

    }

    @Override
    public List<ParameterT> getParameters() {
        return null;
    }

    @Override
    public ObjectProperty<String> listenChange() {
        return null;
    }

    @Override
    public <C extends ControlT> C getControl() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public void setValue(String s) {

    }

    @Override
    public void makeVisible(boolean visible) {

    }

    @Override
    public void makeEnable(boolean enable) {

    }
}
