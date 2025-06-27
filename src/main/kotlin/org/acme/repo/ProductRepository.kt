package org.acme.repo

import com.tfl.framework.core.Repo
import org.acme.dto.Status
import org.acme.model.Products

interface ProductRepository :Repo<Products>{
    fun save(products: Products):Status
    fun getAll():List<Products>
    fun getByUuid(uuid:String):Products?
}