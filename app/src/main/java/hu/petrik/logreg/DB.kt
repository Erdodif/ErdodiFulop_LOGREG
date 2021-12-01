package hu.petrik.logreg

enum class DB(private val nev: String) {
    NAME("users.db"),
    TABLE("felhasznalo"),
    COL_ID("id"),
    COL_NAME("felhnev"),
    COL_EMAIL("email"),
    COL_PASSWORD("jelszo"),
    COL_FULL_NAME("teljesnev");

    companion object {
        const val VERSION: Int =1
    }

    override fun toString(): String {
        return this.nev
    }
}