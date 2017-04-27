package com.three60t.fixatdl.validator;

import com.three60t.fixatdl.model.validation.StrategyEditT;

import java.util.List;

public interface FixStrategyEditValidator {

    List<String> validateStrategyEditRuleAndGetErrorMessage();

    void setStrategyEditTS(List<StrategyEditT> editTS);

}
