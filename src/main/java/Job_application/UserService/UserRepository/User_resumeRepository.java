package Job_application.UserService.UserRepository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import Job_application.UserService.UserEntity.User_resume;

@Repository
public interface User_resumeRepository extends JpaRepository<User_resume, UUID>{

	User_resume findByUserId_Id(UUID id);




}
