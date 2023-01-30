package com.addressBook.commands


import CommandContext
import com.addressBook.dataClasses.Group
import com.addressBook.requests.AddGroupRequest
import com.addressBook.requests.EditGroupRequest
import com.commandPattern.addressBook.storages.Storage
import java.util.*

fun AddGroupRequest.toGroup() = Group(
    groupId = UUID.randomUUID(),
    groupName = this@toGroup.groupName
)

fun EditGroupRequest.toGroup() = Group(
    groupId = this@toGroup.groupId,
    groupName = this@toGroup.groupName
)

class AddGroupCommand(
    val cmdCtx: CommandContext,
    private val request: AddGroupRequest
): Command {
    override fun execute(): Group = Storage.addGroupInTable(request.toGroup())
}

class DeleteGroupCommand(
    val cmdCtx: CommandContext,
    private val groupId: UUID
): Command {
    override fun execute(): String {
        return Storage.deleteGroupInTable(groupId)
    }

}

class EditGroupCommand(
    val cmdCtx: CommandContext,
    private val groupId: UUID,
    private val request: EditGroupRequest
): Command {
    override fun execute(): String {
        return Storage.editGroupInTable(groupId, request.toGroup())
    }
}

class FetchGroupCommand(
    val cmdCtx: CommandContext,
    private val groupId: UUID
): Command {
    override fun execute(): Group {
        return Storage.fetchGroupInTable(groupId)
    }
}
/*
class ShowGroupsCommand: Command {
    override fun execute(): Collection<Group> {
        return Storage.showGroups()
    }

}
*/

/*
class SearchGroupCommand(
    private val query: String
): Command {
    override fun execute(): List<Group> = Storage.searchGroups(query)
}
*/