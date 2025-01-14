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

package com.github.sonus21.rqueue.utils.pebble;

import com.github.sonus21.rqueue.models.db.DeadLetterQueue;
import com.github.sonus21.rqueue.utils.Constants;
import com.mitchellbosecke.pebble.extension.Function;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

@SuppressWarnings("unchecked")
public class DeadLetterQueuesFunction implements Function {

  public static final String FUNCTION_NAME = "dlq";

  @Override
  public Object execute(
      Map<String, Object> args, PebbleTemplate self, EvaluationContext context, int lineNumber) {
    List<DeadLetterQueue> deadLetterQueues = (List<DeadLetterQueue>) args.get("queues");

    if (CollectionUtils.isEmpty(deadLetterQueues)) {
      return "";
    }
    List<String> queues =
        deadLetterQueues.stream().map(DeadLetterQueue::getName).collect(Collectors.toList());
    return String.join(Constants.Comma, queues);
  }

  @Override
  public List<String> getArgumentNames() {
    return Collections.singletonList("queues");
  }
}
