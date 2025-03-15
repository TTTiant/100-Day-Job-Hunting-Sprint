//字符串转换大小写，并且以单词为单位反转并输出

public class StringTransform {
    public String trans(String s, int n) {
        if (n == 0) return s;

        // 按空格拆分字符串
        String[] words = s.split(" ", -1); // 使用 -1 保留空字符串
        StringBuilder res = new StringBuilder();

        // 处理每个单词
        for (String word : words) {
            // 反转单词并调整大小写
            StringBuilder reversedWord = new StringBuilder(word).reverse();
            for (int i = 0; i < reversedWord.length(); i++) {
                char c = reversedWord.charAt(i);
                if (Character.isUpperCase(c)) {
                    reversedWord.setCharAt(i, Character.toLowerCase(c));
                } else if (Character.isLowerCase(c)) {
                    reversedWord.setCharAt(i, Character.toUpperCase(c));
                }
            }
            res.append(reversedWord).append(" ");
        }

        // 去掉最后一个空格并反转整个字符串
        res.setLength(res.length() - 1); // 去掉末尾空格
        return res.reverse().toString();
    }



    public class trans_2 {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         *
         * @param s string字符串
         * @param n int整型
         * @return string字符串
         */
        public String trans(String s, int n) {
            // 边界检测
            if (n == 0) return s;

            // 调整大小写
            StringBuffer res = new StringBuffer();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c >= 'A' && c <= 'Z') {
                    res.append((char) (c - 'A' + 'a')); // 大写转小写
                } else if (c >= 'a' && c <= 'z') {
                    res.append((char) (c - 'a' + 'A')); // 小写转大写
                } else {
                    res.append(c); // 非字母字符直接添加
                }
            }

            // 反转整个字符串
            res.reverse();

            // 反转每个单词
            for (int i = 0; i < n; i++) {
                int j = i;
                while (j < n && res.charAt(j) != ' ') {
                    j++;
                }
                // 反转单词
                String temp = res.substring(i, j);
                StringBuffer buffer = new StringBuffer(temp);
                temp = buffer.reverse().toString();
                res.replace(i, j, temp);
                i = j; // 跳过空格
            }

            return res.toString();
        }
    }


}

