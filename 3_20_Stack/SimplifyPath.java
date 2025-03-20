import java.util.*;

public class SimplifyPath {

        public String simplifyPath(String path) {
            String[] parts = path.split("/");
            Deque<String> stack = new LinkedList<>();

            for (String part : parts) {
                if (part.equals("..")) {
                    if (!stack.isEmpty()) {
                        stack.pop(); // 从栈顶移除元素
                    }
                } else if (!part.isEmpty() && !part.equals(".")) {
                    stack.push(part); // 将元素压入栈顶
                }
            }

            // 构建结果路径
            StringBuilder result = new StringBuilder();
            while (!stack.isEmpty()) {
                result.append("/").append(stack.removeLast()); // 从栈底开始取元素
            }

            return result.length() == 0 ? "/" : result.toString();
        }
}

