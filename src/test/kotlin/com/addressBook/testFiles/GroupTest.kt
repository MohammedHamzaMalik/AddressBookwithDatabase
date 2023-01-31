package com.addressBook.testFiles

import com.addressBook.AppTest
import com.addressBook.entryPoints.addGroup
import com.addressBook.entryPoints.deleteGroup
import com.addressBook.entryPoints.editGroup
import com.addressBook.entryPoints.fetchGroup
import com.addressBook.getAddGroupRequest
import com.addressBook.getEditGroupRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class GroupTest: AppTest() {

    @Order(5)
    @Test
    fun `add group`() {
        val req = getAddGroupRequest()
        val groupResponse = addGroup(appCtx, req)

        Assertions.assertEquals("Vayana", groupResponse.groupName)
    }

    @Order(6)
    @Test
    fun `fetch group`() {
        val req = getAddGroupRequest()
        val groupResponse = addGroup(appCtx, req)
        val fetchGroupResponse = fetchGroup(appCtx, groupResponse.groupId)
        println(fetchGroupResponse)

        Assertions.assertEquals("Vayana", fetchGroupResponse.groupName)
    }

    @Order(7)
    @Test
    fun `edit group`() {
        val req = getAddGroupRequest()
        val groupResponse = addGroup(appCtx, req)
        val editGroupRequest = getEditGroupRequest()
        val editGroupResponse = editGroup(appCtx, groupResponse.groupId, editGroupRequest)
        println(editGroupResponse)

        Assertions.assertEquals("Vayana Interns", editGroupRequest.groupName)
    }

    @Order(8)
    @Test
    fun `delete group`() {
        val req = getAddGroupRequest()
        val groupResponse = addGroup(appCtx, req)
        val deleteGroupResponse = deleteGroup(appCtx, groupResponse.groupId)
        println(deleteGroupResponse)

        Assertions.assertFalse(deleteGroupResponse.toBoolean())
    }

}
