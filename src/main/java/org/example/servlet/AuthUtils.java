package org.example.servlet;

import java.util.regex.Pattern;

public class AuthUtils {
    public static String getLoginFromBody(String body){
        Pattern patternLog = Pattern.compile("(?<=login=)(.*?)(?=&|$)");

        return patternLog.matcher(body)
                .results()
                .map(matchResult -> matchResult.group(1))
                .findFirst()
                .orElse("");
    }

    public static String getPassFromBody(String body){
        Pattern patternPas = Pattern.compile("(?<=pass=)(.*?)(?=&|$)");

        return patternPas.matcher(body)
                .results()
                .map(matchResult -> matchResult.group(1))
                .findFirst()
                .orElse("");
    }
}
