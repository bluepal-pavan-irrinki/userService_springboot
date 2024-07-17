package Job_application.UserService.UserRepository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import Job_application.UserService.UserEntity.User_graduation;


@Repository
public interface User_Graduation_repository extends JpaRepository<User_graduation, UUID>{

 User_graduation findByUserId_Id(UUID user_id);


	

}
