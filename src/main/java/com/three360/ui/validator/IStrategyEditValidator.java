package com.three360.ui.validator;

import com.three360.fixatdl.validation.StrategyEditT;

import java.util.List;

public interface IStrategyEditValidator {

	List<String> validateStrategyEditRuleAndGetErrorMessage();

	void setStrategyEditTS(List<StrategyEditT> editTS);

}
