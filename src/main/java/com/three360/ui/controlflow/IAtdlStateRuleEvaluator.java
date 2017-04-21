package com.three360.ui.controlflow;

import com.three360.fixatdl.flow.StateRuleT;
import javafx.util.Pair;

import java.util.List;

/***
 *
 */
public interface IAtdlStateRuleEvaluator {

    /***
     *
     * @param stateRuleT
     * @return
     */
    List<Pair<AtdlStateRuleResultType, Comparable>> getResult(StateRuleT stateRuleT);

}
