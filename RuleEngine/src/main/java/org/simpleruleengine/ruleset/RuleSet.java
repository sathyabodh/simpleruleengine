package org.simpleruleengine.ruleset;

import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

import org.simpleruleengine.rule.Rule;

public class RuleSet {
	private TreeSet<Rule> rules ;
	public RuleSet() {
		rules = new TreeSet<>((rule1, rule2)-> rule2.priority() - rule1.priority());
	}
	
	public void addRules(Rule rule){
		rules.add(rule);
	}
	
	public Collection<Rule> getRules(){
		return Collections.unmodifiableCollection(rules);
	}
}
