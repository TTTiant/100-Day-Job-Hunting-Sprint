import java.util.*;

public class Merge {


     static class Interval{
        int start;
        int end;
        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }

     @Override
     public String toString() { //重写toString方法配合输出
         return "[" + start + "," + end + "]";
     }
    }

    public ArrayList<Interval> merge(ArrayList<Interval> intervals){
        if(intervals.size() == 0) return intervals;

        Collections.sort(intervals, (a,b) -> a.start - b.start);  //lambda 表达式 根据start升序排序

        int i  = 1;
        while(i < intervals.size()){
            Interval curr = intervals.get(i);
            Interval prev = intervals.get(i-1);
            if(curr.start <= prev.end){
                prev.end = Math.max(prev.end, curr.end);
                intervals.remove(i);
            }else{
                i++;
            }
        }
        return intervals;
    }

    public static void main(String[] args) {
        Merge merge = new Merge();
        ArrayList<Interval> intervals = new ArrayList<>(Arrays.asList(
                new Interval(1,3),
                new Interval(2,6),
                new Interval(5,9),
                new Interval(10,12)
        ));
        ArrayList<Interval> merged = merge.merge(intervals);
        System.out.println(merged);

    }
}
