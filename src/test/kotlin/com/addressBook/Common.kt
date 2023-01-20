package com.addressBook

import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

internal fun connectToDatabase(): Database =
    Database.connect(
        HikariDataSource().apply {
            jdbcUrl = "jdbc:mysql://localhost:3306/addressbook_db"
            username = "hamza"
            password = "password"
            isAutoCommit = false
            maximumPoolSize = 5
        }
    )

val schema = listOf<Table>()

internal fun resetDatabase() {
//    transaction {
//        SchemaUtils.drop(*schema.toTypedArray())
//        SchemaUtils.create(*schema.toTypedArray())
//    }
}