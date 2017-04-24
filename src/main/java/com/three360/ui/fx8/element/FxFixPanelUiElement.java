package com.three360.ui.fx8.element;

import com.three360.fixatdl.core.ParameterT;
import com.three360.fixatdl.layout.*;
import com.three360.ui.common.UiElementAbstractFactory;
import com.three360.ui.common.element.*;
import com.three360.ui.fx8.FxUiElementFactory;
import com.three360.ui.fx8.controlflow.FxAtdlControlFlowRegisterSingleton;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.Collectors;

public class FxFixPanelUiElement implements IFixPanelUiElement<Node, String> {

    private UiElementAbstractFactory factory = FxUiElementFactory.getInstance();

    private StrategyPanelT strategyPanelT;

    private List<Region> regionList;

    private List<ParameterT> parameterTList;

    private FxAtdlControlFlowRegisterSingleton fxAtdlControlFlowRegister;

    public FxFixPanelUiElement(FxAtdlControlFlowRegisterSingleton singleton) {
        this.fxAtdlControlFlowRegister = singleton;
    }

    @Override
    public Node create() {
        if (this.strategyPanelT != null) {
            TitledPane titledPane = new TitledPane();
            titledPane.setCollapsible(this.strategyPanelT.isCollapsible());
            titledPane.setExpanded(!this.strategyPanelT.isCollapsed());
            // titledPane.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            titledPane.setText(this.strategyPanelT.getTitle());
            Pane parent = (this.strategyPanelT.getOrientation() == PanelOrientationT.HORIZONTAL)
                    ? new HBox(20.0) : new VBox();
            // Container for either groups of parameters or strategyPanels, but not both.
            // I.e., a StrategyPanel will contain either all Control elements or all
            // StrategyPanel elements.
            if (this.strategyPanelT.getStrategyPanel() != null && this.strategyPanelT.getStrategyPanel().size() > 0) {
                parent.getChildren().addAll(this.strategyPanelT
                        .getStrategyPanel()
                        .stream()
                        .map(eachStrategyPanelT -> {
                            IFixPanelUiElement<Node, String> element = factory.instantiateNewPanel();
                            element.setStrategyPanelT(eachStrategyPanelT);
                            element.setParameters(this.parameterTList);
                            return element.create();
                        }).collect(Collectors.toList()));
            } else if (this.strategyPanelT.getControl() != null) {
                this.regionList = this.strategyPanelT.getControl().stream().map(controlT -> {
                    if (controlT instanceof CheckBoxListT) {
                        IFixCheckBoxListUiElement<Pane, String> element = factory.instantiateNewCheckBoxList();
                        element.setCheckBoxListT((CheckBoxListT) controlT);
                        element.setParameters(findParameterByName(controlT.getParameterRef()));
                        this.fxAtdlControlFlowRegister.registerControlFlow(element);
                        return element.create();
                    } else if (controlT instanceof CheckBoxT) {
                        IFixCheckBoxUiElement<CheckBox, String> element = factory.instantiateNewCheckBox();
                        element.setCheckBoxT((CheckBoxT) controlT);
                        element.setParameters(findParameterByName(controlT.getParameterRef()));
                        this.fxAtdlControlFlowRegister.registerControlFlow(element);
                        return element.create();
                    } else if (controlT instanceof ClockT) {
                        IFixClockUiElement<HBox, String> element = factory.instantiateNewClock();
                        element.setClockT((ClockT) controlT);
                        element.setParameters(findParameterByName(controlT.getParameterRef()));
                        this.fxAtdlControlFlowRegister.registerControlFlow(element);
                        return element.create();
                    } else if (controlT instanceof DoubleSpinnerT) {
                        // TODO complete the double spinner
                    } else if (controlT instanceof DropDownListT) {
                        IFixDropDownListUiElement<HBox, String> element = factory.instantiateNewDropDownList();
                        element.setDropDownList((DropDownListT) controlT);
                        element.setParameters(findParameterByName(controlT.getParameterRef()));
                        this.fxAtdlControlFlowRegister.registerControlFlow(element);
                        return element.create();
                    } else if (controlT instanceof EditableDropDownListT) {
                        IFixEditableDropDownListUiElement<HBox, String> element = factory.instantiateNewEditableDropDownList();
                        element.setEditableDropDownList((EditableDropDownListT) controlT);
                        element.setParameters(findParameterByName(controlT.getParameterRef()));
                        this.fxAtdlControlFlowRegister.registerControlFlow(element);
                        return element.create();
                    } else if (controlT instanceof LabelT) {
                        IFixLabelUiElement<Label, String> element = factory.instantiateNewLabel();
                        element.setLabel((LabelT) controlT);
                        element.setParameters(findParameterByName(controlT.getParameterRef()));
                        return element.create();
                    } else if (controlT instanceof MultiSelectListT) {
                        IFixMultiSelectListUiElement<Pane, String> element = factory.instantiateNewMultiSelectList();
                        element.setMultiSelectList((MultiSelectListT) controlT);
                        element.setParameters(findParameterByName(controlT.getParameterRef()));
                        this.fxAtdlControlFlowRegister.registerControlFlow(element);
                        return element.create();
                    } else if (controlT instanceof RadioButtonListT) {
                        IFixRadioButtonListUiElement<Pane, String> element = factory.instantiateNewRadioButtonList();
                        element.setRadioButtonListT((RadioButtonListT) controlT);
                        element.setParameters(findParameterByName(controlT.getParameterRef()));
                        this.fxAtdlControlFlowRegister.registerControlFlow(element);
                        return element.create();
                    } else if (controlT instanceof RadioButtonT) {
                        IFixRadioButtonUiElement<RadioButton, String> element = factory.instantiateNewRadioButton();
                        element.setRadioButtonT((RadioButtonT) controlT);
                        element.setParameters(findParameterByName(controlT.getParameterRef()));
                        this.fxAtdlControlFlowRegister.registerControlFlow(element);
                        return element.create();
                    } else if (controlT instanceof SingleSelectListT) {
                        IFixSingleSelectListUiElement<Pane, String> element = factory.instantiateNewSingleSelectList();
                        element.setSingleSelectList((SingleSelectListT) controlT);
                        element.setParameters(findParameterByName(controlT.getParameterRef()));
                        this.fxAtdlControlFlowRegister.registerControlFlow(element);
                        return element.create();
                    } else if (controlT instanceof SingleSpinnerT) {
                        IFixSingleSpinnerUiElement<Pane, String> element = factory.instantiateNewSingleSpinner();
                        element.setSingleSpinner((SingleSpinnerT) controlT);
                        element.setParameters(findParameterByName(controlT.getParameterRef()));
                        this.fxAtdlControlFlowRegister.registerControlFlow(element);
                        return element.create();
                    } else if (controlT instanceof SliderT) {
                        IFixSliderUiElement<Slider, String> element = factory.instantiateNewSlider();
                        element.setSlider((SliderT) controlT);
                        element.setParameters(findParameterByName(controlT.getParameterRef()));
                        this.fxAtdlControlFlowRegister.registerControlFlow(element);
                        return element.create();
                    } else if (controlT instanceof TextFieldT) {
                        IFixTextFieldUiElement<HBox, String> element = factory.instantiateNewTextField();
                        element.setTextField((TextFieldT) controlT);
                        if (controlT.getParameterRef() != null)
                            element.setParameters(findParameterByName(controlT.getParameterRef()));
                        this.fxAtdlControlFlowRegister.registerControlFlow(element);
                        return element.create();
                    } else if (controlT instanceof HiddenFieldT) {

                    }
                    throw new RuntimeException("control is not recognized");
                }).collect(Collectors.toList());
                parent.getChildren().setAll(regionList);
            }
            titledPane.setContent(parent);
            return titledPane;
        }
        return null;
    }

    @Override
    public void setStrategyPanelT(StrategyPanelT strategyPanelT) {
        this.strategyPanelT = strategyPanelT;
    }

    @Override
    public void setParameters(List<ParameterT> parameterTList) {
        this.parameterTList = parameterTList;
    }

    @Override
    public List<ParameterT> getParameters() {
        return this.parameterTList;
    }

    @Override
    public ObjectProperty<String> listenChange() {
        return null;
    }

    @Override
    public ControlT getControl() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public void setValue(String s) {

    }

    @Override
    public void makeVisible(boolean visible) {

    }

    @Override
    public void makeEnable(boolean enable) {

    }
}
