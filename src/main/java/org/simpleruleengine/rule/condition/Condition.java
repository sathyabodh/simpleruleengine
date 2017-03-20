package org.simpleruleengine.rule.condition;

import org.simpleruleengine.rule.RuleContext;

@FunctionalInterface
public interface Condition {
	public boolean evaluate(RuleContext context);
}
