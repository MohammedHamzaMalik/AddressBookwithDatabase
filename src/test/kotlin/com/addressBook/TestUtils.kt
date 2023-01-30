package com.addressBook

import com.addressBook.requests.AddGroupMemberRequest
import com.addressBook.requests.AddGroupRequest
import com.addressBook.requests.EditGroupRequest
import com.commandPattern.addressBook.commands.DeleteContactCommand
import com.commandPattern.addressBook.requests.AddContactRequest
import com.commandPattern.addressBook.requests.EditContactRequest
import java.util.*

fun getAddContactRequest(): AddContactRequest {
    val contact = AddContactRequest(
        "Hamza","Malik",
        mutableMapOf("work" to "work@gmail.com","home" to "home@gmail.com"),
        mutableMapOf("work" to "+91 123","home" to "+91 234"),
        mutableMapOf("HOME" to "ST","WORK" to "BRC")
    )
    return contact
}

fun getEditContactRequest(): EditContactRequest {
    return EditContactRequest(
        UUID.randomUUID(),
        "Mohammed Hamza","Malik",
        mutableMapOf("work" to "work@gmail.com","home" to "home@gmail.com"),
        mutableMapOf("work" to "+91 123","home" to "+91 234"),
        mutableMapOf("HOME" to "ST","WORK" to "BRC")
    )
}

fun getAddGroupRequest(): AddGroupRequest {
    return AddGroupRequest("Vayana")
}

fun getEditGroupRequest(): EditGroupRequest {
    return EditGroupRequest(UUID.randomUUID(),"Vayana Interns")
}

fun getAddGroupMemberRequest(): AddGroupMemberRequest {
    return AddGroupMemberRequest(UUID.randomUUID(),UUID.randomUUID())
}