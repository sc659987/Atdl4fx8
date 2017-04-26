package com.three60t.ui.common.element;

import com.three60t.fixatdl.layout.SliderT;

/**
 * Created by sainik on 3/23/17.
 */
public interface FixSliderUiElement<T, K extends Comparable<?>> extends FixUiElement<T, K> {

    /***
     *
     * @param slider
     */
    void setSlider(SliderT slider);

}
