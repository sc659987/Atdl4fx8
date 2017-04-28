package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.layout.CheckBoxListT;
import com.three60t.fixatdl.model.layout.PanelOrientationT;
import com.three60t.fixatdl.ui.common.element.FixCheckBoxListUiElement;
import com.three60t.fixatdl.utils.Utils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FxFixCheckBoxListUiElement implements FixCheckBoxListUiElement<Pane, String> {

    private CheckBoxListT checkBoxListT;
    private GridPane gridPane;
    private List<CheckBox> checkBoxes;
    private Map<String, CheckBox> enumIdAndCheckBoxMap;
    private ParameterT parameterT;
    private int nextRow = 0;
    private ObjectProperty<String> controlIdEmitter = new SimpleObjectProperty<>();

    @Override
    public Pane create() {
        if (this.checkBoxListT != null) {
            this.gridPane = new GridPane();

            // GUI label addition to grid
            if (!Utils.isEmpty(this.checkBoxListT.getLabel()))
                this.gridPane.add(new Label(this.checkBoxListT.getLabel()),
                        0, this.nextRow++, 1, 1);

            this.gridPane.setHgap(3);
            // creating checkbox in fx from checkboxListT
            this.checkBoxes = this.checkBoxListT
                    .getListItem()
                    .parallelStream()
                    .map(listItemT -> {
                        CheckBox checkBox = new CheckBox();
                        checkBox.setId(listItemT.getEnumID());
                        checkBox.setText(listItemT.getUiRep());
                        return checkBox;
                    }).collect(Collectors.toList());

            // create a map
            enumIdAndCheckBoxMap = checkBoxes
                    .parallelStream()
                    .collect(Collectors.toMap(CheckBox::getId, checkBox -> checkBox));

            // Initialize the check box selected or not on the basis of
            this.checkBoxes.stream()
                    .filter(checkBox -> checkBox.getId().equals(
                            this.checkBoxListT.getInitValue()))
                    .collect(Collectors.toList())
                    .forEach(checkBox -> checkBox.setSelected(true));

            // put the value for first time
            setValue(getValue());

            this.gridPane.setHgap(2.0);
            this.gridPane.setVgap(2.0);

            // call back for all checkbox
            this.checkBoxes.forEach(checkBox -> checkBox.setOnAction(event -> {
                // set the property to notify changes
                controlIdEmitter.setValue(this.checkBoxListT.getID() + ":" + getValue());
                // set the parameter when ever value is changed
                setFieldValueToParameter(getValue(), parameterT);
            }));

            // GUI arrangement of checkboxes
            IntStream.range(0, this.checkBoxes.size()).forEach(index -> {
                if (this.checkBoxListT.getOrientation() == PanelOrientationT.HORIZONTAL) {
                    this.gridPane.add(this.checkBoxes.get(index), 3 * index, this.nextRow, 1, 1);
                } else {
                    this.gridPane.add(this.checkBoxes.get(index), 0, this.nextRow++, 1, 1);
                }
            });
            return this.gridPane;
        }
        return null;
    }

    @Override
    public ObjectProperty<String> listenChange() {
        return this.controlIdEmitter;
    }

    @Override
    public FxFixCheckBoxListUiElement setCheckBoxListT(CheckBoxListT checkBoxListT) {
        this.checkBoxListT = checkBoxListT;
        return this;
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
    public CheckBoxListT getControl() {
        return checkBoxListT;
    }

    @Override
    public String getValue() {
        return String.join(" ", checkBoxes.stream().filter(CheckBox::isSelected)
                .map(CheckBox::getId)
                .collect(Collectors.toList()));
    }

    @Override
    public void setValue(String strings) {
        Arrays.asList(strings.split(" ")).forEach(s -> {
            enumIdAndCheckBoxMap.get(s).setSelected(true);
        });
        setFieldValueToParameter(getValue(), parameterT);
    }

    @Override
    public void makeVisible(boolean visible) {
        // TODO think to use gridPane to visible/invisible
        checkBoxes.forEach(checkBox -> {
            checkBox.setVisible(visible);
        });
    }

    @Override
    public void makeEnable(boolean enable) {
        // TODO think to use gridPane to enable/disable
        checkBoxes.forEach(checkBox -> {
            checkBox.setDisable(!enable);
        });
    }
}
