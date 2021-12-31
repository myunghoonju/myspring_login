package hello.login.domain.upload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {

    private Long id;
    private String productName;
    private UploadFile attachFile;
    private List<UploadFile> imageFiles;
}
