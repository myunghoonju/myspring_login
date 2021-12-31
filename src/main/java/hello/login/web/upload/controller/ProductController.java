package hello.login.web.upload.controller;

import hello.login.domain.upload.Product;
import hello.login.domain.upload.ProductApplicationService;
import hello.login.domain.upload.ProductForm;
import hello.login.domain.upload.ProductRepository;
import hello.login.domain.upload.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j @RequiredArgsConstructor
@Controller @RequestMapping("/api/spring")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductApplicationService productApplicationService;

    @GetMapping("/products/new")
    public String newItem(@ModelAttribute ProductForm form) {
        return "uploading/product-form";
    }

    @PostMapping("/products/new")
    public String save(@ModelAttribute ProductForm form, RedirectAttributes redirectAttributes) throws IOException {
        UploadFile attachFile = productApplicationService.storeFile(form.getAttachFile());
        List<UploadFile> storeImageFiles = productApplicationService.storeFiles(form.getImageFiles());

        Product product = new Product();
        product.setProductName(form.getProductName());
        product.setAttachFile(attachFile);
        product.setImageFiles(storeImageFiles);

        productRepository.save(product);
        redirectAttributes.addAttribute("productId", product.getId());

        return "redirect:/api/spring/products/{productId}";
    }

    @GetMapping("/products/{id}")
    public String products(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);

        return "uploading/product-view";
    }

    @ResponseBody
    @GetMapping("/images/{fileName}")
    public Resource downloadImage(@PathVariable String fileName) throws MalformedURLException {
        return new UrlResource("file:" + productApplicationService.getFullPath(fileName));
    }

    @GetMapping("/attach/{productId}")
    public ResponseEntity<Resource> downloadAttachments(@PathVariable Long productId) throws MalformedURLException {
        Product product = productRepository.findById(productId);
        String storeFileName = product.getAttachFile().getStoreFileName();
        String uploadFileName = product.getAttachFile().getUploadFileName();
        log.info("uploadFile name = {}", uploadFileName);
        UrlResource urlResource = new UrlResource("file:" + productApplicationService.getFullPath(storeFileName));
        String encodeUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodeUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }

}
