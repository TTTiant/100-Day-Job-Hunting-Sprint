import java.util.HashMap;
import java.util.Map;

public class TrieTree {
    //内部类定义节点
    private static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;

        public TrieNode() {
            this.children = new HashMap<>();
            this.isEndOfWord = false;
        }
    }

    private final TrieNode root;

    public TrieTree() { //初始化树，根节点
        this.root = new TrieNode();
    }

    // 插入单词到Trie中
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i); //取出第一个字符
            TrieNode node = current.children.get(c); //存入键对
            if (node == null) { //节点不存在就插入
                node = new TrieNode();
                current.children.put(c, node);
            }
            current = node; //移动指针到新的节点
        }
        current.isEndOfWord = true; //设置标记，表示word结束位置
    }

    // 检查单词是否存在于Trie中
    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEndOfWord;
    }

    // 检查是否有以给定前缀开头的单词
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }

    // 辅助方法：搜索节点
    private TrieNode searchNode(String str) {
        TrieNode current = root; //    设置指针
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            TrieNode node = current.children.get(c);
            if (node == null) {
                return null;
            }
            current = node;
        }
        return current;
    }

    // 打印Trie中的所有单词（用于调试）
    public void printTrie() {
        printTrieHelper(root, new StringBuilder());
    }

    private void printTrieHelper(TrieNode node, StringBuilder currentWord) {
        if (node.isEndOfWord) {
            System.out.println(currentWord.toString());
        }

        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            currentWord.append(entry.getKey());
            printTrieHelper(entry.getValue(), currentWord);
            currentWord.deleteCharAt(currentWord.length() - 1);
        }
    }

    public static void main(String[] args) {
        TrieTree trie = new TrieTree();
        String[] words = {"apple", "app", "banana", "band", "bat"};

        // 插入单词
        for (String word : words) {
            trie.insert(word);
        }

        // 打印Trie中的所有单词
        System.out.println("Trie中的单词:");
        trie.printTrie();

        // 测试搜索功能
        System.out.println("\n测试搜索:");
        System.out.println("apple: " + trie.search("apple"));  // true
        System.out.println("app: " + trie.search("app"));      // true
        System.out.println("ap: " + trie.search("ap"));        // false

        // 测试前缀匹配
        System.out.println("\n测试前缀匹配:");
        System.out.println("前缀ba: " + trie.startsWith("ba"));    // true
        System.out.println("前缀ban: " + trie.startsWith("ban"));  // true
        System.out.println("前缀cat: " + trie.startsWith("cat"));  // false
    }
}