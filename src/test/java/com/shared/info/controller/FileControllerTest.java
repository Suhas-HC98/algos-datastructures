package com.shared.info.controller;

import com.shared.info.dto.InternetProtocol;
import com.shared.info.service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static com.shared.info.utils.Constants.INTERNET_PROTOCOL;
import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileControllerTest {

    private final TextContentParserService textContentParserService = mock(TextContentParserService.class);
    private final JsonContentService jsonContentService = mock(JsonContentService.class);
    private final CsvService csvService = mock(CsvService.class);
    private final XlsxService xlsxService = mock(XlsxService.class);
    private final ReportService reportService = mock(ReportService.class);
    private final PDFService pdfService = mock(PDFService.class);
    private final HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);

    private final FileController controller = new FileController(textContentParserService, jsonContentService,
            csvService, xlsxService, pdfService, reportService);

    @Test
    void should_parse_the_file_and_return_string_when_fileName_is_provided() throws Exception {
        when(textContentParserService.parseTextContent("test")).thenReturn("Content parsed successfully");
        var actualResponse = controller.getFileContentAsString("test");
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
    void should_throw_null_pointer_when_incorrect_fileName_is_provided() throws Exception {
        when(textContentParserService.parseTextContent("test")).thenThrow(NullPointerException.class);
        var actualResponse = controller.getFileContentAsString("test");
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
    void should_fetch_json_date_when_fileName_is_provided() throws Exception {
        when(jsonContentService.fetchJsonData(InternetProtocol.class, "test")).thenReturn(emptyList());
        var actualResponse = controller.getJsonFromFile("test");
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
    void should_throw_exception_when_incorrect_filePath_is_provided() throws Exception {
        when(jsonContentService.fetchJsonData(InternetProtocol.class, "test")).thenThrow(NullPointerException.class);
        var actualResponse = controller.getJsonFromFile("test");
        assertEquals(HttpStatus.BAD_REQUEST, actualResponse.getStatusCode());
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
    void should_fetch_csv_date_when_fileName_is_provided() throws Exception {
        when(csvService.readCSVFileContent(null, "ipdata")).thenReturn(emptyList());
        var actualResponse = controller.readCSV(null, "ipdata");
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
    void should_throw_exception_when_incorrect_fileName_is_provided() throws Exception {
        when(csvService.readCSVFileContent(null, "ipdata")).thenThrow(NullPointerException.class);
        var actualResponse = controller.readCSV(null, "ipdata");
        assertEquals(HttpStatus.BAD_REQUEST, actualResponse.getStatusCode());
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
        when(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol")).thenReturn(emptyList());
        when(csvService.generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"), InternetProtocol.class))
                .thenReturn(new ByteArrayInputStream(new ByteArrayOutputStream().toByteArray()));
        var actualResponse = controller.generateCSV("Sample File");
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
    void should_throw_exception_while_generating_csv_file_when_incorrect_data_is_provided() throws Exception {
        when(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol")).thenReturn(emptyList());
        when(csvService.generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"), InternetProtocol.class))
                .thenThrow(NullPointerException.class);
        var actualResponse = controller.generateCSV("Sample File");
        assertEquals(HttpStatus.BAD_REQUEST, actualResponse.getStatusCode());
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
        var file = new File("src/main/resources/data/files/IpData_mock.xlsx");
        var fileInputStream = new FileInputStream(file);
        var multipartFile = new MockMultipartFile(file.getName(), fileInputStream);
        when(xlsxService.read(multipartFile, InternetProtocol.class)).thenReturn(emptyList());
        var actualResponse = controller.readExcel(multipartFile);
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
    void should_throw_exception_while_reading_xlsx_file_when_incorrect_fileName_is_provided() throws Exception {
        var file = new File("src/main/resources/data/files/IpData_mock.xlsx");
        var fileInputStream = new FileInputStream(file);
        var multipartFile = new MockMultipartFile(file.getName(), fileInputStream);
        when(xlsxService.read(multipartFile, InternetProtocol.class)).thenThrow(NullPointerException.class);
        var actualResponse = controller.readExcel(multipartFile);
        assertEquals(HttpStatus.BAD_REQUEST, actualResponse.getStatusCode());
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
        when(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol")).thenReturn(emptyList());
        when(xlsxService.write(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol")))
                .thenReturn(new ByteArrayInputStream(new ByteArrayOutputStream().toByteArray()));
        var actualResponse = controller.generateExcel("sample");
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        verify(textContentParserService, never()).parseTextContent("test");
        verify(csvService, never()).readCSVFileContent(multipartFile("MOCK_DATA_TEST.csv"), "ipdata");
        verify(csvService, never()).generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"), InternetProtocol.class);
        verify(xlsxService, never()).read(multipartFile("IpData_mock.xlsx"), InternetProtocol.class);
        verify(jsonContentService, times(3)).fetchJsonData(InternetProtocol.class, "InternetProtocol");
        verify(xlsxService).write(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"));
    }

    @Test
    void should_throw_exception_while_generating_xlsx_file_when_incorrect_data_is_provided() throws Exception {
        when(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol")).thenReturn(emptyList());
        when(xlsxService.write(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol")))
                .thenThrow(NullPointerException.class);
        var actualResponse = controller.generateExcel("sample");
        assertEquals(HttpStatus.BAD_REQUEST, actualResponse.getStatusCode());
        verify(textContentParserService, never()).parseTextContent("test");
        verify(csvService, never()).readCSVFileContent(multipartFile("MOCK_DATA_TEST.csv"), "ipdata");
        verify(csvService, never()).generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"), InternetProtocol.class);
        verify(xlsxService, never()).read(multipartFile("IpData_mock.xlsx"), InternetProtocol.class);
        verify(jsonContentService, times(3)).fetchJsonData(InternetProtocol.class, "InternetProtocol");
        verify(xlsxService).write(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"));
    }

    @Test
    void should_generate_pdf_file_when_data_is_provided() throws Exception {
        doNothing().when(pdfService).write(httpServletResponse);
        controller.generatePDF("sample", httpServletResponse);
        verify(pdfService).write(httpServletResponse);
    }

    @Test
    void should_throw_exception_while_generating_pdf_file_when_incorrect_fileName_is_provided() throws Exception {
        doThrow(NullPointerException.class).when(pdfService).write(httpServletResponse);
        controller.generatePDF("dummy", httpServletResponse);
        verify(pdfService).write(httpServletResponse);
    }

    @Test
    void should_throw_exception_while_generating_report_with_incorrect_fileName() throws Exception {
        when(jsonContentService.fetchJsonData(InternetProtocol.class, INTERNET_PROTOCOL)).thenThrow(NullPointerException.class);
        var generateReportResponse = controller.generatePDFReport("IpData Report");
        assertEquals(HttpStatus.BAD_REQUEST, generateReportResponse.getStatusCode());
        verify(jsonContentService).fetchJsonData(InternetProtocol.class, INTERNET_PROTOCOL);
    }

    private MultipartFile multipartFile(String fileName) throws IOException {
        var file = new File("src/main/resources/data/files/" + fileName);
        var fileInputStream = new FileInputStream(file);
        return new MockMultipartFile(file.getName(), fileInputStream);
    }
}
