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

package com.github.sonus21.rqueue.spring.tests.integration;

import com.github.sonus21.rqueue.exception.TimedOutException;
import com.github.sonus21.rqueue.spring.app.SpringApp;
import com.github.sonus21.rqueue.spring.tests.SpringIntegrationTest;
import com.github.sonus21.rqueue.test.tests.MultiLevelQueueTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = SpringApp.class)
@Slf4j
@WebAppConfiguration
@TestPropertySource(
    properties = {
      "spring.redis.port=7006",
      "mysql.db.name=WeightedMultiLevelQueueListener",
      "sms.queue.active=true",
      "notification.queue.active=false",
      "email.queue.active=false",
      "job.queue.active=false",
      "use.system.redis=false",
      "priority.mode=WEIGHTED",
    })
@SpringIntegrationTest
class WeightedMultiLevelQueueListener extends MultiLevelQueueTest {

  @Test
  void simple() throws TimedOutException {
    checkQueueLevelConsumer();
  }

  @Test
  void scheduled() throws TimedOutException {
    checkQueueLevelConsumerWithDelay();
  }
}
