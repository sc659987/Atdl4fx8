package com.three60t.ui.common;

import com.three60t.ui.common.element.*;

public interface UiElementAbstractFactory {

    <T, K extends Comparable<?>> FixCheckBoxListUiElement<T, K> instantiateNewCheckBoxList();

    <T, K extends Comparable<?>> FixCheckBoxUiElement<T, K> instantiateNewCheckBox();

    <T, K extends Comparable<?>> FixClockUiElement<T, K> instantiateNewClock();

    <T, K extends Comparable<?>> FixDoubleSpinnerUiElement<T, K> instantiateNewDoubleSpinner();

    <T, K extends Comparable<?>> FixDropDownListUiElement<T, K> instantiateNewDropDownList();

    <T, K extends Comparable<?>> FixEditableDropDownListUiElement<T, K> instantiateNewEditableDropDownList();

    <T, K extends Comparable<?>> FixLabelUiElement<T, K> instantiateNewLabel();

    <T, K extends Comparable<?>> FixMultiSelectListUiElement<T, K> instantiateNewMultiSelectList();

    <T, K extends Comparable<?>> FixRadioButtonListUiElement<T, K> instantiateNewRadioButtonList();

    <T, K extends Comparable<?>> FixRadioButtonUiElement<T, K> instantiateNewRadioButton();

    <T, K extends Comparable<?>> FixSingleSelectListUiElement<T, K> instantiateNewSingleSelectList();

    <T, K extends Comparable<?>> FixSingleSpinnerUiElement<T, K> instantiateNewSingleSpinner();

    <T, K extends Comparable<?>> FixSliderUiElement<T, K> instantiateNewSlider();

    <T, K extends Comparable<?>> FixTextFieldUiElement<T, K> instantiateNewTextField();

    <T, K extends Comparable<?>> FixHiddenFieldUiElement<T, K> instantiateNewHiddenField();

    <T, K extends Comparable<?>> FixPanelUiElement<T, K> instantiateNewPanel();

    <T, K extends Comparable<?>> FixLayoutUiElement<T, K> instantiateNewLayout();

}