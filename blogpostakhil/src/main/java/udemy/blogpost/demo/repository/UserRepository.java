package udemy.blogpost.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;

import udemy.blogpost.demo.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	public Optional <Users> findByUsername(String username);
}
