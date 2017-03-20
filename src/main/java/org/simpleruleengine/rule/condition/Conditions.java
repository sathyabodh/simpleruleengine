package org.simpleruleengine.rule.condition;

import org.simpleruleengine.rule.RuleContext;

public class Conditions implements Condition{

	private Condition condition = null ;
	
	public Conditions(Condition condition){
		this.condition = condition;
	}
	
	public Conditions and(Condition condition1,Condition...conditions){
		condition = new AndCondition(condition, condition1, conditions);
		return this;
	}
	
	public Conditions or(Condition condition1, Condition...conditions){
		condition = new ORCondition(condition, condition1, conditions);
		return this;
	}

	public Condition build() {
		return condition;
	}
	
	@Override
	public boolean evaluate(RuleContext context) {
		return condition.evaluate(context);
	}

}
