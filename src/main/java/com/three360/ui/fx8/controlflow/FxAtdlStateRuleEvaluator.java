package com.three360.ui.fx8.controlflow;

import com.three360.fixatdl.flow.StateRuleT;
import com.three360.ui.common.element.IFixUiElement;
import com.three360.ui.controlflow.AtdlStateRuleResultType;
import com.three360.ui.controlflow.IAtdlStateRuleEvaluator;
import com.three360.ui.fx8.validator.RecursiveAtdlEditEvaluatorImpl;
import com.three360.ui.validator.AtdlEditEvaluator;
import com.three360.ui.validator.FieldToComparableMapperCache;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 *
 */
public class FxAtdlStateRuleEvaluator implements IAtdlStateRuleEvaluator {

    private AtdlEditEvaluator atdlEditEvaluator;
    private FieldToComparableMapperCache fieldToComparableMapperCache;

    public FxAtdlStateRuleEvaluator(Map<String, IFixUiElement> allIFixUiElement) {
        this.fieldToComparableMapperCache = new FieldToComparableMapperControlCache(allIFixUiElement);
        this.atdlEditEvaluator = new RecursiveAtdlEditEvaluatorImpl(fieldToComparableMapperCache);
    }

    @Override
    public List<Pair<AtdlStateRuleResultType, Comparable>> getResult(StateRuleT stateRuleT) {
        boolean validationResult = atdlEditEvaluator.validate(stateRuleT.getEdit());
        List<Pair<AtdlStateRuleResultType, Comparable>> pairList = new ArrayList<>();
        if (stateRuleT.isEnabled() != null)
            pairList.add(new Pair<>(AtdlStateRuleResultType.ENABLE, validationResult ? stateRuleT.isEnabled() : !stateRuleT.isEnabled()));
        if (stateRuleT.isVisible() != null)
            pairList.add(new Pair<>(AtdlStateRuleResultType.VISIBLE, validationResult ? stateRuleT.isVisible() : !stateRuleT.isVisible()));
        if (stateRuleT.getValue() != null)
            pairList.add(new Pair<>(AtdlStateRuleResultType.VALUE, stateRuleT.getValue()));
        return pairList;
    }
}
