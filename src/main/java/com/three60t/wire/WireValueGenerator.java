package com.three60t.wire;

import com.three60t.fixatdl.core.StrategiesT;
import com.three60t.fixatdl.core.StrategyT;

/****
 *
 */
public interface WireValueGenerator {

    String generateWireValue(StrategiesT strategies, StrategyT selectedStrategy);

}
