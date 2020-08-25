package com.yan.fishmall.search.service;

import com.yan.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

public interface ProductSaveService {

    public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
