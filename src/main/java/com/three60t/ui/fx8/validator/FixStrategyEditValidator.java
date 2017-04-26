package com.three60t.ui.fx8.validator;

import com.three60t.fixatdl.core.ParameterT;
import com.three60t.fixatdl.validation.StrategyEditT;
import com.three60t.ui.evaluator.FixEditEvaluator;
import com.three60t.ui.evaluator.RecursiveFixEditEvaluator;
import javafx.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class FixStrategyEditValidator implements com.three60t.ui.validator.FixStrategyEditValidator {

    private FixEditEvaluator FixEditEvaluator;
    private List<StrategyEditT> strategyEditTS;
    private FieldToComparableMapperParameterCache fieldToComparableMapperParameterCache;

    public FixStrategyEditValidator(List<ParameterT> parameterTS) {
        assert (strategyEditTS != null && parameterTS != null);
        this.fieldToComparableMapperParameterCache = new FieldToComparableMapperParameterCache(parameterTS);
        this.FixEditEvaluator = new RecursiveFixEditEvaluator(this.fieldToComparableMapperParameterCache);
    }

    public void setStrategyEditTS(List<StrategyEditT> editTS) {
        this.strategyEditTS = editTS;
    }

    @Override
    public List<String> validateStrategyEditRuleAndGetErrorMessage() {
        return this.strategyEditTS.stream()
                .map(strategyEditT -> new Pair<>(this.FixEditEvaluator
                        .validate(strategyEditT.getEdit()), strategyEditT.getErrorMessage()))
                .filter(booleanStringPair -> !booleanStringPair.getKey())
                .map(booleanStringPair -> booleanStringPair.getValue())
                .collect(Collectors.toList());
    }
}
