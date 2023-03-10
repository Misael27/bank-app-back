package com.bankappback.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;


public class Mapper<E, C, U, R> {
	
	ModelMapper mapper;
	Class<E> entityClass;
	Class<C> createClass;
	Class<U> updateClass;
	Class<R> responseClass;

	public Mapper(Class<E> entityClass, Class<C> createClass, Class<U> updateClass, Class<R> responseClass) {
		this.entityClass = entityClass;
		this.createClass = createClass;
		this.updateClass = updateClass;
		this.responseClass = responseClass;
		this.mapper = new ModelMapper();
		this.mapper.getConfiguration().setSkipNullEnabled(true);
	}
	
	public R mapEntityToResponse(E account) {
		var result = mapper.map(account, responseClass);
		return result;
	}

	public E mapDtoToEntity(C request) {
		final E account = mapper.map(request, entityClass);
		return account;
	}

	public List<R> mapAllEntityToResponse(List<E> accounts) {
		return accounts.stream()
		.map(entity -> mapper.map(entity, responseClass))
        .collect(Collectors.toList());
	}

	public E mapDtoUpdateToEntity(U request) {
		final E account = mapper.map(request, entityClass);
		return account;
	}
}
