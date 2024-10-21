package com.liyz.dubbo.api.test.algorithm.link;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/8/12 20:35
 */
public class LinkNode {

    private int value;

    private LinkNode next;

    public LinkNode(int value, LinkNode next) {
        this.value = value;
        this.next = next;
    }

    public static void main(String[] args) {
        LinkNode linkNode5 = new LinkNode(5, null);
        LinkNode linkNode4 = new LinkNode(4, linkNode5);
        LinkNode linkNode3 = new LinkNode(3, linkNode4);
        LinkNode linkNode2 = new LinkNode(2, linkNode3);
        LinkNode linkNode1 = new LinkNode(1, linkNode2);
        LinkNode newHead = recursion(linkNode1);
        System.out.println(newHead);
    }

    public static LinkNode recursion(LinkNode headNode) {
        if (headNode == null || headNode.next == null) {
            return headNode;
        }
        LinkNode newHead = recursion(headNode.next);
        headNode.next.next = headNode;
        headNode.next = null;
        return newHead;
    }
}
