package work.szczepanskimichal.project13snippetapp.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Utils {

    private static List<String> colors;
    private static List<String> languages;

    public Utils() {
        colors = new ArrayList<>();
        colors.add("primary");
        colors.add("secondary");
        colors.add("success");
        colors.add("danger");
        colors.add("warning");
        colors.add("info");
        colors.add("light");
        colors.add("dark");
        colors.add("muted");
        colors.add("white");

        languages = new ArrayList<>();
        languages.add("Java");
        languages.add("Python");
        languages.add("C#");
        languages.add("C++");


    }


}
