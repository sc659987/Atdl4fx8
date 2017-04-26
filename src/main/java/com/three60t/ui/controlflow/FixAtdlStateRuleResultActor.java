package com.three60t.ui.controlflow;

import com.three60t.ui.common.element.FixUiElement;
import javafx.util.Pair;

public interface FixAtdlStateRuleResultActor {

    /***
     *
     * @param stateRuleResultComparablePair
     * @param fixUiElement
     */
    void doAct(Pair<FixAtdlStateRuleResultType, Comparable> stateRuleResultComparablePair, FixUiElement fixUiElement);

}
