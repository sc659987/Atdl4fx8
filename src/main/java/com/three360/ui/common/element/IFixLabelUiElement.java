package com.three360.ui.common.element;

import com.three360.fixatdl.layout.LabelT;

public interface IFixLabelUiElement<T, K extends Comparable<?>> extends IFixUiElement<T, K> {

	/***
	 *
	 * @param label
	 */
	void setLabel(LabelT label);
}
