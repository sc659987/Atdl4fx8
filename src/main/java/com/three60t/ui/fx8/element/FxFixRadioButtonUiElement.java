package com.three60t.ui.fx8.element;

import com.three60t.fixatdl.core.ParameterT;
import com.three60t.fixatdl.layout.RadioButtonT;
import com.three60t.ui.Utils;
import com.three60t.ui.common.element.FixRadioButtonUiElement;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FxFixRadioButtonUiElement implements FixRadioButtonUiElement<RadioButton, String> {

    private RadioButtonT radioButtonT;
    private RadioButton radioButton;
    private ParameterT parameterT;
    private static final Map<String, ToggleGroup> TOGGLE_GROUPS = new HashMap<>();
    private ObjectProperty<String> controlIdEmitter = new SimpleObjectProperty<>();

    @Override
    public RadioButton create() {
        if (this.radioButtonT != null) {
            this.radioButton = new RadioButton(this.radioButtonT.getLabel());
            this.radioButton.setId(this.radioButtonT.getID());
            if (!Utils.isEmpty(this.radioButtonT.getRadioGroup())) {
                ToggleGroup toggleGroup = TOGGLE_GROUPS.get(this.radioButtonT.getRadioGroup());
                if (toggleGroup == null) {
                    TOGGLE_GROUPS.put(this.radioButtonT.getRadioGroup(), toggleGroup = new ToggleGroup());
                    toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                        if (oldValue != null)
                            ((RadioButton) oldValue).onActionProperty().get().handle(null);
                    });
                }
                this.radioButton.setToggleGroup(toggleGroup);
            }

            if (this.radioButtonT.isInitValue() != null) {
                this.radioButton.setSelected(this.radioButtonT.isInitValue());
                setValue(getValue());
            }

            this.radioButton.setOnAction(event -> {
                setFieldValueToParameter(getValue(), this.parameterT);
                // publish GUI change mainly for control flow
                controlIdEmitter.setValue(radioButtonT.getID() + ":" + getValue());
            });
            return this.radioButton;
        }
        return null;
    }

    @Override
    public void setRadioButtonT(RadioButtonT radioButtonT) {
        this.radioButtonT = radioButtonT;
    }

    @Override
    public void setParameters(List<ParameterT> parameterTList) {
        if (parameterTList != null && parameterTList.size() > 0)
            this.parameterT = parameterTList.get(0);
    }

    @Override
    public List<ParameterT> getParameters() {
        return Collections.singletonList(this.parameterT);
    }

    @Override
    public ObjectProperty<String> listenChange() {
        return this.controlIdEmitter;
    }

    @Override
    public RadioButtonT getControl() {
        return this.radioButtonT;
    }

    @Override
    public String getValue() {
        return this.radioButton.isSelected() ? radioButtonT.getCheckedEnumRef() == null ? Boolean.TRUE.toString() : radioButtonT.getCheckedEnumRef()
                : radioButtonT.getUncheckedEnumRef() == null ? Boolean.FALSE.toString() : radioButtonT.getUncheckedEnumRef();
    }

    @Override
    public void setValue(String enumId) {
        //TODO wrong logic change it
        if (this.radioButtonT.getCheckedEnumRef() != null)
            this.radioButton.setSelected(this.radioButtonT.getCheckedEnumRef().equals(enumId));
        setFieldValueToParameter(getValue(), this.parameterT);
    }

    @Override
    public void makeVisible(boolean visible) {
        radioButton.setVisible(visible);
    }

    @Override
    public void makeEnable(boolean enable) {
        radioButton.setDisable(!enable);
    }
}
