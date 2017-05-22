package com.three60t.fixatdl.ui.fx8;

import java.time.ZonedDateTime;

import com.three60t.fixatdl.controlflow.FixAtdlControlFlowRegister;
import com.three60t.fixatdl.ui.common.UiElementAbstractFactory;
import com.three60t.fixatdl.ui.common.element.*;
import com.three60t.fixatdl.ui.fx8.element.*;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;

public class FxUiElementFactory implements UiElementAbstractFactory {

    private FxUiElementFactory() {
    }

    private static FxUiElementFactory _singleton;

    public synchronized static FxUiElementFactory getInstance() {
        return _singleton == null ? _singleton = new FxUiElementFactory() : _singleton;
    }

    @Override
    public FixCheckBoxListUiElement<Pane, String> instantiateNewCheckBoxList() {
        return new FxFixCheckBoxListUiElement();
    }

    @Override
    public FixDropDownListUiElement<Pane, String> instantiateNewDropDownList() {
        return new FxFixDropDownListUiElement();
    }

    @Override
    public FixHiddenFieldUiElement instantiateNewHiddenField() {
        // TODO it would be used for default field
        return null;
    }

    @Override
    public FixLabelUiElement<Label, String> instantiateNewLabel() {
        return new FxFixLabelUiElement();
    }

    @Override
    public FixPanelUiElement<Node, String> instantiateNewPanel() {
        return new FxFixPanelUiElement(FixAtdlControlFlowRegister.getSingleTon());
    }

    @Override
    public FixRadioButtonUiElement<RadioButton, String> instantiateNewRadioButton() {
        return new FxFixRadioButtonUiElement();
    }

    @Override
    public FixSliderUiElement<Pane, String> instantiateNewSlider() {
        return new FxFixSliderUiElement();
    }

    @Override
    public FixSingleSpinnerUiElement<Pane, Double> instantiateNewSingleSpinner() {
        return new FxFixSingleSpinnerUiElement();
    }

    @Override
    public FixCheckBoxUiElement<CheckBox, String> instantiateNewCheckBox() {
        return new FxFixCheckBoxUiElement();
    }

    @Override
    public FixClockUiElement<Pane, ZonedDateTime> instantiateNewClock() {
        return new FxFixClockUiElement();
    }

    @Override
    public FixDoubleSpinnerUiElement<Pane, Double> instantiateNewDoubleSpinner() {
        return new FxFixDoubleSpinnerUiElement();
    }

    @Override
    public FixEditableDropDownListUiElement<Pane, String> instantiateNewEditableDropDownList() {
        return new FxFixEditableDropDownListUiElement();
    }

    @Override
    public FixMultiSelectListUiElement<Pane, String> instantiateNewMultiSelectList() {
        return new FxFixMultiSelectListUiElement();
    }

    @Override
    public FixRadioButtonListUiElement<Pane, String> instantiateNewRadioButtonList() {
        return new FxFixRadioButtonListUiElement();
    }

    @Override
    public FixSingleSelectListUiElement<Pane, String> instantiateNewSingleSelectList() {
        return new FxFixSingleSelectListUiElement();
    }

    @Override
    public FixTextFieldUiElement<Pane, String> instantiateNewTextField() {
        return new FxFixTextFieldUiElement();
    }

    @Override
    public FixLayoutUiElement<Node, String> instantiateNewLayout() {
        return new FxFixLayoutUiElement();
    }
}
