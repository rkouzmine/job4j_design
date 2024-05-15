package ru.job4j.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeFirst {
    int value;
    TreeFirst left;
    TreeFirst right;

    public TreeFirst(int value, TreeFirst left, TreeFirst right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public TreeFirst(int value) {
        this.value = value;
    }

    public static int sumRecursive(TreeFirst root) {
        int sum = root.value;

        if (root.left != null) {
            sum += sumRecursive(root.left);
        }

        if (root.right != null) {
            sum += sumRecursive(root.right);
        }
        return sum;
    }

    public static int sumDeep(TreeFirst root) {
        Deque<TreeFirst> stack = new LinkedList<>();
        stack.push(root);
        int sum = 0;

        while (!stack.isEmpty()) {
            TreeFirst node = stack.pop();
            sum += node.value;

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return sum;
    }

    public static int sumWide(TreeFirst root) {
        Queue<TreeFirst> queue = new LinkedList<>();
        queue.add(root);
        int sum = 0;

        while (!queue.isEmpty()) {
            TreeFirst node = queue.remove();
            sum += node.value;

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeFirst root =
                new TreeFirst(20,
                        new TreeFirst(7,
                                new TreeFirst(4, null, new TreeFirst(6)), new TreeFirst(9)),
                        new TreeFirst(35,
                                new TreeFirst(31, new TreeFirst(28), null),
                                new TreeFirst(40, new TreeFirst(38), new TreeFirst(52))));

        System.out.println("Сумма дерева: " + sumRecursive(root));
        System.out.println("Сумма дерева: " + sumDeep(root));
        System.out.println("Сумма дерева: " + sumWide(root));
    }
}
