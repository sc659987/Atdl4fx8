package com.three60t.ui.common.element;

import com.three60t.fixatdl.layout.CheckBoxT;

/**
 * Created by sainik on 3/28/17.
 */
public interface FixCheckBoxUiElement<T, K extends Comparable<?>> extends FixUiElement<T, K> {

    void setCheckBoxT(CheckBoxT checkBoxT);
}
