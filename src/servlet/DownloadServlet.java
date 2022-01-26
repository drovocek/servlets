package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Disposition", "attachment; filename=\"filename.xml\"");
        resp.setContentType("application/xml");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

//        byte[] resources = Files.readAllBytes(Path.of("resources", "xml/first.xml"));
        try (var writer = resp.getOutputStream();
             var is = DownloadServlet.class
                     .getClassLoader()
                     .getResourceAsStream("xml/first.xml")) {
            writer.write(is.readAllBytes());
        }
    }
}
