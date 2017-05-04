package com.three60t.fixatdl.ui.fx8;

import com.three60t.fixatdl.model.core.StrategiesT;
import com.three60t.fixatdl.model.layout.DropDownListT;
import com.three60t.fixatdl.model.layout.ListItemT;
import com.three60t.fixatdl.ui.common.AbstractFixAtdlUi;
import com.three60t.fixatdl.ui.common.UiElementAbstractFactory;
import com.three60t.fixatdl.ui.common.element.FixDropDownListUiElement;
import com.three60t.fixatdl.ui.common.element.FixLayoutUiElement;
import com.three60t.fixatdl.utils.Utils;
import com.three60t.fixatdl.validator.FixParameterValidator;
import com.three60t.fixatdl.validator.FixParameterValidatorImpl;
import com.three60t.fixatdl.validator.FixStrategyEditValidator;
import com.three60t.fixatdl.validator.FixStrategyEditValidatorImpl;
import com.three60t.fixatdl.wire.WireValueManipulator;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FxFixAtdlUi extends AbstractFixAtdlUi<Pane> {

    private Unmarshaller jaxbUnmarshaller;

    private UiElementAbstractFactory factory = FxUiElementFactory.getInstance();

    private final BorderPane borderPane = new BorderPane();

    private FixStrategyEditValidator fixStrategyEditValidator;

    private FixParameterValidator fixParameterValidator;

    private Button validateButton;

    private VBox errorMessageAndValidateButtonBox;

    private TextField textField;

    private WireValueManipulator wireValueManipulator = WireValueManipulator.getInstance();

    public FxFixAtdlUi() {
        try {
            this.jaxbUnmarshaller = JAXBContext.newInstance(StrategiesT.class.getPackage().getName()).createUnmarshaller();
        } catch (Exception e) {
        }
    }

    @Override
    public Pane createUi() {
        if (getStrategies() != null) {
            this.borderPane.getChildren().clear();
            this.borderPane.setTop(createStrategySelectionPanel());
            if (getStrategies().getStrategy().size() > 0)
                setSelectedStrategy(getStrategies().getStrategy().get(0));
            if (getSelectedStrategy() != null) {
                this.fixStrategyEditValidator = new FixStrategyEditValidatorImpl(
                        super.selectedStrategyT.getParameter());
                this.fixParameterValidator = new FixParameterValidatorImpl(super.selectedStrategyT.getParameter());
                createFixLayout();
                createFixErrorMessage();
            }
        }
        return this.borderPane;
    }

    private void createFixLayout() {
        FixLayoutUiElement<Node, String> layoutUiElement = this.factory.instantiateNewLayout();
        layoutUiElement.setStrategyLayout(getSelectedStrategy().getStrategyLayout());
        layoutUiElement.setParameters(super.selectedStrategyT.getParameter());
        this.fixStrategyEditValidator.setStrategyEditTS(super.selectedStrategyT.getStrategyEdit());
        this.borderPane.setCenter(layoutUiElement.create());
    }

    private void createFixErrorMessage() {
        this.errorMessageAndValidateButtonBox = new VBox();

        this.validateButton = new Button("Validate");
        this.textField = new TextField();

        this.textField.setEditable(false);

        HBox validateBox = new HBox();
        validateBox.getChildren().add(this.validateButton);
        validateBox.getChildren().add(this.textField);

        this.validateButton.setOnAction(event -> {
            List<String> errorMessage = Stream.concat(
                    fixStrategyEditValidator.validateStrategyEditRuleAndGetErrorMessage().stream(),
                    fixParameterValidator.validateParameter().stream()).filter(Utils::isNonEmptyString).collect(Collectors.toList());

            this.errorMessageAndValidateButtonBox.getChildren().clear();
            if (errorMessage != null && errorMessage.size() > 0) {
                this.errorMessageAndValidateButtonBox.getChildren().addAll(errorMessage.stream().map(str -> {
                    Label lb = new Label(str);
                    lb.setTextFill(Color.web("#FF0040"));
                    return lb;
                }).collect(Collectors.toList()));
            } else {
                // generate the wire value and put that in text box
                textField.setText(wireValueManipulator.generateWireValue(super.strategiesT, super.selectedStrategyT));
            }
        });
        HBox.setHgrow(textField, Priority.ALWAYS);

        VBox wrapperBox = new VBox();

        this.errorMessageAndValidateButtonBox.getChildren().add(validateBox);

        wrapperBox.getChildren().add(this.errorMessageAndValidateButtonBox);
        wrapperBox.getChildren().add(validateBox);

        this.borderPane.setBottom(wrapperBox);
    }

    @Override
    public Unmarshaller getUnmarshaller() {
        return this.jaxbUnmarshaller;
    }

    @Override
    public Pane createStrategySelectionPanel() {
        HBox strategySelectionBox = new HBox();
        strategySelectionBox.setAlignment(Pos.CENTER);
        if (getStrategies() != null) {
            FixDropDownListUiElement<ComboBox<String>, String> element = this.factory.instantiateNewDropDownList();
            List<ListItemT> listItemTS = getStrategies().getStrategy().stream().map(s -> {
                ListItemT listItemT = new ListItemT();
                listItemT.setUiRep(s.getName());
                listItemT.setEnumID(s.getName());
                return listItemT;
            }).collect(Collectors.toList());
            DropDownListT dropDownListT = new DropDownListT();
            dropDownListT.getListItem().addAll(listItemTS);
            dropDownListT.setID("selectID");
            element.setDropDownList(dropDownListT);
            element.listenChange().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    setSelectedStrategy(findStrategyTByName(element.getValue()));
                    createFixLayout();
                }
            });
            strategySelectionBox.getChildren().add(element.create());
        }
        return strategySelectionBox;
    }
}
