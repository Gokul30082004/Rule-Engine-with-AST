package org.example.API.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
@Component
public class create_rule {

    @Autowired
    ASTService ast;
    public ASTENTITY create_new_rule(String rule){

        List<String> prefix = infixToPostfix(Arrays.stream(rule.split("(?<=<|>|AND|OR|\\(|\\)|=)|(?=<|>|AND|OR|\\(|\\)|=)"))
                .map(String::trim)
                .filter(part -> !part.isEmpty())
                .toList());

        if(prefix.isEmpty())
            return null;
        ASTENTITY tree = buildtree(prefix);

        if(tree == null)
            return null;
//        printtree(tree);


        ast.saveAST(tree);

        return tree;
    }

    private List<String> infixToPostfix(List<String> s) {

        String regex = "'";

//        StringBuilder result = new StringBuilder();
        List<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.size(); i++) {

//            System.out.println(s.get(i));
//            System.out.println(s.get(i) + s.get(i).equals(")"));
            String c = s.get(i).replaceAll(regex, "");

            if (!c.equals("AND") && !c.equals("OR") && !c.equals("<") && !c.equals(">") && !c.equals("=") && !c.equals(")") && !c.equals("(")) {
//                System.out.println(c);
                result.add(c);
            }

            else if (c.equals("(")) {
//                System.out.println(c + " " + i);
                stack.push(c);
            }

            else if (c.equals(")")) {

                while (!stack.isEmpty() && !stack.peek().equals("(")) {
//                    System.out.println(stack.peek() + " " + i + " " + c);
                    result.add(stack.pop());
                }
//                System.out.println(stack.size());
                if(stack.isEmpty())
                    return new ArrayList<>();
                stack.pop();
            }

            else {
//              System.out.println(c);
                while (!stack.isEmpty() && (prec(c) >= prec(stack.peek()))) {
//                    System.out.println(stack.peek() + " " + i + " " + c);
                    result.add(stack.pop());
                }

                stack.push(c);
            }
            // System.out.println(c.equals("AND"));
        }

        // Pop all the remaining elements from the stack
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
        // result.remove("'");

//         System.out.println(result);
//        return Arrays.stream(result.toString().split("(?<=AND|OR|<|>|=)|(?=AND|OR|<|>|=)"))
//                .map(String::trim)
//                .filter(part -> !part.isEmpty())
////                .toList();
    }

    private int prec(String c) {
        if (c.equals("AND") || c.equals("OR"))
            return 2;
        else if(c.equals("("))
            return 3;
        else
            return 1;
    }


    public ASTENTITY buildtree(List<String> s){

        Stack<String>  token= new Stack<>();
        Stack<ASTENTITY> node = new Stack<>();

        for(String st : s) {

            System.out.println(st);
            if(!st.equals("AND") && !st.equals("OR") && !st.equals("<") && !st.equals(">") && !st.equals("=")) {
                token.push(st);
            }
            else if(!st.equals("AND") && !st.equals("OR")) {

                if(token.size() < 2)
                    return null;

                String token1 = token.pop();
                String token2 = token.pop();
                node.push(new ASTENTITY(token1, token2, st));
            }
            else {
                ASTENTITY node1 = node.pop();
                ASTENTITY node2 = node.pop();
                node.push(new ASTENTITY(node1, node2, st));

            }
        }

//      System.out.println(node.size());

        return node.peek();
    }
    public void printtree(ASTENTITY s) {

        if(s == null)
            return;

//        System.out.println(s.getType());
        printtree(s.getLeft());
        printtree(s.getRight());

    }

}

