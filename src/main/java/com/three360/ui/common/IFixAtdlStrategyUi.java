package com.three360.ui.common;

import com.three360.fixatdl.core.StrategyT;

/***
 *
 * @param <T>
 */
public interface IFixAtdlStrategyUi<T> {

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
