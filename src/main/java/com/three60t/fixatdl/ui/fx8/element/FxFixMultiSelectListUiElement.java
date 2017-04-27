package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.layout.ListItemT;
import com.three60t.fixatdl.model.layout.MultiSelectListT;
import com.three60t.fixatdl.ui.common.element.FixMultiSelectListUiElement;
import com.three60t.fixatdl.ui.fx8.FxUtils;
import com.three60t.fixatdl.utils.Utils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FxFixMultiSelectListUiElement implements FixMultiSelectListUiElement<Pane, String> {

    private ListView<ListItemT> multiSelectListView;
    private MultiSelectListT multiSelectListT;
    private ParameterT parameterT;
    private int nextRow = 0;
    private ObjectProperty<String> controlIdEmitter = new SimpleObjectProperty<>();

    private GridPane gridPane;

    @Override
    public Pane create() {
        if (this.multiSelectListT != null) {
            this.gridPane = new GridPane();
            if (!Utils.isEmpty(this.multiSelectListT.getLabel())) {
                this.gridPane.getColumnConstraints().addAll(FxUtils.getOneColumnWidthForGridPane());
                this.gridPane.add(new Label(this.multiSelectListT.getLabel()), 0,
                        this.nextRow++);
            }

            this.multiSelectListView = new ListView<>(FXCollections.observableArrayList(
                    this.multiSelectListT.getListItem()));

            this.multiSelectListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            if (Utils.isNonEmpty(multiSelectListT.getInitValue()))
                setValue(multiSelectListT.getInitValue());

            this.multiSelectListView.setOnMouseClicked(event -> {
                // setFieldValueToParameter(getValue(), parameterT);
                setValue(getValue());
                this.controlIdEmitter.set(this.multiSelectListT.getID() + ":" + getValue());

            });

            this.gridPane.add(this.multiSelectListView, 0, this.nextRow);

            return this.gridPane;
        }
        return null;
    }

    @Override
    public void setMultiSelectList(MultiSelectListT multiSelectListT) {
        this.multiSelectListT = multiSelectListT;
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
    public MultiSelectListT getControl() {
        return this.multiSelectListT;
    }

    @Override
    public String getValue() {
        return String.join(" ", this.multiSelectListView
                .getSelectionModel()
                .getSelectedItems()
                .stream()
                .map(listItemT -> listItemT.getEnumID())
                .collect(Collectors.toList()));
    }

    @Override
    public void setValue(String s) {
        IntStream.range(0, multiSelectListT.getListItem().size())
                .filter(operand -> s.contains(multiSelectListT.getListItem().get(operand).getEnumID()))
                .forEach(value -> {
                    multiSelectListView.getSelectionModel().select(value);
                });
        setFieldValueToParameter(getValue(), parameterT);
    }

    @Override
    public void makeVisible(boolean visible) {
        this.multiSelectListView.setVisible(visible);
    }

    @Override
    public void makeEnable(boolean enable) {
        this.multiSelectListView.setDisable(!enable);
    }
}
