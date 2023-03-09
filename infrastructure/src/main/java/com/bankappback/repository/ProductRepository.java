package com.bankappback.repository;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bankappback.model.ProductDetail;
import com.bankappback.repository.IProductRepository;

/**
 * ProductApiAdapter
 * 
 * @author mjpol
 *
 */
@Component
public class ProductRepository implements IProductRepository {

	RestTemplate restTemplate;
	
	public ProductRepository(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public Integer[] findSimilarProductIdsByProductId(String productId) {
		return restTemplate.getForObject("/product/" + productId + "/similarids", Integer[].class);	
	}

	@Override
	public ProductDetail findProductById(String productId) {
		return restTemplate.getForObject("/product/" + productId, ProductDetail.class);	
	}
	
}
