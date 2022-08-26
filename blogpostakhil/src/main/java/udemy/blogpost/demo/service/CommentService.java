package udemy.blogpost.demo.service;

import java.util.List;

import udemy.blogpost.demo.dto.CommentDto;
public interface CommentService {
	public CommentDto createComment(long postid, CommentDto commentdto);
	public CommentDto getCommentById(long postId, long commentId);
	public List<CommentDto> getCommentsForPostId(long postId);
	public CommentDto updateComment(long postId, long commentId, CommentDto commentdto);
	public void deleteComment(long commentId);
}
