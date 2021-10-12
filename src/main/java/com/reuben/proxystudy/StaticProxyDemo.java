package com.reuben.proxystudy;

public class StaticProxyDemo implements SellProvider{
    SalesService service;

    public StaticProxyDemo(SalesService _service){
        super();
        this.service = _service;
    }

    @Override
    public void sell() {
        preSaleService();
        service.sell();
        afterSaleService();
    }

    void preSaleService(){
        System.out.println("售前服务");
    }

    void afterSaleService(){
        System.out.println("售后增值");
    }
}
