package com.three60t.ui.validator;

import com.three60t.fixatdl.validation.StrategyEditT;

import java.util.List;

public interface FixStrategyEditValidator {

    List<String> validateStrategyEditRuleAndGetErrorMessage();

    void setStrategyEditTS(List<StrategyEditT> editTS);

}
