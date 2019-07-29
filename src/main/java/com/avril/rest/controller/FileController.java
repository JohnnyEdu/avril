package com.avril.rest.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
	private final String FILE_URL = "https://www.unpa.edu.mx/~blopez/algunosLibros/Padre-Rico-Padre-Pobre.pdf?fbclid=IwAR3gla1jnZJekZg6BBWAmjwiGSZWNEAbCMznN3qFyaCKVVVKoYffcF26UFg";
	
	@RequestMapping(path = "/pdfDownload", method = RequestMethod.GET)
	public ResponseEntity<Resource> download() throws IOException {
	    InputStream is = new URL(FILE_URL).openStream();
	    
	    byte[] content = new byte[is.available()];
	    
	    return ResponseEntity.ok()
	    		.header("Content-disposition", "attachment; filename=avril.pdf")
	            .contentLength(content.length)
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(new InputStreamResource(is));
	}
}
