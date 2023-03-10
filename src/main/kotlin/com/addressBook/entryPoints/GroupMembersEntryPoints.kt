package com.addressBook.entryPoints

import AppContext
import CommandContext
import arrow.core.Either
import com.addressBook.commands.*
import com.addressBook.handlers.Handler.addGroupMemberHandler
import com.addressBook.handlers.Handler.connectContactWithGroupsHandler
import com.addressBook.handlers.Handler.connectGroupWithContactsHandler
import com.addressBook.handlers.Handler.deleteGroupMemberHandler
import com.addressBook.handlers.Handler.displayGroupMembersByGroupIdHandler
import com.addressBook.handlers.Handler.displayGroupMembersHandler
import com.addressBook.handlers.Handler.fetchGroupMemberHandler
import com.addressBook.requests.*
import com.commandPattern.addressBook.dataClasses.Contact

fun addGroupMember(
    ac: AppContext,
    req: AddGroupMemberRequest
): Either<Exception, String> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = AddGroupMemberCommand(cmdCtx, req)
    return addGroupMemberHandler(cmd)
}


fun deleteGroupMember(
    ac: AppContext,
    req: DeleteGroupMemberRequest
): Either<Exception, String> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = DeleteGroupMemberCommand(cmdCtx, req)
    return deleteGroupMemberHandler(cmd)
}

fun fetchGroupMember(
    ac: AppContext,
    req: FetchGroupMemberRequest
): Either<Exception, Contact> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = FetchGroupMemberCommand(cmdCtx, req)
    return fetchGroupMemberHandler(cmd)
}
fun connectContactwithGroups(
    ac: AppContext,
    req: ConnectContactwithGroupsRequest
): Either<Exception, String> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = ConnectContactwithGroupsCommand(cmdCtx, req)
    return connectContactWithGroupsHandler(cmd)
}

fun connectGroupwithContacts(
    ac: AppContext,
    req: ConnectGroupwtihContactsRequest
): Either<Exception, String> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = ConnectGroupwtihContactsCommand(cmdCtx, req)
    return connectGroupWithContactsHandler(cmd)
}

fun displayGroupMembers(
    ac: AppContext
): Either<Exception, List<Contact>> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = DisplayGroupMembersCommand(cmdCtx)
    return displayGroupMembersHandler(cmd)
}

fun displayGroupMembersByGroupId(
    ac: AppContext,
    req: DisplayGroupMembersByGroupIdRequest
): Either<Exception, List<Contact>> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = DisplayGroupMembersByGroupIdCommand(cmdCtx, req)
    return displayGroupMembersByGroupIdHandler(cmd)
}