package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.ClockT;

public interface FixClockUiElement<T, K extends Comparable<K>> extends FixUiElement<T, K> {

    /***
     *
     * @param clockT
     */
    void setClockT(ClockT clockT);

}
