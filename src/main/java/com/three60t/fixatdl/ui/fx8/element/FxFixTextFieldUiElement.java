package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.layout.TextFieldT;
import com.three60t.fixatdl.ui.common.element.FixTextFieldUiElement;
import com.three60t.fixatdl.ui.fx8.FxUtils;
import com.three60t.fixatdl.utils.Utils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Collections;
import java.util.List;

public class FxFixTextFieldUiElement implements FixTextFieldUiElement<Pane, String> {

    private TextFieldT textFieldT;

    private TextField textField;
    private GridPane gridPane;

    private ParameterT parameterT;
    private int nextColumn = 0;

    private ObjectProperty<String> controlIdEmitter = new SimpleObjectProperty<>();

    @Override
    public Pane create() {
        if (this.textFieldT != null) {
            this.gridPane = new GridPane();
            if (!Utils.isEmpty(this.textFieldT.getLabel())) {
                this.gridPane.getColumnConstraints().addAll(FxUtils.getTwoColumnSameWidthForGridPane());
                this.gridPane.add(new Label(this.textFieldT.getLabel()), this.nextColumn++, 0);
            }
            this.gridPane.setHgap(3);
            this.textField = new TextField(this.textFieldT.getInitValue());
            this.textField.setOnKeyReleased(event -> {
                setFieldValueToParameter(getValue(), this.parameterT);
                this.controlIdEmitter.setValue(textFieldT.getID() + ":" + getValue());
            });
            if (Utils.isNonEmpty(this.textFieldT.getInitValue()))
                setValue(this.textFieldT.getInitValue());
            this.gridPane.add(this.textField, this.nextColumn, 0);
            return this.gridPane;
        }
        return null;
    }

    @Override
    public void setTextField(TextFieldT textField) {
        this.textFieldT = textField;
    }

    @Override
    public void setParameters(List<ParameterT> parameterTList) {
        if (parameterTList != null && parameterTList.size() > 0)
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
    public TextFieldT getControl() {
        return this.textFieldT;
    }

    @Override
    public String getValue() {
        String str = this.textField.getCharacters().toString();
        return Utils.isEmpty(str) ? null : str;
    }

    @Override
    public void setValue(String text) {
        this.textField.setText(text);
        setFieldValueToParameter(text, parameterT);
    }

    @Override
    public void makeVisible(boolean visible) {
        this.textField.setVisible(visible);
    }

    @Override
    public void makeEnable(boolean enable) {
        this.textField.setDisable(!enable);
    }
}
