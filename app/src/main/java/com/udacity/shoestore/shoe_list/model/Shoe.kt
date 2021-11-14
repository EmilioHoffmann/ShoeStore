package com.udacity.shoestore.shoe_list.model

data class Shoe(
    var name: String = "",
    var company: String = "",
    var size: String = "",
    var description: String = ""
) {
    fun isEmpty(): Boolean {
        return name.isEmpty() && company.isEmpty() && size.isEmpty() && description.isEmpty()
    }
}
