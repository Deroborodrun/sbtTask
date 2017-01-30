package com.sber;

import com.sber.domain.AllureSteps;
import com.sber.domain.ArithmeticOperation;
import com.sber.util.FileHelper;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(value = Parameterized.class)
public class OperationsTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationsTest.class);
    private static final String PATH = "src/main/resources/testfile";
    static {
        //set locale for correct work with float numbers
        Locale.setDefault(Locale.ENGLISH);
    }

    private  ArithmeticOperation operation;


    public OperationsTest(ArithmeticOperation operation) {
        this.operation = operation;
    }

    @Parameterized.Parameters
    public static List<ArithmeticOperation> testData() {
        return FileHelper.parseFile(PATH);
    }

    @Test
    public void test() {
        switch (operation.getOperation()) {
            case ADD:
                AllureSteps.addTest(operation);
                break;
            case SUBTRACT:
                AllureSteps.subtractTest(operation);
                break;
            case MULTIPLYING:
                AllureSteps.multiplyingTest(operation);
                break;
            case DIVISION:
                AllureSteps.divisionTest(operation);
                break;
            default:
                String message = "This operation must have a type";
                LOGGER.error(message);
                throw new IllegalStateException(message);
        }
    }
}
