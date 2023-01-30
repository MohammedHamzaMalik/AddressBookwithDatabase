package com.addressBook

import com.addressBook.dataClasses.Group
import com.addressBook.entryPoints.addGroup
import com.addressBook.entryPoints.deleteGroup
import com.addressBook.entryPoints.editGroup
import com.addressBook.entryPoints.fetchGroup
import com.addressBook.tables.Groups
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GroupTest: AppTest() {

    @Test
    fun `add group`() {
        val req = getAddGroupRequest()
        val groupResponse = addGroup(appCtx, req)

        Assertions.assertEquals("Vayana", groupResponse.groupName)
    }

    @Test
    fun `fetch group`() {
        val req = getAddGroupRequest()
        val groupResponse = addGroup(appCtx, req)
        val fetchGroupResponse = fetchGroup(appCtx, groupResponse.groupId)
        println(fetchGroupResponse)

        Assertions.assertEquals("Vayana", fetchGroupResponse.groupName)
    }

    @Test
    fun `edit group`() {
        val req = getAddGroupRequest()
        val groupResponse = addGroup(appCtx, req)
        val editGroupRequest = getEditGroupRequest()
        val editGroupResponse = editGroup(appCtx, groupResponse.groupId, editGroupRequest) as Group
        println(editGroupResponse)

        Assertions.assertEquals("Vayana", editGroupResponse.groupName)
    }

    @Test
    fun `delete group`() {
        val req = getAddGroupRequest()
        val groupResponse = addGroup(appCtx, req)
        val deleteGroupResponse = deleteGroup(appCtx, groupResponse.groupId)
        println(deleteGroupResponse)

        Assertions.assertFalse(deleteGroupResponse.toBoolean())
    }
}
