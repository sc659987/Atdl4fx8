package com.three60t.ui.common.element;

import com.three60t.fixatdl.layout.SingleSpinnerT;

public interface FixSingleSpinnerUiElement<T, K extends Comparable<?>> extends FixUiElement<T, K> {
    /***
     *
     * @param singleSpinnerT
     */
    void setSingleSpinner(SingleSpinnerT singleSpinnerT);

}
