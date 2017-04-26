package com.three60t.ui.controlflow;

import com.three60t.ui.common.element.FixUiElement;

public interface FixAtdlControlFlowRegister {

    /***
     *
     * @param fixUiElement
     */
    void registerControlFlow(FixUiElement<?, ? extends Comparable<?>> fixUiElement);

    /***
     *
     */
    void executeControlFlowForAllControls();

}
