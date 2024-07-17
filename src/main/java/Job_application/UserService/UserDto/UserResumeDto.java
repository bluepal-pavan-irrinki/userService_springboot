package Job_application.UserService.UserDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import Job_application.UserService.UserEntity.User_data;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class UserResumeDto {
	private UUID id;
	private User_data userId;
	 private List<String> Applied_jobs;
	  private String resumename;
	  private String uploadeddate;
	 private String pdf;
	  

	public String getPdf() {
		return pdf;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	
	public String getResumename() {
		return resumename;
	}
	public void setResumename(String resumename) {
		this.resumename = resumename;
	}
	public String getUploadeddate() {
		return uploadeddate;
	}
	public void setUploadeddate(String uploadeddate) {
		this.uploadeddate = uploadeddate;
	}

	public List<String> getApplied_jobs() {
		return Applied_jobs;
	}
	public void setApplied_jobs(List<String> applied_jobs) {
		Applied_jobs = applied_jobs;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public User_data getUserId() {
		return userId;
	}
	public void setUserId(User_data userId) {
		this.userId = userId;
	}

}
