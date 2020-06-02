package com.cjs.mall.service.impl;

import com.cjs.mall.mbg.mapper.PmsBrandMapper;
import com.cjs.mall.mbg.model.PmsBrand;
import com.cjs.mall.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020-01-13 13:56
 **/
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listBrands() {

        return brandMapper.selectByExample(null);
    }

    @Override
    public int saveBrand(PmsBrand pmsBrand) {

        return brandMapper.insert(pmsBrand);
    }

    @Override
    public PmsBrand getBrand(Long id) {

        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteBrand(Long id) {

        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateBrand(Long id, PmsBrand pmsBrandDTO) {
        PmsBrand pmsBrandDO = pmsBrandDTO;
        pmsBrandDO.setId(id);
        return brandMapper.updateByPrimaryKey(pmsBrandDO);
    }
}
