package com.wsy.design.pattern.adapter.reader;

import java.io.BufferedReader;

public class ReaderAdapter implements BReader {
    private ISReader isReader;

    public ReaderAdapter(ISReader isReader) {
        this.isReader = isReader;
    }

    @Override
    public BufferedReader getBufferedReader() {
        return new BufferedReader(isReader.getISReader());
    }
}
