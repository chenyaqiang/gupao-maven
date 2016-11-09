package blog.login.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinValidator implements ConstraintValidator<Min, Integer> {  
	   
    private int minValue;  
     
    public void initialize(Min min) {  
       // TODO Auto-generated method stub  
       //把Min限制类型的属性value赋值给当前ConstraintValidator的成员变量minValue  
       minValue = min.value();  
    }  
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext arg1) {  
       // TODO Auto-generated method stub  
       //在这里我们就可以通过当前ConstraintValidator的成员变量minValue访问到当前限制类型Min的value属性了  
       return value >= minValue;  
       }
   }

	
