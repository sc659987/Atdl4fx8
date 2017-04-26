package com.three60t.ui.fx8.controlflow;

import com.three60t.ui.common.element.FixUiElement;
import com.three60t.ui.validator.FieldToComparableMapperCache;

import java.util.Map;

public class FieldToComparableMapperControlCache implements FieldToComparableMapperCache {

    private Map<String, FixUiElement> allIFixUiElements;

    public FieldToComparableMapperControlCache(Map<String, FixUiElement> allIFixUiElements) {
        this.allIFixUiElements = allIFixUiElements;
    }

    @Override
    public Comparable get(String controlId) {
        return controlId != null ? allIFixUiElements.get(controlId).getValue() : null;
    }
}
