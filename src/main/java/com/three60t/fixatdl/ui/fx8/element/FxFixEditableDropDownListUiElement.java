package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.layout.EditableDropDownListT;
import com.three60t.fixatdl.model.layout.ListItemT;
import com.three60t.fixatdl.ui.common.element.FixEditableDropDownListUiElement;
import com.three60t.fixatdl.utils.Utils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Collections;
import java.util.List;

// todo check if editable case is working fine up to wire value
public class FxFixEditableDropDownListUiElement
        implements FixEditableDropDownListUiElement<Pane, String> {

    private ComboBox<ListItemT> editableComboBox;
    private EditableDropDownListT editableDropDownListT;
    private GridPane gridPane;

    private int nextColumn = 0;
    private ParameterT parameterT;

    private ObjectProperty<String> checkedProperty = new SimpleObjectProperty<>();

    @Override
    public Pane create() {
        if (this.editableDropDownListT != null) {
            this.gridPane = new GridPane();
            if (Utils.isNonEmpty(this.editableDropDownListT.getLabel()))
                this.gridPane.add(new Label(this.editableDropDownListT.getLabel()),
                        this.nextColumn++, 0);
            this.gridPane.setHgap(3);
            this.editableComboBox = new ComboBox<>();
            this.editableComboBox.getItems()
                    .addAll(this.editableDropDownListT
                            .getListItem());

            this.editableComboBox.setEditable(true);

            if (Utils.isNonEmpty(this.editableDropDownListT.getInitValue()))
                setValue(this.editableDropDownListT.getInitValue());

            this.editableComboBox.setOnAction(event -> {
                this.checkedProperty.set(editableDropDownListT.getID() + ":" + getValue());

                if (this.parameterT != null)
                    setFieldValueToParameter(editableComboBox.getValue(), this.parameterT);
            });

            this.gridPane.add(this.editableComboBox, nextColumn, 0);
            return this.gridPane;
        }
        return null;
    }

    @Override
    public void setEditableDropDownList(EditableDropDownListT editableDropDownListT) {
        this.editableDropDownListT = editableDropDownListT;
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
        return this.checkedProperty;
    }

    @Override
    public EditableDropDownListT getControl() {
        return this.editableDropDownListT;
    }

    // TODO test with example and debug
    @Override
    public String getValue() {
        return this.editableComboBox.getValue().getEnumID();
    }

    @Override
    public void setValue(String enumId) {
        this.editableDropDownListT
                .getListItem()
                .stream()
                .filter(listItemT -> listItemT.getEnumID().equals(enumId))
                .findFirst()
                .ifPresent(listItemT -> {
                    editableComboBox.setValue(listItemT);

                });
        setFieldValueToParameter(getValue(), this.parameterT);
    }

    @Override
    public void makeVisible(boolean visible) {
        editableComboBox.setVisible(visible);
    }

    @Override
    public void makeEnable(boolean enable) {
        this.editableComboBox.setDisable(!enable);
    }

}