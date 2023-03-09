package com.bankappback.service.impl;

import com.bankappback.exception.ResourceNotFoundException;
import com.bankappback.model.ProductDetail;
import com.bankappback.service.IProductService;
import com.bankappback.repository.IProductRepository;

public class ProductService implements IProductService {

	private IProductRepository productRepository;
	
	public ProductService(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public Integer[] findSimilarProductIdsByProductId(String productId) {
		try {
			return productRepository.findSimilarProductIdsByProductId(productId);
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("Product Not found");
		}
	}

	@Override
	public ProductDetail findProductById(String productId) {
		try {
			return productRepository.findProductById(productId);	
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("Product Not found");
		}
	}

}
