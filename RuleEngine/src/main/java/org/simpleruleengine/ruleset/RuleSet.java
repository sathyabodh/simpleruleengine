package org.simpleruleengine.ruleset;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

import org.simpleruleengine.rule.Rule;

public class RuleSet {
	private TreeSet<Rule> rules ;
	public RuleSet() {
		rules = new TreeSet<>((rule1, rule2)-> rule2.priority() - rule1.priority());
	}
	
	public void addRules(Rule...aRules){
		rules.addAll(Arrays.asList(aRules));
	}
	
	public Collection<Rule> getRules(){
		return Collections.unmodifiableCollection(rules);
	}
}
