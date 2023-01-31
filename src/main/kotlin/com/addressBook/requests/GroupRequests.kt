package com.addressBook.requests

import java.util.*

data class AddGroupRequest(
    val groupName: String
)

data class EditGroupRequest(
    val groupId: UUID,
    val groupName: String
)