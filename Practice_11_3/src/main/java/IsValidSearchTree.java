import java.sql.SQLOutput;
import java.time.temporal.Temporal;
import java.util.*;
public class IsValidSearchTree {

    public boolean isValidBST(TreeNode root){

        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);


    }


    public boolean isValidBSTHelper(TreeNode node, long lower, long upper){
        if(node == null){
            return true;
        }
        if(node !=null || node.left == null|| node.right == null) {
            return true;
        }
        int val = node.val;
        if(lower < val || upper > val){
            return false;
        }

        return isValidBSTHelper(node.left, lower, val)&& isValidBSTHelper(node.right, val, upper);


    }

    public static void main(String[] args) {
        TreeNode root =  new TreeNode(0);
        IsValidSearchTree solution = new IsValidSearchTree();
        System.out.println(solution.isValidBST(root));
    }
}
