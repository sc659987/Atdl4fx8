package com.three360.ui.fx8.customelement.doublespinner;

import com.sun.javafx.scene.control.behavior.BehaviorBase;
import com.sun.javafx.scene.control.behavior.KeyBinding;

import java.util.Arrays;
import java.util.List;

import static javafx.scene.input.KeyCode.RIGHT;

/**
 * Created by sainik on 24/04/17.
 */
public class DoubleSpinnerBehavior<T> extends BehaviorBase<DoubleSpinner<T>> {

    protected static final List<KeyBinding> SPINNER_BINDINGS = Arrays.asList(new KeyBinding(RIGHT, "increment-right"));

    public DoubleSpinnerBehavior(DoubleSpinner<T> doubleSpinner) {
        super(doubleSpinner, SPINNER_BINDINGS);
    }

    public void startSpinning(boolean e) {

    }

    public void stopSpinning() {

    }
}
