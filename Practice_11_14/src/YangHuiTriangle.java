import java.util.ArrayList;
import java.util.List;

public class YangHuiTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> dp = new ArrayList<>();
        for(int r = 0 ; r < numRows; r ++){
            List<Integer> row = new ArrayList<>();
            row.add(0,1);
            for(int c = 1; c <r; c++){
                row.add(c, dp.get(r-1).get(c-1) +dp.get(r-1).get(c));
            }
            if(r>0){
                row.add(r,1);
            }
            dp.add(r, row);
        }
        return dp;
    }

    public static void main(String[] args) {
        YangHuiTriangle obj = new YangHuiTriangle();
        System.out.println(obj.generate(10).toString());
    }
}

