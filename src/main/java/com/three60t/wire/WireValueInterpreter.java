package com.three60t.wire;

import com.three60t.fixatdl.core.StrategiesT;
import com.three60t.fixatdl.core.StrategyT;

public interface WireValueInterpreter {

    void consumeWireString(String aa, StrategyT strategyT, StrategiesT strategiesT);
}
