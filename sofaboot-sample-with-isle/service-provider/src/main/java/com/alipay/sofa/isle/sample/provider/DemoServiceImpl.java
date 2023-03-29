package com.alipay.sofa.isle.sample.provider;

import com.alipay.sofa.isle.sample.facade.DemoJvmService;

public class DemoServiceImpl implements DemoJvmService {
    private String message;

    public DemoServiceImpl(String message) {
        this.message = message;
    }

    public DemoServiceImpl() {
    }

    @Override
    public String returnMessage() {
        System.out.println(message);
        return message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
