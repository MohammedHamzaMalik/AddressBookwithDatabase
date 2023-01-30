package com.addressBook.entryPoints

import AppContext
import CommandContext
import com.addressBook.commands.AddGroupMemberCommand
import com.addressBook.commands.DeleteGroupMemberCommand
import com.addressBook.requests.AddGroupMemberRequest
import com.addressBook.requests.DeleteGroupMemberRequest
import java.util.*

fun addGroupMember(
    ac: AppContext,
    req: AddGroupMemberRequest
): String {
    val cmdCtx = CommandContext(ac.db)
    val cmd = AddGroupMemberCommand(cmdCtx, req)
    return cmd.execute()
}


fun deleteGroupMember(
    ac: AppContext,
    req: DeleteGroupMemberRequest
): String {
    val cmdCtx = CommandContext(ac.db)
    val cmd = DeleteGroupMemberCommand(cmdCtx, req)
    return cmd.execute()
}