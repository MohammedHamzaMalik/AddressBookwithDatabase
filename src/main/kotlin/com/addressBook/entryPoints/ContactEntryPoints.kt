package com.addressBook.entryPoints


import AppContext
import CommandContext
import com.commandPattern.addressBook.commands.*
import com.commandPattern.addressBook.dataClasses.Contact
import com.commandPattern.addressBook.requests.*
import java.util.UUID

fun addContact(
    ac: AppContext,
    req: AddContactRequest
): Contact {
    val cmdCtx = CommandContext(ac.db)
    val cmd = AddContactCommand(cmdCtx, req)
    return cmd.execute()
}

fun deleteContact(
    ac: AppContext,
    contactId: UUID
): String {
    val cmdCtx = CommandContext(ac.db)
    val cmd = DeleteContactCommand(cmdCtx, contactId)
    return cmd.execute()
}

fun editContact(
    ac: AppContext,
    contactId: UUID,
    req: EditContactRequest
): String {
    val cmdCtx = CommandContext(ac.db)
    val cmd = EditContactCommand(cmdCtx, contactId, req)
    return cmd.execute()
}

fun fetchContact(
    ac: AppContext,
    contactId: UUID
): Contact {
    val cmdCtx = CommandContext(ac.db)
    val cmd = FetchContactCommand(cmdCtx, contactId)
    return cmd.execute()
}