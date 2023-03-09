package com.bankappback.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import com.bankappback.exception.ResourceNotFoundException;
import com.bankappback.model.ProductDetail;
import com.bankappback.service.IProductService;
import com.bankappback.service.ISimilarService;

/**
 * SimilarService
 * 
 * @author mjpol
 *
 */
public class SimilarService implements ISimilarService {
	
	private IProductService productService;
		
	public SimilarService(IProductService productService) {
		this.productService = productService;
	}
	
	@Override
	public List<ProductDetail> findSimilarProductByProductId(String productId) {
		final List<Integer> productIds = Arrays.asList(productService.findSimilarProductIdsByProductId(productId));
		final List<ProductDetail> productResult = getProductDetailByProductIds(productIds);
		productResult.sort((p1, p2) -> Integer.valueOf(p1.getId()).compareTo(Integer.valueOf(p2.getId())) );
		return productResult;
	}
	
	/**
	 * getProductDetailByProductIds
	 * 
	 * @param productIds
	 * @return
	 */
	private List<ProductDetail> getProductDetailByProductIds(List<Integer> productIds) {
		List<ProductDetail> productResult = new ArrayList<ProductDetail>();
		ForkJoinPool forkJoinPool = new ForkJoinPool(5);
		try {
		    forkJoinPool.submit(() -> productIds.parallelStream().forEach(id -> {
		         final ProductDetail productDetail = this.productService.findProductById(id.toString());
		         synchronized(productResult) {
		        	 productResult.add(productDetail);
		         }
		    })).get();
		} catch (Exception e) {
			throw new ResourceNotFoundException("Product Not found");
		} finally {
		    if (forkJoinPool != null) {
		        forkJoinPool.shutdown();
		    }
		}
		return productResult;
	}
	
}
