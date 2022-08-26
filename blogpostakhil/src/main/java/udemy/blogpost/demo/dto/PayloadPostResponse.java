package udemy.blogpost.demo.dto;

import java.util.List;

import lombok.Data;
import udemy.blogpost.demo.entity.BlogPost;

@Data
public class PayloadPostResponse {
	List<BlogPost> blogposts;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
