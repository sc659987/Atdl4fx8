package com.three360.ui.common.element;

import com.three360.fixatdl.layout.CheckBoxListT;

public interface IFixCheckBoxListUiElement<T, K extends Comparable<?>> extends IFixUiElement<T, K> {

    /**
     * @param checkBoxListT
     */
    IFixCheckBoxListUiElement<T, K> setCheckBoxListT(CheckBoxListT checkBoxListT);
}
