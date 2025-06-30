package org.acme.repo.impl

import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.persistence.NoResultException
import jakarta.persistence.PersistenceContext
import org.acme.dto.ProductFilter
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
    override fun filter(filter: ProductFilter): List<Products> {
        val baseQuery = StringBuilder("SELECT * FROM products WHERE deleted = 0")

        if (!filter.name.isNullOrBlank()) {
            baseQuery.append(" AND LOWER(name) LIKE LOWER('%${filter.name}%')")
        }
        if (!filter.brand.isNullOrBlank()) {
            baseQuery.append(" AND LOWER(brand) LIKE LOWER('%${filter.brand}%')")
        }
        if (!filter.category.isNullOrBlank()) {
            baseQuery.append(" AND LOWER(category) LIKE LOWER('%${filter.category}%')")
        }
        if (!filter.code.isNullOrBlank()) {
            baseQuery.append(" AND LOWER(code) LIKE LOWER('%${filter.code}%')")
        }
        if (filter.priceMin != null) {
            baseQuery.append(" AND price >= ${filter.priceMin}")
        }
        if (filter.priceMax != null) {
            baseQuery.append(" AND price <= ${filter.priceMax}")
        }

        return entityManager.createNativeQuery(baseQuery.toString(), Products::class.java)
            .resultList as List<Products>
    }

}