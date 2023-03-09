package com.bankappback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bankappback.model.ProductDetail;
import com.bankappback.service.ISimilarService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * SimilarController
 * 
 * @author mjpol
 *
 */
@RestController
public class SimilarController {
	
	@Autowired
	private ISimilarService similarService;

	/**
	 * GetProductSimilarsByProductId
	 * 
	 * @param productId
	 * @return
	 */
	@Operation(summary = "Similar products", tags = { "default" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = 
					@Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							array = @ArraySchema(minItems = 0, uniqueItems = true, schema = @Schema(implementation = ProductDetail.class)))),
			@ApiResponse(responseCode = "404", description = "Product Not found", content = @Content(schema = @Schema(hidden = true)))
	})
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/product/{productId}/similar", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ProductDetail>> GetProductSimilarsByProductId(@PathVariable("productId") String productId) {
		return ResponseEntity.status(HttpStatus.OK).body(similarService.findSimilarProductByProductId(productId));
	}
	
}
