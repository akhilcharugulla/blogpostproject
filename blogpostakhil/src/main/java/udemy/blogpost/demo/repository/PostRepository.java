package udemy.blogpost.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemy.blogpost.demo.entity.BlogPost;

@Repository
public interface PostRepository extends JpaRepository<BlogPost, Long>{

}
