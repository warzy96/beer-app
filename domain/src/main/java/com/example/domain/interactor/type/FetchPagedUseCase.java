package com.example.domain.interactor.type;

public interface FetchPagedUseCase<Result> {

    Result execute(int page);
}
