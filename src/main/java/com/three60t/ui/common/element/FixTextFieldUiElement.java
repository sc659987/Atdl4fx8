package com.three60t.ui.common.element;

import com.three60t.fixatdl.layout.TextFieldT;

public interface FixTextFieldUiElement<T, K extends Comparable<?>> extends FixUiElement<T, K> {

    /***
     *
     * @param textField
     */
    void setTextField(TextFieldT textField);

}
