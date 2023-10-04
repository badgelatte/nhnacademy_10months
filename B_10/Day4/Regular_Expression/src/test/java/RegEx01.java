

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class RegEx01 {
    @Test
    public void givenText_whenSimpleRegexMatches_thenCorrect() {
        Pattern pattern = Pattern.compile("foo");
        Matcher matcher = pattern.matcher("foo");
        
        assertTrue(matcher.find());
    }
}
