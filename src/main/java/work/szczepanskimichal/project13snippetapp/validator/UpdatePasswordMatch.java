package work.szczepanskimichal.project13snippetapp.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UpdatePasswordMatchValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UpdatePasswordMatch {
    String message() default "{passwords.dont.match}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
