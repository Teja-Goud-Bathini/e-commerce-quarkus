package org.acme.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String = "",

    var email: String = "",

    var age: Int = 0,

    var uuid : String? = null,

    var deleted: Boolean = false
)
