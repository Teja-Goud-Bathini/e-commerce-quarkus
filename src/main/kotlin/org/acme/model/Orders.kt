package org.acme.model

import jakarta.persistence.*

@Entity
@Table(name = "orders") // table names are usually lowercase
data class Orders(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var uuid: String? = null,
    @Column(name = "user_uid")
    var userUid: String? = null,

    @Column(name = "products_uid")
    var productsUid: String? = null,

    var quantity: Int? = 0,

    var status: Int? = null,

    var deleted: Boolean = false
)
