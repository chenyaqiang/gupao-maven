package blog.login.validator;

import javax.validation.GroupSequence;

/**
 * 有序分组验证 
 * @author wangye
 *
 */
@GroupSequence({GroupValidate.class,GroupValidate2.class})
public interface GroupsSequenceValidate {

}
