package com.company;

public class FakeDB {

    public static int cash = 100;

    public synchronized int getCash(){
        return cash;
    }
    public synchronized void retiroDeCuenta(int disminuirDinero){

            cash = cash - disminuirDinero;

    }
}


