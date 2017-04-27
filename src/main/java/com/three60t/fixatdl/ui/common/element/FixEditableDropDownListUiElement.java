package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.EditableDropDownListT;

/**
 * Created by sainik on 3/24/17.
 */
public interface FixEditableDropDownListUiElement<T, K extends Comparable<K>> extends FixUiElement<T, K> {

    /***
     *
     * @param listT
     */
    void setEditableDropDownList(EditableDropDownListT listT);
}
