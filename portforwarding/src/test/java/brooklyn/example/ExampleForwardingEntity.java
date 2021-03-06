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
package brooklyn.example;

import brooklyn.config.ConfigKey;
import brooklyn.entity.Entity;
import brooklyn.entity.basic.ConfigKeys;
import brooklyn.entity.java.VanillaJavaApp;
import brooklyn.entity.proxying.ImplementedBy;
import brooklyn.event.AttributeSensor;
import brooklyn.event.basic.BasicAttributeSensor;

@ImplementedBy(ExampleForwardingEntityImpl.class)
public interface ExampleForwardingEntity extends VanillaJavaApp {

    ConfigKey<Entity> ORIGIN_ENTITY_FORWARDED = ConfigKeys.newConfigKey(
            Entity.class, "sshtunnel.origin.entity");

    ConfigKey<AttributeSensor<Integer>> ORIGIN_PORT_ATTRIBUTE_FORWARDED = (ConfigKey) ConfigKeys.newConfigKey(
            AttributeSensor.class, "sshtunnel.origin.portAttribute");

    // Note this is not set on this entity; it is set on the origin entity!
    AttributeSensor<String> PUBLIC_KEY_DATA = new BasicAttributeSensor<String>(String.class, 
            "sshtunnel.publicKeyData", "public key data for a node");

}
