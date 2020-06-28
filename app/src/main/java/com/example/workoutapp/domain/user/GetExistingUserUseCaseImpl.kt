package com.example.workoutapp.domain.user

import com.example.workoutapp.domain.user.GetExistingUserUseCase.Input
import com.example.workoutapp.domain.user.GetExistingUserUseCase.Output
import com.example.workoutapp.domain.user.GetExistingUserUseCase.Output.*
import io.reactivex.Single

class GetExistingUserUseCaseImpl(
    private val userRepository: UserRepository
) : GetExistingUserUseCase {
    override fun execute(input: Input): Single<Output> {
        return userRepository.getUser()
            .flatMapSingle {
                if (it > 0) {
                    Single.just(Success as Output)
                }
                else
                    Single.just(NoUsers as Output)
            }
            .onErrorReturn {
                ErrorUnknown
            }
    }
}