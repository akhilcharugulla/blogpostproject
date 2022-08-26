package udemy.blogpost.demo.service;

import java.util.List;

import udemy.blogpost.demo.dto.BlogPostDto;
import udemy.blogpost.demo.dto.PayloadPostResponse;
import udemy.blogpost.demo.entity.BlogPost;

public interface PostService {
	public List<BlogPostDto> getAllPosts();
	public BlogPostDto createPost(BlogPostDto postdto);
	public BlogPostDto getPostById(long id);
	public BlogPostDto updatePost(long id, BlogPostDto newpost);
	public void deletePostById(long id);
	public PayloadPostResponse getAllPostsPagination(int PayloadResponse, int pageSize);
}
