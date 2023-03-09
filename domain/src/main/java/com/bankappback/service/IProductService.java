package com.bankappback.service;

import com.bankappback.model.ProductDetail;

/**
 * IProductService
 * 
 * @author mjpol
 *
 */
public interface IProductService {

	/**
	 * findhSimilarProductIdsByProductId
	 * 
	 * @param productId
	 * @return Integer[]
	 */
	Integer[] findSimilarProductIdsByProductId(String productId);
	
	/**
	 * findProductById
	 * 
	 * @param productId
	 * @return CompletableFuture ProductDetail
	 */
	ProductDetail findProductById(String productId);
	
}
