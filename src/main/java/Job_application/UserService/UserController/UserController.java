package Job_application.UserService.UserController;

import java.util.Map;
import java.util.UUID;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.apache.tika.exception.TikaException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Job_application.UserService.Service.FileExtractionService;
import Job_application.UserService.Service.UserNotFoundException;
import Job_application.UserService.Service.UserService;

import Job_application.UserService.UserDto.SendMailDto;
import Job_application.UserService.UserDto.UserDetailsDTO;
import Job_application.UserService.UserDto.UserDto;
import Job_application.UserService.UserDto.UserGraduationDto;
import Job_application.UserService.UserDto.UserResumeDto;
import Job_application.UserService.UserEntity.User_data;
import Job_application.UserService.UserEntity.User_graduation;
import Job_application.UserService.UserEntity.User_resume;
import Job_application.UserService.UserRepository.UserRepository;
import Job_application.UserService.UserRepository.User_Graduation_repository;
import Job_application.UserService.UserRepository.User_resumeRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS })
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private User_resumeRepository userResumeRepository;
	@Autowired
	private User_Graduation_repository userGraduationRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ModelMapper modelmapper;

	
	private FileExtractionService fileExtractionService = new FileExtractionService();

 
    public UserController(FileExtractionService fileExtractionService) {
        this.fileExtractionService = fileExtractionService;
    }

    @PostMapping("/extract")
    public ResponseEntity<String> extractFileContent(@RequestParam("file") MultipartFile file) {
        try {
            String content = fileExtractionService.extractContent(file);
            return new ResponseEntity<>(content, HttpStatus.OK);
        } catch (IOException | TikaException e) {
            return new ResponseEntity<>("Failed to extract content: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	@GetMapping("/test")
	public String testEndpoint() {
		System.out.println("ppppppppppppppppp");
		return "Test endpoint reached!";
	}
	
	
	

	@PostMapping("/register")
	public ResponseEntity<UserDto> Register(@RequestBody UserDto userDto) throws UserNotFoundException {

		UserDto data = userService.saveUser(userDto);
		return ResponseEntity.ok(data);

	}

	@PostMapping("/login")
	public ResponseEntity<?> Login(@RequestBody Map<String, String> loginData) {
		try {
			User_data data = userService.loginuser(loginData.get("email"), loginData.get("password"));
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (UserNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/upload_user_data")
	public User_resume uploadUserDetails(@RequestParam(name = "Id") String Id,
			@RequestParam(name = "skills") List<String> skills, @RequestParam(name = "pdf") MultipartFile pdf,
			@RequestParam("resumename") String resumename, @RequestParam("uploaddate") String uploadDate)
			throws Exception {
		UUID id = UUID.fromString(Id);

		User_resume userData = userService.UploadDocuments(id, skills, pdf, resumename, uploadDate);
		System.out.println("post upload user data detected");
		return userData;
	}

	@GetMapping("/get_user_details/{user_id}")
	public User_resume getUserData(@PathVariable UUID user_id) {
		System.out.println("User ID: " + user_id);
		User_resume userResumes = userResumeRepository.findByUserId_Id(user_id);
		System.out.println("User Resumes: " + userResumes);
		return userResumes;
	}

	@GetMapping("/get_user_educational/{user_id}")
	public User_graduation GetUserGraduation(@PathVariable UUID user_id) {
		User_graduation educationalDetails = userGraduationRepository.findByUserId_Id(user_id);

		return educationalDetails;

	}

	@PutMapping("/update_user_data")
	public User_resume Getuserdata(@RequestParam(name = "Id") UUID id, @RequestParam(name = "name") String name,
			@RequestParam(name = "mobilenumber") long mobilenumber, @RequestParam(name = "email") String email,
			@RequestParam(name = "skills") List<String> skills, @RequestParam(name = "pdf") MultipartFile pdf)
			throws IOException {
		User_resume updatedResume = userService.updateuserdetails(id, name, mobilenumber, email, skills, pdf);

		return updatedResume;

	}

	@PostMapping("/apply_for_job")
	public User_resume ApplyForJob(@RequestParam(name = "JobId") String JobId,
			@RequestParam(name = "userId") String userId) {
		User_resume SAVED = userService.ApplyForJob(JobId, userId);
		return SAVED;

	}

	@PutMapping("/Save_user_educational_details/{userId}")
	public UserGraduationDto SaveEducationalDetails(@PathVariable String userId,
			@RequestBody UserGraduationDto userGraduationDto) {
		UUID UserId = UUID.fromString(userId);
		UserGraduationDto savedUserEducation = userService.saveEducationalDetails(UserId, userGraduationDto);
		System.out.println("oooooooo");
		return savedUserEducation;

	}

	@GetMapping("/fetch_all_users")
	public List<User_data> FetchAllJobs(@PathVariable("page") int page) {

		List<User_data> fetchedusers = userService.fetchallusers();
		return fetchedusers;

	}

	
	@PutMapping("/Update_profile/{userId}")
	public String UpdateProfile(@PathVariable String userId, @RequestParam(name = "profile") MultipartFile profile)
			throws IOException {
		UUID UserId = UUID.fromString(userId);
		User_data user = userRepository.findById(UserId).get();
		user.setProfile_pic(Base64.getEncoder().encodeToString(profile.getBytes()));
		userRepository.save(user);
		return "updated";

	}

	
	@PatchMapping("/update_user_personal_details/{userId}")
	public UserDto UpdateUserPersonalDetails(@PathVariable String userId, @RequestBody UserDto userDto) {
		UUID UserId = UUID.fromString(userId);
		UserDto updateduser = userService.updateUserPersonalDetails(userDto, UserId);
		return updateduser;
	}

	
	
	private OTP[] items = new OTP[0];

	@PostMapping("/otp-send")
	public ResponseEntity<?> triggerMail(@RequestBody Map<String, String> loginData) {
		String email = loginData.get("email");

		if (email == null || email.isEmpty()) {
			return new ResponseEntity<>("Email is required", HttpStatus.BAD_REQUEST);
		}

		User_data userdata = userRepository.findByEmail(email);
		if (userdata == null) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}

		try {
			OTP otpEmail = new OTP(email, generateOtp());
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			String htmlMsg = String.format("<h3>Hello,</h3><h1>Your OTP code is: %s</h1>", otpEmail.getOtp());

			helper.setText(htmlMsg, true);
			helper.setTo(email);
			helper.setSubject("Regarding Job Portal Application");
			helper.setFrom("pd.pavanirrinki@gmail.com");
			mailSender.send(mimeMessage);

			items = addToArray(items, otpEmail);

			return new ResponseEntity<>(items, HttpStatus.OK);
		} catch (MessagingException e) {
			// Handle email sending errors
			return new ResponseEntity<>("Error sending OTP email", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private OTP[] addToArray(OTP[] array, OTP element) {
		OTP[] newArray = new OTP[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, array.length);
		newArray[array.length] = element;
		return newArray;
	}

	private String generateOtp() {
		int otp = (int) (Math.random() * 900000) + 100000;
		return String.valueOf(otp);
	}

	
	
	
	@PostMapping("/verify_otp")
	public String verifyOTP(@RequestBody Map<String, String> OTPDTO) {
		for (int i = 0; i < items.length; i++) {
			OTP storedOtp = items[i];
			if (storedOtp != null && storedOtp.getEmail().equals(OTPDTO.get("email"))
					&& storedOtp.getOtp().equals(OTPDTO.get("otp"))) {
				items[i] = null;
				return "OTP verified successfully!";
			}
		}
		return "OTP verification failed.";
	}

	
	
	@PatchMapping("/Change_password")
	public ResponseEntity<User_data> ChangePassword(@RequestBody Map<String, String> NewPasswordData) throws UserNotFoundException {
		User_data userdata = userService.ChangePassword(NewPasswordData.get("email"), NewPasswordData.get("password"));
		return ResponseEntity.ok(userdata);
	}
	
	
	
	
	@GetMapping("/user_details/{userId}")
	public ResponseEntity<?> UserDetails(@PathVariable String userId) {
		UUID uuid = UUID.fromString(userId);
		User_data userdata = userRepository.findById(uuid).orElse(null);
		User_resume userResume = userResumeRepository.findByUserId_Id(uuid);
		User_graduation userGraduation = userGraduationRepository.findByUserId_Id(uuid);

		if (userdata == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}

		UserDetailsDTO userDetailsDTO = new UserDetailsDTO(userdata, userResume, userGraduation);
		return ResponseEntity.ok(userDetailsDTO);
	}



}
