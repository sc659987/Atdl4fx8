package com.three60t.ui.controlflow;

import com.three60t.fixatdl.flow.StateRuleT;
import javafx.util.Pair;

import java.util.List;

/***
 *
 */
public interface FixAtdlStateRuleEvaluator {

    /***
     *
     * @param stateRuleT
     * @return
     */
    List<Pair<FixAtdlStateRuleResultType, Comparable>> getResult(StateRuleT stateRuleT);

}
