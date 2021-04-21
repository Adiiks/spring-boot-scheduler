package pl.adiks.springbootscheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.adiks.springbootscheduler.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
