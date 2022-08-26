package udemy.blogpost.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import udemy.blogpost.demo.dto.BlogPostDto;
import udemy.blogpost.demo.dto.PayloadPostResponse;
import udemy.blogpost.demo.entity.BlogPost;
import udemy.blogpost.demo.exception.ResourceNotFoundException;
import udemy.blogpost.demo.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository pRepo;

//	@Autowired
//	ModelMapper mapper;

	@Override
	public List<BlogPostDto> getAllPosts() {
		List<BlogPost> blopPostsList = pRepo.findAll();
		List<BlogPostDto> blopPostsDtoList = blopPostsList.stream().map(i -> mapEntityToDto(i))
				.collect(Collectors.toList());
		return blopPostsDtoList;
	}

	@Override
	public PayloadPostResponse getAllPostsPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<BlogPost> posts = pRepo.findAll(pageable);
		List<BlogPost> listBlogposts = posts.getContent();
		PayloadPostResponse ploadResponse = new PayloadPostResponse();
		ploadResponse.setBlogposts(listBlogposts);
		ploadResponse.setPageNo(posts.getNumber());
		ploadResponse.setPageSize(posts.getSize());
		ploadResponse.setTotalElements(posts.getTotalElements());
		ploadResponse.setLast(posts.isLast());
		return ploadResponse;
	}

	@Override
	public BlogPostDto createPost(BlogPostDto post) {
		BlogPost blogpost = mapDtoToEntity(post);
		BlogPost newblogpost = pRepo.save(blogpost);
		return mapEntityToDto(newblogpost);
	}

	public BlogPostDto getPostById(long id) {
		BlogPost blogpost = new BlogPost();
		blogpost = pRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		return mapEntityToDto(blogpost);
	}

	@Override
	public BlogPostDto updatePost(long id, BlogPostDto newpostToUpdate) {
		BlogPost newBlogPost = pRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		newBlogPost.setContent(newpostToUpdate.getContent());
		newBlogPost.setDescription(newpostToUpdate.getDescription());
		newBlogPost.setTitle(newpostToUpdate.getTitle());
		BlogPost updatedBlogPost = pRepo.save(newBlogPost);
		return mapEntityToDto(updatedBlogPost);
	}

	@Override
	public void deletePostById(long id) {
		pRepo.deleteById(id);
	}

	public BlogPostDto mapEntityToDto(BlogPost blogpost) {
//		BlogPostDto blogpostdto = mapper.map(blogpost, BlogPostDto.class);
		BlogPostDto blogpostdto = new BlogPostDto();
		blogpostdto.setId(blogpost.getId());
		blogpostdto.setContent(blogpost.getContent());
		blogpostdto.setDescription(blogpost.getDescription());
		blogpostdto.setTitle(blogpost.getTitle());
		blogpostdto.setComments(blogpost.getComments());
		return blogpostdto;
	}

	public BlogPost mapDtoToEntity(BlogPostDto blogpostdto) {
//		BlogPost blogpost = mapper.map(blogpostdto, BlogPost.class);
		BlogPost blogpost = new BlogPost();
		blogpost.setId(blogpostdto.getId());
		blogpost.setContent(blogpostdto.getContent());
		blogpost.setDescription(blogpostdto.getDescription());
		blogpost.setTitle(blogpostdto.getTitle());
		blogpost.setComments(blogpostdto.getComments());
		return blogpost;
	}

}
