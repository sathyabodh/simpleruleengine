package org.simpleruleengine.rule.condition;

import java.io.Serializable;

import org.mvel2.MVEL;
import org.simpleruleengine.rule.RuleContext;

public class MVELCondition implements Condition {
	private Serializable expression;
	private String strExpression ;
	
	public MVELCondition(String aExpression){
		this.expression = MVEL.compileExpression(aExpression);
		strExpression = aExpression;
	}
	
	public boolean evaluate(RuleContext context) {
		return (boolean)MVEL.executeExpression(expression, context.getContext());
	}
	
	@Override
	public String toString() {
		return strExpression;
	}

}
