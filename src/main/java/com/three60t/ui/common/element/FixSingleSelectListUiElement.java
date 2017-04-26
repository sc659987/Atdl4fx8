package com.three60t.ui.common.element;

import com.three60t.fixatdl.layout.SingleSelectListT;

public interface FixSingleSelectListUiElement<T, K extends Comparable<?>> extends FixUiElement<T, K> {

    /****
     *
     * @param singleSelectListT
     */
    void setSingleSelectList(SingleSelectListT singleSelectListT);

}
