package org.example.API.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.example.API.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RuleEngineController {

    @Autowired
    create_rule cr;
    @Autowired
    combineengine ce;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ASTService ast;
    @Autowired
    evaluateengine ee;
    @PostMapping("/create-rule")
    public String create_new_rule(@RequestParam String rule){

        if(cr.create_new_rule(rule) != null)
            return "Tree Builded";
        else
            return "Error while Building the tree";
    }

    @PostMapping("/evaluate-rule")
    public boolean evaluate_rule(@RequestBody Map<String, Object> json, @RequestParam String id){
        try {
//            System.out.println(json + " " + id);
//            Map<String, String> map=  objectMapper.readValue(json, Map.class);
            ObjectId objectId = new ObjectId(id);
            ASTENTITY combine_object = ast.getEntityById(objectId);
//            System.out.println(combine_object.getValue());
            if(evaluate_rule_api(combine_object, json))
                return true;
            else
                return false;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON string to Map", e);
        }
    }

    public boolean evaluate_rule_api(ASTENTITY ast, Map<String, Object> jsonString){
        try {
//            System.out.println(ast.getValue());
            return ee.evaluate_rule(ast, jsonString);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON string to Map", e);
        }
    }


    @PostMapping("/combine-rule")
    public String Combine_rule(@RequestParam String s){
        String[] rules = s.split(",");

        for(String rule : rules)
            System.out.println(rule);

        List<ASTENTITY> list = new ArrayList<>();

        for(String rule : rules){
            list.add(cr.create_new_rule(rule));
        }

        ast.saveAST(ce.combine_rules(list));


        return "Combined";
    }

}
