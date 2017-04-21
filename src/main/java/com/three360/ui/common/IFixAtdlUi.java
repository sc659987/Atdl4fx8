package com.three360.ui.common;

import com.three360.fixatdl.core.StrategiesT;
import com.three360.fixatdl.core.StrategyT;

import javax.xml.bind.Unmarshaller;
import java.io.File;

/***
 * FIX Algorithmic Trading Definition Language UI panel interface
 */
public interface IFixAtdlUi<T> {

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
