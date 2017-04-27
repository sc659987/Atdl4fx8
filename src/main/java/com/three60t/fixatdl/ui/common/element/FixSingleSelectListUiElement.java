package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.SingleSelectListT;

public interface FixSingleSelectListUiElement<T, K extends Comparable<K>> extends FixUiElement<T, K> {

    /****
     *
     * @param singleSelectListT
     */
    void setSingleSelectList(SingleSelectListT singleSelectListT);

}
