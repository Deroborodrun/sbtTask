package com.sber.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.annotations.Step;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AllureSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllureSteps.class);
    private static final String EXPECTED_RESULT = "Actual result: ";
    private static final int SCALE = 2;
    private static final int ROUND_MODE = BigDecimal.ROUND_DOWN;


    private AllureSteps() {
    }

    @Step("Addition")
    public static void addTest(ArithmeticOperation arithmeticOperation) {
        LOGGER.info("Testing: " + arithmeticOperation);
        BigInteger expectedResult = arithmeticOperation.getOperand1().add(arithmeticOperation.getOperand2());
        LOGGER.info(EXPECTED_RESULT + expectedResult);
        //check if result has non zero fractional part
        assertTrue(isFractionZero(arithmeticOperation.getResult()));
        assertEquals(expectedResult, arithmeticOperation.getResult().toBigInteger());
    }

    @Step("Substract")
    public static void subtractTest(ArithmeticOperation arithmeticOperation) {
        LOGGER.info("Testing: " + arithmeticOperation);
        BigInteger expectedResult = arithmeticOperation.getOperand1().subtract(arithmeticOperation.getOperand2());
        LOGGER.info(EXPECTED_RESULT + expectedResult);
        //check if result has non zero fractional part
        assertTrue(isFractionZero(arithmeticOperation.getResult()));
        assertEquals(expectedResult, arithmeticOperation.getResult().toBigInteger());
    }

    @Step("Multiply")
    public static void multiplyingTest(ArithmeticOperation arithmeticOperation) {
        LOGGER.info("Testing: " + arithmeticOperation);
        BigInteger expectedResult = arithmeticOperation.getOperand1().multiply(arithmeticOperation.getOperand2());
        LOGGER.info(EXPECTED_RESULT + expectedResult);
        //check if result has non zero fractional part
        assertTrue(isFractionZero(arithmeticOperation.getResult()));
        assertEquals(expectedResult, arithmeticOperation.getResult().toBigInteger());
    }

    @Step("Division")
    public static void divisionTest(ArithmeticOperation arithmeticOperation) {
        LOGGER.info("Testing: " + arithmeticOperation);
        if (arithmeticOperation.isDivisionByZero()) {
            String expectedResult = String.valueOf(Double.NaN);
            LOGGER.info("Divide by zero");
            LOGGER.info(EXPECTED_RESULT + expectedResult);
            assertEquals(expectedResult, arithmeticOperation.getDivisionByZeroResult());
        } else {
            BigDecimal expectedResult = new BigDecimal(arithmeticOperation.getOperand1())
                    .divide(new BigDecimal(arithmeticOperation.getOperand2()), SCALE, ROUND_MODE);
            LOGGER.info(EXPECTED_RESULT + expectedResult);

            assertEquals(expectedResult, arithmeticOperation.getResult().setScale(SCALE, ROUND_MODE));
        }
    }

    private static boolean isFractionZero(BigDecimal decimal) {
        BigDecimal fractionalPart = decimal.remainder(BigDecimal.ONE);
        return BigDecimal.ZERO.compareTo(fractionalPart) == 0;

    }
}
