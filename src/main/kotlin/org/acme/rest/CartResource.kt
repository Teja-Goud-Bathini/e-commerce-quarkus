package org.acme.rest

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.*
import org.acme.dto.Status
import org.acme.model.Orders
import org.acme.service.CartService
import org.acme.util.UIDUtil

@Path("/order")
@ApplicationScoped
class CartResource(val cartService: CartService) {

    @POST
    @Path("/create")
    fun createCart(orders: Orders): Status {
        orders.uuid = UIDUtil.generate()
        return cartService.createCart(orders)
    }
    @POST
    @Path("/submit")
    fun createOrder(orders: Orders): Status {
        orders.uuid = UIDUtil.generate()
        return cartService.createOrder(orders)
    }

    @GET
    @Path("/pending/getAll")
    fun getAllCarts(): List<Orders> {
        return cartService.getAllCarts()
    }
    @GET
    @Path("/submit/getAll")
    fun getAll(): List<Orders> {
        return cartService.getAllOrders()
    }

    @GET
    @Path("/pending/{uuid}")
    fun getCartByUuid(@PathParam("uuid") uuid: String): Orders {
        return cartService.getCartByUuid(uuid) ?: Orders()
    }
    @GET
    @Path("/submit/{uuid}")
    fun getOrderByUuid(@PathParam("uuid") uuid: String): Orders {
        return cartService.getOrderByUuid(uuid) ?: Orders()
    }
    @GET
    @Path("/pending/user/{userUid}")
    fun getCartByUserUid(@PathParam("userUid") userUid: String): List<Orders> {
        return cartService.getCartByUserUid(userUid)
    }


    @PUT
    @Path("/pending/update/{uuid}")
    fun updateCart(@PathParam("uuid") uuid: String, orders: Orders): Status {
        return cartService.updateCart(uuid, orders)
    }
    @PUT
    @Path("/submit/update/{uuid}")
    fun updateOrder(@PathParam("uuid") uuid: String, orders: Orders): Status {
        return cartService.updateOrder(uuid, orders)
    }

    @DELETE
    @Path("/pending/delete/{uuid}")
    fun deleteCart(@PathParam("uuid") uuid: String): Status {
        return cartService.deleteCart(uuid)
    }
    @DELETE
    @Path("/submit/delete/{uuid}")
    fun deleteOrder(@PathParam("uuid") uuid: String): Status {
        return cartService.deleteOrder(uuid)
    }

    @GET
    @Path("/submit/user/{userUid}")
    fun orders(@PathParam("userUid")userUid: String): List<Orders> {
        return cartService.getOrdersByUserUid(userUid)
    }
}
