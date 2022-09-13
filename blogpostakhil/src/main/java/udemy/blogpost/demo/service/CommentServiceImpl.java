package udemy.blogpost.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import udemy.blogpost.demo.dto.CommentDto;
import udemy.blogpost.demo.entity.BlogPost;
import udemy.blogpost.demo.entity.Comment;
import udemy.blogpost.demo.exception.BlogAPIException;
import udemy.blogpost.demo.exception.ResourceNotFoundException;
import udemy.blogpost.demo.repository.CommentRepository;
import udemy.blogpost.demo.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	//Note created addUser method in UserController.Skipped the Service layer..
	//The addUser method will add new user
	
	
	@Autowired
	CommentRepository cRepository;
	
	@Autowired
	PostRepository pRepository;
	
	@Autowired
	ModelMapper modelmapper;
	
	public CommentDto createComment(long postId, CommentDto commentdto) {
		BlogPost post = pRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
		Comment comment = mapDtoToEntity(commentdto);
		comment.setBlogPost(post);
		//comment.setPost(post);

		Comment com = cRepository.save(comment);
		return mapEntityToDto(com);
	}	 

	@Override
	public CommentDto getCommentById(long postId, long commentId) {
		BlogPost post = pRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("id", "post", postId));
		Comment comment = cRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "id", commentId));

		//if(!comment.getPost().getId().equals(post.getId())) {
		if(!comment.getBlogPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment Not belong to post");
		}
		return mapEntityToDto(comment);
	}
	
	@Override
	public List<CommentDto> getCommentsForPostId(long postId){
		List<Comment> commentsforpostId = cRepository.findByblogPostId(postId);
		List<CommentDto> commentsdtoforpostId =  commentsforpostId.stream().map(i ->mapEntityToDto(i)).collect(Collectors.toList());
		return commentsdtoforpostId;
	}
	
	@Override
	public CommentDto updateComment(long postId, long commentId, CommentDto commentdto)
	{
		BlogPost post = pRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("id", "post", postId));
		Comment comment = cRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "id", commentId));
		if(!comment.getBlogPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment Not belong to post");
		}
		comment.setBody(commentdto.getBody());
		comment.setEmail(commentdto.getEmail());
		comment.setName(commentdto.getEmail());
		Comment com = cRepository.save(comment);
		return mapEntityToDto(com);
	}
	
	@Override
	public void deleteComment(long commentId) {
		cRepository.deleteById(commentId);
	}
	
	public CommentDto mapEntityToDto(Comment comment){
		CommentDto commentdto = modelmapper.map(comment, CommentDto.class);
//		CommentDto commentdto = new CommentDto();
//		commentdto.setId(comment.getId());
//		commentdto.setBody(comment.getBody());
//		commentdto.setEmail(comment.getEmail());
//		commentdto.setName(comment.getName());
		return commentdto;
	}
	
	public Comment mapDtoToEntity(CommentDto commentdto){
		Comment comment = modelmapper.map(commentdto, Comment.class);
//		Comment comment = new Comment();
//		comment.setId(commentdto.getId());
//		comment.setBody(commentdto.getBody());
//		comment.setEmail(commentdto.getEmail());
//		comment.setName(commentdto.getName());
		return comment;
	}


}
