package hello.login.web.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/api/servlet/v1")
public class UploadController {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile() {
        return "uploading/upload-form";
    }

    //@PostMapping("/upload")
    public String saveFileServlet(HttpServletRequest request) throws ServletException, IOException {
        log.info("request = {}", request);

        String itemName = request.getParameter("itemName");
        log.info("itemName = {}", itemName);
        Collection<Part> parts = request.getParts();
        log.info("parts = {}", parts);

        for (Part part : parts) {
            log.info("==========");
            log.info("name = {}", part.getName());
            Collection<String> headerNames = part.getHeaderNames();
            for (String headerName : headerNames) {
                log.info("header = {}: {}", headerName, part.getHeader(headerName));
            }
            //content-disposition
            log.info("submittedFileName= {} ", part.getSubmittedFileName());
            //get size
            log.info("size = {}", part.getSize());
            //read data
            InputStream inputStream = part.getInputStream();
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("body = {}", body);

            //save file
            if (StringUtils.hasText(part.getSubmittedFileName())) {
                String fullPath = fileDir + part.getSubmittedFileName();
                log.info("save file dir = {}", fullPath);
                part.write(fullPath);
            }

        }

        return "uploading/upload-form";
    }

    @PostMapping("/upload")
    public String saveFileSpring (
            @RequestParam String itemName,
            @RequestParam("file") MultipartFile multipartFile,
            HttpServletRequest request) throws IOException {

        log.info("request = {}", request);
        log.info("itemName = {}", itemName);
        log.info("multipartFile = {}", multipartFile);

        if (!multipartFile.isEmpty()) {
            String fullPath = fileDir + multipartFile.getOriginalFilename();
            log.info("fullPath = {}", fullPath);
            multipartFile.transferTo(new File(fullPath));
        }

        return "uploading/upload-form";
    }
}
