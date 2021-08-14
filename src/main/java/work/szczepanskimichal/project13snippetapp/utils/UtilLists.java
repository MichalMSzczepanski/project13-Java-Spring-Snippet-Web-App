package work.szczepanskimichal.project13snippetapp.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class UtilLists {

    private List<String> colors;
    private List<String> languages;
    private List<String> favorite;
    private List<String> visibility;
    private List<String> defaultFolder;

    public UtilLists() {
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

//        temporarry list
        languages = new ArrayList<>();
        languages.add("Java");
        languages.add("Python");
        languages.add("C#");
        languages.add("C++");

        favorite = new ArrayList<>();
        favorite.add("yes");
        favorite.add("no");

        visibility = new ArrayList<>();
        visibility.add("private");
        visibility.add("public");

        defaultFolder = new ArrayList<>();
        defaultFolder.add("default folder");

    }


}
