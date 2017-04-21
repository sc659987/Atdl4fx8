package com.three360.ui.fx8.element;

import com.three360.fixatdl.core.ParameterT;
import com.three360.fixatdl.layout.DropDownListT;
import com.three360.fixatdl.layout.ListItemT;
import com.three360.ui.Utils;
import com.three360.ui.common.element.IFixDropDownListUiElement;
import com.three360.ui.fx8.FxUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Collections;
import java.util.List;

public class FxFixDropDownListUiElement implements IFixDropDownListUiElement<Pane, String> {

	private DropDownListT dropDownListT;
	private ComboBox<ListItemT> comboBox = new ComboBox<>();
	private GridPane gridPane;
	private int nextColumn = 0;
	private ParameterT parameterT;

	private ObjectProperty<String> checkedProperty = new SimpleObjectProperty<>();

	@Override
	public void setDropDownList(DropDownListT downList) {
		this.dropDownListT = downList;
	}

	@Override
	public Pane create() {
		if (this.dropDownListT != null) {

			this.gridPane = new GridPane();

			if (!Utils.isEmpty(this.dropDownListT.getLabel())) {
				this.gridPane.getColumnConstraints().addAll(FxUtils.getTwoColumnSameWidthForGridPane());
				this.gridPane.add(new Label(this.dropDownListT.getLabel()), this.nextColumn++, 0);
			}

			this.comboBox.getItems().addAll(this.dropDownListT.getListItem());

			if (Utils.isEmpty(this.dropDownListT.getInitValue()))
				this.comboBox.getSelectionModel().selectFirst();
			else
				setValue(this.dropDownListT.getInitValue());

			this.comboBox.setOnAction(event -> {
				setFieldValueToParameter(comboBox.getValue(), this.parameterT);
				checkedProperty.setValue(dropDownListT.getID() + ":" + comboBox.getValue());
			});

			this.gridPane.add(this.comboBox, this.nextColumn, 0);

			return this.gridPane;
		}
		return null;
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
		return this.checkedProperty;
	}

	@Override
	public DropDownListT getControl() {
		return this.dropDownListT;
	}

	@Override
	public String getValue() {
		return this.comboBox.getValue().getEnumID();
	}

	@Override
	public void setValue(String enumId) {
		dropDownListT.getListItem().stream()
				.filter(listItemT -> listItemT.getEnumID().equals(enumId))
				.findFirst()
				.ifPresent(listItemT -> {
					comboBox.setValue(listItemT);
					setFieldValueToParameter(comboBox.getValue(), this.parameterT);
				});
	}

	@Override
	public void makeVisible(boolean visible) {
		this.comboBox.setVisible(visible);
	}

	@Override
	public void makeEnable(boolean enable) {
		this.comboBox.setDisable(!enable);
	}
}
