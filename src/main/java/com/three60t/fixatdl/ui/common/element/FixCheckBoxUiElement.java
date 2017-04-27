package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.CheckBoxT;

/**
 * Created by sainik on 3/28/17.
 */
public interface FixCheckBoxUiElement<T, K extends Comparable<K>> extends FixUiElement<T, K> {

    void setCheckBoxT(CheckBoxT checkBoxT);
}
