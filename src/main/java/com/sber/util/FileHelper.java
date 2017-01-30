package com.sber.util;

import com.sber.domain.ArithmeticOperation;
import com.sber.domain.OperationType;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class FileHelper {

    static final Logger LOGGER = LoggerFactory.getLogger(FileHelper.class);

    private FileHelper() {
    }

    public static List<ArithmeticOperation> parseFile(String path) {
        List<ArithmeticOperation> data = new ArrayList<>();
        try {

            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(path), "utf-8"), ';');
            String[] line;
            while ((line = reader.readNext()) != null) {
                String operand1 = line[0];
                String operand2 = line[1];
                OperationType type = OperationType.findByName(line[2]);
                String result = line[3];
                ArithmeticOperation arithmeticOperation = new ArithmeticOperation(operand1, operand2, type, result);
                data.add(arithmeticOperation);
            }
        } catch (IOException e) {
            LOGGER.error("Can't parse file " + path, e);
            throw new IllegalStateException(e);
        }
        return data;
    }

}
