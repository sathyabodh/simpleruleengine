package org.simpleruleengine;

import org.simpleruleengine.ruleset.RuleSet;

@FunctionalInterface
public interface RuleEngine {
	public void execute(RuleSet ruleSet);
}
