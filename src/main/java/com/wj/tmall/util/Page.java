package com.wj.tmall.util;

public class Page {
        private int start;  //开始位置
        private int count; //每页显示的数量
        private int  total; //总共有多少条数据
        private String param; //参数

        private static final int defaultCount=5;  //默认每页显示五条

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Page (){
        count = defaultCount;
    }
    public Page(int start, int count) {
        this();
        this.start = start;
        this.count = count;
    }

    //判断是否有前一页
    public boolean isHasPreviouse(){
        //如果开始位置等于0.返回假没有前一页。不等于0反回真，有前一页
        if(start == 0)
            return  false;
        return true;
    }

    //判断是否有后一页
    public boolean isHasNext(){
        if(start == getLast())
            //如果开始位置等于最后位置。没有后一页
            return false;
        return true;
    }

    //根据 每页显示的数量count以及总共有多少条数据total，计算出总共有多少页
    public int  getTotalPage(){
        int totalPage;
        // 假设总数是50，是能够被5整除的，那么就有10页
        if(0 == total % count)
            totalPage=total / count;
        else
            // 假设总数是51，不能够被5整除的，那么就有11页
            totalPage=total / count + 1;
            //如果一页都没有。显示一页
        if(0==totalPage)
            totalPage = 1;
        return totalPage;
    }

    //计算出最后一页的开始数值是多少
    public int getLast(){
        int last;
        if(0 == total % count )
            // 假设总数是50，是能够被5整除的，那么最后一页的开始就是45
            last=total - count;
        else
            // 假设总数是51，不能够被5整除的，那么最后一页的开始就是50
            last=total - total % count;
            //如果last小于0等于0，不小于0等于last
        last = last<0?0:last;
        return  last;
    }



    @Override
    public String toString() {
        return "Page [start=" + start + ", count=" + count + ", total=" + total + ", getStart()=" + getStart()
                + ", getCount()=" + getCount() + ", isHasPreviouse()=" + isHasPreviouse() + ", isHasNext()="
                + isHasNext() + ", getTotalPage()=" + getTotalPage() + ", getLast()=" + getLast() + "]";
    }

}
