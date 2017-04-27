package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.DoubleSpinnerT;

/**
 * Created by sainik on 3/24/17.
 */
public interface FixDoubleSpinnerUiElement<T, K extends Comparable<K>> extends FixUiElement<T, K> {

    void setDoubleSpinner(DoubleSpinnerT doubleSpinnerT);
}
