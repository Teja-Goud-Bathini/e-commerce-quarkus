package org.acme.rest

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import org.acme.dto.Status
import org.acme.model.Products
import org.acme.model.User
import org.acme.service.ProductService
import org.acme.util.UIDUtil
import java.awt.PageAttributes.MediaType

@Path("/products")
@ApplicationScoped
class ProductResource(val productService: ProductService) {
    @POST
    @Path("/create")
    fun createProduct(products: Products):Status{
        println("Hello products")
        products.uuid=UIDUtil.generate()
        productService.createProduct(products)
        return Status(200,"Product Created")
    }
    @GET
    @Path("/getAll")
    fun getAll():List<Products>{
        return productService.getAll()
    }
    @GET
    @Path("/{uuid}")
    fun getByUuid(@PathParam("uuid")uuid:String):Products{
        return productService.getByUuid(uuid) ?: Products()
    }
    @PUT
    @Path("/update/{uuid}")
    fun updateProduct(@PathParam("uuid")uuid: String,products: Products):Status{
        return productService.updateUser(uuid,products)
    }
    @DELETE
    @Path("delete/{uuid}")
    fun deleteProduct(@PathParam("uuid")uuid: String):Status{
        return productService.deleteProduct(uuid)
    }
}