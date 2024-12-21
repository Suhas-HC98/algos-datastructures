package com.shared.info.controller;

import com.shared.info.controller.documentation.FileControllerDocumentation;
import com.shared.info.dto.InternetProtocol;
import com.shared.info.service.*;
import com.shared.info.utils.GenericResponse;
import com.shared.info.utils.SharedServiceResponseBuilder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

import static com.shared.info.enums.ContentStatus.DISPOSITION;
import static com.shared.info.utils.Constants.INTERNET_PROTOCOL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/file")
@AllArgsConstructor
public final class FileController implements FileControllerDocumentation {

    private final TextContentParserService textContentParserService;
    private final JsonContentService jsonContentService;
    private final CsvService csvService;
    private final XlsxService xlsxService;
    private final PDFService pdfService;
    private final ReportService reportService;


    @GetMapping(value = "/fetch-content")
    public ResponseEntity<GenericResponse<?>> getFileContentAsString(@RequestParam(name = "fileName") String fileName) {
        try {
            return new ResponseEntity<>(SharedServiceResponseBuilder.wrapWithGenericResponse(textContentParserService.parseTextContent(fileName)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(SharedServiceResponseBuilder.wrapWithErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/fetch-json")
    public ResponseEntity<GenericResponse<?>> getJsonFromFile(@RequestParam(name = "stringPath") String stringPath) {
        try {
            return new ResponseEntity<>(SharedServiceResponseBuilder.wrapWithGenericResponse(jsonContentService.fetchJsonData(InternetProtocol.class, stringPath)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(SharedServiceResponseBuilder.wrapWithErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/read-csv", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<?>> readCSV(@RequestParam(name = "file") MultipartFile multipartFile, @RequestParam(name = "type") String contentType) {
        try {
            return new ResponseEntity<>(SharedServiceResponseBuilder.wrapWithGenericResponse(csvService.readCSVFileContent(multipartFile, contentType)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(SharedServiceResponseBuilder.wrapWithErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/produce-csv", produces = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> generateCSV(@RequestParam(value = "fileName", defaultValue = "data") String fileName) {
        try {
            return ResponseEntity.ok()
                    .header(DISPOSITION.getKey(), DISPOSITION.getContent().concat(fileName).concat(".csv"))
                    .body(new InputStreamResource(
                            csvService.generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, INTERNET_PROTOCOL), InternetProtocol.class)));
        } catch (Exception e) {
            return new ResponseEntity<>(SharedServiceResponseBuilder.wrapWithErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/read-xlsx", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readExcel(@RequestParam(value = "file") MultipartFile multipartFile) {
        try {
            return new ResponseEntity<>(SharedServiceResponseBuilder.wrapWithGenericResponse(xlsxService.read(multipartFile, InternetProtocol.class)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(SharedServiceResponseBuilder.wrapWithErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/produce-xlsx", produces = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> generateExcel(@RequestParam(value = "fileName") String fileName) {
        try {
            return ResponseEntity.ok()
                    .header(DISPOSITION.getKey(), DISPOSITION.getContent().concat(fileName).concat(".xlsx"))
                    .body(new InputStreamResource(xlsxService.write(jsonContentService.fetchJsonData(InternetProtocol.class, INTERNET_PROTOCOL))));
        } catch (Exception e) {
            return new ResponseEntity<>(SharedServiceResponseBuilder.wrapWithErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/produce-pdf", produces = MULTIPART_FORM_DATA_VALUE)
    public void generatePDF(@RequestParam(value = "fileName", defaultValue = "sample") String fileName, HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader(DISPOSITION.getKey(), DISPOSITION.getContent().concat(fileName).concat(".pdf"));
            pdfService.write(response);
        } catch (Exception e) {
            log.info("Exception occurred while generating PDF with message {} please try later", e.getMessage());
        }
    }

    @GetMapping(value = "/produce-report", produces = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InputStreamResource> generatePDFReport(@RequestParam(value = "title", defaultValue = "IpData Report") String title) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    reportService.generatePdfReport((List<?>) jsonContentService.fetchJsonData(InternetProtocol.class, INTERNET_PROTOCOL).get(0), title));
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report.pdf");
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new InputStreamResource(byteArrayInputStream));
        } catch (Exception e) {
            log.info("Exception occurred while generating Report with message {} please try later", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
