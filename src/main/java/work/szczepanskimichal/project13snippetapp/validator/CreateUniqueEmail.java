package work.szczepanskimichal.project13snippetapp.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CreateUniqueEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateUniqueEmail {
    String message() default "{uniqueEmail.error.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
