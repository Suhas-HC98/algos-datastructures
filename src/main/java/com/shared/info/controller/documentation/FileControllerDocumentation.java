package com.shared.info.controller.documentation;

import com.shared.info.utils.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileControllerDocumentation {

    @Operation(method = "GET", description = "Retrieves the string from file", tags = "file")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    ResponseEntity<GenericResponse<?>> getFileContentAsString(@Parameter(description = "contains file name") String fileName);

    @Operation(method = "GET", description = "Retrieves the json from file", tags = "file")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    ResponseEntity<GenericResponse<?>> getJsonFromFile(@Parameter(description = "path") String stringPath);

    @Operation(method = "POST", description = "Retrieves the data from CSV file", tags = "csv-file")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    ResponseEntity<GenericResponse<?>> readCSV(@Parameter(description = "file") MultipartFile multipartFile, @Parameter(description = "class type") String contentType);

    @Operation(method = "GET", description = "Produces CSV file", tags = "csv-file")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    ResponseEntity<?> generateCSV(@Parameter(description = "filename") String fileName);

    @Operation(method = "POST", description = "Reads Excel file", tags = "xlsx-file")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    ResponseEntity<?> readExcel(@Parameter(description = "file") MultipartFile multipartFile);

    @Operation(method = "GET", description = "Produces Excel file", tags = "xlsx-file")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    ResponseEntity<?> generateExcel(@Parameter(description = "filename") String fileName);

    @Operation(method = "GET", description = "Produces PDF file", tags = "pdf-file")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    void generatePDF(@Parameter(description = "filename") String fileName, HttpServletResponse response);

    @Operation(method = "GET", description = "Produces PDF file", tags = "pdf-file")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    ResponseEntity<InputStreamResource> generatePDFReport(@Parameter(description = "filename") String title);
}
