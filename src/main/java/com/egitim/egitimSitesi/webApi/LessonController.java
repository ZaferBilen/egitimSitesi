package com.egitim.egitimSitesi.webApi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.egitim.egitimSitesi.business.abstracts.ILessonService;
import com.egitim.egitimSitesi.business.requests.CreateLessonsRequest;
import com.egitim.egitimSitesi.business.requests.UpdateLessonsRequest;
import com.egitim.egitimSitesi.business.responses.AdminGetAllLessonsResponse;
import com.egitim.egitimSitesi.business.responses.GetAllLessonsResponse;
import com.egitim.egitimSitesi.business.responses.GetLessonByIdResponse;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
	
	 private final ILessonService lessonService;

	    @Autowired
	    public LessonController(ILessonService lessonService) {
	        this.lessonService = lessonService;
	    }
	
	@GetMapping("/getalllessons")
	public ResponseEntity<List<GetAllLessonsResponse>> getAllLessons() {
        List<GetAllLessonsResponse> lessons = lessonService.getAllLessonsResponse();
        return ResponseEntity.ok(lessons);
    }
	

	@GetMapping("/admin/getalllessons")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AdminGetAllLessonsResponse>> getAdminAllLessons() {
        List<AdminGetAllLessonsResponse> lessons = lessonService.adminGetAllLessonsResponse();
        return ResponseEntity.ok(lessons);
    }
	
	@PostMapping("/admin/addlesson")
	@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> addLesson(@RequestBody CreateLessonsRequest createLessonRequest) {
        lessonService.add(createLessonRequest);
        return ResponseEntity.ok("Lesson Added");
    }
	

    @PutMapping("/admin/updatelesson")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateLesson(@RequestBody UpdateLessonsRequest updateLessonRequest) {
        lessonService.update(updateLessonRequest);
        return ResponseEntity.ok("Lesson Updated");
    }

    
    @DeleteMapping("/admin/deletelesson/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteLesson(@PathVariable int id) {
        lessonService.delete(id);
        return ResponseEntity.ok("Lesson Deleted");
    }
    
    @GetMapping("/lesson/{lessonId}")
    public GetLessonByIdResponse getLessonById(@PathVariable int lessonId) {
    	
    	return lessonService.getLessonById(lessonId);
    }
    
    @PostMapping("/admin/{lessonId}/upload-video")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> uploadVideo(@PathVariable int lessonId, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Dosya seçilmedi.");
        }

        try {
            String uploadDir = "C:\\Users\\Zafer\\Desktop\\projeuploads"; // Yükleme dizini
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDir, fileName).toString();
            File dest = new File(filePath);
            file.transferTo(dest);

            // Yüklenen dosyanın yolu veritabanına kaydedilir
            lessonService.uploadVideo(lessonId, filePath);

            return ResponseEntity.ok("Dosya başarıyla yüklendi: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Dosya yüklenirken bir hata oluştu.");
        }
    }
}
