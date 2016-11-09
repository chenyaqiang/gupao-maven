package blog.login.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinValidator implements ConstraintValidator<Min, Integer> {  
	   
    private int minValue;  
     
    public void initialize(Min min) {  
       // TODO Auto-generated method stub  
       //��Min�������͵�����value��ֵ����ǰConstraintValidator�ĳ�Ա����minValue  
       minValue = min.value();  
    }  
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext arg1) {  
       // TODO Auto-generated method stub  
       //���������ǾͿ���ͨ����ǰConstraintValidator�ĳ�Ա����minValue���ʵ���ǰ��������Min��value������  
       return value >= minValue;  
       }
   }

	
