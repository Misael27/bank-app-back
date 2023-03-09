package com.bankappback.service;

import java.util.List;

import com.bankappback.model.ProductDetail;

/**
 * 
 * @author mjpol
 *
 */
public interface ISimilarService {
	
	/**
	 * FindSimilarProductByProductId
	 * 
	 * @param productId
	 * @return
	 */
	List<ProductDetail> findSimilarProductByProductId(String productId);
	
}
