package com.addressBook.commands

import com.addressBook.CommandContext
import com.addressBook.requests.AddGroupMemberRequest
import com.addressBook.requests.DeleteGroupMemberRequest
import com.commandPattern.addressBook.storages.Storage
import java.util.*

//fun AddGroupMemberRequest.toGroupMember() = GroupMember(
//    groupMemberId = UUID.randomUUID(),
//    groupId = this@toGroupMember.groupId,
//    contactId = this@toGroupMember.contactId
//)
class AddGroupMemberCommand(
    val cmd: CommandContext,
    private val req: AddGroupMemberRequest
): Command {
    override fun execute(): String {
        return Storage.addGroupMemberInTable(req.groupId, req.contactId)
    }
}


class DeleteGroupMemberCommand(
    val cmd: CommandContext,
    private val req: DeleteGroupMemberRequest
): Command {
    override fun execute(): String {
        return Storage.deleteGroupMemberInTable(req.groupId, req.contactId)
    }
}