package com.three360.ui.fx8.controlflow;

import com.three360.ui.common.element.IFixUiElement;
import com.three360.ui.validator.FieldToComparableMapperCache;

import java.util.Map;

public class FieldToComparableMapperControlCache implements FieldToComparableMapperCache {

	private Map<String, IFixUiElement> allIFixUiElements;

	public FieldToComparableMapperControlCache(Map<String, IFixUiElement> allIFixUiElements) {
		this.allIFixUiElements = allIFixUiElements;
	}

	@Override
	public Comparable get(String controlId) {
		return controlId != null ? allIFixUiElements.get(controlId).getValue() : null;
	}
}
