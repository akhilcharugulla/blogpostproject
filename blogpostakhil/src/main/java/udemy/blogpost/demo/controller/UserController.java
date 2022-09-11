package udemy.blogpost.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import udemy.blogpost.demo.entity.Authorities;
import udemy.blogpost.demo.entity.Users;
import udemy.blogpost.demo.repository.AuthoritiesRepository;
import udemy.blogpost.demo.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository uRepo;

	@Autowired
	AuthoritiesRepository aRepo; 
	
//	@Autowired
//	PasswordEncoder pEncoder;
	
	@PostMapping("/registeruser")
	public Users addUser(@RequestBody Users udata) {
		// Actually need to do service call and check whether user is present.
		Users newUser = new Users();
		newUser.setUsername(udata.getUsername());
		newUser.setPassword(new BCryptPasswordEncoder().encode(udata.getPassword()));
		Authorities authorities = aRepo.findByAuthorityname("ADMIN").get();
		newUser.setAuthorities(Collections.singletonList(authorities));
		return uRepo.save(newUser);
	}
}
