package work.szczepanskimichal.project13snippetapp.utils;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// check below anotation on front
//@RequiredArgsConstructor
public enum Languages {

    NOT_AVAILABLE("N/A"),
    PYTHON("Python"),
    JAVA("Java"),
    C("C"),
    JAVASCRIPT("JavaScript");

    private String val;

    // values - iteruje po enumach
    private static List<Languages> languageList = Arrays.asList(values());

    Languages(String val){
        this.val = val;
    }

    public static List<String> getLanguages() {
        return languageList.stream()
                .map(l -> l.val)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return this.val;
    }

}
