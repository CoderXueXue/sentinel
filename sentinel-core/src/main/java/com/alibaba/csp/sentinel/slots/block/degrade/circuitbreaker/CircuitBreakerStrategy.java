/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker;

/**
 * @author Eric Zhao
 * @since 1.8.0
 */
public enum CircuitBreakerStrategy {

    /**
     * 当慢请求比超过阈值时，断路器打开(切断)。
     * 选择以慢调用比例作为阈值，需要设置允许的慢调用 RT（即最大的响应时间），请求的响应时间大于该值则统计为慢调用。
     * 当单位统计时长（statIntervalMs）内请求数目大于设置的最小请求数目，并且慢调用的比例大于阈值，则接下来的熔断时长内请求会自动被熔断。
     * 经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求响应时间小于设置的慢调用 RT 则结束熔断，
     * 若大于设置的慢调用 RT 则会再次被熔断。
     */
    SLOW_REQUEST_RATIO(0),
    /**
     * 当误差率超过阈值时，断路器打开(切断)。
     * 当单位统计时长（statIntervalMs）内请求数目大于设置的最小请求数目，并且异常的比例大于阈值，则接下来的熔断时长内请求会自动被熔断。
     * 经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求成功完成（没有错误）则结束熔断，否则会再次被熔断。
     * 异常比率的阈值范围是 [0.0, 1.0]，代表 0% - 100%。
     */
    ERROR_RATIO(1),
    /**
     *当错误计数超过阈值时，断路器打开(切断)。
     * 当单位统计时长内的异常数目超过阈值之后会自动进行熔断。
     * 经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求成功完成（没有错误）则结束熔断，否则会再次被熔断。
     */
    ERROR_COUNT(2);

    private int type;

    CircuitBreakerStrategy(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
