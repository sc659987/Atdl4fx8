package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.RadioButtonT;

/**
 * Created by sainik on 3/23/17.
 */
public interface FixRadioButtonUiElement<T, K extends Comparable<K>> extends FixUiElement<T, K> {

    void setRadioButtonT(RadioButtonT radioButtonT);
}
