package com.three360.fixatdl.wire;

import com.three360.fixatdl.core.StrategiesT;
import com.three360.fixatdl.core.StrategyT;

/****
 *
 */
public interface WireValueGenerator {

	String generateWireValue(StrategiesT strategies, StrategyT selectedStrategy);

}
