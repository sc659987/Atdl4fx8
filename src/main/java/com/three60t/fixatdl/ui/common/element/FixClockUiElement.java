package com.three60t.fixatdl.ui.common.element;

import com.three60t.fixatdl.model.layout.ClockT;

/**
 * Created by sainik on 4/28/17.
 */
public interface FixClockUiElement<T, K extends Comparable<?>> extends FixUiElement<T, K> {

    void setClockT(ClockT clockT);


}
