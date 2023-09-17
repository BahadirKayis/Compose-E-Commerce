package com.bahadir.tostbangcase.domain.usecase.products

import com.bahadir.tostbangcase.core.Resource
import com.bahadir.tostbangcase.data.model.FiriyaItem
import com.bahadir.tostbangcase.data.repository.FiriyaRepository
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.bahadir.tostbangcase.domain.mapper.FiriyaListMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductUseCaseImpl @Inject constructor(
    private val repository: FiriyaRepository,
    private val mapper: FiriyaListMapper<FiriyaItem, FiriyaUI>,
) :
    GetProductUseCase {
    override fun invoke(): Flow<Resource<List<FiriyaUI>>> = flow {
        when (val response = repository.getProduct()) {
            is Resource.Error -> {
                emit(response)
            }

            is Resource.Success -> {
                emit(Resource.Success(mapper.map(response.data)))
            }
        }
    }
}
