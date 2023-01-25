package com.commandPattern.addressBook.storages

import com.addressBook.tables.*
import com.commandPattern.addressBook.dataClasses.Contact
import com.addressBook.dataClasses.Group
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID

object Storage {
    private val contacts: MutableMap<UUID, Contact> = mutableMapOf()
    private val groups: MutableMap<UUID, Group> = mutableMapOf()

    fun addContactInTable(contact: Contact): Contact {
//        val group = contact.groups
        transaction {
            Contacts.insert {
                it[contactId] = contact.contactId
                it[firstName] = contact.firstName
                it[lastName] = contact.lastName
            }

            contact.emails.forEach { (type, eml) ->
                Emails.insert {
                    it[contactId] = contact.contactId
                    it[emailType] = type
                    it[email] = eml
                }
            }
            contact.phoneNumbers.forEach { (type, number) ->
                PhoneNumbers.insert {
                    it[contactId] = contact.contactId
                    it[phoneNumberType] = type
                    it[phoneNumber] = number
                }
            }
            contact.addresses.forEach { (type, addressValue) ->
                Addresses.insert {
                    it[contactId] = contact.contactId
                    it[addressType] = type
                    it[address] = addressValue
                }
            }
//            GroupsTable.insert {
//                it[groupId] = groupId
//                it[groupName] = groupName
//            }
        }
//        contacts[contact.contactId]=contact
//        contact.groups.forEach { groupName ->
//            val group = groups.values.find { it.groupName==groupName }
//            if(group!=null){
//                group.groupMembers.add(contact)
//                groups[group.groupId]=group
//            } else {
//                val newGroup= Group(UUID.randomUUID(),groupName, mutableListOf(contact))
//                groups[newGroup.groupId]=newGroup
//            }
//        }
        return contact
    }
    fun deleteContactInTable(contactId: UUID): String {
        transaction {
            PhoneNumbers.deleteWhere { PhoneNumbers.contactId eq contactId }
            Emails.deleteWhere { Emails.contactId eq contactId }
            Addresses.deleteWhere { Addresses.contactId eq contactId }
//            GroupMembers.deleteWhere { GroupMembers.contactId eq contactId }
            Contacts.deleteWhere { Contacts.contactId eq contactId }
        }
//        val contact = contacts[contactId]
//        contact?.groups?.forEach { groupName ->
//            val group = groups.values.find { it.groupName==groupName }
//            if(group!=null){
//                group.groupMembers.remove(contact)
//                groups[group.groupId]=group
//            }
//        }
//        contacts.remove(contactId)
        return "Contact with first name as ${contactId} is deleted."
    }
    fun editContactInTable(contactId: UUID, contact: Contact): String {
        transaction {
            Contacts.update({ Contacts.contactId eq contactId }) {
                it[firstName] = contact.firstName
                it[lastName] = contact.lastName
            }

            contact.emails.forEach { (type, eml) ->
                Emails.update({ Emails.contactId eq contactId }) {
                    it[emailType] = type
                    it[email] = eml
                }
            }

            contact.phoneNumbers.forEach { (type, number) ->
                PhoneNumbers.update({ PhoneNumbers.contactId eq contactId }) {
                    it[phoneNumberType] = type
                    it[phoneNumber] = number
                }
            }

            contact.addresses.forEach { (type, addressValue) ->
                Addresses.update({ Addresses.contactId eq contactId }) {
                    it[addressType] = type
                    it[address] = addressValue
                }
            }
        }
//        contacts[contactId] = contact
//        val existingGroups = contact.groups
//
//        // Add contact to new groups
//        existingGroups.forEach { groupName ->
//            val group = groups.values.find { it.groupName==groupName }
//            if (group != null) {
//                group.groupMembers.add(contact)
//            } else {
//                val newGroup = Group(UUID.randomUUID(), groupName, mutableListOf(contact))
//                groups[newGroup.groupId] = newGroup
//            }
//        }
//
//        // Remove contact from old groups
//        val oldGroups = contacts[contactId]?.groups
//        oldGroups?.forEach { groupName ->
//            val group = groups.values.find { it.groupName==groupName }
//            if (group != null && !existingGroups.contains(groupName)) {
//                group.groupMembers.remove(contact)
//            }
//        }
        return "Contact with first name as ${contact.firstName} is edited."
    }
    fun searchContacts(query: String): List<Contact> {
        val searchedContacts: MutableList<Contact> = mutableListOf()
        for(contact in contacts){
            if (
                contact.value.firstName.contains(query,ignoreCase = true) ||
                contact.value.lastName.contains(query,ignoreCase = true) ||
                ("${contact.value.firstName.contains(query,ignoreCase = true)}" + " " +
                        "${contact.value.lastName.contains(query,ignoreCase = true)}").toBoolean() ||
                contact.value.phoneNumbers.values.contains(query) ||
                contact.value.addresses.values.contains(query) ||
                contact.value.emails.values.contains(query) ||
                contact.value.groups.contains(query)
            ) searchedContacts.add(contact.value)
        }
        return searchedContacts.toList()
    }

    fun showContacts(): Collection<Contact>{
        return contacts.values
    }

    fun addGroupInTable(group: Group): Group {
        transaction {
            Groups.insert {
                it[groupId] = group.groupId
                it[groupName] = group.groupName
            }
//            group.groupMembers.forEach { member ->
//                GroupMembers.insert {
//                    it[groupMemberId] = groupMemberId
//                    it[groupId] = group.groupId
//                    it[contactId] = contactId
//                }
//            }
        }
//        groups[group.groupId]=group
//        group.groupMembers.forEach{
//            val contact = contacts[it.contactId]
//            if(contact!=null){
//                contact.groups.add(group.groupName)
//                contacts[it.contactId]=contact
//            }
//        }
        return group
    }


    fun deleteGroupInTable(groupId: UUID): String {
        transaction {
            GroupMembers.deleteWhere { GroupMembers.groupId eq groupId }
            Groups.deleteWhere { Groups.groupId eq groupId }
        }
//        val group = groups[groupId]
//        group?.groupMembers?.forEach {
//            contacts[it.contactId]?.groups?.remove(group.groupName)
//        }
//        groups.remove(groupId)
        return "Group named as ${groupId} is deleted"
    }
    fun showGroups(): Collection<Group> {
        return groups.values
    }

    fun editGroupInTable(groupId: UUID, group: Group): String {
        transaction {
            Groups.update ({ Groups.groupId eq groupId }) {
                it[groupName] = group.groupName
            }
        }
        return "Group named as ${group.groupName} is edited."
//        val previousGroup = groups[groupId]
//        groups[groupId] = group
//
//        var returnStatement = ""
//
//        previousGroup?.groupMembers?.forEach {previousMember ->
//            if (!group.groupMembers.contains(previousMember)) {
//                returnStatement+="${previousMember.firstName+" "+previousMember.lastName} is removed from ${group.groupName}\n"
//                previousMember.groups.remove(previousGroup.groupName)
//            }
//        }
//        group.groupMembers.forEach { newMember ->
//            if (!previousGroup?.groupMembers?.contains(newMember)!!) {
//                returnStatement+="${newMember.firstName+" "+newMember.lastName} is added to ${group.groupName}\n"
//                newMember.groups.add(group.groupName)
//            }
//        }
//        if(previousGroup?.groupName!=group.groupName){
//            group.groupMembers.forEach { member ->
//                member.groups.remove(previousGroup?.groupName)
//                member.groups.add(group.groupName)
//            }
//            returnStatement+="${previousGroup?.groupName} is changed to ${group.groupName}\n"
//        }
//        return returnStatement
    }
    fun searchGroups(query: String): List<Group> {
        val searchedGroup: MutableList<Group> = mutableListOf()
        for(group in groups.values){
            if(group.groupName.contains(query,ignoreCase = true)) searchedGroup.add(group)
        }
        return searchedGroup
    }

    fun addGroupMemberInTable(groupId: UUID, contactId: UUID): String {
        transaction {
            GroupMembers.insert {
                it[this.groupId] = groupId
                it[this.contactId] = contactId
            }
        }
        return "Member with contact id $contactId is added to group with group id $groupId."
    }

    fun deleteGroupMemberInTable(groupId: UUID, contactId: UUID): String {
//        transaction {
//            GroupMembers.deleteWhere { (GroupMembers.groupId eq groupId) and (GroupMembers.contactId eq contactId) }
//        }
        return "Member with contact id $contactId is deleted from group with group id $groupId."
    }
}