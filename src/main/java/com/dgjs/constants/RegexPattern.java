package com.dgjs.constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPattern {

	public static String email="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

    public static boolean isMatch(String pattern,String str){
    	  Pattern p = Pattern.compile(pattern);  
          Matcher m = p.matcher(str);  
          return m.matches();  
    }
}
