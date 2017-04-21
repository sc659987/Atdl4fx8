package com.three360.ui.fx8.controlflow;

import com.three360.fixatdl.layout.ControlT;
import com.three360.fixatdl.validation.EditT;
import com.three360.ui.common.element.IFixUiElement;
import com.three360.ui.controlflow.AtdlStateRuleResultActor;
import com.three360.ui.controlflow.IAtdlControlFlowRegister;
import com.three360.ui.controlflow.IAtdlStateRuleEvaluator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/***
 *
 */
public class FxAtdlControlFlowRegisterSingleton implements IAtdlControlFlowRegister {

    // controlId and Fx Ui element which are dependent on that Id for control flow
    private Map<String, Set<IFixUiElement>> controlIdUiElementMap;

    // evaluate the condition of state rule
    private IAtdlStateRuleEvaluator iAtdlStateRuleEvaluator;

    // act of result of iAtdlStateRuleEvaluator
    private AtdlStateRuleResultActor fxAtdlStateRuleResultActor;

    //
    private Map<String, IFixUiElement> allIFixUiElements = new HashMap<>();

    private static FxAtdlControlFlowRegisterSingleton _singleton;

    public static synchronized FxAtdlControlFlowRegisterSingleton getSingleTon() {
        return _singleton == null ? _singleton = new FxAtdlControlFlowRegisterSingleton() : _singleton;
    }

    private FxAtdlControlFlowRegisterSingleton() {
        controlIdUiElementMap = new HashMap<>();
        iAtdlStateRuleEvaluator = new FxAtdlStateRuleEvaluator(this.allIFixUiElements);
        fxAtdlStateRuleResultActor = new FxAtdlStateRuleResultActor();
    }

    @Override
    public void registerControlFlow(IFixUiElement<?, ? extends Comparable<?>> iFixUiElement) {
        allIFixUiElements.put(iFixUiElement.getControl().getID(), iFixUiElement);
        // get control from iFixUiElement
        ControlT iFixUiElementControlT = iFixUiElement.getControl();
        // for each StateRule map all control Id this UiElement depends on
        iFixUiElementControlT.getStateRule().forEach(stateRuleT -> getAllControlIdFromEdit(stateRuleT.getEdit()).forEach(controlId -> {
            Set<IFixUiElement> iFixUiElements = controlIdUiElementMap.getOrDefault(controlId, new HashSet<>());
            iFixUiElements.add(iFixUiElement);
            controlIdUiElementMap.put(controlId, iFixUiElements);
        }));
        // listen to all
        //TODO change redundant split of string extend SingleObjectProperty
        iFixUiElement.listenChange().addListener((observable, oldValue, newValue) -> executeControlFLowByControlId(newValue.split(":")[0]));
    }

    private void executeControlFLowByControlId(String controlId) {
        controlIdUiElementMap.getOrDefault(controlId, new HashSet<>()).forEach(effectedIFixElement ->
                effectedIFixElement.getControl().getStateRule().forEach(stateRuleT -> iAtdlStateRuleEvaluator
                        .getResult(stateRuleT).forEach(atdlStateRuleResultTypeComparablePair ->
                                fxAtdlStateRuleResultActor.doAct(atdlStateRuleResultTypeComparablePair, effectedIFixElement))
                ));
    }

    @Override
    public void executeControlFlowForAllControls() {
        controlIdUiElementMap.keySet().stream().forEach(this::executeControlFLowByControlId);
    }


    private Set<String> getAllControlIdFromEdit(EditT editT) {
        if (editT.getLogicOperator() != null) {
            return editT.getEdit().stream().map(editT1 -> getAllControlIdFromEdit(editT1)).flatMap(strings -> strings.stream()).collect(Collectors.toSet());
        } else {
            Set<String> stringList = new HashSet<>();
            if (editT.getField() != null)
                stringList.add(editT.getField());
            if (editT.getField2() != null)
                stringList.add(editT.getField2());
            return stringList;
        }
    }

}
