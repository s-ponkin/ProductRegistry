package com.example.product.registry.convert;

public interface Converter<S, T> {

	T convert(S source);
}