/*
*
* Copyright 2013 Netflix, Inc.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/
package com.netflix.niws.client;

import com.netflix.loadbalancer.AbstractLoadBalancer;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

public abstract class AbstractNIWSLoadBalancerPing implements IPing, IClientConfigAware{

    AbstractLoadBalancer lb;
    
    @Override
    public boolean isAlive(Server server) {
        return true;
    }
    
    public void setLoadBalancer(AbstractLoadBalancer lb){
        this.lb = lb;
    }
    
    public AbstractLoadBalancer getLoadBalancer(){
        return lb;
    }

}
