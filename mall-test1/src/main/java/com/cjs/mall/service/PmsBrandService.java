package com.cjs.mall.service;

import com.cjs.mall.mbg.model.PmsBrand;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020-01-13 13:55
 **/
public interface PmsBrandService {

    /**
     * 获取所有商品品牌信息
     * @return
     */
    List<PmsBrand> listBrands();

    /**
     * 添加商品品牌信息
     * @param pmsBrand
     * @return
     */
    int saveBrand(PmsBrand pmsBrand);

    /**
     * 根据id查询商品品牌信息
     * @param id
     * @return
     */
    PmsBrand getBrand(Long id);

    /**
     * 根据id删除一个商品品牌
     * @param id
     * @return
     */
    int deleteBrand(Long id);

    /**
     * 修改商品品牌信息
     * @param id
     * @param pmsBrandDTO
     * @return
     */
    int updateBrand(Long id, PmsBrand pmsBrandDTO);
}
