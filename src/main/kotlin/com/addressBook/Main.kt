package com.commandPattern.addressBook

import com.addressBook.AppContext
import com.addressBook.connectToDatabase
import com.addressBook.entryPoints.*
import com.addressBook.requests.AddGroupMemberRequest
import com.addressBook.resetDatabase
import com.commandPattern.addressBook.requests.AddContactRequest
import com.addressBook.requests.AddGroupRequest
import com.addressBook.requests.EditGroupRequest
import com.commandPattern.addressBook.requests.EditContactRequest


fun main(args: Array<String>) {
    connectToDatabase()
    resetDatabase()


    println("------------------------Contacts Added---------------------------")
    val hamza = addContact(
        AppContext(connectToDatabase()),
        AddContactRequest(
            "Hamza", "Malik",
                mutableMapOf("work" to "work@gmail.com","home" to "home@gmail.com"),
                mutableMapOf("work" to "+91 123","home" to "+91 234"),
                mutableMapOf("HOME" to "ST","WORK" to "BRC"),
                mutableListOf("Vayana","PDPU")
            )
    )

    val zayn = addContact(
        AppContext(connectToDatabase()),
        AddContactRequest("Hamza","Khan",
            mutableMapOf("work" to "work@gmail.com","home" to "home@gmail.com"),
            mutableMapOf("work" to "+91 123","home" to "+91 234"),
            mutableMapOf("HOME" to "ST","WORK" to "BRC"),
            mutableListOf("Vayana")
        )
    )

    val shivam = addContact(
        AppContext(connectToDatabase()),
        AddContactRequest("Shivam","Chavda",
            mutableMapOf("work" to "work@gmail.com","home" to "home@gmail.com"),
            mutableMapOf("work" to "+91 123","home" to "+91 234"),
            mutableMapOf("HOME" to "ST","WORK" to "BRC"),
            mutableListOf("Vayana","Navrachna")
        )
    )

    val parth = addContact(
        AppContext(connectToDatabase()),
        AddContactRequest("Parth","Raval",
            mutableMapOf("work" to "parthwork@gmail.com","home" to "parthhome@gmail.com"),
            mutableMapOf("work" to "+91 789","home" to "+91 765"),
            mutableMapOf("HOME" to "BV","WORK" to "BRC"),
            mutableListOf("Vayana","PDPU")
        )
    )


    println("------------------------Contact Deleted--------------------------")
    val deleteContact = deleteContact(
        AppContext(connectToDatabase()),
        hamza.contactId
    )


    println("------------------------Contact Edited----------------------------")
    val editedContact = editContact(
        AppContext(connectToDatabase()),
        zayn.contactId,
        EditContactRequest(
            zayn.contactId,
                        "Zayn",
                        "Malik",
                        zayn.emails,
                        zayn.phoneNumbers,
                        zayn.addresses,
                        mutableListOf("One Direction","PDPU")
        )
    )


//    println("---------------------------Searched Contacts------------------------")


    println("----------------------------Groups----------------------------")
    val vayana = addGroup(
        AppContext(connectToDatabase()),
        AddGroupRequest(
            "Vayana"
        )
    )

    val peoples = addGroup(
        AppContext(connectToDatabase()),
        AddGroupRequest(
            "Peoples"
        )
    )

    val pdpu = addGroup(
        AppContext(connectToDatabase()),
        AddGroupRequest(
            "PDPU"
        )
    )


    println("------------------------Group Deleted--------------------------")
    val deleteGroup = deleteGroup(
        AppContext(connectToDatabase()),
        vayana.groupId
    )


    println("------------------------Group Edited----------------------------")
    val editedGroup = editGroup(
        AppContext(connectToDatabase()),
        peoples.groupId,
        EditGroupRequest(
            peoples.groupId,
            "Great Peoples"
        )
    )

    println("------------------------Contact Added to Group----------------------------")
    val parthToPdpu = addGroupMember(
        AppContext(connectToDatabase()),
        AddGroupMemberRequest(
            pdpu.groupId,
            parth.contactId
        )
    )
    val shivamToPdpu = addGroupMember(
        AppContext(connectToDatabase()),
        AddGroupMemberRequest(
            pdpu.groupId,
            shivam.contactId
        )
    )
//    println("---------------------------Searched Groups------------------------")

}
