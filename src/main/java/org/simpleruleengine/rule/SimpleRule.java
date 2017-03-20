package org.simpleruleengine.rule;

import java.util.Optional;

import org.simpleruleengine.rule.action.OnFailAction;
import org.simpleruleengine.rule.action.OnSuccessAction;
import org.simpleruleengine.rule.condition.Condition;

public class SimpleRule implements Rule {
	private String name;
	private Condition condition;
	private Optional<OnSuccessAction> successAction ;
	private Optional<OnFailAction> failAction;
	private int priority = DEFAULT_PRIORITY;

	public SimpleRule(String name, Condition aCondition, Optional<OnSuccessAction> aSuccessAction, Optional<OnFailAction> aFailAction) {
		this(name, aCondition, aSuccessAction, aFailAction, DEFAULT_PRIORITY);
	}

	public SimpleRule(String name,Condition aCondition, Optional<OnSuccessAction> aSuccessAction, Optional<OnFailAction> aFailAction, int priority) {
		this.condition = aCondition;
		successAction = aSuccessAction;
		failAction = aFailAction;
		this.priority = priority;
	}

	public boolean evaulate(RuleContext context) {
		boolean result = condition.evaluate(context);
		if(result){
			if(successAction.isPresent())
				successAction.get().onSuccess(context);
		}
		else{
			if(failAction.isPresent())
				failAction.get().onFailure(this, context);
		}
		return result;
	}

	public int priority() {
		return priority;
	}

	public String getName() {
		return name;
	}

}
