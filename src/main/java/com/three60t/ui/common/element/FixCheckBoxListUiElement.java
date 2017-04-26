package com.three60t.ui.common.element;

import com.three60t.fixatdl.layout.CheckBoxListT;

public interface FixCheckBoxListUiElement<T, K extends Comparable<?>> extends FixUiElement<T, K> {

    /**
     * @param checkBoxListT
     */
    FixCheckBoxListUiElement<T, K> setCheckBoxListT(CheckBoxListT checkBoxListT);
}
