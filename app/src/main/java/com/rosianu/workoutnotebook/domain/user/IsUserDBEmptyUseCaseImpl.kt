package com.rosianu.workoutnotebook.domain.user

import com.rosianu.workoutnotebook.domain.user.IsUserDBEmptyUseCase.Input
import com.rosianu.workoutnotebook.domain.user.IsUserDBEmptyUseCase.Output
import com.rosianu.workoutnotebook.domain.user.IsUserDBEmptyUseCase.Output.*
import io.reactivex.Single

class IsUserDBEmptyUseCaseImpl(
    private val userRepository: UserRepository
) : IsUserDBEmptyUseCase {
    override fun execute(input: Input): Single<Output> {
        return userRepository.getUserCount()
            .flatMapSingle {
                if (it > 0) {
                    Single.just(DBNotEmpty as Output)
                }
                else
                    Single.just(DBEmpty as Output)
            }
            .onErrorReturn {
                ErrorUnknown
            }
    }
}