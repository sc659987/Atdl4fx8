package com.three60t.fixatdl.controlflow;

import com.three60t.fixatdl.model.layout.ControlT;
import com.three60t.fixatdl.model.validation.EditT;
import com.three60t.fixatdl.ui.common.element.FixUiElement;

import java.util.*;
import java.util.stream.Collectors;

public class FixAtdlControlFlowRegister {

	// controlId and Fx Ui element which are dependent on that Id for control flow
	private Map<String, Set<FixUiElement>> controlIdUiElementMap;

	// evaluate the condition of state rule
	private FixAtdlStateRuleEvaluator fixAtdlStateRuleEvaluator;

	// act of result of fixAtdlStateRuleEvaluator
	private FixAtdlStateRuleResultActor fxFixAtdlStateRuleResultActor;

	//
	private Map<String, FixUiElement<?, ?>> allIFixUiElements = new HashMap<>();

	private static FixAtdlControlFlowRegister _singleton;

	public static synchronized FixAtdlControlFlowRegister getSingleTon() {
		return _singleton == null ? _singleton = new FixAtdlControlFlowRegister() : _singleton;
	}

	private FixAtdlControlFlowRegister() {
		this.controlIdUiElementMap = new LinkedHashMap<>();
		this.fixAtdlStateRuleEvaluator = new FixAtdlStateRuleEvaluatorImpl(this.allIFixUiElements);
		this.fxFixAtdlStateRuleResultActor = new FixAtdlStateRuleResultActorImpl();
	}

	public void registerControlFlow(FixUiElement<?, ? extends Comparable<?>> fixUiElement) {
		allIFixUiElements.put(fixUiElement.getControl().getID(), fixUiElement);
		// get control from fixUiElement
		ControlT iFixUiElementControlT = fixUiElement.getControl();
		// for each StateRule map all control Id this UiElement depends on
		iFixUiElementControlT.getStateRule().forEach(stateRuleT -> getAllControlIdFromEdit(stateRuleT.getEdit()).forEach(controlId -> {
			Set<FixUiElement> fixUiElements = this.controlIdUiElementMap.getOrDefault(controlId, new LinkedHashSet<>());
			fixUiElements.add(fixUiElement);
			this.controlIdUiElementMap.put(controlId, fixUiElements);
		}));
		// listen to all
		// TODO change redundant split of string extend SingleObjectProperty and change it's behavior
		fixUiElement.listenChange().addListener((observable, oldValue, newValue) -> executeControlFLowByControlId(newValue.split(":")[0]));
	}

	private void executeControlFLowByControlId(String controlId) {
		this.controlIdUiElementMap.getOrDefault(controlId, new LinkedHashSet<>())
				.forEach(effectedIFixElement -> effectedIFixElement.getControl().getStateRule().forEach(stateRuleT -> this.fixAtdlStateRuleEvaluator
						.getResult(stateRuleT).forEach(atdlStateRuleResultTypeComparablePair -> this.fxFixAtdlStateRuleResultActor
								.doAct(atdlStateRuleResultTypeComparablePair, effectedIFixElement))));
	}

	public void executeControlFlowForAllControls() {
		this.controlIdUiElementMap.keySet().stream().forEach(this::executeControlFLowByControlId);
	}

	private Set<String> getAllControlIdFromEdit(EditT editT) {
		if (editT.getLogicOperator() != null) {
			return editT.getEdit().stream()
                    .map(this::getAllControlIdFromEdit)
                    .flatMap(Set::stream)
                    .collect(Collectors.toSet());
		} else {
			Set<String> stringList = new HashSet<>();
			if (editT.getField() != null)
				stringList.add(editT.getField());
			if (editT.getField2() != null)
				stringList.add(editT.getField2());
			return stringList;
		}
	}

}
