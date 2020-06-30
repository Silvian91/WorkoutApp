package com.example.workoutapp.domain.user

import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase.Input
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase.Output
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase.Output.*
import io.reactivex.Single

class IsUserDBEmptyUseCaseImpl(
    private val userRepository: UserRepository
) : IsUserDBEmptyUseCase {
    override fun execute(input: Input): Single<Output> {
        return userRepository.getUser()
            .flatMapSingle {
                if (it > 0) {
                    Single.just(DBEmpty as Output)
                }
                else
                    Single.just(DBNotEmpty as Output)
            }
            .onErrorReturn {
                ErrorUnknown
            }
    }
}