package l20

fun main(arg: Array<String>) {
    var t: Int? = null
    t?.compareTo(3)
    var e: Byte = t?.toByte() ?: 25
}