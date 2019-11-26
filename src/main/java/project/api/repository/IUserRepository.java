package project.api.repository;

import org.springframework.data.repository.CrudRepository;
import project.api.entity.User;

public interface IUserRepository extends CrudRepository<User, Long>{

}
