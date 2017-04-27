package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.LabelT;

public interface FixLabelUiElement<T, K extends Comparable<K>> extends FixUiElement<T, K> {

    /***
     *
     * @param label
     */
    void setLabel(LabelT label);
}
