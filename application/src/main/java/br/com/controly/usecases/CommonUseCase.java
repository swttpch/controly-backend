package br.com.controly.usecases;

public interface CommonUseCase<T, V> {
    V execute(T body);
}
