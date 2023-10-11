package com.liyz.dubbo.api.admin.controller.algorithm;

import com.google.common.collect.Lists;
import com.liyz.dubbo.common.api.result.Result;
import com.liyz.dubbo.security.client.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/10/9 13:26
 */
@Api(tags = "链表算法")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@Anonymous
@RestController
@RequestMapping("/linkedList")
public class LinkedListController {

    private static final LinkedList<Integer> LINKED_LIST = Lists.newLinkedList(Lists.newArrayList(3, 6, 1, 99, 5, 100, 43));

    public static final ListNode<Integer> head;

    public static class ListNode<T> {

        private T value;

        private ListNode<T> next;

        public ListNode(T value) {
            this.value = value;
            this.next = null;
        }
    }

    static {
        head = new ListNode<>(3);
        ListNode<Integer> now = new ListNode<>(6);
        head.next = now;
        now.next = new ListNode<>(1);
        now = now.next;
        now.next = new ListNode<>(99);
        now = now.next;
        now.next = new ListNode<>(5);
        now = now.next;
        now.next = new ListNode<>(100);
        now = now.next;
        now.next = new ListNode<>(43);
    }

    @ApiOperation("快慢指针查找中间值")
    @GetMapping("/mid")
    public Result<Integer> mid() {
        ListNode<Integer> fast, slow;
        fast = slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                slow = head;
                while(slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return Result.success(slow.value);
            }
        }
        return Result.success(slow.value);
    }

    @ApiOperation("快慢指针查找是否有环")
    @GetMapping("/cycle")
    public Result<Boolean> cycle() {
        if (head == null || head.next == null) {
            return Result.success(Boolean.FALSE);
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return Result.success(Boolean.FALSE);
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return Result.success(Boolean.TRUE);
    }
}
