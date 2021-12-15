package work.szczepanskimichal.project13snippetapp.utils;

public enum Colors {

    PRIMARY("text-primary"),
    SECONDARY("text-secondary"),
    SUCCESS("text-success"),
    DANGER("text-danger"),
    WARNING("text-warning"),
    INFO("text-info"),
    LIGHT("text-light"),
    DARK("text-dark"),
    MUTED("text-muted"),
    WHITE("text-white");

    private String bootstrapClass;

    Colors(String bootstrapClass) {
        this.bootstrapClass = bootstrapClass;
    }

}
