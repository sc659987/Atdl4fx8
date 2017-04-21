package com.three360.ui.common.element;

import com.three360.fixatdl.layout.DropDownListT;

/**
 * Created by sainik on 3/23/17.
 */
public interface IFixDropDownListUiElement<T, K extends Comparable<?>> extends IFixUiElement<T, K> {

	/***
	 *
	 * @param downList
	 */
	void setDropDownList(DropDownListT downList);

}
