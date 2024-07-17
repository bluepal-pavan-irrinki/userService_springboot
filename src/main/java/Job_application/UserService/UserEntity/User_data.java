package Job_application.UserService.UserEntity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class User_data {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id", updatable = false, nullable = false)
	private UUID id;
	private String email;
	private String password;
	private String name;
	private long mobilenumber;
	private String Gender;
	private String MaritualStaus;
	private String DateOfBirth;
	private String PhysicallyChallenged;
	private String Hometown;
	private String state;
	private String district;
	private int pincode;
	private String country;
	private String landmark;
	private List<String> LanguageKnown;
	private String Fresher_Experienced;
	private Number Experienced;
	private String Role;
	private String RegisterAs;

	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String profile_pic;
	

	public String getRegisterAs() {
		return RegisterAs;
	}
	public void setRegisterAs(String registerAs) {
		RegisterAs = registerAs;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getMaritualStaus() {
		return MaritualStaus;
	}
	public void setMaritualStaus(String maritualStaus) {
		MaritualStaus = maritualStaus;
	}
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getPhysicallyChallenged() {
		return PhysicallyChallenged;
	}
	public void setPhysicallyChallenged(String physicallyChallenged) {
		PhysicallyChallenged = physicallyChallenged;
	}
	public String getHometown() {
		return Hometown;
	}
	public void setHometown(String hometown) {
		Hometown = hometown;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public List<String> getLanguageKnown() {
		return LanguageKnown;
	}
	public void setLanguageKnown(List<String> languageKnown) {
		LanguageKnown = languageKnown;
	}
	public String getFresher_Experienced() {
		return Fresher_Experienced;
	}
	public void setFresher_Experienced(String fresher_Experienced) {
		Fresher_Experienced = fresher_Experienced;
	}
	public Number getExperienced() {
		return Experienced;
	}
	public void setExperienced(Number experienced) {
		Experienced = experienced;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getProfile_pic() {
		return profile_pic;
	}
	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
}
