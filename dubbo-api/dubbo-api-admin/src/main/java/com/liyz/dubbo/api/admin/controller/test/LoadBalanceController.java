package com.liyz.dubbo.api.admin.controller.test;

import com.google.common.collect.Lists;
import com.liyz.dubbo.api.admin.vo.test.LeastActiveVO;
import com.liyz.dubbo.api.admin.vo.test.WeightRandomVO;
import com.liyz.dubbo.api.admin.vo.test.WeightedRoundRobinVO;
import com.liyz.dubbo.common.api.result.Result;
import com.liyz.dubbo.security.client.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Desc:负载均衡
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/9/27 15:19
 */
@Api(tags = "负载均衡")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@Anonymous
@RestController
@RequestMapping("/loadBalance")
public class LoadBalanceController {

    @ApiOperation("随机")
    @GetMapping("/random")
    public Result<Integer> random() {
        List<Integer> list = Lists.newArrayList(1, 3, 6, 8, 10, 99, 555, 88888);
        int length = list.size();
        return Result.success(list.get(ThreadLocalRandom.current().nextInt(length)));
    }

    @ApiOperation("权重随机")
    @GetMapping("/weight/random")
    public Result<String> weightRandom() {
        List<WeightRandomVO> list = Lists.newArrayList(
                WeightRandomVO.builder().value("A").weight(100).build(),
                WeightRandomVO.builder().value("B").weight(80).build(),
                WeightRandomVO.builder().value("C").weight(70).build(),
                WeightRandomVO.builder().value("D").weight(50).build(),
                WeightRandomVO.builder().value("E").weight(40).build(),
                WeightRandomVO.builder().value("F").weight(30).build(),
                WeightRandomVO.builder().value("G").weight(20).build(),
                WeightRandomVO.builder().value("H").weight(10).build()
        );
        int length = list.size();

        boolean sameWeight = true;
        int[] weights = new int[length];
        int totalWeight = 0;

        int offset;
        int i;
        for(offset = 0; offset < length; ++offset) {
            i = list.get(offset).getWeight();
            totalWeight += i;
            weights[offset] = totalWeight;
            if (sameWeight && totalWeight != i * (offset + 1)) {
                sameWeight = false;
            }
        }

        if (totalWeight > 0 && !sameWeight) {
            offset = ThreadLocalRandom.current().nextInt(totalWeight);

            for(i = 0; i < length; ++i) {
                if (offset < weights[i]) {
                    return Result.success(list.get(i).getValue());
                }
            }
        }
        return Result.success(list.get(ThreadLocalRandom.current().nextInt(length)).getValue());
    }

    private static final ConcurrentMap<String, WeightedRoundRobinVO> MAP = new ConcurrentHashMap<>();

    @ApiOperation("加权轮询")
    @GetMapping("/roundrobin")
    public Result<String> roundrobin() {
        List<WeightRandomVO> list = Lists.newArrayList(
                WeightRandomVO.builder().value("A").weight(3).build(),
                WeightRandomVO.builder().value("B").weight(2).build(),
                WeightRandomVO.builder().value("C").weight(1).build()
        );
        int totalWeight = 0;
        long maxCurrent = Long.MIN_VALUE;
        WeightedRoundRobinVO selectedWRR = null;

        int weight;
        for(Iterator var13 = list.iterator(); var13.hasNext(); totalWeight += weight) {
            WeightRandomVO item = (WeightRandomVO) var13.next();
            weight = item.getWeight();
            int finalWeight = weight;
            WeightedRoundRobinVO weightedRoundRobin = MAP.computeIfAbsent(item.getValue(), (k) -> {
                WeightedRoundRobinVO wrr = new WeightedRoundRobinVO();
                wrr.setWeight(finalWeight);
                wrr.setValue(item.getValue());
                return wrr;
            });
            if (weight != weightedRoundRobin.getWeight()) {
                weightedRoundRobin.setWeight(weight);
            }

            long cur = weightedRoundRobin.increaseCurrent();
            if (cur > maxCurrent) {
                maxCurrent = cur;
                selectedWRR = weightedRoundRobin;
            }
        }
        selectedWRR.sel(totalWeight);
        return Result.success(selectedWRR.getValue());
    }

    private static final List<LeastActiveVO> list = Lists.newArrayList(
            LeastActiveVO.builder().value("A").weight(3).active(new AtomicInteger(3)).build(),
            LeastActiveVO.builder().value("B").weight(2).active(new AtomicInteger(2)).build(),
            LeastActiveVO.builder().value("C").weight(1).active(new AtomicInteger(1)).build()
    );

    @ApiOperation("最小活跃数")
    @GetMapping("/leastactive")
    public Result<String> leastactive() {
        int length = list.size();
        int leastActive = -1;
        int leastCount = 0;
        int[] leastIndexes = new int[length];
        int[] weights = new int[length];
        int totalWeight = 0;
        int firstWeight = 0;
        boolean sameWeight = true;

        int offsetWeight;
        int leastIndex;
        for(offsetWeight = 0; offsetWeight < length; ++offsetWeight) {
            LeastActiveVO item = list.get(offsetWeight);
            leastIndex = item.getActive();
            int afterWarmup = item.getWeight();
            weights[offsetWeight] = afterWarmup;
            if (leastActive != -1 && leastIndex >= leastActive) {
                if (leastIndex == leastActive) {
                    leastIndexes[leastCount++] = offsetWeight;
                    totalWeight += afterWarmup;
                    if (sameWeight && afterWarmup != firstWeight) {
                        sameWeight = false;
                    }
                }
            } else {
                leastActive = leastIndex;
                leastCount = 1;
                leastIndexes[0] = offsetWeight;
                totalWeight = afterWarmup;
                firstWeight = afterWarmup;
                sameWeight = true;
            }
        }

        if (leastCount == 1) {
            LeastActiveVO cur = list.get(leastIndexes[0]);
            cur.increase();
            return Result.success(cur.getValue());
        } else {
            if (!sameWeight && totalWeight > 0) {
                offsetWeight = ThreadLocalRandom.current().nextInt(totalWeight);

                for(int i = 0; i < leastCount; ++i) {
                    leastIndex = leastIndexes[i];
                    offsetWeight -= weights[leastIndex];
                    if (offsetWeight < 0) {
                        LeastActiveVO cur = list.get(leastIndex);
                        cur.increase();
                        return Result.success(cur.getValue());
                    }
                }
            }
            LeastActiveVO cur = list.get(leastIndexes[ThreadLocalRandom.current().nextInt(leastCount)]);
            cur.increase();
            return Result.success(cur.getValue());
        }
    }
}
