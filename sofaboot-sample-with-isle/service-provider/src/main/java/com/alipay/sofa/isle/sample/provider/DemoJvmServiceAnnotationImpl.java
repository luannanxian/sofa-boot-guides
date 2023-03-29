package com.alipay.sofa.isle.sample.provider;

import com.alipay.sofa.isle.sample.facade.DemoJvmService;
import com.alipay.sofa.runtime.api.annotation.SofaService;

@SofaService(uniqueId = "demoServiceClientAnnotationImpl")
public class DemoJvmServiceAnnotationImpl implements DemoJvmService {
    @Override
    public String returnMessage() {
        String message = "Hello, DemoJvmService jvm service service-annotation implementation.";
        System.out.println(message);
        return message;
    }
}
