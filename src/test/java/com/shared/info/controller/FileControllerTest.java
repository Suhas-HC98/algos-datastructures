package com.shared.info.controller;

import com.shared.info.dto.InternetProtocol;
import com.shared.info.service.*;
import com.shared.info.utils.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
class FileControllerTest {

    private final TextContentParserService textContentParserService = mock(TextContentParserService.class);
    private final JsonContentService jsonContentService = mock(JsonContentService.class);
    private final CsvService csvService = mock(CsvService.class);
    private final XlsxService xlsxService = mock(XlsxService.class);
    private final ReportService reportService = mock(ReportService.class);

    private final FileController controller = new FileController(textContentParserService, jsonContentService, csvService, xlsxService, null, reportService);

    @Test
    void should_parse_the_file_and_return_string_when_file_name_is_provided() throws Exception {
        when(textContentParserService.parseTextContent("test")).thenReturn("Content parsed successfully");
        ResponseEntity<GenericResponse<?>> actualResponse = controller.getFileContentAsString("test");

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertNotNull(requireNonNull(actualResponse.getBody()).data());
        assertTrue(actualResponse.getBody().metaData().isSuccess());
        verify(textContentParserService).parseTextContent("test");
        verify(jsonContentService, never()).fetchJsonData(InternetProtocol.class, "test");
        verify(csvService, never()).readCSVFileContent(multipartFile("MOCK_DATA_TEST.csv"), "ipdata");
        verify(jsonContentService, never()).fetchJsonData(InternetProtocol.class, "InternetProtocol");
        verify(csvService, never()).generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"), InternetProtocol.class);
        verify(xlsxService, never()).read(multipartFile("IpData_mock.xlsx"), InternetProtocol.class);
        verify(xlsxService, never()).write(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"));
    }

    @Test
    void should_throw_null_pointer_when_incorrect_file_name_is_provided() throws Exception {
        when(textContentParserService.parseTextContent("test")).thenThrow(NullPointerException.class);
        ResponseEntity<GenericResponse<?>> actualResponse = controller.getFileContentAsString("test");

        assertEquals(HttpStatus.BAD_REQUEST, actualResponse.getStatusCode());
        assertFalse(requireNonNull(actualResponse.getBody()).metaData().isSuccess());
        verify(textContentParserService).parseTextContent("test");
        verify(jsonContentService, never()).fetchJsonData(InternetProtocol.class, "test");
        verify(csvService, never()).readCSVFileContent(multipartFile("MOCK_DATA_TEST.csv"), "ipdata");
        verify(jsonContentService, never()).fetchJsonData(InternetProtocol.class, "InternetProtocol");
        verify(csvService, never()).generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"), InternetProtocol.class);
        verify(xlsxService, never()).read(multipartFile("IpData_mock.xlsx"), InternetProtocol.class);
        verify(xlsxService, never()).write(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"));
    }

    @Test
    void should_fetch_json_date_when_file_name_is_provided() throws Exception {
        when(jsonContentService.fetchJsonData(InternetProtocol.class, "test")).thenReturn(Collections.emptyList());

        ResponseEntity<GenericResponse<?>> actualResponse = controller.getJsonFromFile("test");
        log.info("API status : {}", requireNonNull(actualResponse.getBody()).metaData().isSuccess());
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertFalse(requireNonNull(actualResponse.getBody()).errors().isSuccess());
        verify(textContentParserService, never()).parseTextContent("test");
        verify(jsonContentService).fetchJsonData(InternetProtocol.class, "test");
        verify(csvService, never()).readCSVFileContent(multipartFile("MOCK_DATA_TEST.csv"), "ipdata");
        verify(jsonContentService, never()).fetchJsonData(InternetProtocol.class, "InternetProtocol");
        verify(csvService, never()).generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"), InternetProtocol.class);
        verify(xlsxService, never()).read(multipartFile("IpData_mock.xlsx"), InternetProtocol.class);
        verify(xlsxService, never()).write(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"));
    }

    @Test
    void should_fetch_csv_date_when_file_name_is_provided() throws Exception {
        when(csvService.readCSVFileContent(null, "ipdata")).thenReturn(Collections.emptyList());
        ResponseEntity<GenericResponse<?>> actualResponse = controller.readCSV(null, "ipdata");

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertNotNull(requireNonNull(actualResponse.getBody()).data());
        assertFalse(actualResponse.getBody().metaData().isSuccess());
        verify(textContentParserService, never()).parseTextContent("test");
        verify(jsonContentService, never()).fetchJsonData(InternetProtocol.class, "test");
        verify(csvService).readCSVFileContent(null, "ipdata");
        verify(jsonContentService, never()).fetchJsonData(InternetProtocol.class, "InternetProtocol");
        verify(csvService, never()).generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"), InternetProtocol.class);
        verify(xlsxService, never()).read(multipartFile("IpData_mock.xlsx"), InternetProtocol.class);
        verify(xlsxService, never()).write(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"));
    }

    @Test
    void should_generate_csv_file_when_data_is_provided() throws Exception {
        when(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol")).thenReturn(List.of());
        when(csvService.generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"), InternetProtocol.class))
                .thenReturn(new ByteArrayInputStream(new ByteArrayOutputStream().toByteArray()));

        ResponseEntity<?> actualResponse = controller.generateCSV("Sample File");
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        verify(textContentParserService, never()).parseTextContent("test");
        verify(jsonContentService, never()).fetchJsonData(InternetProtocol.class, "test");
        verify(csvService, never()).readCSVFileContent(multipartFile("MOCK_DATA_TEST.csv"), "ipdata");
        verify(jsonContentService, times(2)).fetchJsonData(InternetProtocol.class, "InternetProtocol");
        verify(csvService).generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"), InternetProtocol.class);
        verify(xlsxService, never()).read(multipartFile("IpData_mock.xlsx"), InternetProtocol.class);
        verify(xlsxService, never()).write(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"));
    }

    @Test
    void should_read_xlsx_file_when_file_name_is_provided() throws Exception {
        File file = new File("src/main/resources/data/files/IpData_mock.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), fileInputStream);

        when(xlsxService.read(multipartFile, InternetProtocol.class)).thenReturn(Collections.emptyList());
        ResponseEntity<?> actualResponse = controller.readExcel(multipartFile);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertTrue(actualResponse.hasBody());
        verify(textContentParserService, never()).parseTextContent("test");
        verify(jsonContentService, never()).fetchJsonData(InternetProtocol.class, "test");
        verify(csvService, never()).readCSVFileContent(multipartFile("MOCK_DATA_TEST.csv"), "ipdata");
        verify(jsonContentService, never()).fetchJsonData(InternetProtocol.class, "InternetProtocol");
        verify(csvService, never()).generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"), InternetProtocol.class);
        verify(xlsxService).read(multipartFile, InternetProtocol.class);
        verify(xlsxService, never()).write(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"));
    }

    @Test
    void should_generate_xlsx_file_when_data_is_provided() throws Exception {
        when(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol")).thenReturn(List.of());
        when(xlsxService.write(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol")))
                .thenReturn(new ByteArrayInputStream(new ByteArrayOutputStream().toByteArray()));

        ResponseEntity<?> actualResponse = controller.generateExcel("sample");
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        verify(textContentParserService, never()).parseTextContent("test");
        verify(csvService, never()).readCSVFileContent(multipartFile("MOCK_DATA_TEST.csv"), "ipdata");
        verify(csvService, never()).generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"), InternetProtocol.class);
        verify(xlsxService, never()).read(multipartFile("IpData_mock.xlsx"), InternetProtocol.class);
        verify(jsonContentService, times(3)).fetchJsonData(InternetProtocol.class, "InternetProtocol");
        verify(xlsxService).write(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"));
    }

    private MultipartFile multipartFile(String fileName) throws IOException {
        File file = new File("src/main/resources/data/files/" + fileName);
        FileInputStream fileInputStream = new FileInputStream(file);

        return new MockMultipartFile(file.getName(), fileInputStream);
    }
}
