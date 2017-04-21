package com.three360.ui.common.element;

import com.three360.fixatdl.layout.StrategyLayoutT;

public interface IFixLayoutUiElement<T, K extends Comparable<?>> extends IFixUiElement<T, K> {

	void setStrategyLayout(StrategyLayoutT strategyLayoutT);

}
