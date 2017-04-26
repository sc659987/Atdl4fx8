package com.three60t.ui.common.element;

import com.three60t.fixatdl.layout.MultiSelectListT;

/**
 * Created by sainik on 3/24/17.
 */
public interface FixMultiSelectListUiElement<T, K extends Comparable<?>> extends FixUiElement<T, K> {

    void setMultiSelectList(MultiSelectListT multiSelectListT);

}
