package com.three60t.fixatdl.controlflow;

import com.three60t.fixatdl.ui.common.element.FixUiElement;

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
