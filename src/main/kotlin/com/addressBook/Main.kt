package com.commandPattern.addressBook


import AppContext
import com.addressBook.connectToDatabase
import com.addressBook.entryPoints.*
import com.addressBook.requests.AddGroupMemberRequest
import com.commandPattern.addressBook.requests.AddContactRequest
import com.addressBook.requests.AddGroupRequest
import com.addressBook.requests.DeleteGroupMemberRequest
import com.addressBook.requests.EditGroupRequest
import com.addressBook.resetDatabase
import com.commandPattern.addressBook.requests.EditContactRequest


fun main(args: Array<String>) {
    val dbConnection = connectToDatabase()
    resetDatabase()


    println("------------------------Contacts Added---------------------------")
    val hamza = addContact(
        AppContext(dbConnection),
        AddContactRequest(
            "Hamza", "Malik",
                mutableMapOf("work" to "work@gmail.com","home" to "home@gmail.com"),
                mutableMapOf("work" to "+91 123","home" to "+91 234"),
                mutableMapOf("HOME" to "ST","WORK" to "BRC")
            )
    )

    val zayn = addContact(
        AppContext(dbConnection),
        AddContactRequest("Hamza","Khan",
            mutableMapOf("work" to "work@gmail.com","home" to "home@gmail.com"),
            mutableMapOf("work" to "+91 123","home" to "+91 234"),
            mutableMapOf("HOME" to "ST","WORK" to "BRC")
        )
    )

    val shivam = addContact(
        AppContext(dbConnection),
        AddContactRequest("Shivam","Chavda",
            mutableMapOf("work" to "work@gmail.com","home" to "home@gmail.com"),
            mutableMapOf("work" to "+91 123","home" to "+91 234"),
            mutableMapOf("HOME" to "ST","WORK" to "BRC")
        )
    )

    val parth = addContact(
        AppContext(dbConnection),
        AddContactRequest("Parth","Raval",
            mutableMapOf("work" to "parthwork@gmail.com","home" to "parthhome@gmail.com"),
            mutableMapOf("work" to "+91 789","home" to "+91 765"),
            mutableMapOf("HOME" to "BV","WORK" to "BRC")
        )
    )
    println(hamza)
    println(zayn)
    println(shivam)
    println(parth)
    println()


    println("------------------------Contact Deleted--------------------------")
    val deleteContact = deleteContact(
        AppContext(dbConnection),
        hamza.contactId
    )
    println(deleteContact)
    println()


    println("------------------------Contact Edited----------------------------")
    val editedContact = editContact(
        AppContext(dbConnection),
        zayn.contactId,
        EditContactRequest(
            zayn.contactId,
                        "Zayn",
                        "Malik",
                        zayn.emails,
                        zayn.phoneNumbers,
                        zayn.addresses
        )
    )
    println(editedContact)
    println()


    println("---------------------------Fetching Contact------------------------")
    val fetchedContact = fetchContact(
        AppContext(dbConnection),
        zayn.contactId
    )
//    println(fetchedContact)


    println("----------------------------Groups Added----------------------------")
    val vayana = addGroup(
        AppContext(dbConnection),
        AddGroupRequest(
            "Vayana"
        )
    )

    val peoples = addGroup(
        AppContext(dbConnection),
        AddGroupRequest(
            "Peoples"
        )
    )

    val pdpu = addGroup(
        AppContext(dbConnection),
        AddGroupRequest(
            "PDPU"
        )
    )
    println(vayana)
    println(peoples)
    println(pdpu)
    println()


    println("------------------------Group Deleted--------------------------")
    val deleteGroup = deleteGroup(
        AppContext(dbConnection),
        vayana.groupId
    )
    println(deleteContact)
    println()


    println("------------------------Group Edited----------------------------")
    val editedGroup = editGroup(
        AppContext(dbConnection),
        peoples.groupId,
        EditGroupRequest(
            peoples.groupId,
            "Great Peoples"
        )
    )
    println(editedGroup)
    println()


    println("------------------------Contact Added to Group----------------------------")
    val parthToPdpu = addGroupMember(
        AppContext(dbConnection),
        AddGroupMemberRequest(
            pdpu.groupId,
            parth.contactId
        )
    )
    val shivamToPdpu = addGroupMember(
        AppContext(dbConnection),
        AddGroupMemberRequest(
            pdpu.groupId,
            shivam.contactId
        )
    )
    println(parthToPdpu)
    println(shivamToPdpu)


    println("------------------------Contact Deleted from Group----------------------------")
    val removeShivamFromPdpu = deleteGroupMember(
        AppContext(dbConnection),
        DeleteGroupMemberRequest(
            pdpu.groupId,
            shivam.contactId
        )
    )
    println(removeShivamFromPdpu)
//    println("---------------------------Searched Groups------------------------")

}
