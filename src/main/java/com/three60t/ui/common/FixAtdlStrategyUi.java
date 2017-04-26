package com.three60t.ui.common;

import com.three60t.fixatdl.core.StrategyT;

/***
 *
 * @param <T>
 */
public interface FixAtdlStrategyUi<T> {

    /***
     *
     * @param strategy
     */
    void setStrategy(StrategyT strategy);

    /***
     *
     * @return
     */
    StrategyT getStrategy();

    /***
     *
     * @return
     */
    T getLayout();

    /***
     *
     * @param t
     * @return
     */
    T getLayoutWithFlowControlRules(T t);

    /***
     *
     * @param t
     * @return
     */
    T getLayoutWithValidationRules(T t);

}
