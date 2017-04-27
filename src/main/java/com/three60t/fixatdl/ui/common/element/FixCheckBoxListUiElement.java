package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.CheckBoxListT;

public interface FixCheckBoxListUiElement<T, K extends Comparable<K>> extends FixUiElement<T, K> {

    /**
     * @param checkBoxListT
     */
    FixCheckBoxListUiElement<T, K> setCheckBoxListT(CheckBoxListT checkBoxListT);
}
