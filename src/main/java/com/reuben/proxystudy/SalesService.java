package com.reuben.proxystudy;

public class SalesService implements SellProvider{

    @Override
    public void sell() {
        System.out.println("销售货物");
    }
}
