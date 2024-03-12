package com.MiniLibrary.MiniLibrary.Controller;


import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Controller
public class PDFController {

    @GetMapping("/download-pdf")
    public ResponseEntity<byte[]> dowloadPdf() throws IOException{

        ClassPathResource pdffile = new ClassPathResource("static/pdf/decart.pdf");

        if(!pdffile.exists()){
            throw new RuntimeException("Книга не найдена!");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=decart.pdf");
         Path path = Paths.get(pdffile.getURI());
        byte[] pdfBytes = Files.readAllBytes(path);

        // Создайте объект ResponseEntity с файлом и заголовками
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(pdfBytes);

    }

}
