package org.acme.repo

import com.tfl.framework.core.Repo
import org.acme.dto.Status
import org.acme.model.Orders

interface CartRepository : Repo<Orders> {
    fun saveCart(orders: Orders): Status
    fun saveOrder(orders: Orders): Status
    fun getAllOrders(): List<Orders>
    fun getAllCarts(): List<Orders>
    fun getCartByUuid(uuid: String): Orders?
    fun getOrderByUuid(uuid: String): Orders?
    fun getCartByUserUid(userUid: String): List<Orders>
    fun getOrdersByUserUid(userUid: String): List<Orders>
}
