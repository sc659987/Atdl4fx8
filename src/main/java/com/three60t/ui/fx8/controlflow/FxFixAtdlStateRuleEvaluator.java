package com.three60t.ui.fx8.controlflow;

import com.three60t.fixatdl.flow.StateRuleT;
import com.three60t.ui.common.element.FixUiElement;
import com.three60t.ui.controlflow.FixAtdlStateRuleEvaluator;
import com.three60t.ui.controlflow.FixAtdlStateRuleResultType;
import com.three60t.ui.evaluator.FixEditEvaluator;
import com.three60t.ui.evaluator.RecursiveFixEditEvaluator;
import com.three60t.ui.validator.FieldToComparableMapperCache;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 *
 */
public class FxFixAtdlStateRuleEvaluator implements FixAtdlStateRuleEvaluator {

    private FixEditEvaluator FixEditEvaluator;
    private FieldToComparableMapperCache fieldToComparableMapperCache;

    public FxFixAtdlStateRuleEvaluator(Map<String, FixUiElement> allIFixUiElement) {
        this.fieldToComparableMapperCache = new FieldToComparableMapperControlCache(allIFixUiElement);
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
