package com.addressBook.testFiles

import com.addressBook.AppTest
import com.addressBook.entryPoints.addGroupMember
import com.addressBook.getAddGroupMemberRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GroupMemberTest: AppTest() {

    @Test
    fun `add group member`() {
        val req = getAddGroupMemberRequest()
        val groupMemberResponse = addGroupMember(appCtx, req)
        println(groupMemberResponse)
    }
}