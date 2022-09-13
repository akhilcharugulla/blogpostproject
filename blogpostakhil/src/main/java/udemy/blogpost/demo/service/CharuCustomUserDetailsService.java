package udemy.blogpost.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import udemy.blogpost.demo.details.MyUserDetails;
import udemy.blogpost.demo.entity.Authorities;
import udemy.blogpost.demo.entity.Users;
import udemy.blogpost.demo.repository.UserRepository;

@Service
public class CharuCustomUserDetailsService implements UserDetailsService{

//Note created addUser method in UserController.Skipped the Service layer..
//The addUser method will add new user
	
	@Autowired 
	UserRepository uRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = uRepo.findByUsername(username).get();//.orElseThrow(()-> new UsernameNotFoundException("user not found"));
		if(user == null) { throw new UsernameNotFoundException("user not found");}
		//return new MyUserDetails(user);
		
		//We can create our custome UserDeatails or We can return import org.springframework.security.core.userdetails.User;
		//where User implements UserDetails (good to use this instead of Custom implementation of User Details)
		
		return new User(user.getUsername(),user.getPassword(),getAllGrantedAuthority(user.getAuthorities()));
	}
	
	public Collection<GrantedAuthority> getAllGrantedAuthority(List<Authorities> authorities) {
		return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthorityname())).collect(Collectors.toList());
	}

}
