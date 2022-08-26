package udemy.blogpost.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import udemy.blogpost.demo.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	//@Query("select c from Comment c where c.BlogPost.Id = ?1")
	//List<Comment> findByPostId(long post_id);
	List<Comment> findByblogPostId(long blog_post_id);

}
