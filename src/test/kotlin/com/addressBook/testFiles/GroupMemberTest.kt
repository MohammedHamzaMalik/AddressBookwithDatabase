package com.addressBook.testFiles

import com.addressBook.*
import com.addressBook.entryPoints.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class GroupMemberTest: AppTest() {

    @Order(9)
    @Test
    fun `add group member`() {
        val groupReq = getAddGroupRequest()
        val groupResponse = addGroup(appCtx, groupReq)

        val contactReq = getAddContactRequest()
        val contactResponse = addContact(appCtx, contactReq)

        val req = getAddGroupMemberRequest(groupResponse.groupId, contactResponse.contactId)
        val groupMemberResponse = addGroupMember(appCtx, req)
        println(groupMemberResponse)
    }

    @Order(10)
    @Test
    fun `delete group member`() {
        val groupReq = getAddGroupRequest()
        val groupResponse = addGroup(appCtx, groupReq)
        val contactReq = getAddContactRequest()
        val contactResponse = addContact(appCtx, contactReq)
        val req = getDeleteGroupMemberRequest(groupResponse.groupId, contactResponse.contactId)
        val groupMemberResponse = deleteGroupMember(appCtx, req)
        println(groupMemberResponse)
    }

    @Order(11)
    @Test
    fun `fetch group member`() {
        val groupReq = getAddGroupRequest()
        val groupResponse = addGroup(appCtx, groupReq)

        val contactReq = getAddContactRequest()
        val contactResponse = addContact(appCtx, contactReq)

        val groupMemberReq = getAddGroupMemberRequest(groupResponse.groupId, contactResponse.contactId)
        val groupMemberResponse = addGroupMember(appCtx, groupMemberReq)
        println(groupMemberResponse)

        val req = getFetchGroupMemberRequest(groupResponse.groupId, contactResponse.contactId)
        val fetchGroupMemberResponse = fetchGroupMember(appCtx, req)
        println(fetchGroupMemberResponse)

    }

    @Order(12)
    @Test
    fun `connect contact and groups`() {
        val groupReq = getAddGroupRequest()
        val groupResponse = addGroup(appCtx, groupReq)
        val group2Req = getAddGroupRequest2()
        val group2Response = addGroup(appCtx, group2Req)
        val contactReq = getAddContactRequest()
        val contactResponse = addContact(appCtx, contactReq)
        val req = getAddGroupMemberRequest(groupResponse.groupId, contactResponse.contactId)
        val groupMemberResponse = addGroupMember(appCtx, req)
        val req2 = getAddGroupMemberRequest(group2Response.groupId, contactResponse.contactId)
        val groupMemberResponse2 = addGroupMember(appCtx, req2)
        println(groupMemberResponse)
        println(groupMemberResponse2)

        val req3 = getConnectContactwithGroupsRequest(contactResponse.contactId, listOf(groupResponse.groupId, group2Response.groupId))
        println(req3)
    }

    @Order(13)
    @Test
    fun `connect group and contacts`() {
        val groupReq = getAddGroupRequest()
        val groupResponse = addGroup(appCtx, groupReq)

        val contactReq = getAddContactRequest()
        val contactResponse = addContact(appCtx, contactReq)

        val contact2Req = getAddContactRequest()
        val contactResponse2 = addContact(appCtx, contact2Req)

        val req = getAddGroupMemberRequest(groupResponse.groupId, contactResponse.contactId)
        val groupMemberResponse = addGroupMember(appCtx, req)
        val req2 = getAddGroupMemberRequest(groupResponse.groupId, contactResponse2.contactId)
        val groupMemberResponse2 = addGroupMember(appCtx, req2)

        println(groupMemberResponse)
        println(groupMemberResponse2)

        val req3 = getConnectGroupwithContactsRequest(groupResponse.groupId, listOf(contactResponse.contactId, contactResponse2.contactId))
        println(req3)
    }
}