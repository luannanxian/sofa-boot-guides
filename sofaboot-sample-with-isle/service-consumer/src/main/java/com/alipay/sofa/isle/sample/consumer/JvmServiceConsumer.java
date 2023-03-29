/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.isle.sample.consumer;

import com.alipay.sofa.isle.sample.facade.DemoJvmService;
import com.alipay.sofa.isle.sample.facade.SampleJvmService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.aware.ClientFactoryAware;
import com.alipay.sofa.runtime.api.client.ClientFactory;
import com.alipay.sofa.runtime.api.client.ReferenceClient;
import com.alipay.sofa.runtime.api.client.param.ReferenceParam;

/**
 * @author xuanbei 18/5/5
 */
public class JvmServiceConsumer implements ClientFactoryAware {
    private ClientFactory    clientFactory;
    @Autowired
    @SuppressWarnings("all")
    private DemoJvmService  sampleDemoJvmService;

    @Autowired
    @SuppressWarnings("all")
    private SampleJvmService sampleJvmService;

    @SofaReference(uniqueId = "annotationImpl")
    private SampleJvmService sampleJvmServiceByFieldAnnotation;

    @SofaReference(uniqueId = "demoServiceClientAnnotationImpl")
    private DemoJvmService demoJvmServiceImplsByFieldAnnotation;

    @SofaReference(uniqueId = "DemoServiceClientImpl")
    private DemoJvmService demoJvmServiceImplsByClient;

    public void init() {
        sampleJvmService.message();
        sampleJvmServiceByFieldAnnotation.message();
        sampleDemoJvmService.returnMessage();
        demoJvmServiceImplsByFieldAnnotation.returnMessage();

        ReferenceClient referenceClient = clientFactory.getClient(ReferenceClient.class);
        ReferenceParam<SampleJvmService> referenceParam = new ReferenceParam<SampleJvmService>();
        referenceParam.setInterfaceType(SampleJvmService.class);
        referenceParam.setUniqueId("serviceClientImpl");
        SampleJvmService sampleJvmServiceClientImpl = referenceClient.reference(referenceParam);
        sampleJvmServiceClientImpl.message();

        ReferenceParam<DemoJvmService> demoReferenceParam = new ReferenceParam<DemoJvmService>();
        demoReferenceParam.setInterfaceType(DemoJvmService.class);
        demoReferenceParam.setUniqueId("demoServiceClientImpl");
        DemoJvmService demoJvmServiceClientImpl = referenceClient.reference(demoReferenceParam);
        demoJvmServiceClientImpl.returnMessage();
    }

    public void setClientFactory(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }
}
