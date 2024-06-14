package com.example.tablecalculator;

public class SimpleExpression {
    private Integer operand1;
    private Integer operand2;
    private String operator;
    private Integer value;

    public SimpleExpression(){
        operand1=0;
        operand2=0;
        operator="+";
        value=0;
    }

    public void setOperand1(int o){
        operand1=o;
    }

    public int getOperand1(){
        return operand1;
    }
    public void setOperand2(int o){
        operand2=o;
    }

    public int getOperand2(){
        return operand2;
    }

    public void setOperator(String s){
        operator=s;
    }

    public String getOperator(){
        return operator;
    }

    public Integer getValue(){
        computeValue();
        return value;
    }

    public void clearOperands(){
        operand1=0;
        operand2=0;
    }

    private void computeValue(){
        value = 0;
        if(operator.contentEquals("+")){
            value = operand1+operand2;
        }
        else if(operator.contentEquals("-")){
            value = operand1-operand2;
        }
        else if(operator.contentEquals("/")){
            value = operand1/operand2;
        }
        else if(operator.contentEquals("x")){
            value = operand1*operand2;
        }
        else{
            value = operand1 % operand2;
        }
    }
}
