package Job_application.UserService.Service;

import java.util.Arrays;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Job_application.UserService.FeignClient.JobsClient;
import Job_application.UserService.UserDto.UserDto;
import Job_application.UserService.UserDto.UserGraduationDto;
import Job_application.UserService.UserEntity.User_data;
import Job_application.UserService.UserEntity.User_graduation;
import Job_application.UserService.UserEntity.User_resume;
import Job_application.UserService.UserRepository.UserRepository;
import Job_application.UserService.UserRepository.User_Graduation_repository;
import Job_application.UserService.UserRepository.User_resumeRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private org.modelmapper.ModelMapper ModelMapper;

	@Autowired
	private User_resumeRepository user_resumeRepository;

	@Autowired
	private User_Graduation_repository user_Graduation_repository;
	
	@Autowired
	private JobsClient jobsClient;

	@Override
	public UserDto saveUser(UserDto userDto) {
		User_data users = userRepository.save(ModelMapper.map(userDto, User_data.class));
		UserDto savedUser = ModelMapper.map(users, UserDto.class);
		return savedUser;
	}

	@Override
	public User_data loginuser(String email, String password) {
		User_data user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UserNotFoundException("User not found with email: " + email);
		}

		if (!user.getPassword().equals(password)) {
			throw new UserNotFoundException("Invalid email or password");
		}
		user.setPassword(null);
		return user;
	}

	@Override
	public User_resume UploadDocuments(UUID id, List<String> skills, MultipartFile pdf, String resumename,
			String uploadDate) throws IOException {
		User_data user = userRepository.findById(id).get();
		User_resume users = new User_resume();
		users.setUserId(user);
		users.setPdf(Base64.getEncoder().encodeToString(pdf.getBytes()));
		users.setSkills(skills);
		users.setResumename(resumename);
		users.setUploadeddate(uploadDate);
		user_resumeRepository.save(users);

		return users;
	}

	@Override
	public User_resume updateuserdetails(UUID id, String name, long mobilenumber, String email, List<String> skills,
			MultipartFile pdf) throws IOException {
		User_data user = userRepository.findById(id).get();
		user.setEmail(email);
		user.setMobilenumber(mobilenumber);
		user.setName(name);
		User_resume saveduser = user_resumeRepository.findByUserId_Id(id);
		saveduser.setPdf(Base64.getEncoder().encodeToString(pdf.getBytes()));
		saveduser.setSkills(skills);
		return user_resumeRepository.save(saveduser);
	}

	@Override
	public User_resume ApplyForJob(String jobIds, String userId) {
		UUID uuid = UUID.fromString(userId);
		User_resume savedUser = user_resumeRepository.findByUserId_Id(uuid);

		if (savedUser == null) {
			throw new RuntimeException("User resume not found for userId: " + userId);
		}

		List<String> existingAppliedJobs = savedUser.getApplied_jobs();

		if (existingAppliedJobs == null) {
			existingAppliedJobs = new ArrayList<String>();
		}

		existingAppliedJobs.add(jobIds);
		savedUser.setApplied_jobs(existingAppliedJobs);
		jobsClient.UpdateAppliedJobs(jobIds, userId);
		
		user_resumeRepository.save(savedUser);

		return savedUser;
	}

	@Override
	public UserGraduationDto saveEducationalDetails(UUID UserId, UserGraduationDto userGraduationDto) {
		User_data user = userRepository.findById(UserId).get();

		User_graduation updateuser = new User_graduation();
		updateuser.setCourse(userGraduationDto.getCourse());
		updateuser.setEndDate(userGraduationDto.getEndDate());
		updateuser.setGradeSystem(userGraduationDto.getGradeSystem());
		updateuser.setGraduation_type(userGraduationDto.getGraduation_type());
		updateuser.setInstitute(userGraduationDto.getInstitute());
		updateuser.setMarks_Grade(userGraduationDto.getMarks_Grade());
		updateuser.setSpecilization(userGraduationDto.getSpecilization());
		updateuser.setStartDate(userGraduationDto.getStartDate());
		updateuser.setUniversity(userGraduationDto.getUniversity());
		updateuser.setUserId(user);
		UserGraduationDto updatedUser = ModelMapper.map(user_Graduation_repository.save(updateuser),
				UserGraduationDto.class);
		return updatedUser;

	}

	@Override
	public List<User_data> fetchallusers() {
		return userRepository.findAll();
	}

	@Override
	public UserDto updateUserPersonalDetails(UserDto userDto, UUID UserId) {
		User_data user = userRepository.findById(UserId).get();
		if (user.getProfile_pic() == null) {
			user.setProfile_pic(null);
		} else {
			user.setProfile_pic(user.getProfile_pic());
		}
		String languages = userDto.getLanguageKnown();
		List<String> languageList = Arrays.asList(languages.split(","));
		user.setName(userDto.getName());
		user.setCountry(userDto.getCountry());
		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setDistrict(userDto.getDistrict());
		user.setEmail(userDto.getEmail());
		user.setExperienced(userDto.getExperienced());
		user.setFresher_Experienced(userDto.getFresher_Experienced());
		user.setGender(userDto.getGender());
		user.setHometown(userDto.getHometown());
		user.setLandmark(userDto.getLandmark());
		user.setLanguageKnown(languageList);
		user.setMaritualStaus(userDto.getMaritualStaus());
		user.setMobilenumber(userDto.getMobilenumber());
		user.setPhysicallyChallenged(userDto.getPhysicallyChallenged());
		user.setPincode(userDto.getPincode());
		user.setRole(userDto.getRole());
		user.setState(userDto.getState());
		userRepository.save(user);
		UserDto updatedUser = ModelMapper.map(user, UserDto.class);
		return updatedUser;
	}

	@Override
	public User_data ChangePassword(String email, String password) {
		User_data userData = userRepository.findByEmail(email);
		if (userData == null) {
			throw new UserNotFoundException("User not found with email: " + email);
		} else {
			userData.setPassword(password);
			userRepository.save(userData);
			return userData;
		}
	}
}
