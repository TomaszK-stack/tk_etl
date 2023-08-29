package com.example.tk_etlproc.reading.sources;

import java.io.FileNotFoundException;

public interface Source {
    String read(String path) throws FileNotFoundException;}
