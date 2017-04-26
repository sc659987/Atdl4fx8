package com.three60t.ui.common;

import com.three60t.fixatdl.core.StrategiesT;
import com.three60t.fixatdl.core.StrategyT;

import javax.xml.bind.Unmarshaller;
import java.io.File;

/***
 * FIX Algorithmic Trading Definition Language UI panel interface
 */
public interface FixAtdlUi<T> {

    /***
     *
     */
    T createStrategySelectionPanel();

    /***
     *
     * @return
     */
    T createUi();

    /***
     *
     * @param file
     */
    void parseFixAtdlFile(File file);

    /***
     * what to validate in a strategy
     *
     * @return
     */
    void validate(StrategyT strategyT);

    /***
     *
     * @return
     */
    StrategiesT getStrategies();

    /***
     *
     */
    void setSelectedStrategy(StrategyT strategyT);

    /***
     *
     * @return
     */
    StrategyT getSelectedStrategy();

    /***
     *
     * @return
     */
    Unmarshaller getUnmarshaller();

}
