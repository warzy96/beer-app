package com.example.domain.interactor.type;

import io.reactivex.Single;

public interface SingleQueryUseCase<Param, Result> {

    Single<Result> execute(Param param, Param param1);
}
