package com.gomezrondon.springintegration;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class FilePrinter {
    public void print(File file) throws IOException {
        Files.lines(file.toPath()).forEach(System.out::println);
    }
}
