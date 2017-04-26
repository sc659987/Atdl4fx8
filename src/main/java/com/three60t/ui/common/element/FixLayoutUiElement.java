package com.three60t.ui.common.element;

import com.three60t.fixatdl.layout.StrategyLayoutT;

public interface FixLayoutUiElement<T, K extends Comparable<?>> extends FixUiElement<T, K> {

    void setStrategyLayout(StrategyLayoutT strategyLayoutT);

}
