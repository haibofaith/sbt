package com.haibo.base.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface PoiService {
    ResponseEntity fileUpload(MultipartFile file);
}
