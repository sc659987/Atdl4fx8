package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.controlflow.FxFixAtdlControlFlowRegisterSingleton;
import com.three60t.fixatdl.converter.ControlTTypeConverter;
import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.layout.ControlT;
import com.three60t.fixatdl.model.layout.StrategyLayoutT;
import com.three60t.fixatdl.ui.common.UiElementAbstractFactory;
import com.three60t.fixatdl.ui.common.element.FixLayoutUiElement;
import com.three60t.fixatdl.ui.common.element.FixPanelUiElement;
import com.three60t.fixatdl.ui.fx8.FxUiElementFactory;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.Collectors;

public class FxFixLayoutUiElement implements FixLayoutUiElement<Node, String> {

    private StrategyLayoutT strategyLayoutT;

    private UiElementAbstractFactory factory = FxUiElementFactory.getInstance();

    private List<ParameterT> allParameterTList;

    private ScrollPane scrollPane = new ScrollPane();

    public FxFixLayoutUiElement() {
    }

    @Override
    public Node create() {
        VBox scrollPaneWrapper = new VBox();
        scrollPaneWrapper.getChildren().add(scrollPane);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(this.strategyLayoutT.getStrategyPanel().stream().map(strategyPanelT -> {
            FixPanelUiElement<Node, String> element = factory.instantiateNewPanel();
            element.setStrategyPanelT(strategyPanelT);
            element.setParameters(this.allParameterTList);
            return element.create();
        }).collect(Collectors.toList()));
        FxFixAtdlControlFlowRegisterSingleton.getSingleTon().executeControlFlowForAllControls();
        scrollPane.setContent(vBox);
        return scrollPane;
    }

    @Override
    public void setStrategyLayout(StrategyLayoutT strategyLayoutT) {
        this.strategyLayoutT = strategyLayoutT;
    }

    @Override
    public void setParameters(List<ParameterT> parameterTList) {
        this.allParameterTList = parameterTList;
    }

    @Override
    public List<ParameterT> getParameters() {
        return this.allParameterTList;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public void setValue(String s) {

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
    public void makeVisible(boolean visible) {

    }

    @Override
    public void makeEnable(boolean enable) {

    }

    @Override
    public ControlTTypeConverter<?> getControlTTypeConverter() {
        return null;
    }
}
