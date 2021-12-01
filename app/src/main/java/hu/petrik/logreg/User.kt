package hu.petrik.logreg

class User(id: Int?, name: String, email: String, password: String, fullname: String) {
    var id:Int? = id
        private set
    var name:String = name
        private set
    var email:String = email
        private set
    var password = password
        private set
    var fullname:String = fullname
        private set

    fun getPasswordHashed():String{
        TODO()
    }

    override fun toString(): String {
        return """
            id = $id
            name = $name
            email = $email
            password = $password
            full name = $fullname
        """.trimIndent()
    }
}