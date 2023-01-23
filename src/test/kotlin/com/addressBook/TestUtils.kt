package com.addressBook

import com.commandPattern.addressBook.dataClasses.Contact
import com.commandPattern.addressBook.dataClasses.Group
import com.commandPattern.addressBook.requests.AddContactRequest
import com.commandPattern.addressBook.requests.EditContactRequest
import java.util.*

//object TestUtils {
//    private fun getContact(): Contact {
//        return Contact(
//            UUID.randomUUID(),
//            "Hamza","Malik",
//            mutableMapOf("work" to "work@gmail.com","home" to "home@gmail.com"),
//            mutableMapOf("work" to "+91 123","home" to "+91 234"),
//            mutableMapOf("HOME" to "ST","WORK" to "BRC"),
//            mutableListOf("Vayana","PDPU")
//        )
//    }
//    fun getGroup(): Group {
//        return Group(
//            UUID.randomUUID(),
//            "Friends",
//            mutableListOf(getContact())
//        )
//    }
    fun getAddContactRequest(): AddContactRequest {
        return AddContactRequest(
            "Hamza","Malik",
            mutableMapOf("work" to "work@gmail.com","home" to "home@gmail.com"),
            mutableMapOf("work" to "+91 123","home" to "+91 234"),
            mutableMapOf("HOME" to "ST","WORK" to "BRC"),
            mutableListOf("Vayana","PDPU")
        )
    }
//    fun getDeleteContactCommand(contactId: UUID): DeleteContactCommand {
//        return DeleteContactCommand(getContact().contactId)
//    }

    fun getEditContactRequest(): EditContactRequest {
        return EditContactRequest(
            UUID.randomUUID(),
            "Mohammed","Malik",
            mutableMapOf("work" to "work@gmail.com","home" to "home@gmail.com"),
            mutableMapOf("work" to "+91 123","home" to "+91 234"),
            mutableMapOf("HOME" to "ST","WORK" to "BRC"),
            mutableListOf("Vayana","PDPU")
        )
    }
//    fun getSearchedContact(): String {
//        return "Contact Searched"
//    }

//    fun getAddGroupRequest(): AddGroupRequest {
//        return AddGroupRequest(
//            "Friends",
//            mutableListOf(getContact())
//        )
//    }
//}