package com.vertie.javacode

class ListItem {
    var id: String? = null
    @kotlin.jvm.JvmField
    var name: String? = null
    var description: String? = null
    var isSelected = false

    constructor() {}
    constructor(id: String?, name: String?, description: String?, isSelected: Boolean) {
        this.id = id
        this.name = name
        this.description = description
        this.isSelected = isSelected
    }

    val isSelect: Unit
        get() {
            isSelected = true
        }

    override fun toString(): String {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isSelected='" + isSelected + '\'' +
                '}'
    }
}