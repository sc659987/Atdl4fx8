package com.three360.ui.fx8.validator;

import com.three360.fixatdl.core.ParameterT;
import com.three360.fixatdl.validation.StrategyEditT;
import com.three360.ui.validator.IAtdlEditEvaluator;
import com.three360.ui.validator.IStrategyEditValidator;
import javafx.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class StrategyEditValidator implements IStrategyEditValidator {

    private IAtdlEditEvaluator IAtdlEditEvaluator;
    private List<StrategyEditT> strategyEditTS;
    private FieldToComparableMapperParameterCache fieldToComparableMapperParameterCache;

    public StrategyEditValidator(List<ParameterT> parameterTS) {
        assert (strategyEditTS != null && parameterTS != null);
        this.fieldToComparableMapperParameterCache = new FieldToComparableMapperParameterCache(parameterTS);
        this.IAtdlEditEvaluator = new RecursiveAtdlEditEvaluator(this.fieldToComparableMapperParameterCache);
    }

    public void setStrategyEditTS(List<StrategyEditT> editTS) {
        this.strategyEditTS = editTS;
    }

    @Override
    public List<String> validateStrategyEditRuleAndGetErrorMessage() {
        return this.strategyEditTS.stream()
                .map(strategyEditT -> new Pair<>(this.IAtdlEditEvaluator
                        .validate(strategyEditT.getEdit()), strategyEditT.getErrorMessage()))
                .filter(booleanStringPair -> !booleanStringPair.getKey())
                .map(booleanStringPair -> booleanStringPair.getValue())
                .collect(Collectors.toList());
    }
}
