/**
 * 
 */
package com.bankappback.repository;

import com.bankappback.model.ProductDetail;

/**
 * @author mjpol
 *
 */
public interface IProductRepository {
	
	/**
	 * findSimilarProductIdsByProductId
	 * 
	 * @param productId
	 * @return Integer[]
	 */
	Integer[] findSimilarProductIdsByProductId(String productId);
	
	/**
	 * findProductById
	 * 
	 * @param productId
	 * @return ProductDetail
	 */
	ProductDetail findProductById(String productId);

}
