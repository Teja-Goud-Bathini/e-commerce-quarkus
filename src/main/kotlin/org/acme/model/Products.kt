package org.acme.model

import io.vertx.core.cli.annotations.Description
import jakarta.persistence.*

@Entity
@Table(name = "Products")
class Products(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String ?= null,
    var uuid : String? = null,
    var description:String?=null,
    var code:String?=null,
    var price:Double?=0.0,
    var quantity:Int?=0,
    var brand:String?=null,
    var category: String?=null,
    var deleted: Boolean = false
)
