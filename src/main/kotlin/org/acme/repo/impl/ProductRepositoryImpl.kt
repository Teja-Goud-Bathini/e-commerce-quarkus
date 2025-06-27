package org.acme.repo.impl

import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.persistence.NoResultException
import jakarta.persistence.PersistenceContext
import org.acme.dto.Status
import org.acme.model.Products
import org.acme.model.User
import org.acme.repo.ProductRepository
@ApplicationScoped
class ProductRepositoryImpl(@PersistenceContext val entityManager: EntityManager)
    :ProductRepository{
    override fun save(products: Products): Status {
        entityManager.persist(products)
        return Status(200,"Product Created ")
    }

    override fun getAll(): List<Products> {
        val query="select * from products where deleted=0"
        val nativeQuery=entityManager.createNativeQuery(query,Products::class.java)
        return nativeQuery.resultList as List<Products>
    }

    override fun getByUuid(uuid:String): Products? {
        return try{
            val query="select * from products where uuid=?1 and deleted=0"
            val nativeQuery=entityManager.createNativeQuery(query,Products::class.java)
            nativeQuery.setParameter(1,uuid)
            return nativeQuery.singleResult as Products
        }catch (e : NoResultException){
            null
        }

    }
}