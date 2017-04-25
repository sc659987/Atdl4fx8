package com.three360.ui.fx8.validator;

import com.three360.fixatdl.validation.EditT;
import com.three360.ui.validator.FieldToComparableMapperCache;
import com.three360.ui.validator.IAtdlEditEvaluator;

/***
 *
 */
public class RecursiveAtdlEditEvaluator implements IAtdlEditEvaluator {

    private FieldToComparableMapperCache fieldToComparableMapperCache;

    public RecursiveAtdlEditEvaluator(FieldToComparableMapperCache fieldToComparableMapperCache) {
        this.fieldToComparableMapperCache = fieldToComparableMapperCache;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public boolean validate(EditT editT) {

        if (isLogicalEdit(editT)) {
            switch (editT.getLogicOperator()) {
                case AND:
                    // AND - behaves as short circuit
                    return editT.getEdit().stream().allMatch(this::validate);
                case OR:
                    // OR - new interpretation
                    return editT.getEdit().parallelStream().anyMatch(this::validate);
                default:
                    return false;
            }
        } else {
            Comparable comparable1, comparable2;
            comparable1 = fieldToComparableMapperCache.get(editT.getField());
            comparable2 = fieldToComparableMapperCache.get(editT.getField2());

            if (comparable2 == null)
                comparable2 = tryToConvert(comparable1, editT.getValue());

            switch (editT.getOperator()) {
                case EQ:
                    return comparable1 != null && comparable1.compareTo(comparable2) == 0;
                case EX:
                    return comparable1 != null;
                case GE:
                    return comparable1 != null && comparable1.compareTo(comparable2) >= 0;
                case GT:
                    return comparable1 != null && comparable1.compareTo(comparable2) > 0;
                case LE:
                    return comparable1 != null && comparable1.compareTo(comparable2) <= 0;
                case LT:
                    return comparable1 != null && comparable1.compareTo(comparable2) < 0;
                case NE:
                    return comparable1 != null && comparable1.compareTo(comparable2) != 0;
                case NX:
                    return comparable1 == null;
                default:
                    return false;
            }
        }
    }

    // Make changer here
    private Comparable tryToConvert(Comparable o1, Comparable o2) {
        if (Integer.class.isInstance(o1) && o2 instanceof String) {
            return Integer.parseInt((String) o2);
        } else if (Boolean.class.isInstance(o1) && o2 instanceof String) {
            return Boolean.parseBoolean((String) o2);
        }
        return o2;
    }

    public boolean isLogicalEdit(EditT editT) {
        return editT.getLogicOperator() != null;
    }
}
