package org.acme.repo

import com.tfl.framework.core.Repo
import org.acme.model.User

interface UserRepository : Repo<User> {
    fun save(user: User): User
    fun getAll(): List<User>
    fun getByUuid(uuid: String): User?
//    fun updateByUuid(uuid:String,user: User):Int

}
