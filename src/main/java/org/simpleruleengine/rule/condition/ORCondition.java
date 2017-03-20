package org.simpleruleengine.rule.condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.simpleruleengine.rule.RuleContext;

public class ORCondition implements Condition {
	private List<Condition> conditions = new ArrayList<>();
	
	public ORCondition(Condition condition1, Condition condition2, Condition ...conditions) {
		this.conditions.add(condition1);
		this.conditions.add(condition2);
		this.conditions.addAll(Arrays.asList(conditions));
	}
	
	@Override
	public boolean evaluate(RuleContext context) {
		return conditions.stream().anyMatch(cond->cond.evaluate(context));
	}
	
	@Override
	public String toString() {
		String conditionString = "(";
		for(int i =0; i < conditions.size(); ++i){
			if(i > 0)
				conditionString += " || ";
			conditionString += conditions.get(i).toString();
		}
		conditionString = ")";
		return conditionString;
	}
}
