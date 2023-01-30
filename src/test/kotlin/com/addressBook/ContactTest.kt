package com.addressBook

import com.addressBook.entryPoints.addContact
import com.addressBook.entryPoints.deleteContact
import com.addressBook.entryPoints.editContact
import com.addressBook.entryPoints.fetchContact
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ContactTest: AppTest() {

    @Test
    fun `add contact`() {
        val req = getAddContactRequest()
        val contactResponse = addContact(appCtx, req)
        println(contactResponse)

        Assertions.assertEquals("Hamza", contactResponse.firstName)
        Assertions.assertEquals("Malik", contactResponse.lastName)
        Assertions.assertTrue(contactResponse.emails.containsValue("work@gmail.com"))
        Assertions.assertTrue(contactResponse.phoneNumbers.containsValue("+91 123"))
        Assertions.assertTrue(contactResponse.addresses.containsValue("ST"))
    }

    @Test
    fun `fetch contact`() {
        val req = getAddContactRequest()
        val contactResponse = addContact(appCtx, req)
        val fetchContactResponse = fetchContact(appCtx, contactResponse.contactId)
        println(fetchContactResponse)

        Assertions.assertEquals("Hamza", fetchContactResponse.firstName)
    }

    @Test
    fun `edit contact`() {

        val req = getAddContactRequest()
        val contactResponse = addContact(appCtx, req)
        val editedContactRequest = getEditContactRequest()
        val editContactResponse = editContact(appCtx, contactResponse.contactId, editedContactRequest)
        println(editContactResponse)

        Assertions.assertEquals("Mohammed Hamza", editedContactRequest.firstName)
    }

    @Test
    fun `delete contact`() {
        val req = getAddContactRequest()
        val contactResponse = addContact(appCtx, req)
        val deleteContactResponse = deleteContact(appCtx, contactResponse.contactId)
        println(deleteContactResponse)

        Assertions.assertFalse(deleteContactResponse.toBoolean())
    }
}