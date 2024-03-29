package com.example.CookieDemo.controller;

import com.example.CookieDemo.service.ExcelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("excel/")
public class FileController {
    private final ExcelService excelService;

    public FileController(ExcelService excelService) {
        this.excelService = excelService;
    }
    @PostMapping("upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){
        try{
            return ResponseEntity.ok(this.excelService.uploadFile(file));
        }catch (Exception e){
            return ResponseEntity.of(Optional.of(Map.of("Message : ", "Error while uploading file try again")));
        }
    }
    @GetMapping("export")
    public ResponseEntity<?> export(){
        try{
            return ResponseEntity.ok(this.excelService.exportData());
        }catch (Exception e){
            return ResponseEntity.of(Optional.of(Map.of("Message : ", "Error while exporting file try again")));
        }
    }


}
