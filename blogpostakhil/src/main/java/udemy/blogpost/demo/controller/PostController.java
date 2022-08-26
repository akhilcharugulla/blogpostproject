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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import udemy.blogpost.demo.dto.BlogPostDto;
import udemy.blogpost.demo.dto.PayloadPostResponse;
import udemy.blogpost.demo.service.PostService;

@RestController
@RequestMapping("/postapi")
public class PostController {

	@Autowired
	PostService pService;

	//@ApiOperation is a swagger annotation that gives info on each method
	
	@ApiOperation(value = "creates a post", notes="This api creates a new post and adds post to db")
	@PostMapping("v1/createpost")
	public ResponseEntity<BlogPostDto> createPost(@Valid @RequestBody BlogPostDto postdto) {
		return new ResponseEntity<>(pService.createPost(postdto), HttpStatus.CREATED);
	}

	@GetMapping("v1/allposts")
	public List<BlogPostDto> getAllPosts() {
		return pService.getAllPosts();
	}

	@GetMapping("v1/allpostspagination")
	public PayloadPostResponse getAllPostsPagination(@RequestParam(value="pageNo", required=false)int pageNo,@RequestParam(value="pageSize", required = false)int pageSize ) {
		return pService.getAllPostsPagination(pageNo, pageSize);
	}
	
	@GetMapping("v1/getpost/{id}")
	public ResponseEntity<BlogPostDto> getPostById(@PathVariable long id) {
		return new ResponseEntity<>(pService.getPostById(id), HttpStatus.OK);
	}

	@PutMapping("v1/{id}")
	public ResponseEntity<BlogPostDto> updatePost(@PathVariable long id,@Valid @RequestBody BlogPostDto newposttoUpdate) {
		return new ResponseEntity<>(pService.updatePost(id, newposttoUpdate), HttpStatus.OK);
	}

	@DeleteMapping("v1/{id}")
	public void deletePost(@PathVariable long id) {
		 pService.deletePostById(id);
	}
}
