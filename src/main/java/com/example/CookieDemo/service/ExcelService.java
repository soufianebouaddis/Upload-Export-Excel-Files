package com.example.CookieDemo.service;
import com.example.CookieDemo.model.Person;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.nio.file.Files;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@AllArgsConstructor
public class ExcelService {
    private PersonService personService;
    private ResourceLoader resourceLoader;

    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file.";
        }
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            if (iterator.hasNext()) {
                iterator.next();
            }
            List<Person> people = new ArrayList<>();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Person person = new Person();
                person.setNom(currentRow.getCell(1).getStringCellValue());
                person.setPrenom(currentRow.getCell(2).getStringCellValue());
                people.add(person);
            }
            personService.saveAll(people);
            workbook.close();
            inputStream.close();
            return "File uploaded successfully.";
        } catch (IOException e) {
            return "Failed to upload file: " + e.getMessage();
        }
    }

    public ResponseEntity<String> exportData() {
        List<Person> people = personService.persons();

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Person");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nom");
            headerRow.createCell(2).setCellValue("Prenom");

            int rowNum = 1;
            for (Person person : people) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(person.getId());
                row.createCell(1).setCellValue(person.getNom());
                row.createCell(2).setCellValue(person.getPrenom());
            }

            // Create the directory if it does not exist
            String excelDirectory = "src/main/resources/excel";
            Path directory = Paths.get(excelDirectory);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            String filename = "exported_people.xlsx";
            Path filePath = Paths.get(directory.toString(), filename);

            try (FileOutputStream fileOut = new FileOutputStream(filePath.toString())) {
                workbook.write(fileOut);
            }

            // Get the URL of the exported file
            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/excel/export/")
                    .path(filename)
                    .toUriString();

            return ResponseEntity.ok("Data exported successfully. File saved at: " + fileUrl);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to export data: " + e.getMessage());
        }
    }
}


