package Job_application.UserService.Service;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileExtractionService {

    private  Tika tika;

    public FileExtractionService() {
        this.tika = new Tika();
    }

    public String extractContent(MultipartFile file) throws IOException, TikaException {
        return tika.parseToString(file.getInputStream());
    }
}
