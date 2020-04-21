package com.liteworm.dao;

import com.liteworm.beans.Goods;

/**
 * @ClassName GoodsDao
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/21 14:50
 * @Version 1.0
 **/
public interface GoodsDao {
    Goods selectGoodsById(Integer id);
    int updateGoods(Goods goods);
}
