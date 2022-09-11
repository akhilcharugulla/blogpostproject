package udemy.blogpost.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Authorities {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String authorityname;
}
