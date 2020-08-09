package com.wsy.design.pattern.adapter;

import com.wsy.design.pattern.adapter.charge.ChinaCharge;
import com.wsy.design.pattern.adapter.reader.BReader;
import com.wsy.design.pattern.adapter.reader.ISReader;
import com.wsy.design.pattern.adapter.reader.ReaderAdapter;
import com.wsy.design.pattern.adapter.reader.impl.ISReaderImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

public class AdapterMain {
    public static void main(String[] args) {
        ChinaCharge chinaCharge = new ChinaAdapter(new USA());
        chinaCharge.chinaCharge();
        try {
            ISReader isReader = new ISReaderImpl(new FileInputStream(new File("/Users/wangsongye/Documents/codes/Android进阶/DesignPattern/src/com/wsy/design/pattern/facade/facade.txt")));
            BReader bReader = new ReaderAdapter(isReader);
            BufferedReader bufferedReader = bReader.getBufferedReader();
            String line = bufferedReader.readLine();
            System.out.println(line);
            bufferedReader.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}