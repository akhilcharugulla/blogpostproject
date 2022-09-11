package udemy.blogpost.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import udemy.blogpost.demo.entity.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
	Optional<Authorities> findByAuthorityname(String name);
}
