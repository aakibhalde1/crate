/*
 * Licensed to CRATE Technology GmbH ("Crate") under one or more contributor
 * license agreements.  See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.  Crate licenses
 * this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.  You may
 * obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * However, if you have executed another commercial license agreement
 * with Crate these terms will supersede the license and you may use the
 * software solely pursuant to the terms of the relevant commercial agreement.
 */

package io.crate.expression.reference;

import io.crate.execution.engine.collect.CollectExpression;
import io.crate.execution.engine.collect.NestableCollectExpression;
import io.crate.expression.NestableInput;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ObjectCollectExpression<R> implements NestableCollectExpression<R, Map<String, Object>> {

    protected final Map<String, NestableInput> childImplementations;
    protected Map<String, Object> value;

    public ObjectCollectExpression(Map<String, NestableInput> childImplementations) {
        this.childImplementations = childImplementations;
    }

    public ObjectCollectExpression() {
        this.childImplementations = new HashMap<>();
    }

    @Override
    public NestableInput getChild(String name) {
        return childImplementations.get(name);
    }

    @Override
    public void setNextRow(R r) {
        Map<String, Object> map = new HashMap<>(childImplementations.size());
        for (Map.Entry<String, NestableInput> e : childImplementations.entrySet()) {
            NestableInput nestableInput = e.getValue();
            if (nestableInput instanceof CollectExpression) {
                //noinspection unchecked
                ((CollectExpression) nestableInput).setNextRow(r);
            }
            Object value = nestableInput.value();
            map.put(e.getKey(), value);
        }
        value = Collections.unmodifiableMap(map);
    }

    @Override
    public Map<String, Object> value() {
        return value;
    }
}