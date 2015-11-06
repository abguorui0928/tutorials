package tutorials.hibernate.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tutorials.hibernate.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Serializable> {
}
