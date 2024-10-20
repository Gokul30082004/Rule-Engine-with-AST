package org.example.API.model;

import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class evaluateengine {

    public boolean evaluate_rule(ASTENTITY ast, Map<String, Object> given_data){

        System.out.println(ast.getType());
        if(ast.getType().equals("AND")) {
            return evaluate_rule(ast.getLeft(), given_data) && evaluate_rule(ast.getRight(), given_data);
        }
        else if(ast.getType().equals("OR"))
            return evaluate_rule(ast.getLeft(), given_data) || evaluate_rule(ast.getRight(), given_data);
        else if(ast.getType().equals("<")){

            System.out.println((given_data.get(ast.getKey())) + " " + ast.getValue());

            if((int)(given_data.get(ast.getKey())) < Integer.parseInt(ast.getValue()))
                return true;
            else
                return false;

        }
        else if(ast.getType().equals(">")){
            System.out.println((given_data.get(ast.getKey())) + " " + ast.getValue());
            if((int)(given_data.get(ast.getKey())) > Integer.parseInt(ast.getValue()))
                return true;
            else
                return false;
        }
        else{
            System.out.println((given_data.get(ast.getKey())) + " " + ast.getValue());
            if(given_data.get(ast.getKey()).equals(ast.getValue()))
                return true;
            else
                return false;
        }
    }

}
