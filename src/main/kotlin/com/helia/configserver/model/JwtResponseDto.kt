package com.helia.configserver.model

import com.fasterxml.jackson.annotation.JsonProperty

data class JwtResponseDto(@get:JsonProperty("id_token") val token: String)