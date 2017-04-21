package com.three360.ui.common.element;

import com.three360.fixatdl.layout.DoubleSpinnerT;

/**
 * Created by sainik on 3/24/17.
 */
public interface IFixDoubleSpinnerUiElement<T, K extends Comparable<?>> extends IFixUiElement<T, K> {

	void setDoubleSpinner(DoubleSpinnerT doubleSpinnerT);
}
