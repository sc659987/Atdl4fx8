package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.DropDownListT;

/**
 * Created by sainik on 3/23/17.
 */
public interface FixDropDownListUiElement<T, K extends Comparable<K>> extends FixUiElement<T, K> {

    /***
     *
     * @param downList
     */
    void setDropDownList(DropDownListT downList);

}
