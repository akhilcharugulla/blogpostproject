package udemy.blogpost.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	String username;
	
	//@JsonIgnore
	String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
	List<Authorities> authorities;
}
