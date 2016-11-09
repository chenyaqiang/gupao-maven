package blog.login.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy=MinValidator.class)  
public @interface Min {  
   
    int value() default 0;  
     
    String message();  
     
    Class<?>[] groups() default {};  
     
    Class<? extends Payload>[] payload() default {};  
}  
