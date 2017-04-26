package com.three60t.ui.abs;

import com.three60t.fixatdl.core.StrategiesT;
import com.three60t.fixatdl.core.StrategyT;
import com.three60t.ui.common.FixAtdlUi;

import javax.xml.bind.JAXBElement;
import java.io.File;

public abstract class AbstractFixAtdlUi<T> implements FixAtdlUi<T> {

    protected StrategiesT strategiesT;

    protected StrategyT selectedStrategyT;

    @Override
    public void parseFixAtdlFile(File file) {
        try {
            if (file != null && file.exists() && !file.isDirectory()) {
                JAXBElement o = (JAXBElement) getUnmarshaller().unmarshal(file);
                if (o.getValue() instanceof StrategiesT) {
                    this.strategiesT = (StrategiesT) o.getValue();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void validate(StrategyT strategyT) {
        // TODO validate the xml validation take it from atdl4j

    }

    @Override
    public StrategiesT getStrategies() {
        return this.strategiesT;
    }

    @Override
    public void setSelectedStrategy(StrategyT strategyT) {
        this.selectedStrategyT = strategyT;
    }

    @Override
    public StrategyT getSelectedStrategy() {
        return this.selectedStrategyT;
    }

    public StrategyT findStrategyTByName(final String s) {
        return this.strategiesT != null ? this.strategiesT
                .getStrategy()
                .parallelStream()
                .filter(strategyT -> strategyT.getUiRep().equals(s))
                .findFirst()
                .orElse(null) : null;
    }

}
