package com.addressBook.entryPoints


import AppContext
import CommandContext
import com.addressBook.commands.AddGroupCommand
import com.addressBook.commands.DeleteGroupCommand
import com.addressBook.commands.EditGroupCommand
import com.addressBook.commands.FetchGroupCommand
import com.addressBook.dataClasses.Group
import com.addressBook.requests.AddGroupRequest
import com.addressBook.requests.EditGroupRequest
import java.util.*

fun addGroup(
    ac: AppContext,
    req: AddGroupRequest
): Group {
    val cmdCtx = CommandContext(ac.db)
    val cmd = AddGroupCommand(cmdCtx, req)
    return cmd.execute()
}

fun deleteGroup(
    ac: AppContext,
    groupId: UUID
): String {
    val cmdCtx = CommandContext(ac.db)
    val cmd = DeleteGroupCommand(cmdCtx, groupId)
    return cmd.execute()
}

fun editGroup(
    ac: AppContext,
    groupId: UUID,
    req: EditGroupRequest
): String {
    val cmdCtx = CommandContext(ac.db)
    val cmd = EditGroupCommand(cmdCtx, groupId, req)
    return cmd.execute()
}

fun fetchGroup(
    ac: AppContext,
    groupId: UUID
): Group {
    val cmdCtx = CommandContext(ac.db)
    val cmd = FetchGroupCommand(cmdCtx, groupId)
    return cmd.execute()
}

