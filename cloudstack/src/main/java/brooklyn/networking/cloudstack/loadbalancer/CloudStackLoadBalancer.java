/*
 * Copyright 2013-2014 by Cloudsoft Corporation Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package brooklyn.networking.cloudstack.loadbalancer;

import java.util.Set;

import brooklyn.config.ConfigKey;
import brooklyn.entity.annotation.Effector;
import brooklyn.entity.basic.Attributes;
import brooklyn.entity.basic.ConfigKeys;
import brooklyn.entity.basic.Lifecycle;
import brooklyn.entity.basic.MethodEffector;
import brooklyn.entity.proxy.AbstractNonProvisionedController;
import brooklyn.entity.proxying.ImplementedBy;
import brooklyn.event.AttributeSensor;
import brooklyn.event.basic.AttributeSensorAndConfigKey;
import brooklyn.event.basic.BasicAttributeSensor;

import com.google.common.reflect.TypeToken;

@ImplementedBy(CloudStackLoadBalancerImpl.class)
public interface CloudStackLoadBalancer extends AbstractNonProvisionedController {

    AttributeSensor<Lifecycle> SERVICE_STATE = Attributes.SERVICE_STATE;

    AttributeSensor<String> LOAD_BALANCER_ID = new BasicAttributeSensor<String>(String.class, "cloudstack.loadbalancer.id", "The load balancer id within CloudStack");

    AttributeSensorAndConfigKey<String, String> LOAD_BALANCER_NAME = ConfigKeys.newSensorAndConfigKey(String.class, "cloudstack.loadbalancer.name", "The load balancer name");

    ConfigKey<String> ALGORITHM = ConfigKeys.newStringConfigKey("cloudstack.loadbalancer.algorithm", "Load balancing algorithm (default is ROUNDROBIN)", "ROUNDROBIN");

    ConfigKey<Integer> INSTANCE_PORT = ConfigKeys.newIntegerConfigKey("cloudstack.loadbalancer.instancePort", "The port for instances being balanced", 8080);

    ConfigKey<String> PUBLIC_IP_ID = ConfigKeys.newStringConfigKey("cloudstack.loadbalancer.publicIpId");

    ConfigKey<String> DESCRIPTION = ConfigKeys.newStringConfigKey("cloudstack.loadbalancer.description");

    ConfigKey<String> DOMAIN_ID = ConfigKeys.newStringConfigKey("cloudstack.loadbalancer.domainId");

    ConfigKey<String> ZONE_ID = ConfigKeys.newStringConfigKey("cloudstack.loadbalancer.zoneId");

    ConfigKey<String> ACCOUNT_IN_DOMAIN = ConfigKeys.newStringConfigKey("cloudstack.loadbalancer.accountInDomain");

    /** @deprecated in CloudStack; open firewall explicitly */
    @Deprecated
    ConfigKey<Set<String>> ALLOWED_SOURCE_CIDRs = ConfigKeys.newConfigKey(
            new TypeToken<Set<String>>() { }, "cloudstack.loadbalancer.cidr", "List of allowed source CIDRs (Deprecated in CloudStack)");

    /** @deprecated in CloudStack; open firewall explicitly */
    @Deprecated
    ConfigKey<Boolean> OPEN_FIREWALL = ConfigKeys.newBooleanConfigKey("cloudstack.loadbalancer.openFirewall", "Open firewall (Deprecated in CloudStack)", Boolean.FALSE);

    MethodEffector<Void> DELETE_LOAD_BALANCER = new MethodEffector<Void>(CloudStackLoadBalancer.class, "deleteLoadBalancer");

    @Effector(description="Deletes the load balancer")
    void deleteLoadBalancer();
}