package com.three360.ui.fx8.customelement.doublespinner;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

/**
 * Created by sainik on 4/10/17.
 */
// TODO it has to be done
public class DoubleSpinner<T> extends Control {

    private static final String DEFAULT_STYLE_CLASS = "double-spinner";

    public static final String STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL = "arrows-on-right-horizontal";


    DoubleSpinner() {
        getStyleClass().add(DEFAULT_STYLE_CLASS);
        setAccessibleRole(AccessibleRole.SPINNER);


    }


    private ReadOnlyObjectWrapper<T> value = new ReadOnlyObjectWrapper<>(this, "value");

    public final T getValue() {
        return value.get();
    }

    public final ReadOnlyObjectProperty<T> valueProperty() {
        return value;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new DoubleSpinnerSkin<T>(this);
    }


    public final TextField getEditor() {
        return editorProperty().get();
    }

    public final ReadOnlyObjectProperty<TextField> editorProperty() {
        return null;
    }

    public void increment() {

    }
}
