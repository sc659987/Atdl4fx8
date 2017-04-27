package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.MultiSelectListT;

/**
 * Created by sainik on 3/24/17.
 */
public interface FixMultiSelectListUiElement<T, K extends Comparable<K>> extends FixUiElement<T, K> {

    void setMultiSelectList(MultiSelectListT multiSelectListT);

}
