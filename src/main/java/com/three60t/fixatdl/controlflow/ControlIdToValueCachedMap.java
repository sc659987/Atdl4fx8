package com.three60t.fixatdl.controlflow;

import com.three60t.fixatdl.ui.common.element.FixUiElement;
import com.three60t.fixatdl.utils.CachedMap;

import java.util.Map;

public class ControlIdToValueCachedMap implements CachedMap {

    private Map<String, FixUiElement<?, ?>> allIFixUiElements;

    public ControlIdToValueCachedMap(Map<String, FixUiElement<?, ?>> allIFixUiElements) {
        this.allIFixUiElements = allIFixUiElements;
    }

    @Override
    public Comparable get(String controlId) {
        return controlId != null ? allIFixUiElements.get(controlId).getValue() : null;
    }
}
