package com.example.presidentapp;

public class Rule{
    String ruleId;
    String rule;

    public Rule(){}

    public Rule(String ruleId,String rule) {
        this.ruleId = ruleId;
        this.rule = rule;
    }

    public String getRuleId(){
        return ruleId;
    }

    public String getRule() {
        return rule;
    }

}
