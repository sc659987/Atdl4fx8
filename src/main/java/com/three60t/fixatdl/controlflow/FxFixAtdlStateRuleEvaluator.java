package com.three60t.fixatdl.controlflow;

import com.three60t.fixatdl.evaluator.RecursiveFixEditEvaluator;
import com.three60t.fixatdl.model.flow.StateRuleT;
import com.three60t.fixatdl.ui.common.element.FixUiElement;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 *
 */
public class FxFixAtdlStateRuleEvaluator implements FixAtdlStateRuleEvaluator {

    private com.three60t.fixatdl.evaluator.FixEditEvaluator FixEditEvaluator;
    private ControlIdToValueCachedMap fieldToComparableMapperCache;

    public FxFixAtdlStateRuleEvaluator(Map<String, FixUiElement<?,?>> allIFixUiElement) {
        this.fieldToComparableMapperCache = new ControlIdToValueCachedMap(allIFixUiElement);
        this.FixEditEvaluator = new RecursiveFixEditEvaluator(fieldToComparableMapperCache);
    }

    @Override
    public List<Pair<FixAtdlStateRuleResultType, Comparable>> getResult(StateRuleT stateRuleT) {
        boolean validationResult = FixEditEvaluator.validate(stateRuleT.getEdit());
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
