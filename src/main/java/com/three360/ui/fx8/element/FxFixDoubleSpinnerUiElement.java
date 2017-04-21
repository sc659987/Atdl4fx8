package com.three360.ui.fx8.element;

import com.three360.fixatdl.core.ParameterT;
import com.three360.fixatdl.layout.ControlT;
import com.three360.fixatdl.layout.DoubleSpinnerT;
import com.three360.ui.common.element.IFixDoubleSpinnerUiElement;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Pane;

import java.util.List;

public class FxFixDoubleSpinnerUiElement implements IFixDoubleSpinnerUiElement<Pane, String> {

	@Override
	public Pane create() {
		return null;
	}

	@Override
	public void setDoubleSpinner(DoubleSpinnerT doubleSpinnerT) {

	}

	@Override
	public void setParameters(List<ParameterT> parameterTList) {

	}

	@Override
	public List<ParameterT> getParameter() {
		return null;
	}

	@Override
	public ObjectProperty<String> listenChange() {
		return null;
	}

	@Override
	public <C extends ControlT> C getControl() {
		return null;
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
