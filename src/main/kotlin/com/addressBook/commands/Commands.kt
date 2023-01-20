package com.commandPattern.addressBook.commands

import com.addressBook.CommandContext
import com.commandPattern.addressBook.dataClasses.Contact
import com.commandPattern.addressBook.dataClasses.Group
import com.commandPattern.addressBook.requests.*
import com.commandPattern.addressBook.storages.Storage
import java.util.UUID

class AddressBook(
    val history: MutableList<Command> = mutableListOf()
){

    fun executeCommand(command: Command): Any {
        history.add(command)
        return command.execute()
    }
}
interface Command{
    fun execute(): Any
}

class AddContactCommand(
    val cmdCtx: CommandContext,
    private val requests: AddContactRequest
): Command {
    override fun execute(): Contact = Storage.addContact(requests.toContact())
}

class DeleteContactCommand(
    private val contactId: UUID
//    private val query: String
): Command {
    override fun execute(): String {
        return Storage.deleteContact(contactId)
    }
}

class EditContactCommand(
    private val contactId: UUID,
    private val requests: EditContactRequest
): Command {
    override fun execute(): String {
        return Storage.editContact(contactId, requests.toContact())
    }
}

class SearchContactCommand(
    private val query: String
): Command {
    override fun execute(): List<Contact> {
        return Storage.searchContacts(query)
    }

}

class ShowContactCommand: Command {
    override fun execute(): Collection<Contact> {
        return Storage.showContacts()
    }
}

class AddGroupCommand(
    private val request: AddGroupRequest
): Command {
    override fun execute(): Group = Storage.addGroup(request.toGroup())
}

class DeleteGroupContact(
    private val groupId: UUID
): Command {
    override fun execute(): String {
        return Storage.deleteGroup(groupId)
    }

}

class ShowGroupsCommand: Command {
    override fun execute(): Collection<Group> {
        return Storage.showGroups()
    }

}
class EditGroupCommand(
    private val groupId:UUID,
    private val request: EditGroupRequest
): Command {
    override fun execute(): String {
        return Storage.editGroup(groupId, request.toGroup())
    }
}
class SearchGroupCommand(
    private val query: String
): Command {
    override fun execute(): List<Group> = Storage.searchGroups(query)
}