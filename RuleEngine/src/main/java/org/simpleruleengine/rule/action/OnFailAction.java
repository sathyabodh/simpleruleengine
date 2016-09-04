package org.simpleruleengine.rule.action;

import org.simpleruleengine.rule.Rule;
import org.simpleruleengine.rule.RuleContext;

@FunctionalInterface
public interface OnFailAction {
	void onFailure(Rule rule, RuleContext context);
}
