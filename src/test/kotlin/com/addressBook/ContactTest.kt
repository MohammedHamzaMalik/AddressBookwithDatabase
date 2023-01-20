package com.addressBook

import com.addressBook.entryPoints.addContact
//import com.addressBook.TestUtils.getDeleteContactCommand
import com.commandPattern.addressBook.commands.AddressBook
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ContactTest: AppTest() {
    @Test
    fun `add contact`() {
        val req = getAddContactRequest()
        val contactResponse = addContact(appCtx, req)
//        val contactResponse = obj.executeCommand(AddContactCommand(getAddContactRequest())) as Contact
//        val addContactRequest = getAddContactRequest()
//        val addContactCommand = AddContactCommand(addContactRequest)
//        val contactResponse = addContactCommand.execute()

        Assertions.assertEquals("Hamza", contactResponse.firstName)
        Assertions.assertEquals("Malik", contactResponse.lastName)
        Assertions.assertTrue(contactResponse.emails.containsValue("work@gmail.com"))
        Assertions.assertTrue(contactResponse.phoneNumbers.containsValue("+91 123"))
        Assertions.assertTrue(contactResponse.addresses.containsValue("ST"))
        Assertions.assertTrue(contactResponse.groups.contains("Vayana"))
    }

//    @Test
//    fun `delete contact`() {
//        val obj = AddressBook()
//        val contactResponse = obj.executeCommand(AddContactCommand(getAddContactRequest())) as Contact
//        val deletedContactResponse = obj.executeCommand(DeleteContactCommand(getDeleteContactCommand(contactResponse.contactId))) as Contact
//        Assertions.assertEquals("Hamza", deletedContactResponse.firstName)
//    }
//    @Test
//    fun `edit contact`() {
//        val obj = AddressBook()
//        val contactResponse = obj.executeCommand(AddContactCommand(getAddContactRequest())) as Contact
//
//        val editedContactResponse = obj.executeCommand(EditContactCommand(contactResponse.contactId,
//            getEditContactRequest())) as Contact
//        Assertions.assertEquals("Hamza", editedContactResponse.firstName)
//        val editContactRequest = EditContactRequest(
//            contactResponse.contactId,
//            "Zayn",
//            "Malik",
//            contactResponse.emails,
//            contactResponse.phoneNumbers,
//            contactResponse.addresses,
//            contactResponse.groups
//        )
//        val editContactCommand = EditContactCommand(contactResponse.contactId, editContactRequest)
//        val editedContactResponse = editContactCommand.execute() as Contact
//        Assertions.assertEquals("John", editedContactResponse.firstName)
//    }
}