package com.three60t.fixatdl.controlflow;

import com.three60t.fixatdl.evaluator.FixAtdlEditEvaluator;
import com.three60t.fixatdl.evaluator.RecursiveFixAtdlEditEvaluator;
import com.three60t.fixatdl.model.flow.StateRuleT;
import com.three60t.fixatdl.ui.common.element.FixUiElement;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FixAtdlStateRuleEvaluatorImpl implements FixAtdlStateRuleEvaluator {

    private FixAtdlEditEvaluator fixAtdlEditEvaluator;
    private ControlIdToValueCachedMap fieldToComparableMapperCache;

    public FixAtdlStateRuleEvaluatorImpl(Map<String, FixUiElement<?, ?>> allIFixUiElement) {
        this.fieldToComparableMapperCache = new ControlIdToValueCachedMap(allIFixUiElement);
        this.fixAtdlEditEvaluator = new RecursiveFixAtdlEditEvaluator(this.fieldToComparableMapperCache);
    }

    @Override
    public List<Pair<FixAtdlStateRuleResultType, Comparable>> getResult(StateRuleT stateRuleT) {
        boolean validationResult = fixAtdlEditEvaluator.validate(stateRuleT.getEdit());
        List<Pair<FixAtdlStateRuleResultType, Comparable>> pairList = new ArrayList<>();
        if (stateRuleT.isEnabled() != null)
            pairList.add(new Pair<>(FixAtdlStateRuleResultType.ENABLE, validationResult ? stateRuleT.isEnabled() : !stateRuleT.isEnabled()));
        if (stateRuleT.isVisible() != null)
            pairList.add(new Pair<>(FixAtdlStateRuleResultType.VISIBLE, validationResult ? stateRuleT.isVisible() : !stateRuleT.isVisible()));
        if (stateRuleT.getValue() != null && validationResult)
            pairList.add(new Pair<>(FixAtdlStateRuleResultType.VALUE, stateRuleT.getValue()));
        return pairList;
    }

}
