package com.alipay.sofa.isle.sample.provider;

import com.alipay.sofa.isle.sample.facade.DemoJvmService;
import com.alipay.sofa.isle.sample.facade.SampleJvmService;
import com.alipay.sofa.runtime.api.aware.ClientFactoryAware;
import com.alipay.sofa.runtime.api.client.ClientFactory;
import com.alipay.sofa.runtime.api.client.ServiceClient;
import com.alipay.sofa.runtime.api.client.param.ServiceParam;
import org.springframework.beans.factory.annotation.Autowired;

public class PublishDemoServiceByClient implements ClientFactoryAware {
    private ClientFactory clientFactory;
    public void init() {
        ServiceClient serviceClient = clientFactory.getClient(ServiceClient.class);
        ServiceParam serviceParam = new ServiceParam();
        serviceParam.setInstance(new DemoServiceImpl(
                "Hello, DemoJvmService jvm service service-client implementation."));
        serviceParam.setUniqueId("demoServiceClientImpl");
        serviceParam.setInterfaceType(DemoJvmService.class);
        serviceClient.service(serviceParam);
    }

    @Override
    public void setClientFactory(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }
}
