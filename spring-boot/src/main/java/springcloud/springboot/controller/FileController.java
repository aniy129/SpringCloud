package springcloud.springboot.controller;

import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class FileController {
    @RequestMapping("/file")
    public String file() {
        return "file";
    }

    @RequestMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get("d:/" + file.getOriginalFilename());
        Files.write(path, bytes);
        response.getWriter().write("ok");

    }
}
