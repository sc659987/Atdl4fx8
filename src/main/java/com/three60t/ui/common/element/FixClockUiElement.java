package com.three60t.ui.common.element;

import com.three60t.fixatdl.layout.ClockT;

public interface FixClockUiElement<T, K extends Comparable<?>> extends FixUiElement<T, K> {

    /***
     *
     * @param clockT
     */
    void setClockT(ClockT clockT);

}
