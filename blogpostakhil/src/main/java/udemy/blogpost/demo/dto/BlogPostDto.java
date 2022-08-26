package udemy.blogpost.demo.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import udemy.blogpost.demo.entity.Comment;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostDto {
	//ApiModelProperty is swagger annotation that helps to add more info  for fields of model.
	
    	@ApiModelProperty(value = "Post id. This must be unique")
		private long id;
    	
		// post title cannot be null or empty. so use @NotEmpty annotation. 
		@NotEmpty  
		@Size(min=2 , message="Post title must have character greater than 2")
	    @ApiModelProperty(value = "Post title. Post title must have character greater than 2")
		private String title;
		
		@NotEmpty
		@Size(min=3 , message="Post description must be alteast 3 characters")
		@ApiModelProperty(value = "Post description. Post description must be alteast 3 characters")
		private String description;
		
		@NotEmpty
		@ApiModelProperty(value = "Post Content.The content cannot be empty")
		private String Content;
		private List<Comment> comments;
}
