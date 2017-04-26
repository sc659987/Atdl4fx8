package com.three60t.ui.fx8.controlflow;

import com.three60t.ui.common.element.FixUiElement;
import com.three60t.ui.controlflow.FixAtdlStateRuleResultActor;
import com.three60t.ui.controlflow.FixAtdlStateRuleResultType;
import javafx.util.Pair;

/***
 *
 */
public class FxFixAtdlStateRuleResultActor implements FixAtdlStateRuleResultActor {

    @Override
    public void doAct(Pair<FixAtdlStateRuleResultType, Comparable> stateRuleResultComparablePair,
                      FixUiElement fixUiElement) {
        switch (stateRuleResultComparablePair.getKey()) {
            case ENABLE:
                fixUiElement.makeEnable((Boolean) stateRuleResultComparablePair.getValue());
                break;
            case VALUE:
                fixUiElement.setValue(stateRuleResultComparablePair.getValue().equals(FixUiElement.NULL_VALUE) ? null : stateRuleResultComparablePair.getValue());
                break;
            case VISIBLE:
                fixUiElement.makeVisible((Boolean) stateRuleResultComparablePair.getValue());
                break;
            default:
        }
    }
}
