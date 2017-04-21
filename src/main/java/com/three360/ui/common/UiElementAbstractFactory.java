package com.three360.ui.common;

import com.three360.ui.common.element.*;

public interface UiElementAbstractFactory {

	<T, K extends Comparable<?>> IFixCheckBoxListUiElement<T, K> instantiateNewCheckBoxList();

	<T, K extends Comparable<?>> IFixCheckBoxUiElement<T, K> instantiateNewCheckBox();

	<T, K extends Comparable<?>> IFixClockUiElement<T, K> instantiateNewClock();

	<T, K extends Comparable<?>> IFixDoubleSpinnerUiElement<T, K> instantiateNewDoubleSpinner();

	<T, K extends Comparable<?>> IFixDropDownListUiElement<T, K> instantiateNewDropDownList();

	<T, K extends Comparable<?>> IFixEditableDropDownListUiElement<T, K> instantiateNewEditableDropDownList();

	<T, K extends Comparable<?>> IFixLabelUiElement<T, K> instantiateNewLabel();

	<T, K extends Comparable<?>> IFixMultiSelectListUiElement<T, K> instantiateNewMultiSelectList();

	<T, K extends Comparable<?>> IFixRadioButtonListUiElement<T, K> instantiateNewRadioButtonList();

	<T, K extends Comparable<?>> IFixRadioButtonUiElement<T, K> instantiateNewRadioButton();

	<T, K extends Comparable<?>> IFixSingleSelectListUiElement<T, K> instantiateNewSingleSelectList();

	<T, K extends Comparable<?>> IFixSingleSpinnerUiElement<T, K> instantiateNewSingleSpinner();

	<T, K extends Comparable<?>> IFixSliderUiElement<T, K> instantiateNewSlider();

	<T, K extends Comparable<?>> IFixTextFieldUiElement<T, K> instantiateNewTextField();

	<T, K extends Comparable<?>> IFixHiddenFieldUiElement<T, K> instantiateNewHiddenField();

	<T, K extends Comparable<?>> IFixPanelUiElement<T, K> instantiateNewPanel();

	<T, K extends Comparable<?>> IFixLayoutUiElement<T, K> instantiateNewLayout();

}