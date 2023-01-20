package com.commandPattern.addressBook.storages

import com.commandPattern.addressBook.dataClasses.Contact
import com.commandPattern.addressBook.dataClasses.Group
import java.util.UUID

object Storage {
    private val contacts: MutableMap<UUID, Contact> = mutableMapOf()
    private val groups: MutableMap<UUID, Group> = mutableMapOf()
    fun addContact(contact: Contact): Contact {
        contacts[contact.contactId]=contact
        contact.groups.forEach { groupName ->
            val group = groups.values.find { it.groupName==groupName }
            if(group!=null){
                group.groupMembers.add(contact)
                groups[group.groupId]=group
            } else {
                val newGroup= Group(UUID.randomUUID(),groupName, mutableListOf(contact))
                groups[newGroup.groupId]=newGroup
            }
        }
        return contact
    }
    fun deleteContact(contactId: UUID): String {
        val contact = contacts[contactId]
        contact?.groups?.forEach { groupName ->
            val group = groups.values.find { it.groupName==groupName }
            if(group!=null){
                group.groupMembers.remove(contact)
                groups[group.groupId]=group
            }
        }
        contacts.remove(contactId)
        return "Contact with first name as ${contact?.firstName} is deleted."
    }
    fun editContact(contactId: UUID, contact: Contact): String {
        contacts[contactId] = contact
        val existingGroups = contact.groups

        // Add contact to new groups
        existingGroups.forEach { groupName ->
            val group = groups.values.find { it.groupName==groupName }
            if (group != null) {
                group.groupMembers.add(contact)
            } else {
                val newGroup = Group(UUID.randomUUID(), groupName, mutableListOf(contact))
                groups[newGroup.groupId] = newGroup
            }
        }

        // Remove contact from old groups
        val oldGroups = contacts[contactId]?.groups
        oldGroups?.forEach { groupName ->
            val group = groups.values.find { it.groupName==groupName }
            if (group != null && !existingGroups.contains(groupName)) {
                group.groupMembers.remove(contact)
            }
        }
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

    fun addGroup(group: Group): Group {
        groups[group.groupId]=group
        group.groupMembers.forEach{
            val contact = contacts[it.contactId]
            if(contact!=null){
                contact.groups.add(group.groupName)
                contacts[it.contactId]=contact
            }
        }
        return group
    }
    fun deleteGroup(groupId: UUID): String {
        val group = groups[groupId]
        group?.groupMembers?.forEach {
            contacts[it.contactId]?.groups?.remove(group.groupName)
        }
        groups.remove(groupId)
        return "Group named as ${group?.groupName} is deleted"
    }
    fun showGroups(): Collection<Group> {
        return groups.values
    }
    fun editGroup(groupId: UUID, group: Group): String {
        val previousGroup = groups[groupId]
        groups[groupId] = group

        var returnStatement = ""

        previousGroup?.groupMembers?.forEach {previousMember ->
            if (!group.groupMembers.contains(previousMember)) {
                returnStatement+="${previousMember.firstName+" "+previousMember.lastName} is removed from ${group.groupName}\n"
                previousMember.groups.remove(previousGroup.groupName)
            }
        }
        group.groupMembers.forEach { newMember ->
            if (!previousGroup?.groupMembers?.contains(newMember)!!) {
                returnStatement+="${newMember.firstName+" "+newMember.lastName} is added to ${group.groupName}\n"
                newMember.groups.add(group.groupName)
            }
        }
        if(previousGroup?.groupName!=group.groupName){
            group.groupMembers.forEach { member ->
                member.groups.remove(previousGroup?.groupName)
                member.groups.add(group.groupName)
            }
            returnStatement+="${previousGroup?.groupName} is changed to ${group.groupName}\n"
        }
        return returnStatement
    }
    fun searchGroups(query: String): List<Group> {
        val searchedGroup: MutableList<Group> = mutableListOf()
        for(group in groups.values){
            if(group.groupName.contains(query,ignoreCase = true)) searchedGroup.add(group)
        }
        return searchedGroup
    }
}