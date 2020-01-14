package com.cjs.mall.controller;

import com.cjs.mall.common.CommonResult;
import com.cjs.mall.mbg.model.PmsBrand;
import com.cjs.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 商品品牌管理控制器
 * @author: cuijunsheng
 * @date: 2020-01-13 13:56
 **/
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@Controller
@RequestMapping("/brand")
public class PmsBrandController {

    private static Logger logger = LoggerFactory.getLogger(PmsBrandController.class);

    @Autowired
    private PmsBrandService brandService;

    @ApiOperation(value = "获取商品品牌列表")
    @GetMapping("queryAllBrand")
    @ResponseBody
    public CommonResult<List<PmsBrand>> queryAllBrand() {

        List<PmsBrand> brands = brandService.listBrands();
        return CommonResult.success(brands);
    }

    @ApiOperation(value = "根据id查找商品品牌")
    @GetMapping("getBrand/{id}")
    @ResponseBody
    public CommonResult<PmsBrand> getBrand(@PathVariable(value = "id") Long id) {

        return CommonResult.success(brandService.getBrand(id));
    }


    @ApiOperation(value = "添加商品品牌")
    @PostMapping("createBrand")
    @ResponseBody
    public CommonResult createBrand(PmsBrand pmsBrand) {
        CommonResult result;
        int count = brandService.saveBrand(pmsBrand);
        if (count > 0) {
            result = CommonResult.success(pmsBrand);
            logger.info("createBrand successfully:{}", pmsBrand);
        } else {
            result = CommonResult.failed();
            logger.info("createBrand failed:{}", pmsBrand);
        }

        return result;
    }


    @ApiOperation(value = "删除商品品牌")
    @GetMapping("deleteBrand/{id}")
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        CommonResult result;
        int count = brandService.deleteBrand(id);
        if (count > 0) {
            result = CommonResult.success(null);
            logger.info("deleteBrand successfully:id={}", id);
        } else {
            result = CommonResult.failed();
            logger.info("deleteBrand failed:id={}", id);
        }

        return result;
    }

    @ApiOperation(value = "修改指定商品品牌信息")
    @PostMapping("updateBrand/{id}")
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, PmsBrand pmsBrandVO) {
        CommonResult result;
        PmsBrand pmsBrandDTO = pmsBrandVO;
        int count = brandService.updateBrand(id, pmsBrandDTO);
        if (count > 0) {
            result = CommonResult.success(pmsBrandDTO);
            logger.info("updateBrand successfully:id={},pmsBrandVO={}", id,pmsBrandVO);
        } else {
            result = CommonResult.failed();
            logger.info("updateBrand failed:id={},pmsBrandVO={}", id,pmsBrandVO);
        }

        return result;
    }
}
