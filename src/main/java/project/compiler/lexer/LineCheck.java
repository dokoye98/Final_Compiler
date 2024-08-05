package project.compiler.lexer;

import java.util.ArrayList;
import java.util.List;

public class LineCheck {


    public static List<String> lineChecker(String input){
        List<String> lines = new ArrayList<>();
        if (containsCheck(input)) {
            String[] twoLines = input.split(";");
            for (String line : twoLines) {
                String trimmedLine = line.trim();
                if (!trimmedLine.isEmpty()) {
                    lines.add(trimmedLine);
                }
            }
        } else {
            String trimmedInput = input.trim();
            if (!trimmedInput.isEmpty()) {
                lines.add(trimmedInput);
            }
        }

        return lines;
    }

    private static boolean containsCheck(String input){
        return input.contains(";");
    }


}
