package com.epam.academy.parkingmachine;

public class Main
{
    public static void main( String[] args )
    {
        Cashier cashier = new Cashier();
        Thread t = new Thread(cashier);
        t.start();
    }
}
