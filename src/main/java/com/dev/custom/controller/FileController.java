package com.dev.custom.controller;

import com.dev.custom.service.FileServiceImpl;
import com.dev.custom.service.data.response.Response;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    @Autowired
    FileServiceImpl fileService;

    @PostMapping("upload")
    public List<List<String>> uploadExcel(@RequestBody MultipartFile file) throws IOException {
        List<List<String>> data = new ArrayList<>();
//        if (file == null) {
//            return Collections.emptyList();
//        }
//        String fileName = file.getOriginalFilename();
//        if (fileName == null || fileName.isEmpty()) {
//            return Collections.emptyList();
//        }
//        try (InputStream inputStream = file.getInputStream()) {
//            Workbook workbook;
//            if (fileName.endsWith(".xlsx")) {
//                workbook = new XSSFWorkbook(inputStream);
//            } else if (fileName.endsWith(".xls")) {
//                workbook = new HSSFWorkbook(inputStream);
//            } else {
//                throw new ReadFileException("can not import file excel data !!!");
//            }
//            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
//
//            for (Row row : sheet) {
//                Iterator<Cell> cellIterator = row.cellIterator();
//                List<String> rowData = new ArrayList<>();
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    rowData.add(getCellValue(cell));
//                }
//                data.add(rowData);
//            }
//        } catch (Exception e) {
//            log.error("Read file error!!! : {}", (Object) e.getStackTrace());
//        }
        return data;
    }
    private String getCellValue(Cell cell) {
//        return switch (cell.getCellType()) {
//            case STRING -> cell.getStringCellValue();
//            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
//            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
//            default -> "";
//        };
        return "";
    }

    @PostMapping("/images")
    public Response<Object> uploadImage(@ModelAttribute MultipartFile file) throws IOException {
//        return fileService.uploadFile(file);
        return null;
    }

    @DeleteMapping("image/{id}")
    private boolean delete(@PathVariable("id") String imageId) throws IOException {
        return false;
//        return fileService.deleteImageFile(imageId);
    }
}
