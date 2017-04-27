package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.layout.DropDownListT;
import com.three60t.fixatdl.model.layout.ListItemT;
import com.three60t.fixatdl.ui.common.element.FixDropDownListUiElement;
import com.three60t.fixatdl.utils.Utils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Collections;
import java.util.List;

public class FxFixDropDownListUiElement implements FixDropDownListUiElement<Pane, String> {

    private DropDownListT dropDownListT;
    private ComboBox<ListItemT> comboBox = new ComboBox<>();
    private GridPane gridPane;
    private int nextColumn = 0;
    private ParameterT parameterT;

    private ObjectProperty<String> controlIdEmitter = new SimpleObjectProperty<>();

    @Override
    public void setDropDownList(DropDownListT downList) {
        this.dropDownListT = downList;
    }

    @Override
    public Pane create() {
        if (this.dropDownListT != null) {

            this.gridPane = new GridPane();

            if (!Utils.isEmpty(this.dropDownListT.getLabel())) {
                this.gridPane.add(new Label(this.dropDownListT.getLabel()), this.nextColumn++, 0);
            }
            this.gridPane.setHgap(3);
            this.comboBox.getItems().addAll(this.dropDownListT.getListItem());

            if (Utils.isEmpty(this.dropDownListT.getInitValue()))
                this.comboBox.getSelectionModel().selectFirst();
            else
                setValue(this.dropDownListT.getInitValue());

            this.comboBox.setOnAction(event -> {
                setFieldValueToParameter(getValue(), this.parameterT);
                this.controlIdEmitter.setValue(dropDownListT.getID() + ":" + comboBox.getValue());
            });

            this.gridPane.add(this.comboBox, this.nextColumn, 0);

            return this.gridPane;
        }
        return null;
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
    public DropDownListT getControl() {
        return this.dropDownListT;
    }

    @Override
    public String getValue() {
        return this.comboBox.getValue().getEnumID();
    }

    @Override
    public void setValue(String enumId) {
        dropDownListT.getListItem().stream()
                .filter(listItemT -> listItemT.getEnumID().equals(enumId))
                .findFirst()
                .ifPresent(listItemT -> {
                    comboBox.setValue(listItemT);
                    setFieldValueToParameter(getValue(), this.parameterT);
                });
    }

    @Override
    public void makeVisible(boolean visible) {
        this.comboBox.setVisible(visible);
    }

    @Override
    public void makeEnable(boolean enable) {
        this.comboBox.setDisable(!enable);
    }
}
