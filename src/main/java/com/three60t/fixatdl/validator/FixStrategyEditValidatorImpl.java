package com.three60t.fixatdl.validator;

import com.three60t.fixatdl.evaluator.FixAtdlEditEvaluator;
import com.three60t.fixatdl.evaluator.RecursiveFixAtdlEditEvaluator;
import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.validation.StrategyEditT;
import javafx.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class FixStrategyEditValidatorImpl implements FixStrategyEditValidator {

    private FixAtdlEditEvaluator FixAtdlEditEvaluator;
    private List<StrategyEditT> strategyEditTS;
    private ParameterNameToConstValueCachedMap fieldToComparableMapperParameterCache;

    public FixStrategyEditValidatorImpl(List<ParameterT> parameterTS) {
        assert (strategyEditTS != null && parameterTS != null);
        this.fieldToComparableMapperParameterCache = new ParameterNameToConstValueCachedMap(parameterTS);
        this.FixAtdlEditEvaluator = new RecursiveFixAtdlEditEvaluator(this.fieldToComparableMapperParameterCache);
    }

    public void setStrategyEditTS(List<StrategyEditT> editTS) {
        this.strategyEditTS = editTS;
    }

    @Override
    public List<String> validateStrategyEditRuleAndGetErrorMessage() {
        return this.strategyEditTS.stream()
                .map(strategyEditT -> new Pair<>(this.FixAtdlEditEvaluator
                        .validate(strategyEditT.getEdit()), strategyEditT.getErrorMessage()))
                .filter(booleanStringPair -> !booleanStringPair.getKey())
                .map(booleanStringPair -> booleanStringPair.getValue())
                .collect(Collectors.toList());
    }
}
