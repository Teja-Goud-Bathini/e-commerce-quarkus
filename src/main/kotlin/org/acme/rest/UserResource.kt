package org.acme.rest

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.acme.dto.Status
import org.acme.model.User
import org.acme.service.UserService
import org.acme.util.UIDUtil

@Path("/users")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UserResource(private val userService: UserService) {

    @POST
    @Path("/create")
    fun createUser(user: User): Status {
        println("create user called")
        user.uuid = UIDUtil.generate()
        userService.createUser(user)
        return Status(200, "created successfully")
    }

    @GET
    @Path("/getAll")
    fun getAll(): List<User> {
        return userService.getAll()
    }

    @GET
    @Path("/{uuid}")
    fun getByUuid(@PathParam("uuid") uuid: String): User {
        return userService.getByUuid(uuid)?: User()
    }
    @PUT
    @Path("/update/{uuid}")
    fun updateUser(@PathParam("uuid")uuid: String,user: User):Status{
     return  userService.updateUser(uuid,user)
    }
    @DELETE
    @Path("/delete/{uuid}")
    fun deleteUser(@PathParam("uuid") uuid:String):Status{
        return userService.deleteUser(uuid)
    }
}

