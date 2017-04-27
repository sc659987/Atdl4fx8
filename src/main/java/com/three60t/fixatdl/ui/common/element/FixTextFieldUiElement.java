package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.TextFieldT;

public interface FixTextFieldUiElement<T, K extends Comparable<K>> extends FixUiElement<T, K> {

    /***
     *
     * @param textField
     */
    void setTextField(TextFieldT textField);

}
