package com.jimmy.angular2.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jimmy.angular2.constants.Constants;

public class StorageService {
	public void store(MultipartFile file) throws Exception {
		Path rootLocation = Paths.get(Constants.STORAGE);
        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
	public Stream<Path> loadAll() throws Exception {
		 Path rootLocation = Paths.get(Constants.STORAGE);
	        try {
	            return Files.walk(rootLocation, 1)
	                    .filter(path -> !path.equals(rootLocation))
	                    .map(path -> rootLocation.relativize(path));
	        } catch (IOException e) {
	            throw new Exception("Failed to read stored files", e);
	        }

	 }
	    public Path load(String filename) {
	    	Path rootLocation = Paths.get(Constants.STORAGE);
	        return rootLocation.resolve(filename);
	    }

	    public Resource loadAsResource(String filename) throws Exception {
	        try {
	            Path file = load(filename);
	            Resource resource = new UrlResource(file.toUri());
	            if(resource.exists() || resource.isReadable()) {
	                return resource;
	            }
	            else {
	                throw new Exception("Could not read file: " + filename);

	            }
	        } catch (MalformedURLException e) {
	            throw new Exception("Could not read file: " + filename, e);
	        }
	    }

	    public void deleteAll() {
	    	Path rootLocation = Paths.get(Constants.STORAGE);
	        FileSystemUtils.deleteRecursively(rootLocation.toFile());
	    }

	    public void init() throws Exception {
	    	Path rootLocation = Paths.get(Constants.STORAGE);
	        try {
	            Files.createDirectory(rootLocation);
	        } catch (IOException e) {
	            throw new Exception("Could not initialize storage", e);
	        }
	    }
}
