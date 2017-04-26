package com.three60t.ui.common.element;

import com.three60t.fixatdl.layout.StrategyPanelT;

/**
 * Created by sainik on 3/23/17.
 */
public interface FixPanelUiElement<T, K extends Comparable<?>> extends FixUiElement<T, K> {

    /***
     *
     */
    void setStrategyPanelT(StrategyPanelT strategyPanelT);

}
