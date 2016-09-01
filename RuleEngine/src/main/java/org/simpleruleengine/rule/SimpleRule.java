package org.simpleruleengine.rule;

import java.util.Optional;

import org.simpleruleengine.rule.action.OnFailAction;
import org.simpleruleengine.rule.action.OnSuccessAction;
import org.simpleruleengine.rule.condition.Condition;

public class SimpleRule implements Rule {

	private Condition condition;
	private Optional<OnSuccessAction> successAction ;
	private Optional<OnFailAction> failAction;
	private static final int DEFAULT_PRIORITY = 1 ;
	private int priority = DEFAULT_PRIORITY;

	public SimpleRule(Condition aCondition, Optional<OnSuccessAction> aSuccessAction, Optional<OnFailAction> aFailAction) {
		this(aCondition, aSuccessAction, aFailAction, DEFAULT_PRIORITY);
	}

	public SimpleRule(Condition aCondition, Optional<OnSuccessAction> aSuccessAction, Optional<OnFailAction> aFailAction, int priority) {
		this.condition = aCondition;
		successAction = aSuccessAction;
		failAction = aFailAction;
		this.priority = priority;
	}

	public boolean evaulate() {
		boolean result = condition.evaluate();
		if(result){
			OnSuccessAction action = successAction.orElse(()->{});
			action.onSuccess();
		}
		else{
			OnFailAction action = failAction.orElse(()->{});
			action.onFailure();
		}
		return false;
	}

	public int priority() {
		return priority;
	}

}
