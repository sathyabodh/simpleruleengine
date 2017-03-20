package org.simpleruleengine;

import org.simpleruleengine.rule.RuleContext;
import org.simpleruleengine.ruleset.RuleSet;

public interface RuleEngine {
	public void execute(RuleSet ruleSet, RuleContext context);
	public void executeAll(RuleSet ruleSet, RuleContext context);
}
