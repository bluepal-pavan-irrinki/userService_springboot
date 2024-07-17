package Job_application.UserService.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="JOBS-SERVICE",url = "http://localhost:8093/Jobs")
public interface JobsClient {
	@PostMapping("/update_applied_Job")
	public String UpdateAppliedJobs(@RequestParam(name = "jobIds") String jobIds, @RequestParam(name = "userId") String userId);
}
