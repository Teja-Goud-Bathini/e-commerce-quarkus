package org.acme.service

import io.quarkus.arc.Unremovable
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.acme.dto.ProductFilter
import org.acme.dto.Status
import org.acme.model.Products
import org.acme.repo.ProductRepository

@ApplicationScoped
@Transactional
@Unremovable
class ProductService(val productRepository: ProductRepository) {
    fun createProduct(products: Products):Status{
        return productRepository.save(products)
    }
    fun  getAll():List<Products>{
        return productRepository.getAll()
    }
    fun getByUuid(uuid:String):Products?{
        return productRepository.getByUuid(uuid)
    }
    fun updateUser(uuid: String,products: Products):Status{
        var dbProducts=getByUuid(uuid)
        if(dbProducts==null)return Status(404,"Product Not Found")
        dbProducts.name=products.name
        dbProducts.brand=products.brand
        dbProducts.category=products.category
        dbProducts.price=products.price
        dbProducts.quantity=products.quantity
        dbProducts.description=products.description
        return Status(200,"Product Updated Sucessfully")
    }
    fun deleteProduct(uuid: String):Status{
        var dbProducts=getByUuid(uuid)
        if(dbProducts==null)return Status(400,"Product Not FOund")
        dbProducts.deleted=true
        return Status(200,"Product Deleted Sucessfully")
    }
    fun filterProducts(filter: ProductFilter): List<Products>{
        return productRepository.filter(filter)
    }
}