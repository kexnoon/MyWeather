package ru.alexeypopov.myweather.common

data class UIModel <out T> (val status: UIModelStatus, val data: T?, val message: String? = null) {
    companion object {
        fun <T> success(data: T?): UIModel<T> {
            return UIModel(
                status = UIModelStatus.SUCCESS,
                data = data,
                message = null
            )
        }

        fun <T> error(message: String?): UIModel<T> {
            return UIModel(
                status = UIModelStatus.ERROR,
                data = null,
                message = message
            )
        }

        fun <T> loading(): UIModel<T> {
            return UIModel(
                status = UIModelStatus.LOADING,
                data = null,
                message = null
            )
        }
    }
}

enum class UIModelStatus { SUCCESS, ERROR, LOADING }

