package Job_application.UserService.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;


import Job_application.UserService.UserDto.UserDto;
import Job_application.UserService.UserDto.UserGraduationDto;

import Job_application.UserService.UserEntity.User_data;
import Job_application.UserService.UserEntity.User_resume;

public interface UserService {
	
	UserDto saveUser(UserDto userDto);
	User_data loginuser(String email,String password);
	User_resume UploadDocuments(UUID id, List<String> skills, MultipartFile pdf, String resumename, String uploadDate) throws IOException;
	User_resume updateuserdetails(UUID id, String name, long mobilenumber, String email, List<String> skills,MultipartFile pdf) throws IOException;
	User_resume ApplyForJob(String jobId, String userId);
	List<User_data> fetchallusers();
	UserGraduationDto saveEducationalDetails(UUID UserId, UserGraduationDto userGraduationDto);
	UserDto updateUserPersonalDetails(UserDto userDto, UUID userId);
	User_data ChangePassword(String email, String password);
    
	
    
}
