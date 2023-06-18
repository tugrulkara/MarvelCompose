package com.tugrulkara.marvelcompose.util

object Constants {

    const val BASE_URL="https://gateway.marvel.com"
    const val PUBLIC_KEY="556b97ed08f77ee56457738533d2cae4"
    const val PRIVATE_KEY="081cad8b574ae4a7d08999eb6976aad2e74f2144"
    val TIME_STAMP = System.currentTimeMillis()
    val HASH = "$TIME_STAMP${PRIVATE_KEY}${PUBLIC_KEY}".md5()
    const val CHAR_ID="characterId"
}