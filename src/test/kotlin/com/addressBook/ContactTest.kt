package com.addressBook

import com.addressBook.entryPoints.addContactENT
//import com.addressBook.TestUtils.getDeleteContactCommand
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ContactTest: AppTest() {

//    var random: Int

    @Test
    fun `add contact`() {
        val req = getAddContactRequest()
        val contactResponse = addContactENT(appCtx, req)
//        val contactResponse = obj.executeCommand(AddContactCommand(getAddContactRequest())) as Contact
//        val addContactRequest = getAddContactRequest()
//        val addContactCommand = AddContactCommand(addContactRequest)
//        val contactResponse = addContactCommand.execute()

        Assertions.assertEquals("Hamza", contactResponse.firstName)
        Assertions.assertEquals("Malik", contactResponse.lastName)
//        Assertions.assertTrue(contactResponse.emails.containsValue("work@gmail.com"))
//        Assertions.assertTrue(contactResponse.phoneNumbers.containsValue("+91 123"))
//        Assertions.assertTrue(contactResponse.addresses.containsValue("ST"))
//        Assertions.assertTrue(contactResponse.groups.contains("Vayana"))
    }

//    @Test
//    fun `edit contact`() {
//
//        val req = getAddContactRequest()
//        val contactResponse = addContact(appCtx, req)
//        val editedReq = getEditContactRequest()
//
//        Assertions.assertEquals("Mohammed", editedReq.firstName)
//    }
}