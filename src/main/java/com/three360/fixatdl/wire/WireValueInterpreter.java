package com.three360.fixatdl.wire;

import com.three360.fixatdl.core.StrategiesT;
import com.three360.fixatdl.core.StrategyT;

public interface WireValueInterpreter {

	void consumeWireString(String aa, StrategyT strategyT, StrategiesT strategiesT);
}
