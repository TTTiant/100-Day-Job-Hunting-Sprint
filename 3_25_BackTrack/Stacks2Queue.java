import java.util.Stack;

public class Stacks2Queue<T> {
    private Stack<T> stack1; // 用于入队
    private Stack<T> stack2; // 用于出队

    public Stacks2Queue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    // 入队（直接压入 stack1）
    public void push(T x) {
        stack1.push(x);
    }

    // 出队（如果 stack2 为空，就把 stack1 的元素全部倒入 stack2）
    public T pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }
        return stack2.pop();
    }

    // 查看队首元素（类似 pop，但不删除）
    public T peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }
        return stack2.peek();
    }

    // 判断队列是否为空
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public static void main(String[] args) {
        Stacks2Queue<Integer> queue = new Stacks2Queue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);

        System.out.println(queue.pop()); // 1（先入先出）
        System.out.println(queue.peek()); // 2（队首现在是 2）
        System.out.println(queue.pop()); // 2
        System.out.println(queue.empty()); // false（还剩 3）
        System.out.println(queue.pop()); // 3
        System.out.println(queue.empty()); // true（队列空了）
    }
}