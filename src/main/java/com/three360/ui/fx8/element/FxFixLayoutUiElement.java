package com.three360.ui.fx8.element;

import com.three360.fixatdl.core.ParameterT;
import com.three360.fixatdl.layout.ControlT;
import com.three360.fixatdl.layout.StrategyLayoutT;
import com.three360.ui.common.UiElementAbstractFactory;
import com.three360.ui.common.element.IFixLayoutUiElement;
import com.three360.ui.common.element.IFixPanelUiElement;
import com.three360.ui.fx8.FxUiElementFactory;
import com.three360.ui.fx8.controlflow.FxAtdlControlFlowRegisterSingleton;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.Collectors;

public class FxFixLayoutUiElement implements IFixLayoutUiElement<Node, String> {

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
            IFixPanelUiElement<Node, String> element = factory.instantiateNewPanel();
            element.setStrategyPanelT(strategyPanelT);
            element.setParameters(this.allParameterTList);
            return element.create();
        }).collect(Collectors.toList()));
        FxAtdlControlFlowRegisterSingleton.getSingleTon().executeControlFlowForAllControls();
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
    public List<ParameterT> getParameter() {
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
}