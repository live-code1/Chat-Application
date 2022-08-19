package com.sample.chatapplication.data.network

data class ResultResponse<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        NO_DATA,
        NO_DATA_WITH_MESSAGE,
        EMPTY_PAGINATED_LIST,
        NO_INTERNET,
        PAGINATED_LIST,
        LOADING_PAGINATED_LIST,
        LOADING,
        LOADING_COMPLETED_WITH_EMPTY,
        LOADING_COMPLETED,
        ADD_LOCAL_DATA
    }

    companion object {
        fun <T> success(data: T): ResultResponse<T> {
            return ResultResponse(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> addLocalData(data: T): ResultResponse<T> {
            return ResultResponse(
                Status.ADD_LOCAL_DATA,
                data,
                null
            )
        }

        fun <T> error(message: String, data: T? = null): ResultResponse<T> {
            return ResultResponse(
                Status.ERROR,
                data,
                message
            )
        }

        fun <T> loading(data: T? = null, mOffset: String): ResultResponse<T> {
            return ResultResponse(
                Status.LOADING,
                data,
                mOffset
            )
        }

        fun <T> loadingPaginatedList(data: T? = null, mOffset: String): ResultResponse<T> {
            return ResultResponse(
                Status.LOADING_PAGINATED_LIST,
                data,
                mOffset
            )
        }

        fun <T> loadingCompleted(data: T? = null): ResultResponse<T> {
            return ResultResponse(
                Status.LOADING_COMPLETED,
                data,
                null
            )
        }

        fun <T> loadingCompletedWithEmpty(data: T? = null): ResultResponse<T> {
            return ResultResponse(
                Status.LOADING_COMPLETED_WITH_EMPTY,
                data,
                null
            )
        }

        fun <T> noData(data: T? = null): ResultResponse<T> {
            return ResultResponse(
                Status.NO_DATA,
                data,
                ""
            )
        }

        fun <T> noDataWithMessage(data: T? = null, mMessage: String): ResultResponse<T> {
            return ResultResponse(
                Status.NO_DATA_WITH_MESSAGE,
                data,
                mMessage
            )
        }

        fun <T> emptyPaginatedList(data: T? = null): ResultResponse<T> {
            return ResultResponse(
                Status.EMPTY_PAGINATED_LIST,
                data,
                ""
            )
        }

        fun <T> paginatedList(data: T? = null): ResultResponse<T> {
            return ResultResponse(
                Status.PAGINATED_LIST,
                data,
                ""
            )
        }

        fun <T> noInternet(data: T? = null): ResultResponse<T> {
            return ResultResponse(
                Status.NO_INTERNET,
                data,
                ""
            )
        }


    }
}