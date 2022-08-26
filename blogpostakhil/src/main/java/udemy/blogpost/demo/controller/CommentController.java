package udemy.blogpost.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udemy.blogpost.demo.dto.CommentDto;
import udemy.blogpost.demo.service.CommentService;

@RestController
@RequestMapping("/commentapi")
public class CommentController {

	@Autowired
	CommentService cService;

	@PostMapping("v1/{postId}/createcomment")
	public ResponseEntity<CommentDto> createComment(@PathVariable long postId,@Valid @RequestBody CommentDto commentdto) {
		return new ResponseEntity<CommentDto>(cService.createComment(postId, commentdto), HttpStatus.CREATED);
	}

	@GetMapping("v1/{postId}/comment/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable long postId, @PathVariable long commentId) {
		return new ResponseEntity<CommentDto>(cService.getCommentById(postId, commentId), HttpStatus.OK);
	}

	@GetMapping("v1/getcommentsforpostId/{postId}")
	public List<CommentDto> getCommentForPostId(@PathVariable long postId) {
		return cService.getCommentsForPostId(postId);
	}

	@PutMapping("v1/updateComment/{postId}/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable long postId, @PathVariable long commentId,
			@Valid @RequestBody CommentDto commentdto) {
		return new ResponseEntity<CommentDto>(cService.updateComment(postId, commentId, commentdto), HttpStatus.OK);

	}

	@DeleteMapping("v1/deletecomment/{commentId}")
	public void deleteComment(@PathVariable long commentId) {
		cService.deleteComment(commentId);
	}

}
