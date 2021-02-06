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

package com.github.sonus21.rqueue.core.middleware;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.github.sonus21.TestBase;
import com.github.sonus21.rqueue.CoreUnitTest;
import com.github.sonus21.rqueue.core.Job;
import com.github.sonus21.rqueue.core.context.DefaultContext;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

@CoreUnitTest
class ContextMiddlewareTest extends TestBase {

  @Test
  void handleReturnNullContext() {
    ContextMiddleware contextMiddleware = job -> null;
    assertThrows(
        IllegalStateException.class, () -> contextMiddleware.handle(mock(Job.class), null));
  }

  @Test
  void handle() throws Exception {
    ContextMiddleware contextMiddleware = job -> DefaultContext.EMPTY;
    AtomicInteger atomicInteger = new AtomicInteger();
    contextMiddleware.handle(
        mock(Job.class),
        () -> {
          atomicInteger.incrementAndGet();
          return null;
        });
    assertEquals(1, atomicInteger.get());
  }
}