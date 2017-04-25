package com.three360.ui.fx8.customelement.doublespinner;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import javafx.css.PseudoClass;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.AccessibleAction;
import javafx.scene.AccessibleRole;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Created by sainik on 24/04/17.
 */
public class DoubleSpinnerSkin<T> extends BehaviorSkinBase<DoubleSpinner<T>, DoubleSpinnerBehavior<T>> {
    private TextField textField;

    private Region firstIncrementArrow;
    private StackPane firstIncrementArrowButton;

    private Region secondIncrementArrow;
    private StackPane secondIncrementArrowButton;

    private Region firstDecrementArrow;
    private StackPane firstDecrementArrowButton;

    private Region secondDecrementArrow;
    private StackPane secondDecrementArrowButton;


    protected DoubleSpinnerSkin(DoubleSpinner<T> spinner) {
        super(spinner, null);

        textField = spinner.getEditor();
        getChildren().add(textField);


        firstIncrementArrow = new Region();
        firstIncrementArrow.setFocusTraversable(false);
        firstIncrementArrow.getStyleClass().setAll("increment-arrow");
        firstIncrementArrow.setMaxWidth(Region.USE_PREF_SIZE);
        firstIncrementArrow.setMaxHeight(Region.USE_PREF_SIZE);
        firstIncrementArrow.setMouseTransparent(true);

        firstIncrementArrowButton = new StackPane() {
            public void executeAccessibleAction(AccessibleAction action, Object... parameters) {
                switch (action) {
                    case FIRE:
                        getSkinnable().increment();
                    default:
                        super.executeAccessibleAction(action, parameters);
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
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {

        final double firstIncrementArrowButtonWidth = firstIncrementArrowButton.snappedLeftInset() +
                snapSize(firstIncrementArrow.prefWidth(-1)) + firstIncrementArrowButton.snappedRightInset();

        final double firstDecrementArrowButtonWidth = firstDecrementArrowButton.snappedLeftInset() +
                snapSize(firstDecrementArrow.prefWidth(-1)) + firstDecrementArrowButton.snappedRightInset();

        final double widestFirstArrowButton = Math.max(firstIncrementArrowButtonWidth, firstDecrementArrowButtonWidth);

        final double secondIncrementArrowButtonWidth = secondIncrementArrowButton.snappedLeftInset() +
                snapSize(secondIncrementArrow.prefWidth(-1) + secondIncrementArrowButton.snappedRightInset());

        final double secondDecrementArrowButtonWidth = secondDecrementArrow.snappedLeftInset() +
                snapSize(secondDecrementArrow.prefWidth(-1) + secondIncrementArrowButton.snappedRightInset());

        final double widestSecondArrowButton = Math.max(secondIncrementArrowButtonWidth, secondDecrementArrowButtonWidth);

        final double textFieldStartX = contentX;
        final double firstButtonStartX = contentX + contentWidth - widestFirstArrowButton - widestSecondArrowButton;
        final double secondButtonStartX = contentX + contentWidth - widestSecondArrowButton;
        final double halfHeight = Math.floor(contentHeight / 2.0);

        textField.resizeRelocate(textFieldStartX, contentY, contentWidth - widestFirstArrowButton, contentHeight);
        firstIncrementArrowButton.resize(widestFirstArrowButton, halfHeight);

        positionInArea(firstIncrementArrowButton, firstButtonStartX, contentY,
                widestFirstArrowButton, halfHeight, 0, HPos.CENTER, VPos.CENTER);

        firstDecrementArrowButton.resize(widestFirstArrowButton, halfHeight);
        positionInArea(firstDecrementArrowButton, firstButtonStartX, contentY + halfHeight,
                widestFirstArrowButton, contentHeight - halfHeight, 0, HPos.CENTER, VPos.BOTTOM);

        secondIncrementArrowButton.resize(widestSecondArrowButton, halfHeight);
        positionInArea(secondIncrementArrowButton, secondButtonStartX, contentY, widestSecondArrowButton, halfHeight, 0, HPos.CENTER, VPos.CENTER);

        secondDecrementArrowButton.resize(widestSecondArrowButton, halfHeight);
        positionInArea(secondDecrementArrowButton, secondButtonStartX, contentY, widestSecondArrowButton, halfHeight, 0, HPos.CENTER, VPos.CENTER);

    }


    @Override
    protected double computeMinHeight(double width, double topInset,
                                      double rightInset, double bottomInset,
                                      double leftInset) {
        return computePrefHeight(width, topInset, rightInset, bottomInset, leftInset);
    }

    @Override
    protected double computePrefHeight(double width, double topInset,
                                       double rightInset, double bottomInset,
                                       double leftInset) {
        double textFieldHeight = textField.prefHeight(width);
        return topInset + textFieldHeight + bottomInset;
    }

    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        final double textfieldWidth = textField.prefWidth(height);
        return leftInset + textfieldWidth + rightInset;
    }

    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return getSkinnable().prefHeight(width);
    }


    @Override
    protected double computeMaxWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return getSkinnable().prefWidth(height);
    }

    @Override
    protected double computeBaselineOffset(double topInset, double rightInset, double bottomInset, double leftInset) {
        return textField.getLayoutBounds().getMinY() + textField.getLayoutY() + textField.getBaselineOffset();
    }


    /***************************************************************************
     *                                                                         *
     * Stylesheet Handling                                                     *
     *                                                                         *
     **************************************************************************/

    private static PseudoClass CONTAINS_FOCUS_PSEUDOCLASS_STATE = PseudoClass.getPseudoClass("contains-focus");

}
