package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.converter.TypeConverter;
import com.three60t.fixatdl.converter.TypeConverterRepo;
import com.three60t.fixatdl.model.core.EnumPairT;
import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.layout.RadioButtonT;
import com.three60t.fixatdl.ui.common.element.FixRadioButtonUiElement;
import com.three60t.fixatdl.utils.Utils;
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

    private TypeConverter<?, ?> controlTTypeConverter;

    @Override
    public RadioButton create() {
        if (this.radioButtonT != null) {
            this.radioButton = new RadioButton(this.radioButtonT.getLabel());

            this.controlTTypeConverter = TypeConverterRepo.createParameterTypeConverter(parameterT);

            this.radioButton.setId(this.radioButtonT.getID());
            if (!Utils.isEmptyString(this.radioButtonT.getRadioGroup())) {
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

            initializeControl();
            setFieldValueToParameter(getRadioButtonParamValue(radioButtonT.isInitValue() == null ? false : radioButtonT.isInitValue()), parameterT);

            this.radioButton.setOnAction(event -> {
                setFieldValueToParameter(getRadioButtonParamValue(isSelectedFromControlValue(getValue())), this.parameterT);
                // publish GUI change mainly for control flow
                controlIdEmitter.setValue(radioButtonT.getID() + ":" + getValue());
            });
            return this.radioButton;
        }
        return null;
    }

    private String getRadioButtonParamValue(final Boolean isSelected) {
        if (this.radioButtonT.getCheckedEnumRef() != null && this.radioButtonT.getUncheckedEnumRef() != null) {
            String enumId = isSelected ? this.radioButtonT.getCheckedEnumRef() : this.radioButtonT.getUncheckedEnumRef();
            return this.parameterT.getEnumPair()
                    .stream()
                    .filter(enumPairT -> enumPairT.getEnumID().equals(enumId))
                    .findFirst()
                    .orElseGet(() -> {
                        EnumPairT enumPairT = new EnumPairT();
                        enumPairT.setWireValue(isSelected.toString());
                        return enumPairT;
                    }).getWireValue();
        }
        return isSelected.toString();
    }

    private String getRadioButtonControlValue(Boolean isSelected) {
        if (this.radioButtonT.getCheckedEnumRef() != null
                && this.radioButtonT.getUncheckedEnumRef() != null) {
            return isSelected ? this.radioButtonT.getCheckedEnumRef() : this.radioButtonT.getUncheckedEnumRef();
        }
        return isSelected.toString();
    }

    private boolean isSelectedFromControlValue(String string) {
        if (this.radioButtonT.getCheckedEnumRef() != null
                && this.radioButtonT.getUncheckedEnumRef() != null) {
            return string.equals(this.radioButtonT.getCheckedEnumRef());
        }
        return Boolean.parseBoolean(string);
    }


    @Override
    public void initializeControl() {
        if (this.radioButtonT.isInitValue() != null)
            this.radioButton.setSelected(this.radioButtonT.isInitValue());

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
        return getRadioButtonControlValue(this.radioButton.isSelected());
    }

    @Override
    public void setValue(String isSelected) {
        this.radioButton.setSelected(isSelectedFromControlValue(isSelected));
        setFieldValueToParameter(getRadioButtonParamValue(isSelectedFromControlValue(isSelected)), this.parameterT);
    }

    @Override
    public void makeVisible(boolean visible) {
        radioButton.setVisible(visible);
    }

    @Override
    public void makeEnable(boolean enable) {
        radioButton.setDisable(!enable);
        if(enable)
            initializeControl();
    }

    @Override
    public TypeConverter<?, ?> getControlTTypeConverter() {
        return this.controlTTypeConverter;
    }
}
