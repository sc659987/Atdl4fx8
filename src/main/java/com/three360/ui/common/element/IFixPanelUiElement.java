package com.three360.ui.common.element;

import com.three360.fixatdl.layout.StrategyPanelT;

/**
 * Created by sainik on 3/23/17.
 */
public interface IFixPanelUiElement<T, K extends Comparable<?>> extends IFixUiElement<T, K> {

	/***
	 *
	 */
	void setStrategyPanelT(StrategyPanelT strategyPanelT);

}
