package study;

import java.util.regex.Pattern;

public class SpellChecker {
    
    private static final Pattern ROMAN = Pattern.compile("^(?=[MDCLXVI])M*D?C{0,4}L?X{0,4}V?I{0,4}$");
    
    public boolean hasRomanNumeral(String inputText) { 
        return ROMAN.matcher(inputText).matches();
     }
}
