package org.acme.service

import io.quarkus.arc.Unremovable
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.acme.dto.Status
import org.acme.model.User
import org.acme.repo.UserRepository

@ApplicationScoped
@Transactional
@Unremovable
class UserService(private val userRepository: UserRepository) {

    fun createUser(user: User): User {
        return userRepository.save(user)
    }

    fun getAll(): List<User> {
        return userRepository.getAll()
    }

    fun getByUuid(uuid: String): User? {
        return userRepository.getByUuid(uuid)
    }
    fun updateUser(uuid: String,user: User):Status{
        val flag=getByUuid(uuid)
        println(flag)
        if(flag==null)return Status(404,"User Not Found")

        flag.name=user.name
        flag.email=user.email
        flag.age=user.age
        return Status(200,"Updated Sucessfully")
    }
    fun deleteUser(uuid: String):Status{
        var dbUser=getByUuid(uuid)
        if(dbUser==null)return  Status(404,"User Not Found")
        dbUser.deleted=true
        return Status(200,"User Deleted SuccessFully")
    }
}
