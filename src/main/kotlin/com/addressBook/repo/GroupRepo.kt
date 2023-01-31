package com.addressBook.repo

import arrow.core.Either
import com.addressBook.dataClasses.Group
import com.addressBook.tables.AllGroupMembers
import com.addressBook.tables.Groups
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.*

object GroupRepo {
    //    Main function with logic for GROUP
    fun addGroupInTable(group: Group): Either<Exception, Group> {
        return try {
            transaction {
                Groups.insert {
                    it[groupId] = group.groupId
                    it[groupName] = group.groupName
                }
            }
            Either.Right(group)
        } catch (e: Exception) {
            Either.Left(Exception("Error while adding group in table"))
        }
    }
    fun deleteGroupInTable(groupId: UUID): Either<Exception, String> {
        return try {
            transaction {
                AllGroupMembers.deleteWhere { AllGroupMembers.groupId eq groupId }
                Groups.deleteWhere { Groups.groupId eq groupId }
            }
            Either.Right("Group named as ${groupId} is deleted")
        } catch (e: Exception) {
            Either.Left(Exception("Error while deleting group in table"))
        }
    }
    fun editGroupInTable(groupId: UUID, group: Group): Either<Exception, String> {
        return try {
            transaction {
                Groups.update({ Groups.groupId eq groupId }) {
                    it[groupName] = group.groupName
                }
            }
            Either.Right("Group named as ${group.groupName} is edited.")
        } catch (e: Exception) {
            Either.Left(Exception("Error while editing group in table"))
        }
    }
    fun fetchGroupInTable(groupId: UUID): Either<Exception, Group> {
        return try {
            lateinit var group: Group
            transaction {
                val groupRow = Groups.select { Groups.groupId eq groupId }.firstOrNull()
                if (groupRow != null) {
                    val groupName = groupRow[Groups.groupName]
                    group = Group(groupId, groupName)
                }
            }
            Either.Right(group)
        } catch (e: Exception) {
            Either.Left(Exception("Error while fetching group in table"))
        }
    }
}