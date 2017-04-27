package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.StrategyLayoutT;

public interface FixLayoutUiElement<T, K extends Comparable<K>> extends FixUiElement<T, K> {

    void setStrategyLayout(StrategyLayoutT strategyLayoutT);

}
