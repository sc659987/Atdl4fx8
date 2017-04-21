package com.three360.ui.fx8.element;

import com.three360.fixatdl.core.ParameterT;
import com.three360.fixatdl.layout.LabelT;
import com.three360.ui.common.element.IFixLabelUiElement;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Label;

import java.util.Collections;
import java.util.List;

public class FxFixLabelUiElement implements IFixLabelUiElement<Label, String> {

	private Label label;
	private LabelT labelT;

	private ParameterT parameterT;

	@Override
	public Label create() {
		if (this.labelT != null) {
			this.label = new Label();
			this.label.setText(this.labelT.getLabel());
			return this.label;
		}
		return null;
	}

	@Override
	public void setLabel(LabelT label) {
		this.labelT = label;
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
		return null;
	}

	@Override
	public LabelT getControl() {
		return this.labelT;
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void setValue(String s) {

	}

	@Override
	public void makeVisible(boolean visible) {

	}

	@Override
	public void makeEnable(boolean enable) {

	}
}
