package org.acme.repo.impl

import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.persistence.NoResultException
import jakarta.persistence.PersistenceContext
import org.acme.dto.Status
import org.acme.model.Orders
import org.acme.repo.CartRepository

@ApplicationScoped
class CartRepositoryImpl(@PersistenceContext val entityManager: EntityManager) : CartRepository {
    override fun saveCart(orders: Orders): Status {
        entityManager.persist(orders)
        return Status(200, "Cart Created")
    }
    override fun saveOrder(orders: Orders): Status {
        entityManager.persist(orders)
        return Status(200, "Order Placed")
    }

    override fun getAllCarts(): List<Orders> {
        val query = "SELECT * FROM orders WHERE deleted = 0 and status=1"
        val nativeQuery = entityManager.createNativeQuery(query, Orders::class.java)
        return nativeQuery.resultList as List<Orders>
    }
    override fun getAllOrders(): List<Orders> {
        val query = "SELECT * FROM orders WHERE deleted = 0 and status=2"
        val nativeQuery = entityManager.createNativeQuery(query, Orders::class.java)
        return nativeQuery.resultList as List<Orders>
    }
    override fun getCartByUuid(uuid: String): Orders? {
        return try {
            val query = "SELECT * FROM orders WHERE uuid = ?1 AND deleted = 0 and status=1"
            val nativeQuery = entityManager.createNativeQuery(query, Orders::class.java)
            nativeQuery.setParameter(1, uuid)
            nativeQuery.singleResult as Orders
        } catch (e: NoResultException) {
            null
        }
    }

    override fun getOrderByUuid(uuid: String): Orders? {
        return try {
            val query = "SELECT * FROM orders WHERE uuid = ?1 AND deleted = 0 and status=2"
            val nativeQuery = entityManager.createNativeQuery(query, Orders::class.java)
            nativeQuery.setParameter(1, uuid)
            nativeQuery.singleResult as Orders
        } catch (e: NoResultException) {
            null
        }
    }
    override fun getCartByUserUid(userUid: String): List<Orders> {
        val query = "SELECT * FROM orders WHERE user_uid = ?1 AND deleted = 0 and status=1"
        val nativeQuery = entityManager.createNativeQuery(query, Orders::class.java)
        nativeQuery.setParameter(1, userUid)
        return nativeQuery.resultList as List<Orders>
    }

    override fun getOrdersByUserUid(userUid: String): List<Orders> {
        val query = "SELECT * FROM orders WHERE user_uid = ?1 AND deleted = 0 and status=2"
        val nativeQuery = entityManager.createNativeQuery(query, Orders::class.java)
        nativeQuery.setParameter(1, userUid)
        return nativeQuery.resultList as List<Orders>
    }
}
