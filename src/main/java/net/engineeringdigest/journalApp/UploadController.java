package net.engineeringdigest.journalApp;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;

@RestController
@RequestMapping("/mock-api")
public class UploadController {
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadCode(
            @RequestHeader("Name") String name,
            @RequestHeader("Email") String email,
            @RequestHeader("College") String college,
            @RequestHeader("StudentId") String studentId,
            @RequestHeader("FileName") String fileName,
            @RequestHeader("Password") String passwordBase64,
            @RequestPart("file") MultipartFile file) {

        // Decode base64 password to verify
        String decodedPassword = new String(Base64.getDecoder().decode(passwordBase64));

        System.out.println("---- Received Request ----");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("College: " + college);
        System.out.println("Student ID: " + studentId);
        System.out.println("FileName: " + fileName);
        System.out.println("Password (decoded): " + decodedPassword);
        System.out.println("Uploaded File: " + file.getOriginalFilename());

        return ResponseEntity.ok("✅ Upload successful!");
    }
}
