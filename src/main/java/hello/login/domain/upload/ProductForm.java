package hello.login.domain.upload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductForm {

    private Long id;
    private String productName;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;
}
