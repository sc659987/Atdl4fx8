package com.three60t.fixatdl.converter.test;

import com.three60t.fixatdl.converter.ForClass;
import com.three60t.fixatdl.converter.Map;
import com.three60t.fixatdl.model.core.UTCTimestampT;


@ForClass(UTCTimestampT.class)
public class UTCTimeOnlyConverter {

    @Map(from = String.class, to = UTCTimestampT.class)
    public UTCTimestampT from(String fromString) {
        return null;
    }

}
