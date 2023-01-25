package com.addressBook.requests

import java.util.*

//data class SearchContactRequest(
//    val query: String
//)
//fun SearchContactRequest.toQuery() = Contact(
//    query = this@toQuery.query
//)
data class AddGroupRequest(
    val groupName: String
)

data class EditGroupRequest(
    val groupId: UUID,
    val groupName: String
)