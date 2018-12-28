package comparator;

import com.wj.tmall.pojo.Product;


import java.util.Comparator;

//价格比较器 把价格低的放前面
public class ProductPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        double result = p1.getPromotePrice() - p2.getPromotePrice();
        return (int) (result < 0 ? Math.floor(result) : Math.ceil(result));
    }


}