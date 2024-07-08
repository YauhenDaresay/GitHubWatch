package co.daresay.githubwatch.data.settings.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AccessTokenModel(
    val token: String,

    @SerialName("expires_at")
    val expiresAt: String = "",
)
