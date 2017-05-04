package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.converter.TypeConverter;
import com.three60t.fixatdl.converter.TypeConverterRepo;
import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.layout.LabelT;
import com.three60t.fixatdl.ui.common.element.FixLabelUiElement;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Label;

import java.util.Collections;
import java.util.List;

//TODO check with specification label text can be changed
public class FxFixLabelUiElement implements FixLabelUiElement<Label, String> {

    private Label label;
    private LabelT labelT;

    private ParameterT parameterT;

    private TypeConverter<?, ?> controlTTypeConverter;

    @Override
    public Label create() {
        if (this.labelT != null) {
            controlTTypeConverter = TypeConverterRepo.createParameterTypeConverter(parameterT);

            this.label = new Label();
            this.label.setText(this.labelT.getLabel());
            return this.label;
        }
        return null;
    }

    @Override
    public void initializeControl() {

    }

    @Override
    public void setLabel(LabelT label) {
        this.labelT = label;
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
        return null;
    }

    @Override
    public LabelT getControl() {
        return this.labelT;
    }

    @Override
    public String getValue() {
        return this.label.getText();
    }

    @Override
    public void setValue(String s) {
        this.label.setText(s);
    }

    @Override
    public void makeVisible(boolean visible) {
        this.label.setVisible(visible);
    }

    @Override
    public void makeEnable(boolean enable) {
        this.label.setDisable(!enable);
    }

    @Override
    public TypeConverter<?, ?> getControlTTypeConverter() {
        return this.controlTTypeConverter;
    }
}
