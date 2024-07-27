package com.shared.info.service;

import jakarta.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface PDFService {
    void write(HttpServletResponse response) throws Exception;
}
