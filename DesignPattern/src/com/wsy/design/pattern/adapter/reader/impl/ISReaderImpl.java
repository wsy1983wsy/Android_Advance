package com.wsy.design.pattern.adapter.reader.impl;

import com.wsy.design.pattern.adapter.reader.ISReader;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ISReaderImpl implements ISReader {
    private InputStream inputStream;

    public ISReaderImpl(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStreamReader getISReader() {
        return new InputStreamReader(inputStream);
    }
}
