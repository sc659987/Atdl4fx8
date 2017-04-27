package com.three60t.fixatdl.controlflow;

import com.three60t.fixatdl.ui.common.element.FixUiElement;
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