package com.bahadir.tostbangcase.presentation.navigation

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.google.gson.Gson

@Suppress("DEPRECATION")
class AssetParamType : NavType<FiriyaUI>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): FiriyaUI? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, FiriyaUI::class.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): FiriyaUI {
        return Gson().fromJson(value, FiriyaUI::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: FiriyaUI) {
        bundle.putParcelable(key, value)
    }
}
