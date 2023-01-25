package com.addressBook.requests

import java.util.*

data class AddGroupMemberRequest(
    val groupId: UUID,
    val contactId: UUID
)

data class DeleteGroupMemberRequest(
    val groupId: UUID,
    val contactId: UUID
)