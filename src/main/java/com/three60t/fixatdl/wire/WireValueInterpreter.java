package com.three60t.fixatdl.wire;

import com.three60t.fixatdl.model.core.StrategiesT;
import com.three60t.fixatdl.model.core.StrategyT;

public interface WireValueInterpreter {

    void consumeWireString(String aa, StrategyT strategyT, StrategiesT strategiesT);
}
