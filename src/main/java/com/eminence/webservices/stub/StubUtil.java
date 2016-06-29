package com.eminence.webservices.stub;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StubUtil {

    static Matcher getMatcher(String url, String urlPattern) {
        String regex = urlPattern.replace("*","(.*)");
        Pattern p = Pattern.compile(regex);
        return p.matcher(url);
    }

    static Map<String,String> getPathVariableValues(String url, String urlPattern, String[] pathVariableNames) {
        Map<String,String> pathVariables = new HashMap<>();
        Matcher matcher = getMatcher(url, urlPattern);
        if(matcher.find()) {
            for(int i=0; i<= matcher.groupCount(); i++) {
                String group = matcher.group(i);
                if(i != 0){
                    pathVariables.put(pathVariableNames[i-1],group);
                }
            }
        }
        return pathVariables;
    }

    public static void main(String[] args) {
        String urlPattern = "/app/rest/users/*/*";
        String url = "http://localhost:9090/app/rest/users/MYNAME/123";
        String[] pathVariablenNames = {"username","id"};

        Map<String, String> pathVariableValues = getPathVariableValues(url, urlPattern, pathVariablenNames);
        System.out.println(pathVariableValues);
    }

    boolean urlMatches(String url, String urlPattern) {
        return getMatcher(url, urlPattern).find();
    }

}
