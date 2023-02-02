package ext

fun Float.toPercentString(): String {
    return (this * 100).toInt().toString() + "%"
}
