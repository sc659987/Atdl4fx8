package com.three360.ui.fx8.element;

import com.three360.fixatdl.core.ParameterT;
import com.three360.fixatdl.layout.PanelOrientationT;
import com.three360.fixatdl.layout.RadioButtonListT;
import com.three360.ui.Utils;
import com.three360.ui.common.element.IFixRadioButtonListUiElement;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FxFixRadioButtonListUiElement implements IFixRadioButtonListUiElement<Pane, String> {

	private RadioButtonListT radioButtonListT;
	private GridPane pane;
	private List<RadioButton> radioButtonList;
	private ToggleGroup toggleGroup;
	private ParameterT parameterT;

	private ObjectProperty<String> controlIdEmitter = new SimpleObjectProperty<>();

	@Override
	public Pane create() {
		if (this.radioButtonListT != null) {
			this.pane = new GridPane();
			this.toggleGroup = new ToggleGroup();
			this.pane.add(new Label(this.radioButtonListT.getLabel()), 0, 0, GridPane.REMAINING, 1);
			this.radioButtonList = this.radioButtonListT.getListItem().stream().map(listItemT -> {
				RadioButton radioButton = new RadioButton();
				radioButton.setText(listItemT.getUiRep());
				radioButton.setId(listItemT.getEnumID());
				radioButton.setToggleGroup(toggleGroup);
				return radioButton;
			}).collect(Collectors.toList());

			if (Utils.isNonEmpty(this.radioButtonListT.getInitValue()))
				setValue(this.radioButtonListT.getInitValue());

			this.radioButtonList.forEach(radioButton -> {

				radioButton.setOnAction(event -> {
					setFieldValueToParameter(((RadioButton) event.getSource()).getId(), parameterT);
					this.controlIdEmitter.setValue(this.radioButtonListT.getID() + ":" + getValue());
				});

			});

			for (int i = 0; i < this.radioButtonList.size(); i++) {
				if (this.radioButtonListT.getOrientation() == PanelOrientationT.HORIZONTAL) {
					this.pane.add(this.radioButtonList.get(i), 3 * i, 1, 1, 1);
				} else {
					this.pane.add(this.radioButtonList.get(i), 0, i + 1, GridPane.REMAINING, 1);
				}
			}
			return pane;
		}
		return null;
	}

	@Override
	public void setRadioButtonListT(RadioButtonListT radioButtonListT) {
		this.radioButtonListT = radioButtonListT;
	}

	@Override
	public void setParameters(List<ParameterT> parameterTList) {
		if (parameterTList != null && parameterTList.size() > 0)
			this.parameterT = parameterTList.get(0);
	}

	@Override
	public List<ParameterT> getParameter() {
		List<ParameterT> parameterTS = Collections.emptyList();
		parameterTS.add(this.parameterT);
		return parameterTS;
	}

	@Override
	public ObjectProperty<String> listenChange() {
		return this.controlIdEmitter;
	}

	@Override
	public String getValue() {
		return radioButtonList
				.stream()
				.filter(RadioButton::isSelected)
				.map(radioButton -> radioButton.getId())
				.findFirst()
				.orElse("");
	}

	@Override
	public void setValue(String s) {
		this.radioButtonList
				.stream()
				.filter(radioButton -> radioButton.getId().equals(s) ? true : false)
				.forEach(radioButton -> {
					radioButton.setSelected(true);
				});
	}

	@Override
	public RadioButtonListT getControl() {
		return this.radioButtonListT;
	}

	@Override
	public void makeVisible(boolean visible) {
		radioButtonList.forEach(radioButton -> {
			radioButton.setVisible(visible);
		});
	}

	@Override
	public void makeEnable(boolean enable) {
		radioButtonList.forEach(radioButton -> {
			radioButton.setDisable(!enable);
		});
	}
}
