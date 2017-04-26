package com.three60t.ui.fx8.controlflow;

import com.three60t.fixatdl.layout.ControlT;
import com.three60t.fixatdl.validation.EditT;
import com.three60t.ui.common.element.FixUiElement;
import com.three60t.ui.controlflow.FixAtdlControlFlowRegister;
import com.three60t.ui.controlflow.FixAtdlStateRuleEvaluator;
import com.three60t.ui.controlflow.FixAtdlStateRuleResultActor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FxFixAtdlControlFlowRegisterSingleton implements FixAtdlControlFlowRegister {

    // controlId and Fx Ui element which are dependent on that Id for control flow
    private Map<String, Set<FixUiElement>> controlIdUiElementMap;

    // evaluate the condition of state rule
    private FixAtdlStateRuleEvaluator fixAtdlStateRuleEvaluator;

    // act of result of fixAtdlStateRuleEvaluator
    private FixAtdlStateRuleResultActor fxFixAtdlStateRuleResultActor;

    //
    private Map<String, FixUiElement> allIFixUiElements = new HashMap<>();

    private static FxFixAtdlControlFlowRegisterSingleton _singleton;

    public static synchronized FxFixAtdlControlFlowRegisterSingleton getSingleTon() {
        return _singleton == null ? _singleton = new FxFixAtdlControlFlowRegisterSingleton() : _singleton;
    }

    private FxFixAtdlControlFlowRegisterSingleton() {
        controlIdUiElementMap = new HashMap<>();
        fixAtdlStateRuleEvaluator = new FxFixAtdlStateRuleEvaluator(this.allIFixUiElements);
        fxFixAtdlStateRuleResultActor = new FxFixAtdlStateRuleResultActor();
    }

    @Override
    public void registerControlFlow(FixUiElement<?, ? extends Comparable<?>> fixUiElement) {
        allIFixUiElements.put(fixUiElement.getControl().getID(), fixUiElement);
        // get control from fixUiElement
        ControlT iFixUiElementControlT = fixUiElement.getControl();
        // for each StateRule map all control Id this UiElement depends on
        iFixUiElementControlT.getStateRule().forEach(stateRuleT -> getAllControlIdFromEdit(stateRuleT.getEdit()).forEach(controlId -> {
            Set<FixUiElement> fixUiElements = controlIdUiElementMap.getOrDefault(controlId, new HashSet<>());
            fixUiElements.add(fixUiElement);
            controlIdUiElementMap.put(controlId, fixUiElements);
        }));
        // listen to all
        //TODO change redundant split of string extend SingleObjectProperty and change it's behavior
        fixUiElement.listenChange().addListener((observable, oldValue, newValue) -> executeControlFLowByControlId(newValue.split(":")[0]));
    }

    private void executeControlFLowByControlId(String controlId) {
        controlIdUiElementMap.getOrDefault(controlId, new HashSet<>()).forEach(effectedIFixElement ->
                effectedIFixElement.getControl().getStateRule().forEach(stateRuleT -> fixAtdlStateRuleEvaluator
                        .getResult(stateRuleT).forEach(atdlStateRuleResultTypeComparablePair ->
                                fxFixAtdlStateRuleResultActor.doAct(atdlStateRuleResultTypeComparablePair, effectedIFixElement))));
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
