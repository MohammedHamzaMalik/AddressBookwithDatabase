package com.addressBook.entryPoints

import com.addressBook.AppContext
import com.addressBook.CommandContext
import com.commandPattern.addressBook.commands.*
import com.commandPattern.addressBook.dataClasses.Contact
import com.commandPattern.addressBook.requests.AddContactRequest
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