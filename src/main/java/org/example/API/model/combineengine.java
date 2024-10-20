package org.example.API.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class combineengine {

    @Autowired
    ASTService ast;
    private int combine_rules_most_frequent_operator(ASTENTITY rule){

        if(rule == null)
            return 0;

        int temp = 0;

        if(rule.getType().equals("AND")) temp = 1;
        else if(rule.getType().equals("OR")) temp = -1;

        return temp  + combine_rules_most_frequent_operator(rule.getLeft()) + combine_rules_most_frequent_operator(rule.getRight());


    }
    public ASTENTITY combine_rules(List<ASTENTITY> rules){

        Map<ASTENTITY, Integer> map = new HashMap<>();
        for(ASTENTITY rule : rules){
            map.put(rule, combine_rules_most_frequent_operator(rule));
        }

        while(rules.size() != 1){

            for(int i = 0;i < rules.size() - 1; i++){

                System.out.println(map.get(rules.get(i)) + " " + map.get(rules.get(i + 1)));

                ASTENTITY new_node = new ASTENTITY(rules.get(i), rules.get(i + 1),
                        (map.get(rules.get(i)) + map.get(rules.get(i + 1)) > 0 ? "AND" : "OR"));
                map.put(new_node, (map.get(rules.get(i)) + map.get(rules.get(i + 1))));
                rules.remove(i);
                rules.remove(i);
                // System.out.println(new_node.type + " " + i);
                rules.add(new_node);

            }

        }
        ast.saveAST(rules.get(0));
        return rules.get(0);
    }
}
