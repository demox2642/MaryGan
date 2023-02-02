package com.example.second.models

data class Raitings(
    val `0`: UserRaitings,
    val `1`: UserRaitings,
    val `2`: UserRaitings,
    val `3`: UserRaitings,
    val `4`: UserRaitings,
    val `5`: UserRaitings,
    val `6`: UserRaitings,
    val `7`: UserRaitings,
    val `8`: UserRaitings
)

fun Raitings.toList(): List<UserRaitings> {
    return listOf<UserRaitings>(this.component1(), this.component2(), this.component3(), this.component4(), this.component5(), this.component6(), this.component7(), this.component8(), this.component9())
}
