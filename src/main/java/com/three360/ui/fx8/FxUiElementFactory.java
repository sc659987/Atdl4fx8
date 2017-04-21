package com.three360.ui.fx8;

import com.three360.ui.common.UiElementAbstractFactory;
import com.three360.ui.common.element.*;
import com.three360.ui.fx8.controlflow.FxAtdlControlFlowRegisterSingleton;
import com.three360.ui.fx8.element.*;
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
    public IFixCheckBoxListUiElement<Pane, String> instantiateNewCheckBoxList() {
        return new FxFixCheckBoxListUiElement();
    }

    @Override
    public IFixDropDownListUiElement<Pane, String> instantiateNewDropDownList() {
        return new FxFixDropDownListUiElement();
    }

    @Override
    public IFixHiddenFieldUiElement instantiateNewHiddenField() {
        // TODO registerControlFlow it
        return null;
    }

    @Override
    public IFixLabelUiElement<Label, String> instantiateNewLabel() {
        return new FxFixLabelUiElement();
    }

    @Override
    public IFixPanelUiElement<Node, String> instantiateNewPanel() {
        return new FxFixPanelUiElement(FxAtdlControlFlowRegisterSingleton.getSingleTon());
    }

    @Override
    public IFixRadioButtonUiElement<RadioButton, String> instantiateNewRadioButton() {
        return new FxFixRadioButtonUiElement();
    }

    @Override
    public IFixSliderUiElement<Pane, String> instantiateNewSlider() {
        return new FxFixSliderUiElement();
    }

    @Override
    public IFixSingleSpinnerUiElement<Pane, Double> instantiateNewSingleSpinner() {
        return new FxFixSingleSpinnerUiElement();
    }

    @Override
    public IFixCheckBoxUiElement<CheckBox, String> instantiateNewCheckBox() {
        return new FxFixCheckBoxUiElement();
    }

    @Override
    public IFixClockUiElement<Pane, String> instantiateNewClock() {
        return new FxFixClockUiElement();
    }

    @Override
    public IFixDoubleSpinnerUiElement<Pane, String> instantiateNewDoubleSpinner() {
        return new FxFixDoubleSpinnerUiElement();
    }

    @Override
    public IFixEditableDropDownListUiElement<Pane, String> instantiateNewEditableDropDownList() {
        return new FxFixEditableDropDownListUiElement();
    }

    @Override
    public IFixMultiSelectListUiElement<Pane, String> instantiateNewMultiSelectList() {
        return new FxFixMultiSelectListUiElement();
    }

    @Override
    public IFixRadioButtonListUiElement<Pane, String> instantiateNewRadioButtonList() {
        return new FxFixRadioButtonListUiElement();
    }

    @Override
    public IFixSingleSelectListUiElement<Pane, String> instantiateNewSingleSelectList() {
        return new FxFixSingleSelectListUiElement();
    }

    @Override
    public IFixTextFieldUiElement<Pane, String> instantiateNewTextField() {
        return new FxFixTextFieldUiElement();
    }

    @Override
    public IFixLayoutUiElement<Node, String> instantiateNewLayout() {
        return new FxFixLayoutUiElement();
    }
}
