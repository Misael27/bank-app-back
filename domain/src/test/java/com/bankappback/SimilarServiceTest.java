//package com.bankappback;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.mockito.BDDMockito.given;
//import org.mockito.Mock;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.stubbing.Answer;
//
//import com.bankappback.model.ProductDetail;
//import com.bankappback.service.impl.ProductService;
//import com.bankappback.service.impl.SimilarService;
//
//
//public class SimilarServiceTest {
//	
//    @Mock
//    private ProductService productService;
//	
//	private SimilarService similarService;
//	final String productIdRequest = "1";
//	final String productIdTwo = "2";
//	final String productIdThree = "3";
//	final Integer[] productSimilarIds = {Integer.valueOf(productIdTwo), Integer.valueOf(productIdThree)};
//
//	
//	@BeforeEach
//	void setUp()
//    {
//		given(productService.findSimilarProductIdsByProductId(productIdRequest))
//		.willReturn(productSimilarIds);
//		given(productService.findProductById(productIdTwo)).willAnswer(new Answer<ProductDetail>() {
//			@Override
//			public ProductDetail answer(InvocationOnMock invocation) throws Throwable {
//				Thread.sleep(1000);
//				return new ProductDetail(productIdTwo, "Product" + productIdTwo, 10.0, true);
//			}
//		});
//		given(productService.findProductById(productIdThree))
//		.willReturn(new ProductDetail(productIdThree, "Product" + productIdThree, 10.0, true));
//        this.similarService = new SimilarService(this.productService);
//    }
//	
//	@Test
//	void getAllPerson()
//    {	
//		//Arrange
//		
//		int expectedResultLenght = 2;
//		
//		//Act
//		final List<ProductDetail> result = similarService.findSimilarProductByProductId(productIdRequest);
//		
//		//Asserts
//		Assertions.assertEquals(expectedResultLenght, result.size());
//		Assertions.assertEquals(result.get(0).getId(), productIdTwo);
//		Assertions.assertEquals(result.get(1).getId(), productIdThree);
//		
//    }
//
//}
