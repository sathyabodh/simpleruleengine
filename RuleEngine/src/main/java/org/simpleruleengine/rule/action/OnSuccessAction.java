package org.simpleruleengine.rule.action;

import org.simpleruleengine.rule.RuleContext;

@FunctionalInterface
public interface OnSuccessAction {
	void onSuccess(RuleContext context);
}
