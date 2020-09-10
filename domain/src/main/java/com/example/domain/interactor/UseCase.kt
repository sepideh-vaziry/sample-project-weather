package com.example.domain.interactor

import kotlinx.coroutines.*

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 */

abstract class UseCase<T, Params> {

    private var parentJob: Job = Job()

    /**
     * Executing the current [UseCase] on the background thread.
     */
    internal abstract suspend fun executeOnBackground(params: Params?) : T

    /**
     * Executes the current use case.
     *
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    fun execute(
        scope: CoroutineScope,
        params: Params?,
        onComplete: ((useCaseResult: UseCaseResult<T>) -> Unit)
    ) {
        if (parentJob.isCancelled) {
            parentJob = Job()
        }

        CoroutineScope(scope.coroutineContext + parentJob).launch {

            try {
                val result = withContext(Dispatchers.IO) {
                    executeOnBackground(params)
                }

                onComplete.invoke(UseCaseResult.Success(result))
            }
            catch (t: Throwable) {
                onComplete.invoke(UseCaseResult.Error(t))
            }

        }

    }

    /**
     * Executes the current use case.
     *
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    suspend fun execute(
        params: Params?
    ) : T {
        if (parentJob.isCancelled) {
            parentJob = Job()
        }

        return executeOnBackground(params)

    }

}
