package blog.login.validator;

import javax.validation.GroupSequence;

/**
 * ���������֤ 
 * @author wangye
 *
 */
@GroupSequence({GroupValidate.class,GroupValidate2.class})
public interface GroupsSequenceValidate {

}
