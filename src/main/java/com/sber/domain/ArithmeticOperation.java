package com.sber.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ArithmeticOperation implements Serializable {
    private BigInteger operand1;
    private BigInteger operand2;
    private OperationType operation;
    private BigDecimal result;
    private boolean isDivisionByZero = false;
    private String divisionByZeroResult = null;

    public ArithmeticOperation(String operand1, String operand2, OperationType operation, String result) {
        this.operand1 = new BigInteger(operand1);
        this.operand2 = new BigInteger(operand2);
        this.operation = operation;
        if (operation == OperationType.DIVISION && this.operand2.equals(BigInteger.ZERO)) {
            isDivisionByZero = true;
            divisionByZeroResult = result;
        } else {
            this.result = new BigDecimal(result);
        }
    }

    public BigInteger getOperand1() {
        return operand1;
    }

    public BigInteger getOperand2() {
        return operand2;
    }

    public OperationType getOperation() {
        return operation;
    }

    public BigDecimal getResult() {
        return result;
    }

    public boolean isDivisionByZero() {
        return isDivisionByZero;
    }

    public String getDivisionByZeroResult() {
        return divisionByZeroResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArithmeticOperation that = (ArithmeticOperation) o;

        if (isDivisionByZero != that.isDivisionByZero) return false;
        if (operand1 != null ? !operand1.equals(that.operand1) : that.operand1 != null) return false;
        if (operand2 != null ? !operand2.equals(that.operand2) : that.operand2 != null) return false;
        if (operation != that.operation) return false;
        if (result != null ? !result.equals(that.result) : that.result != null) return false;
        return divisionByZeroResult != null ? divisionByZeroResult.equals(that.divisionByZeroResult) : that.divisionByZeroResult == null;
    }

    @Override
    public int hashCode() {
        int result1 = operand1 != null ? operand1.hashCode() : 0;
        result1 = 31 * result1 + (operand2 != null ? operand2.hashCode() : 0);
        result1 = 31 * result1 + (operation != null ? operation.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (isDivisionByZero ? 1 : 0);
        result1 = 31 * result1 + (divisionByZeroResult != null ? divisionByZeroResult.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "ArithmeticOperation{" +
                "operand1=" + operand1 +
                ", operand2=" + operand2 +
                ", operation=" + operation +
                ", result=" + (isDivisionByZero ? Double.NaN : result) +
                '}';
    }
}
