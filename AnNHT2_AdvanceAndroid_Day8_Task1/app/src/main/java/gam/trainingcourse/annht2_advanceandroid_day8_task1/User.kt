package gam.trainingcourse.annht2_advanceandroid_day8_task1

import java.io.Serializable

class User(var username: String, var password: String) : Serializable {

    override fun toString(): String {
        return "Username: $username, password: $password"
    }
}