package org.simpleruleengine;

import org.simpleruleengine.rule.RuleContext;
import org.simpleruleengine.ruleset.RuleSet;

public class SimpleRuleEngine implements RuleEngine {

	@Override
	public void execute(RuleSet ruleSet,RuleContext context) {
		ruleSet.getRules().stream().anyMatch(rule->rule.evaulate(context));
	}

	@Override
	public void executeAll(RuleSet ruleSet, RuleContext context) {
		ruleSet.getRules().forEach(rule->rule.evaulate(context));
	}

}
