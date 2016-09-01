package org.simpleruleengine.rule.condition;

public class SimpleCondition implements Condition {
	private String expression;
	public SimpleCondition(String aExpression){
		this.expression = aExpression;
	}
	
	public boolean evaluate() {
		return false;
	}

}
