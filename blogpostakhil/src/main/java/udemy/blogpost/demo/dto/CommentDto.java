package udemy.blogpost.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommentDto {
	@ApiModelProperty(value="Comment id. This must be unique")
	private long id;
	
	@NotEmpty
	@Size(min = 3,message="Name cannot be null or empty")
	@ApiModelProperty(value="user name. Name cannot be null or empty")
	private String name;
	
	@NotEmpty
	@Email(message="email cannot be null or empty")
	@ApiModelProperty(value="Users Emaild id. This must be uniqu and email cannot be null or emptye")
	private String email;
	
	@NotEmpty
	@Size(min=3, message="body cannot be null or empty ")
	@ApiModelProperty(value="Comments body. body  cannot be null or empty")
	private String body;
}
