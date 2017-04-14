package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xuch.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Test {
    String index() default "";

    Class expected() default None.class;

    class None extends Throwable {
        private static final long serialVersionUID = 1L;

        private None() {}

    }
}
