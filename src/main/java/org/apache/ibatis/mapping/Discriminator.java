/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.mapping;

import java.util.Collections;
import java.util.Map;

import org.apache.ibatis.session.Configuration;

/**鉴别器
 *
 *   <discriminator javaType="int" column="vehicle_type">
 *     <case value="1" resultMap="carResult"/>
 *     <case value="2" resultMap="truckResult"/>
 *     <case value="3" resultMap="vanResult"/>
 *     <case value="4" resultMap="suvResult"/>
 *   </discriminator>
 *
 *   <resultMap  type="org.apache.ibatis.submitted.discriminator.Vehicle"  id="vehicleResult">
 *     <id property="id" column="id" />
 *     <result property="maker" column="maker" />
 *     <discriminator javaType="int" column="vehicle_type">
 *       <case value="1"  resultType="org.apache.ibatis.submitted.discriminator.Car">
 *         <result property="doorCount" column="door_count" />
 *       </case>
 *       <case value="2"  resultType="org.apache.ibatis.submitted.discriminator.Truck">
 *         <result property="carryingCapacity"
 *           column="carrying_capacity" />
 *       </case>
 *     </discriminator>
 *   </resultMap>
 * @author Clinton Begin
 */
public class Discriminator {

  private ResultMapping resultMapping;
  private Map<String, String> discriminatorMap;

  Discriminator() {
  }

  public static class Builder {
    private Discriminator discriminator = new Discriminator();

    public Builder(Configuration configuration, ResultMapping resultMapping, Map<String, String> discriminatorMap) {
      discriminator.resultMapping = resultMapping;
      discriminator.discriminatorMap = discriminatorMap;
    }

    public Discriminator build() {
      assert discriminator.resultMapping != null;
      assert discriminator.discriminatorMap != null;
      assert !discriminator.discriminatorMap.isEmpty();
      //lock down map
      // lock down map 生成不可变集合，避免修改
      discriminator.discriminatorMap = Collections.unmodifiableMap(discriminator.discriminatorMap);
      return discriminator;
    }
  }

  public ResultMapping getResultMapping() {
    return resultMapping;
  }

  public Map<String, String> getDiscriminatorMap() {
    return discriminatorMap;
  }

  public String getMapIdFor(String s) {
    return discriminatorMap.get(s);
  }

}
