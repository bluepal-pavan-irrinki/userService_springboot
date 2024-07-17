package Job_application.UserService.UserDto;

import Job_application.UserService.UserEntity.User_data;
import Job_application.UserService.UserEntity.User_graduation;
import Job_application.UserService.UserEntity.User_resume;

public class UserDetailsDTO {
	
	private User_data userdata;
    private User_resume userResume;
    private User_graduation userGraduation;

    public UserDetailsDTO(User_data userdata, User_resume userResume, User_graduation userGraduation) {
        this.userdata = userdata;
        this.userResume = userResume;
        this.userGraduation = userGraduation;
    }

    // Getters and setters
    public User_data getUserdata() {
        return userdata;
    }

    public void setUserdata(User_data userdata) {
        this.userdata = userdata;
    }

    public User_resume getUserResume() {
        return userResume;
    }

    public void setUserResume(User_resume userResume) {
        this.userResume = userResume;
    }

    public User_graduation getUserGraduation() {
        return userGraduation;
    }

    public void setUserGraduation(User_graduation userGraduation) {
        this.userGraduation = userGraduation;
    }

}
