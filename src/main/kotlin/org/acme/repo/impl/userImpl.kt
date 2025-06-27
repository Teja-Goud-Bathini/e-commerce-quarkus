package org.acme.repo.impl

import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.persistence.NoResultException
import jakarta.persistence.PersistenceContext
import org.acme.model.User
import org.acme.repo.UserRepository

@ApplicationScoped
class userImpl(
    @PersistenceContext val entityManager: EntityManager
) : UserRepository {

    override fun save(user: User): User {
        entityManager.persist(user)
        return user
    }

    override fun getAll(): List<User> {
        val query = "SELECT * FROM users WHERE deleted = 0"
        val nativeQuery = entityManager.createNativeQuery(query, User::class.java)
        return nativeQuery.resultList as List<User>
    }

    override fun getByUuid(uuid: String): User? {
        return try {
            val query = "SELECT * FROM users WHERE uuid = ?1 AND deleted = 0"
            val nativeQuery = entityManager.createNativeQuery(query, User::class.java)
            nativeQuery.setParameter(1, uuid)
            nativeQuery.singleResult as User
        } catch (e: NoResultException) {
            null
        }
    }

//    override fun updateByUuid(uuid   d: String, user: User): Int {
//        val query="""
//            update users set name=?1, email=?2,age=?3
//            where uuid=?4
//        """.trimIndent()
//        val nativeQuery=entityManager.createNativeQuery(query)
//        nativeQuery.setParameter(1,user.name)
//        nativeQuery.setParameter(2,user.email)
//        nativeQuery.setParameter(3,user.age)
//        nativeQuery.setParameter(4,uuid)
//        return nativeQuery.executeUpdate()
//    }
}
