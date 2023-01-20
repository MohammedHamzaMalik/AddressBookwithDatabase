package com.commandPattern.addressBook.requests

import com.commandPattern.addressBook.dataClasses.Contact
import com.commandPattern.addressBook.dataClasses.Group
import java.util.*

data class AddContactRequest(
    val firstName: String,
    val lastName: String,
    val emails: MutableMap<String, String>,
    val phoneNumbers: MutableMap<String, String>,
    val addresses: MutableMap<String, String>,
    val groups: MutableList<String>
)

fun AddContactRequest.toContact() = Contact(
    contactId = UUID.randomUUID(),
    firstName = this@toContact.firstName,
    lastName = this@toContact.lastName,
    emails = this@toContact.emails,
    phoneNumbers = this@toContact.phoneNumbers,
    addresses = this@toContact.addresses,
    groups = this@toContact.groups
)

data class EditContactRequest(
    val contactId: UUID,
    val firstName: String,
    val lastName: String,
    val emails: MutableMap<String, String>,
    val phoneNumbers: MutableMap<String, String>,
    val addresses: MutableMap<String, String>,
    val groups: MutableList<String>
)

fun EditContactRequest.toContact() = Contact(
    contactId = this@toContact.contactId,
    firstName = this@toContact.firstName,
    lastName = this@toContact.lastName,
    emails = this@toContact.emails,
    phoneNumbers = this@toContact.phoneNumbers,
    addresses = this@toContact.addresses,
    groups = this@toContact.groups
)
//data class SearchContactRequest(
//    val query: String
//)
//fun SearchContactRequest.toQuery() = Contact(
//    query = this@toQuery.query
//)
data class AddGroupRequest(
    val groupName: String,
    val groupMembers: MutableList<Contact>
)

fun AddGroupRequest.toGroup() = Group(
    groupId = UUID.randomUUID(),
    groupName = this@toGroup.groupName,
    groupMembers = this@toGroup.groupMembers
)
data class EditGroupRequest(
    val groupId: UUID,
    val groupName: String,
    val groupMembers: MutableList<Contact>
)

fun EditGroupRequest.toGroup() = Group(
    groupId = this@toGroup.groupId,
    groupName = this@toGroup.groupName,
    groupMembers = this@toGroup.groupMembers
)