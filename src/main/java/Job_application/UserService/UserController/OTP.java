package Job_application.UserService.UserController;

public class OTP {
   private String email;
   private String otp;
   
public OTP(String email, String string) {
    this.email = email;
    this.otp = string;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getOtp() {
	return otp;
}
public void setOtp(String otp) {
	this.otp = otp;
}


}