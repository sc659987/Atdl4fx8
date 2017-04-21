package com.three360.ui.common.element;

import com.three360.fixatdl.layout.MultiSelectListT;

/**
 * Created by sainik on 3/24/17.
 */
public interface IFixMultiSelectListUiElement<T, K extends Comparable<?>> extends IFixUiElement<T, K> {

	void setMultiSelectList(MultiSelectListT multiSelectListT);

}
