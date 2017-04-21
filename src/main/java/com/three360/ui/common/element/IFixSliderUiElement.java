package com.three360.ui.common.element;

import com.three360.fixatdl.layout.SliderT;

/**
 * Created by sainik on 3/23/17.
 */
public interface IFixSliderUiElement<T, K extends Comparable<?>> extends IFixUiElement<T, K> {

	/***
	 *
	 * @param slider
	 */
	void setSlider(SliderT slider);

}
