package com.three60t.ui.common.element;

import com.three60t.fixatdl.layout.LabelT;

public interface FixLabelUiElement<T, K extends Comparable<?>> extends FixUiElement<T, K> {

    /***
     *
     * @param label
     */
    void setLabel(LabelT label);
}
