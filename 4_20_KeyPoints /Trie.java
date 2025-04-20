import java.util.HashMap;
import java.util.Map;

public class Trie {
    //嵌套的树节点结构
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndofWord;

        TrieNode() {
            this.children = new HashMap<>();
            this.isEndofWord = false;
        }
    }
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        //
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i); //取出第一个字符
            TrieNode node = current.children.get(c);
            if(node == null) { //节点不存在就插入
                node = new TrieNode();
                current.children.put(c, node);
            }
            current = node; //移动指针到新的节点
        }
        current.isEndofWord = true; //设置标记，表示word结束位置
    }
    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEndofWord;
    }

    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }

    public TrieNode searchNode(String str){
        TrieNode current = root;
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            TrieNode node = current.children.get(c);
            if(node == null) {
                return null;
            }
            current = node;
        }
        return current;
    }

    public void printTrie(){
        printTrieRecursively(root,new StringBuilder());

    }
    public void printTrieRecursively(TrieNode node, StringBuilder currentWord){
        if (node.isEndofWord) {
            System.out.println(currentWord.toString());
        }

        for(Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            currentWord.append(entry.getKey());
            printTrieRecursively(entry.getValue(), currentWord);
            currentWord.deleteCharAt(currentWord.length() - 1);
        }
    }
}
