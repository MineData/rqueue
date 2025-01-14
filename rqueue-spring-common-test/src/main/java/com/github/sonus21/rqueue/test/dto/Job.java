/*
 *  Copyright 2021 Sonu Kumar
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 *
 */

package com.github.sonus21.rqueue.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Job extends BaseQueueMessage {

  private String type;

  public Job(String id, String type) {
    super(id);
    this.type = type;
  }

  public static Job newInstance() {
    Type[] types = Type.values();
    int typeId = RandomUtils.nextInt(0, types.length);
    String id = UUID.randomUUID().toString();
    String type = types[typeId].name();
    return new Job(id, type);
  }

  @JsonIgnore
  public boolean isValid() {
    return !StringUtils.isEmpty(getId()) && !StringUtils.isEmpty(getType());
  }

  enum Type {
    FULL_TIME,
    PART_TIME,
    CONTRACT
  }
}
