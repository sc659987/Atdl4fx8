package com.three360.ui.fx8.customelement;

import com.sun.javafx.scene.control.behavior.SpinnerBehavior;
import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import javafx.scene.AccessibleAction;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Created by sainik on 24/04/17.
 */
public class DoubleSpinnerSkin<T> extends BehaviorSkinBase<Spinner<T>, SpinnerBehavior<T>> {
    private TextField textField;

    private Region firstIncrementArrow;
    private StackPane firstIncrementArrowButton;

    private Region secondIncrementArrow;
    private StackPane secondIncrementArrowButton;

    private Region firstDecrementArrow;
    private StackPane firstDecrementArrowButton;

    private Region secondDecrementArrow;
    private StackPane secondDecrementArrowButton;


    protected DoubleSpinnerSkin(Spinner<T> spinner) {
        super(spinner, null);

        textField = spinner.getEditor();
        getChildren().add(textField);


        firstDecrementArrow = new Region();
        firstDecrementArrow.setFocusTraversable(false);
        firstDecrementArrow.getStyleClass().setAll("increment-arrow");
        firstDecrementArrow.setMaxWidth(Region.USE_PREF_SIZE);
        firstDecrementArrow.setMaxHeight(Region.USE_PREF_SIZE);
        firstDecrementArrow.setMouseTransparent(true);

        firstIncrementArrowButton = new StackPane() {
            public void executeAccessibleAction(AccessibleAction action, Object... parameters) {
                switch (action) {
                    case FIRE: getSkinnable().increment();
                    default: super.executeAccessibleAction(action, parameters);
                }
            }
        };
        firstIncrementArrowButton.setAccessibleRole(AccessibleRole.INCREMENT_BUTTON);
        firstIncrementArrowButton.setFocusTraversable(false);
        firstDecrementArrow.getStyleClass().setAll("increment-arrow-button");
        firstIncrementArrowButton.getChildren().add(firstDecrementArrow);
        firstIncrementArrowButton.setOnMousePressed(e -> {
            getSkinnable().requestFocus();
            getBehavior().startSpinning(true);
        });
        firstIncrementArrowButton.setOnMouseReleased(e -> getBehavior().stopSpinning());



    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    protected void handleControlPropertyChanged(String propertyReference) {
        super.handleControlPropertyChanged(propertyReference);
    }
}
