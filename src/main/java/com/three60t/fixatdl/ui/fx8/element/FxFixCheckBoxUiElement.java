package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.layout.CheckBoxT;
import com.three60t.fixatdl.ui.common.element.FixCheckBoxUiElement;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.CheckBox;

import java.util.Collections;
import java.util.List;

// TODO check with checkbox group
public class FxFixCheckBoxUiElement implements FixCheckBoxUiElement<CheckBox, Boolean> {

    private CheckBoxT checkBoxT;
    private CheckBox checkBox;
    private ParameterT parameterT;

    private ObjectProperty<String> controlIdEmitter = new SimpleObjectProperty<>();

    @Override
    public CheckBox create() {
        if (this.checkBoxT != null) {
            this.checkBox = new CheckBox(this.checkBoxT.getLabel());

            if (this.checkBoxT.isInitValue() != null) {
                this.checkBox.setSelected(this.checkBoxT.isInitValue());
                setValue(getValue());
            }

            this.checkBox.setOnAction(event -> {
                this.controlIdEmitter.setValue(this.checkBoxT.getID() + ":" + getValue());
                setFieldValueToParameter(this.checkBox.isSelected() ? this.checkBoxT.getCheckedEnumRef() : this.checkBoxT.getUncheckedEnumRef(),
                        this.parameterT);
            });

            return this.checkBox;
        }
        return null;
    }

    @Override
    public void setCheckBoxT(CheckBoxT checkBoxT) {
        this.checkBoxT = checkBoxT;
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
    public CheckBoxT getControl() {
        return this.checkBoxT;
    }

    @Override
    public Boolean getValue() {
        return this.checkBox.isSelected();
    }

    @Override
    public void setValue(Boolean value) {
        this.checkBox.setSelected(value);
        if (this.checkBoxT.getCheckedEnumRef() != null)
            setFieldValueToParameter(this.checkBox.isSelected() ? this.checkBoxT.getCheckedEnumRef() : this.checkBoxT.getUncheckedEnumRef(),
                    this.parameterT);

    }

    @Override
    public void makeVisible(boolean visible) {
        this.checkBox.setVisible(visible);
    }

    @Override
    public void makeEnable(boolean enable) {
        this.checkBox.setDisable(!enable);
    }
}