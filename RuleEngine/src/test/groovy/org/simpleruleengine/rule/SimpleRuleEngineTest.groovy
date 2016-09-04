package org.simpleruleengine.rule

import groovy.transform.stc.MapEntryOrKeyValue.Options

import org.simpleruleengine.SimpleRuleEngine
import org.simpleruleengine.rule.action.OnFailAction;
import org.simpleruleengine.rule.action.OnSuccessAction;
import org.simpleruleengine.rule.condition.Conditions
import org.simpleruleengine.rule.condition.MVELCondition
import org.simpleruleengine.ruleset.RuleSet

import spock.lang.Specification

class SimpleRuleEngineTest extends Specification {

	class Employee{
		def name;
		def experiance;
		def designation;
		def type;
		def bonus = 0;
	}
	
	def "TestRulePriority"(){
		given:
				Employee emp = new Employee(name:"E1", experiance:experiance, designation:designation);
			
				MVELCondition cond1 = new MVELCondition("employee.experiance > 2");
				MVELCondition cond2 = new MVELCondition("employee.designation == 'Manager'");
				
//				Condition cond3 = RuleContext ctx -> { return "Development".equals(ctx.get("employee").division);} ;
				OnSuccessAction bonusForExperience = {aContext->
														Employee employee = aContext.get("employee"); 
														employee.bonus = 100;
													 } as OnSuccessAction;


				SimpleRule rule1 = new SimpleRule(cond1, Optional.of(bonusForExperience), Optional.ofNullable(null), 1);

				Conditions experienceWithDesignationConditions  = new Conditions(cond1);
				experienceWithDesignationConditions.and(cond2);
				OnSuccessAction bonsuForDesignation = {aContext->
														Employee employee = aContext.get("employee");
														employee.bonus = 150;
													   } as OnSuccessAction;

				SimpleRule rule2 = new SimpleRule(experienceWithDesignationConditions, Optional.of(bonsuForDesignation), Optional.ofNullable(null), 2);
				
				RuleSet set = new RuleSet();
				set.addRules(rule1, rule2);
				
				SimpleRuleEngine engine = new SimpleRuleEngine();
				
				RuleContext context = new RuleContext();
				context.add("employee", emp);
		when:
				engine.execute(set, context);
		then:
				emp.bonus == expectedBonus;
		where:
		experiance	|designation	|expectedBonus
		5			|"Engineer"		|100
		6			|"Manager"		|150	
	}
	
	
	def "TestExecuteAll"(){
		given:
				Employee emp = new Employee(name:"E1", experiance:experiance, designation:designation);
			
				MVELCondition cond1 = new MVELCondition("employee.experiance > 2");
				MVELCondition cond2 = new MVELCondition("employee.designation == 'Manager'");
				
//				Condition cond3 = RuleContext ctx -> { return "Development".equals(ctx.get("employee").division);} ;
				OnSuccessAction bonusForExperience = {aContext->
														Employee employee = aContext.get("employee");
														employee.bonus += 100;
													 } as OnSuccessAction;


				SimpleRule rule1 = new SimpleRule(cond1, Optional.of(bonusForExperience), Optional.ofNullable(null), 1);

				Conditions experienceWithDesignationConditions  = new Conditions(cond1);
				experienceWithDesignationConditions.and(cond2);
				OnSuccessAction bonsuForDesignation = {aContext->
														Employee employee = aContext.get("employee");
														employee.bonus += 150;
													   } as OnSuccessAction;

				SimpleRule rule2 = new SimpleRule(experienceWithDesignationConditions, Optional.of(bonsuForDesignation), Optional.ofNullable(null), 2);
				
				RuleSet set = new RuleSet();
				set.addRules(rule1, rule2);
				
				SimpleRuleEngine engine = new SimpleRuleEngine();
				
				RuleContext context = new RuleContext();
				context.add("employee", emp);
		when:
				engine.executeAll(set, context);
		then:
				emp.bonus == expectedBonus;
		where:
		experiance	|designation	|expectedBonus
		5			|"Engineer"		|100
		6			|"Manager"		|250
	}
}
