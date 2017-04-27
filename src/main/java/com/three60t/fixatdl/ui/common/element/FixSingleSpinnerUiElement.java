package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.SingleSpinnerT;

public interface FixSingleSpinnerUiElement<T, K extends Comparable<K>> extends FixUiElement<T, K> {
    /***
     *
     * @param singleSpinnerT
     */
    void setSingleSpinner(SingleSpinnerT singleSpinnerT);

}
