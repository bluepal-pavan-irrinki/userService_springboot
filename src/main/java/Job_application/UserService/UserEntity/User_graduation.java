package Job_application.UserService.UserEntity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User_graduation {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "Id", updatable = false, nullable = false)
private UUID id;

@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinColumn(name = "user_id")
private User_data userId;

private String Graduation_type;

private String University;

private String Course;

private String Specilization;

private String Institute;

private String StartDate;

private String EndDate;

private String GradeSystem;

private String Marks_Grade;

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

public String getGraduation_type() {
	return Graduation_type;
}

public void setGraduation_type(String graduation_type) {
	Graduation_type = graduation_type;
}

public String getUniversity() {
	return University;
}

public String getInstitute() {
	return Institute;
}

public void setInstitute(String institute) {
	Institute = institute;
}

public void setUniversity(String university) {
	University = university;
}

public String getCourse() {
	return Course;
}

public void setCourse(String course) {
	Course = course;
}

public String getSpecilization() {
	return Specilization;
}

public void setSpecilization(String specilization) {
	Specilization = specilization;
}

public String getStartDate() {
	return StartDate;
}

public void setStartDate(String startDate) {
	StartDate = startDate;
}

public String getEndDate() {
	return EndDate;
}

public void setEndDate(String endDate) {
	EndDate = endDate;
}

public String getGradeSystem() {
	return GradeSystem;
}

public void setGradeSystem(String gradeSystem) {
	GradeSystem = gradeSystem;
}

public String getMarks_Grade() {
	return Marks_Grade;
}

public void setMarks_Grade(String marks_Grade) {
	Marks_Grade = marks_Grade;
}



}