package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.converter.TypeConverter;
import com.three60t.fixatdl.converter.TypeConverterRepo;
import com.three60t.fixatdl.model.core.EnumPairT;
import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.layout.CheckBoxT;
import com.three60t.fixatdl.ui.common.element.FixCheckBoxUiElement;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.CheckBox;

import java.util.Collections;
import java.util.List;

public class FxFixCheckBoxUiElement implements FixCheckBoxUiElement<CheckBox, Boolean> {

    private CheckBoxT checkBoxT;
    private CheckBox checkBox;
    private ParameterT parameterT;

    private ObjectProperty<String> controlIdEmitter = new SimpleObjectProperty<>();

    private TypeConverter<?, ?> controlTTypeConverter;

    @Override
    public CheckBox create() {
        if (this.checkBoxT != null) {
            this.checkBox = new CheckBox(this.checkBoxT.getLabel());

            this.controlTTypeConverter = TypeConverterRepo.createParameterTypeConverter(parameterT);

            if (this.checkBoxT.isInitValue() != null) {
                this.checkBox.setSelected(this.checkBoxT.isInitValue());
                setValue(getValue());
            }

            this.checkBox.setOnAction(event -> {
                this.controlIdEmitter.setValue(this.checkBoxT.getID() + ":" + getValue());
                setFieldValueToParameter(getCheckBoxParamValue(checkBox.isSelected()),
                        this.parameterT);
            });

            return this.checkBox;
        }
        return null;
    }


    private String getCheckBoxParamValue(Boolean isSelected) {
        String toReturn = isSelected.toString();
        EnumPairT foundEnumPairT = parameterT.getEnumPair()
                .parallelStream()
                .filter(enumPairT -> enumPairT.getEnumID().equals(isSelected.toString()))
                .findFirst().get();


        if (foundEnumPairT != null) {
            toReturn = foundEnumPairT.getWireValue();
        } else if (this.checkBoxT.getCheckedEnumRef() != null && this.checkBoxT.getUncheckedEnumRef() != null) {
            toReturn = isSelected ? this.checkBoxT.getCheckedEnumRef() : this.checkBoxT.getUncheckedEnumRef();
        }
        return toReturn;
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
        if (this.checkBoxT.getCheckedEnumRef() != null
                && this.checkBoxT.getUncheckedEnumRef() != null)
            setFieldValueToParameter(getParameterValueFromWireValue(
                    this.checkBox.isSelected() ? this.checkBoxT.getCheckedEnumRef()
                            : this.checkBoxT.getUncheckedEnumRef()),
                    this.parameterT);
    }

    private String getParameterValueFromWireValue(String enumID) {
        return this.parameterT.getEnumPair()
                .stream()
                .filter(enumPairT -> enumPairT.getEnumID().equals(enumID))
                .findFirst().map(enumPairT -> enumPairT.getWireValue())
                .orElse(null);
    }

    @Override
    public void makeVisible(boolean visible) {
        this.checkBox.setVisible(visible);
    }

    @Override
    public void makeEnable(boolean enable) {
        this.checkBox.setDisable(!enable);
    }

    @Override
    public TypeConverter<?, ?> getControlTTypeConverter() {
        return this.controlTTypeConverter;
    }
}
