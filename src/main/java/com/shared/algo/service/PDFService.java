package com.shared.algo.service;

import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface PDFService {
    void write(HttpServletResponse response) throws Exception;
}
