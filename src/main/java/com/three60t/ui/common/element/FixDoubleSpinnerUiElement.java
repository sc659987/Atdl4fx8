package com.three60t.ui.common.element;

import com.three60t.fixatdl.layout.DoubleSpinnerT;

/**
 * Created by sainik on 3/24/17.
 */
public interface FixDoubleSpinnerUiElement<T, K extends Comparable<?>> extends FixUiElement<T, K> {

    void setDoubleSpinner(DoubleSpinnerT doubleSpinnerT);
}
