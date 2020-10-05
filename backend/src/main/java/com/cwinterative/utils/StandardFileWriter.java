package com.cwinterative.utils;

import java.io.File;
import java.io.PrintWriter;

public class StandardFileWriter implements FileWriter {

    PrintWriter writer ;

    public StandardFileWriter() {

    }

    @Override
    public void write(String content) {

        try {
            writer = new PrintWriter(new File("querylogs.log"));
            writer.write(content);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    
    
}
