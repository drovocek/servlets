package edu.volkov.mvc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import edu.volkov.mvc.service.ImageService;

import java.io.IOException;
import java.io.InputStream;

import static edu.volkov.mvc.util.UrlPath.IMAGES_URL;

@WebServlet(IMAGES_URL + "/*")
public class ImagesServlet extends HttpServlet {

    private final ImageService imageService = ImageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        var imagePath = requestURI.replace("/images", "");
        imageService.get(imagePath)
                .ifPresentOrElse(is -> {
                            resp.setContentType("application/octet-stream");
                            writeImage(is, resp);
                        },
                        () -> resp.setStatus(404));
    }

    @SneakyThrows
    private void writeImage(InputStream is, HttpServletResponse resp) {
        try (is; var out = resp.getOutputStream()) {
            int currentByte;
            while ((currentByte = is.read()) != -1) {
                out.write(currentByte);
            }
        }
    }
}
