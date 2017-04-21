package com.three360.ui.validator;

import com.three360.fixatdl.core.ParameterT;

import java.util.List;

public interface ParameterValidator {

	boolean validate(List<ParameterT> parameterTS);
}
