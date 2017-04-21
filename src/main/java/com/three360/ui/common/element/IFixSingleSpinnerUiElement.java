package com.three360.ui.common.element;

import com.three360.fixatdl.layout.SingleSpinnerT;

public interface IFixSingleSpinnerUiElement<T, K extends Comparable<?>> extends IFixUiElement<T, K> {
	/***
	 *
	 * @param singleSpinnerT
	 */
	void setSingleSpinner(SingleSpinnerT singleSpinnerT);

}
