package com.three360.ui.common.element;

import com.three360.fixatdl.layout.ClockT;

public interface IFixClockUiElement<T, K extends Comparable<?>> extends IFixUiElement<T, K> {

	/***
	 *
	 * @param clockT
	 */
	void setClockT(ClockT clockT);

}
