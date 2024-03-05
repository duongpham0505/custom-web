package com.dev.custom.controller;

import com.dev.custom.exception.ReadFileException;
import com.dev.custom.service.FileServiceImpl;
import com.dev.custom.service.data.response.Response;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@RestController
public class FileController {
    @Autowired
    FileServiceImpl fileService;

    @PostMapping("upload")
    public List<List<String>> uploadExcel(@RequestBody MultipartFile file) throws IOException {
        List<List<String>> data = new ArrayList<>();
        if (file == null) {
            return Collections.emptyList();
        }
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            return Collections.emptyList();
        }
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook;
            if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                throw new ReadFileException("can not import file excel data !!!");
            }
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                List<String> rowData = new ArrayList<>();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    rowData.add(getCellValue(cell));
                }
                data.add(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    private String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    @PostMapping("/images")
    public Response<Object> uploadImage(@ModelAttribute MultipartFile file) throws IOException {
        return fileService.uploadFile(file);
    }

    @DeleteMapping("image/{id}")
    private boolean delete(@PathVariable("id") String imageId) throws IOException {
        return fileService.deleteImageFile(imageId);
    }
}
