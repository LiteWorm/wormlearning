package com.liteworm.service.impl;

import com.liteworm.beans.Goods;
import com.liteworm.beans.Sale;
import com.liteworm.dao.GoodsDao;
import com.liteworm.dao.SaleDao;
import com.liteworm.excep.NotEnoughException;
import com.liteworm.service.BuyGoods;

/**
 * @ClassName BuyGoodsIml
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/21 15:03
 * @Version 1.0
 **/
public class BuyGoodsIml implements BuyGoods {
    private  GoodsDao goodsDao;
    private SaleDao saleDao;

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    public void setSaleDao(SaleDao saleDao) {
        this.saleDao = saleDao;
    }

    @Override
    public void buyGoods(Integer goodsId, Integer nums) throws  NullPointerException,NotEnoughException{
        //生成sale订单
        Sale sale = new Sale();
        sale.setGid(goodsId);
        sale.setNums(nums);
        saleDao.insertSale(sale);

        //修改库存
        Goods goods = goodsDao.selectGoodsById(goodsId);
        if(goods == null){

            throw  new NullPointerException(goodsId + "没有，不存在");
        }
        if (goods.getAmount() < nums){
            //库存不足
            throw new NotEnoughException(goodsId + "库存不足");
        }

        //操作库存
        goods.setAmount(nums);
        goodsDao.updateGoods(goods);
    }
}