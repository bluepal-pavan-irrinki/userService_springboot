package Job_application.UserService.UserEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
//Jackson will skip the specified properties when converting the object to JSON or when creating an object from JSON.
public class User_resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", updatable = false, nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User_data userId;

    private List<String> skills;
    
    private String resumename;
    private String uploadeddate;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String pdf;
    private List<String> Applied_jobs;
    
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

	public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }


    public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
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
