package gam.trainingcourse.annht2_advanceandroid_day6_component2.model

import java.io.Serializable

class User(var username: String, var password: String) : Serializable {

    override fun toString(): String {
        return "Username: $username, password: $password"
    }
}