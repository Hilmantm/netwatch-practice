package id.motionlab.netwatch.data.sources.network.response

data class BaseResponse<T>(

    val page: Int,

    val results: T,

)
