package Job_application.UserService.UserRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import Job_application.UserService.UserEntity.User_data;


@Repository
public interface UserRepository extends JpaRepository<User_data, UUID>{

	User_data findByEmail(String email);

	

	

}
