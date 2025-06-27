package org.acme.service

import io.quarkus.arc.Unremovable
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.acme.dto.Status
import org.acme.model.Orders
import org.acme.repo.CartRepository

@ApplicationScoped
@Transactional
@Unremovable
class CartService(val cartRepository: CartRepository) {

    fun createCart(orders: Orders): Status {
        orders.status=1
        return cartRepository.saveCart(orders)
    }
    fun createOrder(orders: Orders): Status {
        orders.status=2
        return cartRepository.saveOrder(orders)
    }

    fun getAllCarts(): List<Orders> {
        return cartRepository.getAllCarts()
    }
    fun getAllOrders(): List<Orders> {
        return cartRepository.getAllOrders()
    }
    fun getCartByUuid(uuid: String): Orders? {
        return cartRepository.getCartByUuid(uuid)
    }
    fun getOrderByUuid(uuid: String): Orders? {
        return cartRepository.getOrderByUuid(uuid)
    }

    fun getCartByUserUid(userUid: String):List<Orders>{
        return cartRepository.getCartByUserUid(userUid)
    }
    fun getOrdersByUserUid(userUid: String):List<Orders>{
        return cartRepository.getOrdersByUserUid(userUid)
    }

    fun updateCart(uuid: String, orders: Orders): Status {
        val dbCart = getCartByUuid(uuid)
        if (dbCart == null) return Status(404, "Cart Not Found")

        dbCart.quantity = orders.quantity
        dbCart.status = orders.status

        return Status(200, "Cart Updated Successfully")
    }
    fun updateOrder(uuid: String, orders: Orders): Status {
        val dbCart = getOrderByUuid(uuid)
        if (dbCart == null) return Status(404, "Order Not Found")

        dbCart.quantity = orders.quantity
        dbCart.status = orders.status

        return Status(200, "Order Updated Successfully")
    }

    fun deleteCart(uuid: String): Status {
        val dbCart = getCartByUuid(uuid)
        if (dbCart == null) return Status(404, "Cart Not Found")

        dbCart.deleted = true
        return Status(200, "Cart Deleted Successfully")
    }
    fun deleteOrder(uuid: String): Status {
        val dbOrder = getOrderByUuid(uuid)
        if (dbOrder == null) return Status(404, "Cart Not Found")

        dbOrder.deleted = true
        return Status(200, "Cart Deleted Successfully")
    }
}
