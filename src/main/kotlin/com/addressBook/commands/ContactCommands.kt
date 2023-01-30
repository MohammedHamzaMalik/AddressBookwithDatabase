package com.commandPattern.addressBook.commands


import CommandContext
import com.addressBook.commands.Command
import com.commandPattern.addressBook.dataClasses.Contact
import com.commandPattern.addressBook.requests.*
import com.commandPattern.addressBook.storages.Storage
import java.util.UUID

fun AddContactRequest.toContact() = Contact(
    contactId = UUID.randomUUID(),
    firstName = this@toContact.firstName,
    lastName = this@toContact.lastName,
    emails = this@toContact.emails,
    phoneNumbers = this@toContact.phoneNumbers,
    addresses = this@toContact.addresses
)

fun EditContactRequest.toContact() = Contact(
    contactId = this@toContact.contactId,
    firstName = this@toContact.firstName,
    lastName = this@toContact.lastName,
    emails = this@toContact.emails,
    phoneNumbers = this@toContact.phoneNumbers,
    addresses = this@toContact.addresses
)

class AddContactCommand(
    val cmdCtx: CommandContext,
    private val requests: AddContactRequest
): Command {
    override fun execute(): Contact = Storage.addContactInTable(requests.toContact())
}

class DeleteContactCommand(
    val cmdCtx: CommandContext,
    private val contactId: UUID
): Command {
    override fun execute(): String {
        return Storage.deleteContactInTable(contactId)
    }
}

class EditContactCommand(
    val cmdCtx: CommandContext,
    private val contactId: UUID,
    private val requests: EditContactRequest
): Command {
    override fun execute(): String {
        return Storage.editContactInTable(contactId, requests.toContact())
    }
}

class FetchContactCommand(
    val cmdCtx: CommandContext,
    private val contactId: UUID
): Command {
    override fun execute(): Contact {
        return Storage.fetchContactInTable(contactId)
    }
}
/*class SearchContactCommand(
    private val query: String
): Command {
    override fun execute(): List<Contact> {
        return Storage.searchContacts(query)
    }

}*/

/*class ShowContactCommand: Command {
    override fun execute(): Collection<Contact> {
        return Storage.showContacts()
    }
}*/