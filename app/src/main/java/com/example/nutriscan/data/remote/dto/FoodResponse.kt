package com.example.nutriscan.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    val products: List<ProductDto> = emptyList()
)

data class ProductDto(
    @SerializedName("product_name")
    val productName: String? = null,

    val nutriments: NutrimentsDto? = null
)

data class NutrimentsDto(
    @SerializedName("calo_100g")
    val calories: Double? = null,

    @SerializedName("proteins_100g")
    val protein: Double? = null,

    @SerializedName("carbohydrates_100g")
    val carbs: Double? = null
)