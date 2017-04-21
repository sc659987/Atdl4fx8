package com.three360.ui.common.element;

import com.three360.fixatdl.layout.SingleSelectListT;

public interface IFixSingleSelectListUiElement<T, K extends Comparable<?>> extends IFixUiElement<T, K> {

	/****
	 *
	 * @param singleSelectListT
	 */
	void setSingleSelectList(SingleSelectListT singleSelectListT);

}
