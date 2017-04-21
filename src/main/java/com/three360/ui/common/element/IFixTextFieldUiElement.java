package com.three360.ui.common.element;

import com.three360.fixatdl.layout.TextFieldT;

public interface IFixTextFieldUiElement<T, K extends Comparable<?>> extends IFixUiElement<T, K> {

	/***
	 *
	 * @param textField
	 */
	void setTextField(TextFieldT textField);

}
