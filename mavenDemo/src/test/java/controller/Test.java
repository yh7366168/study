package controller;

import constant.ExceptionConstant;
import util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {

    public static void main(String[] args) {
        try{
            testCheckedException();
        }catch (CheckedException e){
            e.printStackTrace();
        }

        testUnCheckedException();
    }

    public static void testCheckedException() throws CheckedException{
        if(true){
            throw new CheckedException(ExceptionConstant.TestConstant.ExcceptionMessage);
        }
    }

    public static void testUnCheckedException(){
        if(true){
            throw new UnCheckedException("非检查异常，不处理编译器可以过去");
        }
    }
}
