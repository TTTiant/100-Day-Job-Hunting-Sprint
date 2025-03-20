import java.util.Deque;
import java.util.LinkedList;

 class MinStack {
    private Deque<Integer> stack;  //存储所有元素
    private Deque<Integer> minStack;  //存储当前最小值

    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }

    // 将元素压入栈
    public void push(int val) {
        stack.push(val);  // 压入主栈
        // 如果辅助栈为空，或者新元素小于等于辅助栈的栈顶元素，则压入辅助栈
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    // 弹出栈顶元素
    public void pop() {
        // 如果主栈的栈顶元素等于辅助栈的栈顶元素，则辅助栈也需要弹出
        if (stack.pop().equals(minStack.peek())) {
            minStack.pop();
        }
    }

    // 获取栈顶元素
    public int top() {
        return stack.peek();
    }

    // 获取栈中的最小元素
    public int getMin() {
        return minStack.peek();
    }
}

