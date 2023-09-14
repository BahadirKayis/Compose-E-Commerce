package com.bahadir.tostbangcase.domain.mapper

interface FiriyaBaseMapper<I, O> {
    fun map(input: I): O
}
