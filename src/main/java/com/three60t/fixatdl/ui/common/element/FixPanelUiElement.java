package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.StrategyPanelT;

/**
 * Created by sainik on 3/23/17.
 */
public interface FixPanelUiElement<T, K extends Comparable<K>> extends FixUiElement<T, K> {

    /***
     *
     */
    void setStrategyPanelT(StrategyPanelT strategyPanelT);

}
