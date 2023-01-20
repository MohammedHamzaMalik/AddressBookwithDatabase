package com.addressBook.entryPoints

import com.addressBook.AppContext
import com.addressBook.CommandContext
import com.commandPattern.addressBook.commands.AddContactCommand
import com.commandPattern.addressBook.dataClasses.Contact
import com.commandPattern.addressBook.requests.AddContactRequest

fun addContact(
    ac: AppContext,
    req: AddContactRequest
): Contact {
    val cmdCtx = CommandContext(ac.db)
    val cmd = AddContactCommand(cmdCtx, req)
    return cmd.execute()
}