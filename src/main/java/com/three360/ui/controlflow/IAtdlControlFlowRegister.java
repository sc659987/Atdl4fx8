package com.three360.ui.controlflow;

import com.three360.ui.common.element.IFixUiElement;

public interface IAtdlControlFlowRegister {

    void registerControlFlow(IFixUiElement<?, ? extends Comparable<?>> iFixUiElement);

    void executeControlFlowForAllControls();

}
