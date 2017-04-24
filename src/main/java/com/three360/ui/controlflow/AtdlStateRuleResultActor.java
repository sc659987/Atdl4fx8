package com.three360.ui.controlflow;

import com.three360.ui.common.element.IFixUiElement;
import javafx.util.Pair;

public interface AtdlStateRuleResultActor {

	/***
	 *
	 * @param stateRuleResultComparablePair
	 * @param iFixUiElement
	 */
	void doAct(Pair<AtdlStateRuleResultType, Comparable> stateRuleResultComparablePair, IFixUiElement iFixUiElement);

}
