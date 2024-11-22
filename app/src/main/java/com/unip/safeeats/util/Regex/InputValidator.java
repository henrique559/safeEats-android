package com.unip.safeeats.util.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    public static boolean isInputValid(String input,  String pattern){
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);

        return matcher.matches();
    }

    public static String emailPattern(){ return "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"; }
    public static String telefonePattern(){ return ".*"; }
    public static String cepPattern(){ return ".*"; }
    public static String numPattern(){ return "\\d{4}"; }
    public static String cpfPattern(){ return ".*"; }

}
